package com.sg;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class FeedFetch {
    static Document getDocument(String feedUrl) {
        try {
            URL url = new URL(feedUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(urlConnection.getInputStream());
            document.getDocumentElement().normalize();
            if (!document.getDocumentElement().getNodeName().equals("rss")) return null;
            return document;
        } catch (MalformedURLException e) {
            System.err.printf("Malformed url %s%n", feedUrl);
        } catch (ParserConfigurationException e) {
            System.err.println("Unable to parse document");
        } catch (SAXException | IOException e) {
            System.err.println("Some weired exception");
        }
        return null;
    }
}
