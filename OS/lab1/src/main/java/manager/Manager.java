package manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager {

    private static final ArrayList<ServerThread> serverThreadsList = new ArrayList<>();
    private static final ArrayList<Double> gainedValue = new ArrayList<>();
    private static final ArrayList<Process> processesList = new ArrayList<>();
    private static ServerSocket serverSocket;
    public static AtomicBoolean cancel = new AtomicBoolean(false);
    public static AtomicBoolean gotResult = new AtomicBoolean(false);

    public static AtomicBoolean programStatus = new AtomicBoolean(true);

    public static void start(int value) throws IOException {
        serverSocket = new ServerSocket(1005, 2);
        System.out.println("Server started!");
        while(programStatus.get()) {
            processesList.clear();
            gainedValue.clear();
            gotResult.set(false);
            cancel.set(false);
            startProcess("F", value);
            startProcess("G", value);
            while (!gotResult.get()) {
                if (cancel.get()) {
                    handleHardFail();
                }
            }
            try {
                Thread.sleep(1000);
                programStatus = statusPrompt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endSession();
    }

    public static AtomicBoolean statusPrompt() throws IOException {
        boolean goodInput = false;
        int value = 0;
        while(!goodInput) {
            System.out.println("\n1 - Continue\n2 - Stop\n\nEnter your value");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            value = Integer.parseInt(reader.readLine());
            if (value > 2 || value < 1){
                System.out.println("\nWrong argument. Retry.");
            }
            else
                goodInput = true;
        }

        switch (value){
            case 1:
                return new AtomicBoolean(true);
            case 2:
                return new AtomicBoolean(false);
        }
        return new AtomicBoolean(false);
    }

    public static void handleHardFail(){
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
