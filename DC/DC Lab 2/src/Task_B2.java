import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task_B2 {
    public static BlockingQueue<Integer> concurrentLinkedQueue1 = new LinkedBlockingQueue<>();
    public static BlockingQueue<Integer> concurrentLinkedQueue2 = new LinkedBlockingQueue<>();

    public static int STORAGE_SIZE = 100;

    public static void main(String[] args) {
        Tasks2 task = new Tasks2(concurrentLinkedQueue1, concurrentLinkedQueue2);

        Thread thread1 = new Thread(() -> task.produce());
        Thread thread2 = new Thread(() -> task.produceAndConsume());
        Thread thread3 = new Thread(() -> task.count());
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Tasks2 {
    private static int maxCapacity = 100;
    BlockingQueue<Integer> queue1, queue2;

    Tasks2(BlockingQueue queue1, BlockingQueue queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    public void produce() {
        int item = 1;

        while (true) {

            System.out.println("Producer added " + item);
            try {
                queue1.put(item++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (item == maxCapacity) {
                break;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void produceAndConsume() {

        while (true) {
            Integer item;


            try {
                item = queue1.take();
                System.out.println("ProducerAndConsumer took " + item);
                queue2.put(item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public void count() {
        while (true) {
            int item = 0;
            try {
                item = queue2.take();
                System.out.println("Consumer took " + item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

