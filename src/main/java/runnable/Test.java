package runnable;


public class Test {

    public static void main(String[] args) {

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello world!");
//            }
//        });


        Thread t = new Thread(() -> System.out.println("Hello world"));

        t.start();
        System.out.println(t.getState());
        t.interrupt();
    }




}
