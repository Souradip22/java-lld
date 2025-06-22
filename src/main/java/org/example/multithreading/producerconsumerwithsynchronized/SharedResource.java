package org.example.multithreading.producerconsumerwithsynchronized;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {

    private int capacity;
    private Queue<Integer> queue;

    public SharedResource(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    // Producer acquires lock ➜ Queue Full ➜ wait() ➜ Releases lock
    public synchronized void publish(int item) throws InterruptedException {
        while (queue.size() == capacity){
            System.out.println("Queue is full, Producer is waiting for " +
                    "consumer");
            wait();
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        notify();
    }

    // Consumer acquires lock → queue not empty → consumes item → notify() producer → releases lock.
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()){
            System.out.println("Queue is empty, Consumer is waiting for " +
                    "producer");
            wait();
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notify();
        return item;
    }

}
