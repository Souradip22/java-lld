package org.example.multithreading.stampedlock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

    private int sharedData = 0;
    private final StampedLock lock = new StampedLock();

    // Writer Method
    public void write(String threadName, int value) {
        long stamp = lock.writeLock();
        try {
            System.out.println(threadName + " is writing: " + value);
            Thread.sleep(1000);
            sharedData = value;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println(threadName + " finished writing.");
        }
    }

    // Optimistic Reader Method
    public void optimisticRead(String threadName) {
        long stamp = lock.tryOptimisticRead();
        int data = sharedData;

        // Simulate some processing
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        if (!lock.validate(stamp)) {
            // If a write happened during the read, fallback to normal read lock
            System.out.println(threadName + " detected write, retrying with read lock...");
            stamp = lock.readLock();
            try {
                data = sharedData;
            } finally {
                lock.unlockRead(stamp);
            }
        }

        System.out.println(threadName + " read data: " + data);
    }

    public static void main(String[] args) {
        StampedLockExample example = new StampedLockExample();

        Runnable reader = () -> example.optimisticRead(Thread.currentThread().getName());
        Runnable writer = () -> example.write(Thread.currentThread().getName(), (int)(Math.random() * 100));

        // Start Readers
        for (int i = 0; i < 3; i++) {
            new Thread(reader, "Reader-" + i).start();
        }

        // Start Writer
        new Thread(writer, "Writer").start();

        // Start more Readers
        for (int i = 3; i < 6; i++) {
            new Thread(reader, "Reader-" + i).start();
        }
    }
}

