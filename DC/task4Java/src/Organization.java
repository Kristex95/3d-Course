import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Organization {
    public BlockingQueue<Customer> queue;
    private List<Operator> operators;
    private static final int OPERATORS_COUNT = 2;
    Organization(){
        operators = new ArrayList<>();
        queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < OPERATORS_COUNT; i++){
            Operator operator = new Operator(this);
            operators.add(operator);
            operator.start();
        }
    }

    public void ExitFromQueue(Customer customer){
        queue.remove(customer);
    }

    public void AddCustomer(Customer customer){
        queue.add(customer);
    }

    public Customer GetCustomer(){
        return queue.poll();
    }
}
