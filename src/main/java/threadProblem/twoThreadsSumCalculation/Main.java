package threadProblem.twoThreadsSumCalculation;

public class Main {
    public static void main(String[] args) {
        long programStart = System.nanoTime();
        CustomThread thread1 = new CustomThread(0, 500_000_000);
        CustomThread thread2 = new CustomThread(500_000_000, 1_000_000_000);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long programEnd = System.nanoTime();
        System.out.println((programEnd - programStart) + " , " + (thread1.getSum() + thread2.getSum()));
    }
}
