package org.example.designpatterns.creational.singleton;

public class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    // private constructor to prevent instantiation
    private EagerSingleton(){
    }
    public static EagerSingleton getInstance() {
        return instance;
    }
}
