package org.example.designpatterns.creational.singleton;

public class BillPughSingleton implements Cloneable{

    private BillPughSingleton(){

    }
    // Static Inner Class (not loaded until getInstance() is called)
    private static class SingletonHelper{
        private static final BillPughSingleton instance =
                new BillPughSingleton();
    }

    public static BillPughSingleton getInstance(){
        return SingletonHelper.instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // DANGEROUS!
    }
}
