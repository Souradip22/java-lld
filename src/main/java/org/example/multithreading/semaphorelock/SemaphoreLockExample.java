package org.example.multithreading.semaphorelock;

import java.util.concurrent.Semaphore;

public class SemaphoreLockExample {

    private final Semaphore lock = new Semaphore(2);

    private void accessResource(String threadName){
        try {
            System.out.println(threadName + " is trying to acquire lock");
            lock.acquire();
            System.out.println(threadName + " acquired lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(threadName + " is going to release the lock");
            lock.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreLockExample example = new SemaphoreLockExample();

        Runnable conumer1 = () -> example.accessResource("Consumer-1");
        Runnable conumer2 = () -> example.accessResource("Consumer-2");
        Runnable conumer3 = () -> example.accessResource("Consumer-3");

        new Thread(conumer1).start();
        new Thread(conumer2).start();
        new Thread(conumer3).start();
    }
}
