import java.util.Random;

public class MatrixCreator {
    private final Random random = new Random();

    public double[] GetVectorB(double[][] matrix) {
        int n = matrix.length;
        double vec[] = new double[n];
        for (int i = 0; i < n; i++) {
            vec[i] = 0;
            for (int j = 0; j < n; j++)
            vec[i] += matrix[i][j];
        }
        return vec;
    }
    public double[][] GetRandomMatrix(int n){
        double matrix[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j){
                    matrix[i][j] = random.nextInt(500);
                }else
                    matrix[i][j] = random.nextInt(10);
            }
        }
        return matrix;
    }

    public boolean Convergence(double[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            double sum = 0.0;
            for (int j = 0; j < matrix.length; j++){
                sum+= matrix[i][j];
            }
            if(matrix[i][i] < sum - matrix[i][i])
                return false;
        }
        return true;
    }

}
