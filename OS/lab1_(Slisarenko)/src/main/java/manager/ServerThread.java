package manager;

import org.newsclub.net.unix.AFUNIXSocket;

import java.io.*;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private final InputStream in;
    private final OutputStream out;
    private final ArrayList<Double> gainedValue;
    private final String funcName;

    ServerThread(String funcName, AFUNIXSocket sock, int value, ArrayList<Double> gainedValue) throws IOException {
        final File socketFile = new File(new File(System.getProperty("java.io.tmpdir")), "junixsocket-test.sock");

        in = sock.getInputStream();
        out = sock.getOutputStream();

        this.gainedValue = gainedValue;
        this.funcName = funcName;
        send(value);
        setDaemon(true);
        start();

    }

    @Override
    public void run() {
        String message = "";
        byte[] buf = new byte[128];
        boolean exitProcessBool = false;
        try {
            while (!exitProcessBool) {

                message = new String(buf, 0, in.read(buf), "UTF-8");
                exitProcessBool = handleMessage(message);
                System.out.println("Server Thread-" + getId() + " message: " + message + ";");

            }

            if (gainedValue.size() == 2) {
                System.out.println("Final result: " + gainedValue.get(0) + " * " + gainedValue.get(1) + " = " + gainedValue.get(0) * gainedValue.get(1));
                Manager.gotResult.set(true);
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        System.out.println("Server Thread-" + getId() + " message: server stopped;");
    }

    private void send(int msg) {
        try {
            out.write(msg);
            out.flush();
        } catch (IOException ignored) {
        }
    }

    private boolean handleMessage(String message) {
        if (message != null || message != "") {
            if (message.contains("EXCEPTION")) {
                Manager.cancel.set(true);
                return true;
            }
            if (message.contains("stop")) {
                return true;
            }
            if (message.contains("Returned")) {
                synchronized (gainedValue) {
                    Double val = Double.parseDouble(message.substring("Returned: ".length()));
                    gainedValue.add(val);
                    System.out.println(val);
                }
            }
        }
        return false;
    }
}
