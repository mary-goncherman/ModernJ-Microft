package chapter_15;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {

        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(() -> {result.left = Methods_f_g.f(x);});
        Thread t2 = new Thread(() -> {result.right = Methods_f_g.g(x);});

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(result.left + result.right);



    }

}
