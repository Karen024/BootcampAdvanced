package threadProblem.oneThreadPdfCreation;

public class Main {
    public static void main(String[] args) {
        //9516933400
        long startTime = System.nanoTime();
        MyCustomThread myCustomThread = new MyCustomThread();
        myCustomThread.start();
        try {
            myCustomThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("Operation Complete in  " + (endTime - startTime));
    }
}
