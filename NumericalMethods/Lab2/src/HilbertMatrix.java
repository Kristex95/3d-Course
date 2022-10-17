import java.util.ArrayList;

public class HilbertMatrix {
    double[][] matrix;

    HilbertMatrix(int size){
        matrix = new double[size][size];
        for (int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                matrix[i-1][j-1] = 1.0 / (i + j - 1);
            }
        }
    }

    public double[][] GetHilbert(){
        return matrix;
    }
}
