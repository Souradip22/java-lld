package org.example.projects.producerconsumerproblem;

public class ProducerConsumerDemo {
    public static void main(String[] args) {

//        Producer acquires lock ➜ Queue Full ➜ wait() ➜ Releases lock
//        Consumer acquires lock ➜ Consumes item ➜ notify() ➜ Producer wakes
        SharedResource resource = new SharedResource(3);

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
