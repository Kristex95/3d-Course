package manager;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager {

    private static final ArrayList<ServerThread> serverThreadsList = new ArrayList<>();
    private static final ArrayList<Double> gainedValue = new ArrayList<>();
    private static final ArrayList<Process> processesList = new ArrayList<>();
    private static ServerSocket serverSocket;
    public static AtomicBoolean gotResult = new AtomicBoolean(false);

    public static void start(int value) throws IOException {
        serverSocket = new ServerSocket(1005, 2);
        System.out.println("Server started!");

        startProcess("F", value);
        startProcess("G", value);
        while(!gotResult.get()){

        }

        endSession();
    }


    private static void endSession(){
        System.out.println("Server closed!");
        for (Process p : processesList)
            p.destroy();
        System.exit(0);
    }

    private static void startProcess(String functionName, int value) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", ";F:\\Learning\\3d-Course\\OS\\lab1\\lib\\lab1.jar", "clients.Func" + functionName).inheritIO();
        processBuilder.directory(new File("F:\\Learning\\3d-Course\\OS\\lab1\\target\\classes"));
        processesList.add(processBuilder.start());

        Socket socket = serverSocket.accept();
        serverThreadsList.add(new ServerThread(functionName, socket, value, gainedValue));
    }
}
