public class Task_A {
    public static final int forest_size = 100;
    public static final int numberOfBees = 4;
    public static final Manager manager = new Manager();
    public static int[][] forest = new int[forest_size][forest_size];

    public static void main(String[] args) {

        int x, y;
        x = (int) (Math.random() * forest_size);
        y = (int) (Math.random() * forest_size);
        forest[x][y] = 1;

        Bee[] bee_hive = new Bee[numberOfBees];

        for (int i = 0; i < numberOfBees; i++) {
            bee_hive[i] = new Bee("Bee" + i);
            Thread thread = new Thread(bee_hive[i]);
            thread.start();
        }
    }
}

class Bee implements Runnable {
    private String beeName;

    Bee(String name) {
        this.beeName = name;
    }

    public String getBeeName() {
        return beeName;
    }

    public void setBeeName(String name) {
        this.beeName = name;
    }

    @Override
    public void run() {
        int row;
        while (Task_A.manager.isPoohHasNotFound()) {
            synchronized (Task_A.manager) {
                row = Task_A.manager.getCounterAndIncrease();
            }
            System.out.println(beeName + " - " + row);
            for (int i = 0; i < Task_A.forest_size; i++) {
                if (Task_A.forest[row][i] == 1 && Task_A.manager.isPoohHasNotFound()) {
                    Task_A.manager.setPoohHasFound(true);

                    System.out.println(beeName + " found on " + row);

                    break;
                }
            }
        }
    }
}


class Manager {
    private int counter = 0;
    private boolean poohHasFound = false;

    public int getCounterAndIncrease() {
        int readyCounter = counter;
        if (counter < Task_A.forest_size - 1) {
            counter++;
        }
        return readyCounter;
    }

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        if (!poohHasFound)
            counter++;
    }

    public boolean isPoohHasNotFound() {
        return !poohHasFound;
    }

    public void setPoohHasFound(boolean poohHasFound) {
        this.poohHasFound = poohHasFound;
    }
}
