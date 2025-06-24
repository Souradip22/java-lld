package org.example.multithreading.threadpoolexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureDemo {
    static class FileProcess implements Callable<String> {
        private String fileName;
        public FileProcess(String fileName) {
            this.fileName = fileName;
        }
        @Override
        public String call() throws Exception {
            System.out.println("File processed start for file: " + fileName + " by - "+ Thread.currentThread().getName());
            if (fileName.equals("error.txt")){
                throw new Exception("Couldn't process file: "+ fileName);
            }
            Thread.sleep(2000);
            return "Processed file: " + fileName;
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<String> files = List.of("file1.txt", "file2.txt", "error.txt",
                "file3.txt");

        List<Future<String>> futures = new ArrayList<>();
        for(String file: files){
            futures.add(executor.submit(new FileProcess(file)));
        }

        for (Future<String> future: futures){

            try {
                System.out.println(future.get()); // BLOCKING
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted.");
            } catch (ExecutionException e) {
                System.out.println("Exception: " + e.getCause().getMessage());
            }
        }
        executor.shutdown();

    }
}
