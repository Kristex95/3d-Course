package manager;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private final BufferedReader in;
    private final BufferedWriter out;
    private final ArrayList<Double> gainedValue;
    private final String funcName;

    ServerThread(String funcName, Socket socket, int value, ArrayList<Double> gainedValue) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.gainedValue = gainedValue;
        this.funcName = funcName;
        send(String.valueOf(value));
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        String message = "";
        boolean exitProcessBool = false;
        try {
            while (!exitProcessBool) {

                message = in.readLine();
                exitProcessBool = handleMessage(message);
                System.out.println("Server Thread-" + getId() + " message: " + message + ";");

            }

            if (gainedValue.size() == 2){
                System.out.println("Final result: " + gainedValue.get(0) + " * " +  gainedValue.get(1) + " = " + gainedValue.get(0) * gainedValue.get(1));
                Manager.gotResult.set(true);
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        System.out.println("Server Thread-" + getId() + " message: server stopped;");
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    private boolean handleMessage(String message){
        if (message != null || message != ""){
            if (message.contains("EXCEPTION")){
                Manager.cancel.set(true);
                return true;
            }
            if (message.contains("stop")){
                return true;
            }
            if (message.contains("Returned")){
                synchronized (gainedValue){
                    Double val = Double.parseDouble(message.substring("Returned: ".length()));
                    gainedValue.add(val);
                    System.out.println(val);
                }
            }
        }
        return false;
    }
}
