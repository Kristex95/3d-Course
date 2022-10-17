import java.util.concurrent.atomic.AtomicBoolean;

public class Customer extends Thread{
    Organization organization;
    String number;
    public AtomicBoolean acquiered;
    Customer(Organization organization, String number){
        this.organization = organization;
        this.number = number;
        acquiered = new AtomicBoolean(false);
    }

    @Override
    public void run() {
        while (true){
            /** Вход в очередь **/
            Call();
            System.out.println("Customer " + number + " calling;");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            /** Выход из очереди **/
            if(!acquiered.get()) {
                System.out.println("Customer " + number + " exited queue;");
                Exit();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(acquiered.get()){
                break;
            }

        }
    }

    private void Call(){
        organization.AddCustomer(this);
    }

    private void Exit(){
        organization.ExitFromQueue(this);
    }
}
