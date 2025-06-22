package org.example.multithreading.reentrantreadwritelock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {
    private int sharedData = 0;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    private void read(){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading " +
                    "data "+ sharedData);
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " finish " +
                    "reading ");
            rwLock.readLock().unlock();

        }
    }
    private void write(int value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " WRITING " +
                    "data "+ value);
            Thread.sleep(3000);
            sharedData = value;
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " finish " +
                    "WRITING.... ");
            rwLock.writeLock().unlock();

        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockExample example =
                new ReentrantReadWriteLockExample();

        Runnable reader = () -> example.read();
        Runnable writer = () -> example.write(new Random().nextInt(10));
        for (int i = 0; i < 3; i++) {
            new Thread(reader, "Reader-" + i).start();
        }

        new Thread(writer, "Writer-1").start();
        new Thread(writer, "Writer-2").start();

        for (int i = 3; i < 6; i++) {
            new Thread(reader, "Reader-" + i).start();
        }
    }
}
