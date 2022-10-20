package manager;

import os.lab1.compfuncs.basic.DoubleOps;
import os.lab1.compfuncs.basic.IntOps;
//import os.lab1.compfuncs.basicOLD.IntOps;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println();
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "manager.TEST").inheritIO();
        processBuilder.directory(new File("F:\\Learning\\3d-Course\\OS\\lab_1\\target\\classes"));
        processBuilder.start();

        System.out.println("Enter a parameter");
        String parameter = inputParameter();
        Optional<Double> result;
        try {
            result = DoubleOps.trialF(Integer.parseInt(parameter));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (result.isPresent())
            System.out.println("result present");
            System.out.println(result.get());

        //Manager.run(parameter);
    }

    private static String inputParameter(){
        Scanner s = new Scanner(System.in);
        return s.next();
    }
}
