public class Newton {
    double a, b, eps;

    Newton(double a, double b, double eps) {
        this.a = a;
        this.b = b;
        this.eps = eps;
        Calculate();
    }

    static double f(double x) {
        return (double) Math.pow(x, 2) - 4;
    }

    static double derF(double x) {
        return (double) 2 * x;
    }

    static double doubleDerF(double x) {
        return (double) 2;
    }


    public static void main(String[] args) {
        new Newton(-Math.PI, 0, 0.0001);
    }

     void Calculate(){
        double x0 = a;
        if(!(f(x0)*doubleDerF(x0) > 0)){
            x0 = b;
            if (!(f(x0)*doubleDerF(x0) > 0)){
                System.out.println("Несходимость");
            }
        }
        while(Math.abs(f(x0)) >= 2*eps){
            double nextX = x0 - f(x0)/derF(x0);
            x0 = nextX;
        }
         System.out.println(x0);
         System.out.println(f(x0));
    }
}
