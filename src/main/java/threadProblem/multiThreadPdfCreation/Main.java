package threadProblem.multiThreadPdfCreation;

import threadProblem.oneThreadPdfCreation.MyCustomThread;

public class Main {
    public static void main(String[] args) {
        //7289778500
        long startTime = System.nanoTime();
        MyCustomThreadMultiThreading thread1 = new MyCustomThreadMultiThreading(1, 200);
        MyCustomThreadMultiThreading thread2 = new MyCustomThreadMultiThreading(201, 400);
        MyCustomThreadMultiThreading thread3 = new MyCustomThreadMultiThreading(401, 600);
        MyCustomThreadMultiThreading thread4 = new MyCustomThreadMultiThreading(601, 800);
        MyCustomThreadMultiThreading thread5 = new MyCustomThreadMultiThreading(801, 1000);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("Operation Complete in  " + (endTime - startTime));
    }
}
