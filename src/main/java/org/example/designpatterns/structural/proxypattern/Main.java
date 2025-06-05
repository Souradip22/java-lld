package org.example.designpatterns.structural.proxypattern;

public class Main {
    public static void main(String[] args) {
        Image img1 = new RealImage("Cat.png"); // loading the image even when
        // we are not displaying
        Image img2 = new RealImage("DOG.png");// gets loaded evn when we are
        // not using it
        img1.displayImage();


        Image img3 = new ProxyImage("Bus.png"); //not loaded until it's
        // displayed
        img3.displayImage(); // loaded + displayed
        img3.displayImage(); // only displayed

        Image img4 = new ProxyImage("Train.png");// not loaded

    }
}
