package com.sg;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class ArgsParse {

    private static final long DEFAULT_DELAY = 2500;
    private static final long MIN_DELAY = 500;
    private static final String DEFAULT_URL = "http://feeds.bbci.co.uk/news/rss.xml";

    protected static class Args {

        final long delay;
        final String url;

        Args(long delay, String url) {
            this.delay = delay;
            this.url = url;
        }

        public Args() {
            this(DEFAULT_DELAY, DEFAULT_URL);
        }

        @Override
        public String toString() {
            return String.format("{ delay = %d, url = %s }", delay, url);
        }
    }

    static Args parse(String[] args) {
        Scanner scanner = new Scanner(String.join(" " /* space */ , args));
        long delay = DEFAULT_DELAY;
        String url = DEFAULT_URL;
        while(scanner.hasNext()) {
            String token = scanner.next();
            if(token.startsWith("-")) {
                switch (token) {
                    case "-d":
                        try {
                            long newDelay = scanner.nextLong();
                            if(newDelay > MIN_DELAY) {
                                delay = newDelay;
                            } else System.err.println("Delay too short");
                        } catch (InputMismatchException e) {
                            System.err.println("Bad value for '-d'");
                        } catch (NoSuchElementException e) {
                            System.err.println("No value specified for '-d'");
                        }
                        break;
                    case "-u":
                        try {
                            url = scanner.next();
                        } catch (NoSuchElementException e) {
                            System.err.println("No value specified for '-u'");
                        }
                        break;
                    default:
                        System.err.printf("Unrecognized switch %s\n", token);
                        break;
                }
            } else {
                System.err.printf("Unrecognized option %s\n", token);
            }
        }
        return new Args(delay, url);
    }
}
