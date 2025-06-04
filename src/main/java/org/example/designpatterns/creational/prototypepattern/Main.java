package org.example.designpatterns.creational.prototypepattern;

public class Main {
    public static void main(String[] args) {
        DocumentRegistry.loadPrototypes(); // only loading once

        long start = System.currentTimeMillis();
        Document doc1 = DocumentRegistry.getClonedDocument("Invoice");
        Document doc2 = DocumentRegistry.getClonedDocument("Invoice");
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }
}
