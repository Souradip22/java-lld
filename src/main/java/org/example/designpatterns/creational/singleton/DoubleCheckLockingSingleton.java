package org.example.designpatterns.creational.singleton;

public class DoubleCheckLockingSingleton {

    // volatile ensures that the instance variable is always read from main
    // memory,
    // bypassing the local cache of each core, thus guaranteeing that all threads see the most up-to-date value.
    private static volatile DoubleCheckLockingSingleton instance = null;
    private DoubleCheckLockingSingleton(){

    }

    public static DoubleCheckLockingSingleton getInstance(){
        if (instance == null) {
            synchronized (DoubleCheckLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }
}
