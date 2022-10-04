package TaskB;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TaskB {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        MyThread[] threads = new MyThread[4];
        for (int i = 0; i < threads.length; i++) {
            MyThread thread = new MyThread(cyclicBarrier);
            threads[i] = thread;
            thread.start();
        }
        Monitor monitor = new Monitor(threads);
        monitor.main();
    }
}

class MyThread extends Thread {
    private static Object object;
    CyclicBarrier cyclicBarrier;
    private String string = "";
    private boolean abEquals = false;

    MyThread(CyclicBarrier cyclicBarrier) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            string += (char) (random.nextInt(4) + 'A');
        }
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            synchronized (cyclicBarrier) {
                Random random = new Random();
                int rand = random.nextInt(string.length());
                switch (string.charAt(rand)) {
                    case 'A':
                        string = string.substring(0, rand) + "C" + string.substring(rand + 1);
                        break;
                    case 'B':
                        string = string.substring(0, rand) + "D" + string.substring(rand + 1);
                        break;
                    case 'C':
                        string = string.substring(0, rand) + "A" + string.substring(rand + 1);
                        break;
                    case 'D':
                        string = string.substring(0, rand) + "B" + string.substring(rand + 1);
                        break;
                }
                int ACount = 0;
                int BCount = 0;
                for (int i = 0; i < string.length(); i++) {
                    if (string.charAt(i) == 'A') {
                        ACount += 1;
                    } else if (string.charAt(i) == 'B') {
                        BCount += 1;
                    }
                }
                if (ACount == BCount)
                    abEquals = true;
                else
                    abEquals = false;
            }
            System.out.println(getId() + " " + string);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public boolean isAbEquals() {
        return abEquals;
    }
}

