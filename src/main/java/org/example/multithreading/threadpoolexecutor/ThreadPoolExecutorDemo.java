package org.example.multithreading.threadpoolexecutor;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread th = new Thread(r);
                th.setName("- Custom Thread Name -");
                return th;
            }
        };
        RejectedExecutionHandler handler = (r, executor) -> {
            System.out.println("Rejected Task: " + r.toString());
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                factory,
                handler
        );
        for (int i = 1; i <= 10; i++){
            int taskId = i;
            executor.submit(()->{
                System.out.println(Thread.currentThread().getName() + " is executing Task " + taskId);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
        System.out.println("Active Threads: " + executor.getActiveCount());


        // Get available CPU cores
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available CPU Cores: " + numberOfCores);

        // Example 1: CPU-bound task → Core Pool Size ≈ Number of CPU Cores
        int cpuBoundPoolSize = numberOfCores;
        System.out.println("Recommended Core Pool Size for CPU-bound tasks: " + cpuBoundPoolSize);

        // Example 2: I/O-bound task → Use the formula
        double ioWaitTime = 8.0; // seconds the thread waits (example)
        double cpuComputeTime = 2.0; // seconds the thread uses CPU (example)

        int ioBoundPoolSize = (int) (numberOfCores * (1 + (ioWaitTime / cpuComputeTime)));
        System.out.println("Recommended Core Pool Size for I/O-bound tasks: " + ioBoundPoolSize);
    }
}
