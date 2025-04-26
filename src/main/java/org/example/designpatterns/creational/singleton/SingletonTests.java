package org.example.designpatterns.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTests {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, CloneNotSupportedException {

        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();

        System.out.println("EagerSingleton hashCodes: " + instance1.hashCode() + " - " + instance2.hashCode());

        LazySingleton instance3 = LazySingleton.getInstance();
        LazySingleton instance4 = LazySingleton.getInstance();

        System.out.println("LazySingleton hashCodes: " + instance3.hashCode() + " -" +
                " " + instance4.hashCode());

        DoubleCheckLockingSingleton instance5 = DoubleCheckLockingSingleton.getInstance();
        DoubleCheckLockingSingleton instance6 = DoubleCheckLockingSingleton.getInstance();

        System.out.println("DoubleCheckLocking hashCodes: " + instance5.hashCode() + " -" +
                " " + instance6.hashCode());

        BillPughSingleton instance7 = BillPughSingleton.getInstance();
        BillPughSingleton instance8 = BillPughSingleton.getInstance();

        System.out.println("BillPughSingleton hashCodes: " + instance7.hashCode() + " -" +
                " " + instance8.hashCode());


        //Demo thread safe example with BillPugh solution
        Runnable task = () -> {
            BillPughSingleton instance = BillPughSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " : " + instance.hashCode());
        };
        for (int i = 0; i < 3; i++){
            Thread newThread = new Thread(task);
            newThread.start();
        }

        // Test reflection breaks BullPugh solution
        BillPughSingleton billPughInstance = BillPughSingleton.getInstance();
        Constructor<BillPughSingleton> declaredConstructor = BillPughSingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        BillPughSingleton billPughInstance2 = declaredConstructor.newInstance();

        System.out.println("Reflection breaks BillPugh solution: " + billPughInstance.hashCode() + " -- " + billPughInstance2.hashCode());

        BillPughSingleton bps1 = BillPughSingleton.getInstance();
        BillPughSingleton bps2 = (BillPughSingleton) bps1.clone(); // clone
        // creates another object!

        System.out.println("Cloneable breaks BillPugh solution: " + bps1.hashCode() + " -- " + bps2.hashCode());

        // FIX the above problem - reflection, cloneable, serializable

        SingletonFinal sf3 = SingletonFinal.getInstance();
        SingletonFinal sf4 = (SingletonFinal) sf3.clone(); // clone
        // creates another object!

        System.out.println("Cloneable proof singleton test: " + sf3.hashCode() + " -- " + sf4.hashCode());

        SingletonFinal sf1 = SingletonFinal.getInstance();
        Constructor<SingletonFinal> declaredConstructor1 = SingletonFinal.class.getDeclaredConstructor();
        declaredConstructor1.setAccessible(true);

        SingletonFinal sf2 = declaredConstructor1.newInstance();
        System.out.println("Reflection proof singleton test: " + sf1.hashCode() +" --" + sf2.hashCode());


    }
}
