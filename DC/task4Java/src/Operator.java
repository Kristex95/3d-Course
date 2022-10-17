class Operator extends Thread{
    Organization organization;
    Operator(Organization organization){
        this.organization = organization;
    }
    @Override
    public void run() {
        while(true){
            Customer customer;
            try {
                customer = organization.GetCustomer();
                customer.acquiered.set(true);
                System.out.println("Operator " + getId() + " accepted customer #" + customer.number + " call;");
                Thread.sleep(5000);
                System.out.println("Operator " + getId() + " ended call;");
            }
            catch (Exception e){

            }
        }
    }
}
