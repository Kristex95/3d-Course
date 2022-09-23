public class Dichotomy {
    double a, b, eps;

    Dichotomy(double a, double b, double eps) {
        this.a = a;
        this.b = b;
        this.eps = eps;
        Calculate();
    }

    static double calcEq(double x) {
        return (double) 5 * Math.pow(x, 3) - 2 * Math.pow(x, 2) * Math.sin(x) - (double) 2 / 5;
    }

    public static void main(String[] args) {
        new Dichotomy(0, Math.PI, 0.0001);
    }

    double Calculate() {
        double c;
        double newC = 0;
        if (calcEq(a) * calcEq(b) < 0) {
            do {
                c = (a + b) / 2;
                if (calcEq(a) * calcEq(c) < 0) {
                    b = c;
                } else {
                    a = c;
                }
                newC = (a + b) / 2;

            } while (Math.abs(newC - c) >= 2 * eps);
            System.out.println(newC);
            System.out.println(calcEq(newC));
        }
        return 0;
    }
}
