package org.example.designpatterns.structural.facadepattern;

public class Main {
    public static void main(String[] args) {
        HomeTheaterFacade facade = new HomeTheaterFacade();
        facade.watchMovie(20, 55);
    }
}
