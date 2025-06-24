package org.example.multithreading.threadpoolexecutor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<String> files = List.of("file1.txt", "file2.txt", "error.txt", "file3.txt");

        // Submit tasks using CompletableFuture
        List<CompletableFuture<Void>> futures = files.stream()
                .map(file -> CompletableFuture
                        .supplyAsync(() -> processFile(file), executor)
                        .handle((result, ex) -> {
                            if (ex != null) {
                                System.out.println("Exception: " + ex.getCause().getMessage());
                                return null;
                            } else {
                                System.out.println(result);
                                return result;
                            }
                        })
                        .thenAccept(res -> { /* No action needed, just to unify return type */ })
                )
                .collect(Collectors.toList());

        // Wait for all tasks to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        System.out.println("Main is about to complete");

        executor.shutdown();
    }

    private static String processFile(String fileName) {
        System.out.println("File processed start for file: " + fileName + " by - " + Thread.currentThread().getName());
        if (fileName.equals("error.txt")) {
            throw new RuntimeException("Couldn't process file: " + fileName);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Task was interrupted");
        }
        return "Processed file: " + fileName;
    }
}
