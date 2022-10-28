public class Main {
    private static final int SIZE =10;
    public static void main(String[] args) {

        HilbertMatrix hilbertMatrix = new HilbertMatrix(SIZE);
        MatrixCreator creator = new MatrixCreator();
        //double[][] A = creator.GetRandomMatrix(SIZE);
        double[][] A = {{3,2,1}, {2,5,3}, {2,3,5}};
        double[] B = creator.GetVectorB(A);
        double[] C = creator.GetVectorB(hilbertMatrix.GetHilbert());
        for (int i = 0; i < B.length; i++) {
            System.out.println(B[i]);
        }

        /** Перевірка збіжності **/
        while (!creator.Convergence(A)){
            A = creator.GetRandomMatrix(SIZE);
            B = creator.GetVectorB(A);
            System.out.println("Матриця несходима. Змінюємо матрицю");
        }

        System.out.println("Перевірка з рандомним вектором");

        /** Гаус **/
        new Gauss(A, B);
        System.out.println();

        /** Якобі **/
        new Seidel(A, B);
        System.out.println();

        /** Зейдель **/
        new Jacobi(A, B);
        System.out.println();
        System.out.println();

        /** Гільберт Гаус **/
        System.out.println("Гільберт");
        new Gauss(hilbertMatrix.GetHilbert(), C);
    }
}
