public class Main {
    private final static int NUMBER_OF_CUSTOMERS = 10;
    public static void main(String[] args) {
        Organization organization = new Organization();
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++){
            Customer customer = new Customer(organization, String.valueOf(i+1));
            customer.start();
        }
    }
}
