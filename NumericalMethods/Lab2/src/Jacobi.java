public class Jacobi {
    private double EPS = 0.000001;
    private int n;
    private double[][] Matrix;
    private double[] VectorB;

    public Jacobi(double[][] matrix, double[] Vector) {
        this.Matrix = matrix;
        this.VectorB = Vector;
        this.n = Matrix.length;
        GetResult();
    }

    public void GetResult() {
        double[] X0 = new double[n];
        double[] X = new double[n];
        for (int i = 0; i < n; i++) {
            X0[i] = VectorB[i] / Matrix[i][i];
        }
        double norm;
        int k = 0;
        do {
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (j == i) continue;
                    sum += Matrix[i][j] * X0[j];
                }
                X[i] = (double) ((VectorB[i] - sum) / Matrix[i][i]);
            }
            norm = normCalc(X0, X, n);
            for (int i = 0; i < n; i++) {
                X0[i] = X[i];
            }

            k++;
            if (k > 100) {
                System.out.println("Ітерація не виконалась!");
                return;
            }
        } while (norm > EPS);
        System.out.print("Jacobi results: ");
        for (int i = 0; i < n; i++) {
            System.out.print(X0[i] + ", ");
        }
    }

    private double normCalc(double[] x1, double[] x2, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(x1[i] - x2[i]);
        }
        return sum;
    }
}
