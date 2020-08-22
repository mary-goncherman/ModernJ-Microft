package chapter_15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int x = 1337;
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> y = Methods_f_g.ff(x); //executorService.submit(() -> Methods_f_g.f(x));
        Future<Integer> z = Methods_f_g.gg(x); //executorService.submit(() -> Methods_f_g.g(x));

        System.out.println(y.get() + z.get());

        executorService.shutdown();

    }
}
