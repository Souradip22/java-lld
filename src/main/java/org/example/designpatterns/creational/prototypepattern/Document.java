package org.example.designpatterns.creational.prototypepattern;

public class Document implements Cloneable {
    String type;
    String content;
    String metadata;

    private Document(){}

    public Document(String type) {
        this.type = type;
        this.content = "Default content for " + type;
        this.metadata = loadMetadataFromServer(type);
    }

    private String loadMetadataFromServer(String type) {
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        return "Metadata for " + type;
    }

    @Override
    public Document clone() {
        Document clone = new Document();
        clone.type = this.type;
        clone.content = this.content;
        clone.metadata = this.metadata;
        return clone;
    }
}
