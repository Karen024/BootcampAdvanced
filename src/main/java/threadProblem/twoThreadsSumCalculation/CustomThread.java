package threadProblem.twoThreadsSumCalculation;


import java.util.Objects;

public class CustomThread extends Thread {
    private final long from;
    private final long to;
    private int sum;

    public CustomThread(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (long i = from; i < to; i++) {
            sum += i;
        }
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) + " , " + sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomThread)) return false;
        CustomThread that = (CustomThread) o;
        return from == that.from &&
                to == that.to &&
                sum == that.sum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, sum);
    }

    @Override
    public String toString() {
        return "CustomThread{" +
                "from=" + from +
                ", to=" + to +
                ", sum=" + sum +
                '}';
    }
}
