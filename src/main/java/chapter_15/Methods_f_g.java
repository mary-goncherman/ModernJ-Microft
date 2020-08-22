package chapter_15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Methods_f_g {

    public static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static int f(int x) {
        return x-337;
    }

    public static int g(int x) {
        return x+3;
    }

    public static Future<Integer> ff(int x) {
        return executorService.submit(() -> x-337);

    }

    public static Future<Integer> gg(int x) {
        return executorService.submit(() -> x+3);

    }

}
