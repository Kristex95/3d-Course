package manager;

import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager {

    private static final ArrayList<ServerThread> serverThreadsList = new ArrayList<>();
    private static final ArrayList<Double> gainedValue = new ArrayList<>();
    private static final ArrayList<Process> processesList = new ArrayList<>();
    public static AtomicBoolean cancel = new AtomicBoolean(false);
    public static AtomicBoolean gotResult = new AtomicBoolean(false);
    public static AtomicBoolean programStatus = new AtomicBoolean(true);

    public static void start(int value) throws IOException {
        final File socketFile = new File(new File(System.getProperty("java.io.tmpdir")), "junixsocket-test.sock");
        try (AFUNIXServerSocket server = AFUNIXServerSocket.newInstance()) {
            server.bind(AFUNIXSocketAddress.of(socketFile));
            System.out.println("Server started!");
            while (programStatus.get()) {
                processesList.clear();
                gainedValue.clear();
                gotResult.set(false);
                cancel.set(false);
                startProcess("F", value, server);
                startProcess("G", value, server);
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

    private static void startProcess(String functionName, int value, AFUNIXServerSocket serverSocket) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-cp", ";F:\\Learning\\3d-Course\\OS\\lab1_(Slisarenko)\\lib\\lab1.jar;C:\\Users\\Kristex\\.m2\\repository\\com\\kohlschutter\\junixsocket\\junixsocket-native-common\\2.5.2\\junixsocket-native-common-2.5.2.jar;C:\\Users\\Kristex\\.m2\\repository\\com\\kohlschutter\\junixsocket\\junixsocket-common\\2.5.2\\junixsocket-common-2.5.2.jar", "clients.Func" + functionName).inheritIO();
        processBuilder.directory(new File("F:\\Learning\\3d-Course\\OS\\lab1_(Slisarenko)\\target\\classes"));
        processesList.add(processBuilder.start());
        AFUNIXSocket socket = serverSocket.accept();
        serverThreadsList.add(new ServerThread(functionName, socket, value, gainedValue));
    }
}
