package TaskA;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread extends Thread {
    boolean arr[];
    CyclicBarrier cyclicBarrier;
    int start;
    int end;

    MyThread(boolean arr[], CyclicBarrier cyclicBarrier, int start, int end) {
        this.arr = arr;
        this.cyclicBarrier = cyclicBarrier;
        this.start = start - 1;
        this.end = end - 1;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (arr) {
                boolean arrCopy[] = arr;
                int i = start;
                if (start == 0) {
                    if (arr[0] == true) {
                        if (arrCopy[1] == false) {
                            arr[0] = !arr[0];
                        }
                    }
                    i++;
                }

                for (; i < end; i++) {
                    if (arr[i] == false) {
                        if (arrCopy[i - 1] == true)
                            arr[i] = !arr[i];
                    } else if (i == arr.length - 1) {
                        if (arr[i] == false) {
                            if (arrCopy[i - 1] == true)
                                arr[i] = true;
                        }
                        break;
                    } else if (arr[i] == true) {
                        if (arrCopy[i + 1] == false)
                            arr[i] = !arr[i];
                    }
                }
                PrintSoliders();
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void PrintSoliders() {
        for (int i = start; i < end; i++) {
            System.out.print("solider #" + (i + 1) + " : ");
            if (arr[i] == true) {
                System.out.print("right ");
            } else {
                System.out.print("left ");
            }
        }
        System.out.println();
    }
}
