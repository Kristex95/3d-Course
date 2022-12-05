package ThreeDiagonal;

import java.io.File;
import java.util.List;

public class Main {
    private static final MatrixValidator matrixValidator = new MatrixValidator();

    public static void main(String[] args) {
        ThreeDiagonalMatrix threeDiagonalMatrix = new ThreeDiagonalMatrix(new File("src/main/resources/matrix.txt"));
        Solver solver = new Solver(matrixValidator);
        List<Double> res = solver.solve(threeDiagonalMatrix);
        if(res!= null){
            System.out.println(res);
        }
    }
}
