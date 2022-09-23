public class Relaxation {
    double a, b, eps;

    Relaxation(double a, double b, double eps) {
        this.a = a;
        this.b = b;
        this.eps = eps;
        Calculate();
    }

    public static void main(String[] args) {
        new Relaxation(0, Math.PI, 0.001);
    }

    static double calcEq(double x) {
        return (double) Math.pow(x, 2) - 4;
    }

    double Calculate() {
        double m1 = 2, M1 = 6,tau = -2/(m1+M1), x1 = a, x = Double.MAX_VALUE;
        int i = 0;
        while (Math.abs(x - x1) >= eps) {
            x = x1;
            x1 = x + tau * calcEq(x);
            i++;
        }
        System.out.println("i: " + i + ", x: " + x);
        return 1;
    }
}
