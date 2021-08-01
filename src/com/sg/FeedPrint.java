package com.sg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FeedPrint {
    public static void printFeeds(Document document, long delay) throws InterruptedException {
        System.out.println("Feeds:");
        NodeList itemNodes = document.getElementsByTagName("item");
        for (int i = 0; i < itemNodes.getLength(); i++) {
            Node itemNode = itemNodes.item(i);
            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element newsElement = (Element) itemNode;
                System.out.println();
                System.out.println(
                        "title: " + getTextContent(newsElement, "title")
                );
                System.out.println(
                        "description: " + getTextContent(newsElement, "description")
                );
                System.out.println(
                        "link: " + getTextContent(newsElement, "link")
                );
                System.out.println();
            }
            Thread.sleep(delay);
        }
    }

    public static void printChannelInfo(Document document) {
        Node channelNode = document.getElementsByTagName("channel").item(0);
        if (channelNode.getNodeType() == Node.ELEMENT_NODE) {
            Element channelElement = (Element) channelNode;
            System.out.println();
            System.out.println(
                    "channel title: " + getTextContent(channelElement, "title")
            );
            System.out.println(
                    "link: " + getTextContent(channelElement, "link")
            );
            System.out.println(
                    "description: " + getTextContent(channelElement, "description")
            );
            System.out.println(
                    "language: " + getTextContent(channelElement, "language")
            );
            System.out.println();
        }
    }

    private static String getTextContent(Element e, String tagName) {
        return e.getElementsByTagName(tagName).item(0).getTextContent();
    }
}