package org.example.multithreading.producerconsumerwithlock;

import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        SharedResource resource = new SharedResource(3, lock);

        Thread producerThread = new Thread(()->{
            for (int i = 0; i < 6; i++) {
                try {
                    resource.publish(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consumerThread = new Thread(()->{
            for (int i = 0; i < 6; i++) {
                try {
                    resource.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producerThread.start();
        consumerThread.start();

    }
}
