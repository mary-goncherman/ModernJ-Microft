package chapter_15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFComplete {
    public static void main(String[] args)throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> a = new CompletableFuture<>();
        CompletableFuture<Integer> b = new CompletableFuture<>();
        CompletableFuture<Integer> c = a.thenCombine(b, (y,z) -> y + z);

        executorService.submit(() -> a.complete(f(x)));
        executorService.submit(() -> b.complete(g(x)));

        // операция с запуститься только тогда, когда выполняться а и б, нет ожидание при получении результата
        System.out.println(c.get());

        executorService.shutdown();

//        CompletableFuture<Integer> a = new CompletableFuture<>();
//        executorService.submit(() -> a.complete(g(x)));
//        int b = f(x);
//        System.out.println(b + a.get());
//        executorService.shutdown();
    }

    public static int f(int x) {
        return x-337;
    }

    public static int g(int x) {
        return x+3;
    }
}
