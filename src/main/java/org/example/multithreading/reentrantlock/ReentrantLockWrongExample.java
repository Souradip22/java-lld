package org.example.multithreading.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWrongExample {
    private final ReentrantLock lock = new ReentrantLock();

    private void accessResource(String threadName){
        try {
            System.out.println(threadName + " is going to acquire lock");
            lock.lock();
            System.out.println(threadName + " acquired the lock");
            Thread.sleep(2000);
        } catch (Exception e){
            // handle exceptio
        } finally {
            System.out.println(threadName + " is releasing the lock");
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantLockWrongExample obj1 = new ReentrantLockWrongExample();
        ReentrantLockWrongExample obj2 = new ReentrantLockWrongExample();

        Runnable consumer1 =
                () -> obj1.accessResource(Thread.currentThread().getName());
        Runnable consumer2 =
                () -> obj2.accessResource(Thread.currentThread().getName());

        // not the right way...
        // we are creating the lock object twice
        // so both thread will acquire the lock
        new Thread(consumer1).start();
        new Thread(consumer2).start();


    }
}
