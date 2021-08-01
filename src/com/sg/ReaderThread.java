package com.sg;

import org.w3c.dom.Document;

public class ReaderThread extends Thread {

    private ArgsParse.Args args;

    ReaderThread(ArgsParse.Args args) {
        this.args = args;
    }

    @Override
    public void run() {
        Document document = FeedFetch.getDocument(args.url);
        if (document != null) {
            FeedPrint.printChannelInfo(document);
            try {
                FeedPrint.printFeeds(document, args.delay);
            } catch (InterruptedException e) {
                System.err.println("Don't interrupt me!");
            }
        }
    }
}
