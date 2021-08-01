package com.sg;

public class Main {

    public static void main(String[] args) {
        ArgsParse.Args parsed = ArgsParse.parse(args); // Parse command line args
        System.out.println("hit ctrl+c to stop the program");
        Thread reader = new ReaderThread(parsed);
        reader.start();
        try {
            reader.join();
        } catch (InterruptedException ignore) {}
    }
}
