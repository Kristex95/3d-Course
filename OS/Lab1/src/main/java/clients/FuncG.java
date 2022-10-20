package clients;

import os.lab1.compfuncs.basic.DoubleOps;
import os.lab1.compfuncs.basic.IntOps;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

public class FuncG {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {
        try {
            clientSocket = new Socket("localhost", 1005);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write("Process FuncG started\n");
            out.flush();

            String value = in.readLine();
            out.write("FuncG received: " + value + "\n");
            out.flush();

            Optional<Double> result = DoubleOps.trialG(Integer.parseInt(value));
            if(result.isPresent()) {
                out.write("Returned: " + result.get() + "\n");
                out.flush();
            } else {
                out.write("IntOps.trialG failed" + "\n");
                out.flush();
            }

            out.write("stop\n");
            out.flush();
        } catch (IOException | InterruptedException e) {
            out.write("EXCEPTION \"" + e + "\"" + "\n");
            out.flush();

            clientSocket.close();
            in.close();
            out.close();
        }
    }
}
