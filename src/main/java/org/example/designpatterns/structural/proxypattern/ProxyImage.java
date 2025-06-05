package org.example.designpatterns.structural.proxypattern;

public class ProxyImage implements Image{
    private String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void displayImage() {
        if (realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.displayImage();
    }
}
