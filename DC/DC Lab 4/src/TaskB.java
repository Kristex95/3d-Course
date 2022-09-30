import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class TaskB {
    public static void main(String[] args) {
        ReentrantLock rtLock = new ReentrantLock();
        Garden garden = new Garden();
        garden.PrintGarden();

        ThreadWaster threadWaster = new ThreadWaster(rtLock, garden);
        threadWaster.start();
        ThreadPrinter threadPrinter = new ThreadPrinter(rtLock, garden);
        threadPrinter.start();
        ThreadRainer threadRainer = new ThreadRainer(rtLock, garden);
        threadRainer.start();
        ThreadFilewriter threadFilewriter = new ThreadFilewriter(rtLock, garden, "./src/TaskB_Output");
        threadFilewriter.start();
    }
}

class Garden {
    private int width = 10;
    public int[][] field = new int[width][width];

    Garden() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = 1;
            }
        }
        System.out.println("All grown up!");
    }

    public void PrintGarden() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(field[i][j] + "  ");
                if (j == width - 1) {
                    System.out.print("");
                }
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < width - 1; i++) {
            System.out.print("---");
        }
        System.out.println("-");
        System.out.println();
    }

    public void ChangePlantStatus(int xPos, int yPos, int status) {
        if (xPos < 0 || xPos >= width || yPos < 0 || yPos >= width) {
            throw new NumberFormatException("Integer is out of range.");
        }
        if (status != 0 && status != 1) {
            throw new NumberFormatException("Invalid status integer.");
        }
        field[xPos][yPos] = status;
    }
}

class ThreadWaster extends Thread {
    ReentrantLock rtLock;
    Garden garden;

    ThreadWaster(ReentrantLock rtLock, Garden garden) {
        this.rtLock = rtLock;
        this.garden = garden;
    }

    @Override
    public void run() {
        while (true) {
            rtLock.lock();
            try {
                int randX = ThreadLocalRandom.current().nextInt(0, 9);
                int randY = ThreadLocalRandom.current().nextInt(0, 9);
                garden.ChangePlantStatus(randX, randY, 0);
            } finally {
                rtLock.unlock();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class ThreadPrinter extends Thread {
    ReentrantLock rtLock;
    Garden garden;

    ThreadPrinter(ReentrantLock rtLock, Garden garden) {
        this.rtLock = rtLock;
        this.garden = garden;
    }

    @Override
    public void run() {
        while (true) {
            rtLock.lock();
            try {
                garden.PrintGarden();
            } finally {
                rtLock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class ThreadRainer extends Thread {
    ReentrantLock rtLock;
    Garden garden;

    ThreadRainer(ReentrantLock rtLock, Garden garden) {
        this.rtLock = rtLock;
        this.garden = garden;
    }

    @Override
    public void run() {
        while (true) {
            rtLock.lock();
            try {
                for (int i = 0; i < garden.field.length; i++) {
                    for (int j = 0; j < garden.field.length; j++) {
                        garden.ChangePlantStatus(i, j, 1);
                    }
                }
            } finally {
                rtLock.unlock();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class ThreadFilewriter extends Thread {
    ReentrantLock rtLock;
    Garden garden;
    String filename;

    ThreadFilewriter(ReentrantLock rtLock, Garden garden, String filename) {
        this.rtLock = rtLock;
        this.garden = garden;
        this.filename = filename;
    }

    @Override
    public void run() {
        while (true) {
            rtLock.lock();
            try {
                FileWriter fw = null;
                try {
                    fw = new FileWriter(filename, true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                PrintWriter writer = new PrintWriter(fw);
                System.out.println("File Write");

                for (int i = 0; i < garden.field.length; i++) {

                    byte[] strToBytes = Arrays.toString(garden.field[i]).getBytes();
                    writer.print(Arrays.toString(garden.field[i]));
                    writer.println();
                }
                writer.println();
                writer.close();
            }
            finally {
                rtLock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}