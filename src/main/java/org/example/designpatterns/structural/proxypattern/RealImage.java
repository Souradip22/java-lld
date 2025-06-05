package org.example.designpatterns.structural.proxypattern;

public class RealImage implements Image {
    private String fileName;
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromDisk();
    }
    private void loadImageFromDisk(){
        System.out.println("Loading image from: " + fileName);
    }
    @Override
    public void displayImage() {
        System.out.println("Display image: " + fileName);
    }
}
