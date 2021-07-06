package threadProblem.oneThreadSumCalculation;

public class Main {
    public static void main(String[] args) {
        long programStart = System.nanoTime();
        MyThread myThread = new MyThread();
        myThread.start();
        try{
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long programEnd = System.nanoTime();
        System.out.println((programEnd - programStart) + " , " + myThread.getSum());
    }
}
