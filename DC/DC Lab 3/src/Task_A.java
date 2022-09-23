public class Task_A {
    static int beesCount = 4;
    static Pot pot = new Pot();
    public static void main(String[] args) {
        Bee[] bees = new Bee[beesCount];
        for (int i = 0; i < beesCount; i++){
            bees[i] = new Bee(pot, 1);
            bees[i].start();
        }
        Winnie Pooh = new Winnie(pot);
        Pooh.start();
    }
}

class Pot{
    private boolean isFull = false;
    int MAX_CAPACITY = 100;
    int currentVolume = 0;

    void IncreaseVolume(int val){
        currentVolume++;
        if(currentVolume == MAX_CAPACITY){
            isFull = true;
            System.out.println("Pot is full");
        }
    }

    void EatAll(){
        isFull = false;
        currentVolume = 0;
    }

    public boolean isFull() {
        return isFull;
    }
}

class Bee extends Thread {
    Pot honeyPot;
    int honeyCapacity;
    Bee(Pot _pot, int honey){
        honeyPot = _pot;
        honeyCapacity = honey;
    }

    @Override
    public void run() {
        while (!interrupted()){
            synchronized (honeyPot) {
                if (honeyPot.isFull()) {
                    try {
                        honeyPot.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    honeyPot.IncreaseVolume(honeyCapacity);
                    if(honeyPot.isFull()){
                        honeyPot.notifyAll();
                    }
                    System.out.println("Current pot volume: " + honeyPot.currentVolume);
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

class Winnie extends Thread{
    private Pot honeyPot;
    Winnie(Pot _pot){
        honeyPot = _pot;
    }
    @Override
    public void run() {
        while (!interrupted()){
            synchronized (honeyPot){
                if(!honeyPot.isFull()) {
                    try {
                        honeyPot.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                honeyPot.EatAll();
                System.out.println("Pooh has eaten honey");
                honeyPot.notifyAll();
            }
        }
    }
}