package org.example.projects.pubsubsystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {
    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(3);

    public static void dispatch(Consumer consumer, Message message){
        executorService.submit(()->{
            consumer.consume(message);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static void shutdown(){
        executorService.shutdown();
    }
}
