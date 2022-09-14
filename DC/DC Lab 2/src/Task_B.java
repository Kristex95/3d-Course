import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task_B {
    public static BlockingQueue<Integer> concurrentLinkedQueue1 = new LinkedBlockingQueue<>();
    public static BlockingQueue<Integer> concurrentLinkedQueue2 = new LinkedBlockingQueue<>();

    public static int STORAGE_SIZE = 100;

    public static void main(String[] args) {
        Tasks task = new Tasks(concurrentLinkedQueue1, concurrentLinkedQueue2);

        Thread thread1 = new Thread(() -> task.produce());
        Thread thread2 = new Thread(() -> task.produceAndConsume());
        Thread thread3 = new Thread(() -> task.count());
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Tasks {
    private static int maxCapacity = 100;
    BlockingQueue<Integer> queue1, queue2;

    Tasks(BlockingQueue queue1, BlockingQueue queue2) {
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    public void produce() {
        int item = 1;

        while (true) {
            synchronized (queue1) {
                while (queue1.size() >= 10) {
                    try {
                        queue1.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Producer added " + item);
                queue1.add(item++);

                if (item == maxCapacity) {
                    break;
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                queue1.notifyAll();
            }

        }
        //System.out.println("Task \"produce\" done!");
    }

    public void produceAndConsume() {

        while (true) {
            Integer item;
            synchronized (queue1) {

                while (queue1.isEmpty()) {
                    try {
                        queue1.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                item = queue1.poll();
                queue1.notifyAll();
            }

            synchronized (queue2) {
                while (queue2.size() >= 10) {
                    try {
                        queue2.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                queue2.add(item);
                queue2.notifyAll();

                System.out.println("ProducerAndConsumer took " + item);

            }

        }
    }



    public void count() {
        while (true) {
            synchronized (queue2) {
                while (queue2.isEmpty()) {
                    try {
                        queue2.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int item = queue2.poll();
                System.out.println("Consumer took " + item);

                queue2.notifyAll();
            }
        }
    }
}

