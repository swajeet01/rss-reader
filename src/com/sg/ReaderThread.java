package com.sg;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ReaderThread extends Thread {

    private ArgsParse.Args args;

    ReaderThread(ArgsParse.Args args) {
        this.args = args;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(args.url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(urlConnection.getInputStream());
            NodeList nodeList = document.getElementsByTagName("item");
            Node next = nodeList.item(0);
            while (next != null) {
                System.out.println(String.join(System.lineSeparator(),
                        next.getTextContent().split("\\s{2,}")));
                Thread.sleep(args.delay);
                next = next.getNextSibling();
            }
        } catch (MalformedURLException e) {
            System.err.printf("Malformed URL %s\n", args.url);
        } catch (IOException e) {
            System.err.printf("Unable to connect to %s\n", args.url);
        } catch (ParserConfigurationException e) {
            System.err.println("Err 1");
        } catch (SAXException e) {
            System.err.println("Err 2");
        } catch (InterruptedException e) {
            System.err.println("ReaderThread interrupted");
        }
    }
}
