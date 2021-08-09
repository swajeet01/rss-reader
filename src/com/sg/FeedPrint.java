package com.sg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class FeedPrint {
    static void printFeeds(Document document, long delay) throws InterruptedException {
        System.out.println("Feeds:");
        NodeList itemNodes = document.getElementsByTagName("item");
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Node itemNode = itemNodes.item(i);
            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element newsElement = (Element) itemNode;
                System.out.println();
                printFormatted("title", getTextContent(newsElement, "title"));
                printFormatted("description", getTextContent(newsElement, "description"));
                printFormatted("link", getTextContent(newsElement, "link"));
                System.out.println();
            }
            Thread.sleep(delay);
        }
    }

    static void printChannelInfo(Document document) {
        System.out.println("Channel Info:");
        Node channelNode = document.getElementsByTagName("channel").item(0);
        if (channelNode.getNodeType() == Node.ELEMENT_NODE) {
            Element channelElement = (Element) channelNode;
            System.out.println();
            printFormatted("channel title", getTextContent(channelElement, "title"));
            printFormatted("link", getTextContent(channelElement, "link"));
            printFormatted("description", getTextContent(channelElement, "description"));
            printFormatted("language", getTextContent(channelElement, "language"));
            System.out.println();
        }
    }

    private static String getTextContent(Element e, String tagName) {
        return e.getElementsByTagName(tagName).item(0).getTextContent().trim();
    }

    private static void printFormatted(String tag, String content) {
        System.out.printf("%-15s: %s%n", tag, content);
    }

}