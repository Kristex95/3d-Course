public class Seidel {
    private double EPS = 0.000001;
    private int n;
    private double[][] Matrix;
    private double[] VectorB;
    public Seidel(double[][] Matrix, double[] Vector){
        this.Matrix = Matrix;
        this.VectorB = Vector;
        this.n = Matrix.length;
        GetResult();
    }

    public void GetResult(){
        double[] X0 = new double[n];
        double[] X = new double[n];
        int i1 = 0;
        double s, sum, delta;
        do {
            for (int i = 0; i < n; i++) {
                X[i] = VectorB[i];
                for (int j = 0; j < i - 1; j++) {
                    X[i] -= Matrix[i][j] * X[j];
                }
                for (int j = i + 1; j < n; j++) {
                    X[i] -= Matrix[i][j] * X0[j];
                }
                X[i] /= Matrix[i][i];
            }
            i1++;
            delta = 0;
            for (int i = 0; i < n; i++) {
                delta += Math.abs(X[i] - X0[i]);
                X0[i] = X[i];
            }
            s = 0;
            sum = 0;
            for (int i = 0; i < n; i++) {
                sum = VectorB[i];
                for (int j = 0; j < n; j++) {
                    sum -= Matrix[i][j] * X[j];
                }
                s += sum * sum;
            }
            s = Math.sqrt(s);
        }
        while (s > EPS && delta > EPS);
        System.out.print("Seidel results: ");
        for (int i = 0; i < n; i++) {
            System.out.print(X[i] + ", ");
        }
    }
}
