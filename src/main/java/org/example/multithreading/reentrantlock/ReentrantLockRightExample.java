package org.example.multithreading.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockRightExample {
    private final ReentrantLock lock;

    public ReentrantLockRightExample(ReentrantLock lock) {
        this.lock = lock;
    }

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
        ReentrantLock lock = new ReentrantLock();
        ReentrantLockRightExample obj1 = new ReentrantLockRightExample(lock);
        ReentrantLockRightExample obj2 = new ReentrantLockRightExample(lock);

        Runnable consumer1 =
                () -> obj1.accessResource(Thread.currentThread().getName());
        Runnable consumer2 =
                () -> obj2.accessResource(Thread.currentThread().getName());

        // using the same lock by the threads
        new Thread(consumer1).start();
        new Thread(consumer2).start();


    }
}
