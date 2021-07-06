package threadProblem.oneThreadSumCalculation;

import java.util.Objects;

public class MyThread extends Thread {
    private int sum;

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 1_000_000_000; i++) {
            sum += i;
        }
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) + " , " + sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyThread)) return false;
        MyThread myThread = (MyThread) o;
        return sum == myThread.sum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum);
    }

    @Override
    public String toString() {
        return "MyThread{" +
                "sum=" + sum +
                '}';
    }
}
