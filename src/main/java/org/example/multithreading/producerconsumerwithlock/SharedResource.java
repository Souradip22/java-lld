package org.example.multithreading.producerconsumerwithlock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    private final int capacity;
    private final ReentrantLock lock;
    private final Condition condition;
    private Queue<Integer> queue;

    public SharedResource(int capacity, ReentrantLock lock) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.lock = lock;
        this.condition = lock.newCondition();
    }

    // Producer acquires lock ➜ Queue Full ➜ wait() ➜ Releases lock
    public void publish(int item) throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() == capacity) {
                System.out.println("Queue is full, Producer is waiting for " +
                        "consumer");
                condition.await();
            }
            queue.add(item);
            System.out.println("Produced: " + item);
            condition.signal();

        } finally {
            lock.unlock();
        }
    }

    // Consumer acquires lock → queue not empty → consumes item → notify() producer → releases lock.
    public int consume() throws InterruptedException{
        lock.lock();
        try {

            while (queue.isEmpty()) {
                System.out.println("Queue is empty, Consumer is waiting for " +
                        "producer");
                condition.await();
            }
            int item = queue.poll();
            System.out.println("Consumed: " + item);
            condition.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

}
