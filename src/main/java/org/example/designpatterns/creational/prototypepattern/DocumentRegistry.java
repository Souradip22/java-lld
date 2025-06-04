package org.example.designpatterns.creational.prototypepattern;

import java.util.HashMap;
import java.util.Map;

public class DocumentRegistry {

    private static Map<String, Document> prototypes = new HashMap<>();
    public static void loadPrototypes() {
        Document invoice = new Document("Invoice");
        prototypes.put("Invoice", invoice);
    }

    public static Document getClonedDocument(String type) {
        return prototypes.get(type).clone();
    }
}
