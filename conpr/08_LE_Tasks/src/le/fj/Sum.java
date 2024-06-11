package le.fj;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;

@SuppressWarnings("serial")
public class Sum implements Callable<Integer> {
    final int THRESHOLD = 1000000;
    final int[] array;
    final int lo;
    final int hi;

    Sum(int[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    public Integer call() {
        if (hi - lo < THRESHOLD) {
            int sum = array[lo];
            for(int i = lo + 1; i < hi; i++) {
                sum += array[i];
            }
            return sum; 
        } else {
            int mid = (hi + lo) / 2;
            Sum firstHalf = new Sum(array, lo, mid);
            Sum secondHalf = new Sum(array, mid, hi);
            return secondHalf.call() + firstHalf.call();
        }
    }
    
    public static void main(String[] args) {
        Random rnd = new Random();
        int SIZE = 40000000;
        ExecutorService fj = Executors.newVirtualThreadPerTaskExecutor();
      
        
        int[] l = new int[SIZE];
        for (int i = 0; i < l.length; i++) {
            l[i] = rnd.nextInt(100);
        }
        
        long start = System.currentTimeMillis();
        try {
            int sum = fj.submit(new Sum(l, 0, l.length)).get();
            long duration = System.currentTimeMillis() - start;
            System.out.println("Sum: " + sum + " duration: " + duration + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
