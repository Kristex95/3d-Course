package TaskB;

public class Monitor {
    private boolean isEqual = false;
    private MyThread[] threads;

    Monitor(MyThread[] threads) {
        this.threads = threads;
    }

    public void main() {
        System.out.println(1);
        while (!isEqual) {
            int counter = 0;
            for (int i = 0; i < threads.length; i++) {
                if(threads[i].isAbEquals())
                    counter++;
            }
            if(counter >= 3){
                isEqual = true;
                System.out.println("Equal");
            }
        }
        for (MyThread thread : threads) {
            thread.interrupt();
        }
    }
}
