package TaskA;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        int solidersNumber = 10;
        boolean arr[] = new boolean[solidersNumber];
        Random random = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = int2bool(random.nextInt(2));
        }

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        MyThread[] threads = new MyThread[2];
        MyThread newThread1 = new MyThread(arr, cyclicBarrier, 1, 5);
        MyThread newThread2 = new MyThread(arr, cyclicBarrier, 6, 10);
        threads[0] = newThread1;
        threads[1] = newThread2;
        newThread1.start();
        newThread2.start();

    }

    public static boolean int2bool(int val) {
        if (val <= 0) return false;
        return true;
    }
}
