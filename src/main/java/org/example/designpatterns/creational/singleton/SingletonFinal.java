package org.example.designpatterns.creational.singleton;

public class SingletonFinal {
    private SingletonFinal() {
        // Preventing instantiation via reflection
        if (SingletonHelper.INSTANCE != null) { // this will load the
            // InnerClass SingletonHelper
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    // Static inner class responsible for holding the Singleton instance
    private static class SingletonHelper {
        static{
            System.out.println("THIS CLASS IS LOADED");
        }
        // The instance is created when the SingletonHelper class is loaded
        private static final SingletonFinal INSTANCE = new SingletonFinal();
    }

    public static void checkIfInnerClassLoaded(){
        System.out.println("method called");
    }

    // Global access point for the Singleton instance
    public static SingletonFinal getInstance() {
        System.out.println("Get Instance called");
        return SingletonHelper.INSTANCE;
    }

    // Prevent cloning of the Singleton instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getInstance();
//        throw new CloneNotSupportedException("Cloning of this Singleton is not allowed");
    }

    // Ensure that during deserialization, the same instance is returned
    protected Object readResolve() {
        return getInstance();
    }
}

