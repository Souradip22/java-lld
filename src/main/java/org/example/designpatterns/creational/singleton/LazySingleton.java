package org.example.designpatterns.creational.singleton;

public class LazySingleton {
    private static LazySingleton instance = null;
    private LazySingleton(){
    }

    public static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
