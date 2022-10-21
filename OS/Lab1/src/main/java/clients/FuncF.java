package clients;

import os.lab1.compfuncs.basic.IntOps;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.concurrent.*;

public class FuncF {
    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    private static final int maxAttempts = 3;

    private static int attemptsCounter = 0;

    public static void main(String[] args) throws IOException, ExecutionException {
        try {
            clientSocket = new Socket("localhost", 1005);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write("Process FuncF started\n");
            out.flush();

            String value = in.readLine();
            out.write("FuncF received: " + value + "\n");
            out.flush();

            while(attemptsCounter < maxAttempts) {
                Optional<Integer> result;

                    ExecutorService service = Executors.newCachedThreadPool();
                try{
                    Future<Optional<Integer>> future = service.submit(()-> IntOps.trialF(Integer.parseInt(value)));
                    result = future.get(10, TimeUnit.SECONDS);

                    if (result.isPresent()) {
                        out.write("Returned: " + result.get() + "\n");
                        out.flush();

                        out.write("stop"+"\n");
                        out.flush();
                        break;
                    }



                } catch (TimeoutException e) {
                    attemptsCounter++;
                    if (attemptsCounter == maxAttempts){
                        out.write("IntOps.trialF Hard fail" + "\n");
                        out.flush();
                        break;
                    }else {
                        out.write("IntOps.trialF Soft fail" + "\n");
                        out.flush();
                        Thread.sleep(2000);
                    }
                }finally {
                    service.shutdown();
                }
            }



        } catch (IOException | InterruptedException e) {
            out.write("EXCEPTION \"" + e + "\"" + "\n");
            out.flush();

            clientSocket.close();
            in.close();
            out.close();
        }
        finally {
            out.write("stop\n");
            out.flush();

            clientSocket.close();
            in.close();
            out.close();
        }
    }
}
