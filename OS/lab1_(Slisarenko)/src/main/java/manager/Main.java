package manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter your value: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(reader.readLine());

        /*ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", ";C:\\Users\\Kristex\\.m2\\repository\\com\\kohlschutter\\junixsocket\\junixsocket-native-common\\2.5.2\\junixsocket-native-common-2.5.2.jar;C:\\Users\\Kristex\\.m2\\repository\\com\\kohlschutter\\junixsocket\\junixsocket-common\\2.5.2\\junixsocket-common-2.5.2.jar","manager.Test").inheritIO();
        processBuilder.directory(new File("F:\\Learning\\3d-Course\\OS\\lab1_(Slisarenko)\\target\\classes"));
        processBuilder.start();*/

        Manager.start(value);
    }
}
