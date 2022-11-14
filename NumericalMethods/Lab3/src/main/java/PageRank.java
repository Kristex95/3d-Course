import java.util.ArrayList;
import java.util.List;

public class PageRank {

    static final double j = 0.15;
    static final double eps = 0.0000001;
    static double[][] matrix = {
            {0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 1, 0}
    };

    public static void main(String[] args) {
        double[][] result = Markov(matrix);
        System.out.println("Markov:");
        printMatrix(result);
        System.out.println("Matrix M:");
        double[][] matrixM = findMatrixM(matrix);
        printMatrix(matrixM);
        System.out.println("Range:");
        double[] range = rangeMatrix(matrix);
        printVector(range);

    }

    public static void printVector(double[] vec){
        for (double i: vec) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static double[][] Markov(double[][] matrix) {
        double[][] new_matrix = new double[5][5];
        ArrayList<Integer> counters = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int counter = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[j][i] == 1) {
                    counter++;
                }
            }
            counters.add(counter);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[j][i] == 1) {
                    new_matrix[j][i] = 1.0 / counters.get(i);
                } else {
                    matrix[j][i] = 0;
                }
            }
        }

        return new_matrix;
    }

    static double[] rangeMatrix(double[][] matrix) {
        double[] x0 = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            x0[i] = (1.0 / matrix.length);
        }

        double[] x1 = new double[matrix.length];

        int i = 1;
        while (true) {
            x1 = multiplyMatrixByVector(getMatrixDegree(Markov(matrix), i), x0);
            i++;
            if(Norma(x1,x0) < eps){
                break;
            }

            x0 = x1;
        }
        System.out.println(i);
        return x1;
    }

    public static double Norma(double[] vec1, double[] vec2){
        double sum = 0;
        for (int i = 0; i < vec1.length; i++) {
            sum+= Math.abs(vec1[i] - vec2[i]);
        }
        return sum;
    }

    public static double[][] multiplyMatrixByMatrix(double[][] result, double[][] matrix) {
        double[][] temp = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    temp[i][j] += result[i][k] * matrix[k][j];
                }
            }
        }
        return temp;
    }

    public static double[][] getMatrixDegree(double[][] matrix, int degree) {
        double[][] result = matrix;
        for (int i = 1; i < degree; i++) {
            result = multiplyMatrixByMatrix(result, matrix);
        }
        return result;
    }
    static double[][] multiplyMatrix(double[][] matrix, double number) {
        double[][] new_matrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                new_matrix[i][j] = matrix[i][j] * number;
            }
        }
        return new_matrix;
    }

    public static double[] multiplyMatrixByVector(double[][] markovMatrix, double[] x0) {
        double[] result = new double[markovMatrix.length];
        for (int i = 0; i < markovMatrix.length; i++) {
            for (int j = 0; j < markovMatrix.length; j++) {
                result[i] += markovMatrix[i][j] * x0[j];
            }
        }
        return result;
    }

    static double[][] sumMatrix(double[][] matrix, double[][] matrix2) {
        double[][] new_matrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                new_matrix[i][j] = matrix[i][j] + matrix2[i][j];
            }
        }
        return new_matrix;
    }

    static double[][] findMatrixM(double[][] matrix) {
        double[][] matrixB = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrixB[i][j] = 1.0 / matrix.length;
            }
        }

        return sumMatrix(multiplyMatrix(matrixB, j), multiplyMatrix(Markov(matrix), 1 - j));
    }

    static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
