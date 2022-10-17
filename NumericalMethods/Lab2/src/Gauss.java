public class Gauss {
    Gauss(double[][] A, double[] B){
        Find(A, B);
    }

    public void Find(double[][] A, double[] B){
        int N = B.length;
        for (int k = 0; k < N; k++)
        {
            /** знаходимо початковий рядок **/
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            /** міняємо рядки матриці**/
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            /** міняємо значення в комірках рядка **/
            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            /** точка прив'язки A та B **/
            for (int i = k + 1; i < N; i++)
            {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }

        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        System.out.print("Gauss  results: ");
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + ", ");
        }
    }
}
