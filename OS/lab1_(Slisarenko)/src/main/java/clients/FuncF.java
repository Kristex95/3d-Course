package clients;

import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import os.lab1.compfuncs.basic.IntOps;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.*;

public class FuncF {
    private static final int maxAttempts = 3;
    private static int attemptsCounter = 0;

    public static void main(String[] args) throws IOException {
        InputStream in;
        OutputStream out = null;
        final File socketFile = new File(new File(System.getProperty("java.io.tmpdir")), "junixsocket-test.sock");
        try (AFUNIXSocket sock = AFUNIXSocket.connectTo(AFUNIXSocketAddress.of(socketFile))) {
            in = sock.getInputStream();
            out = sock.getOutputStream();

            out.write("Process FuncF started".getBytes(StandardCharsets.UTF_8));
            out.flush();

            String value = String.valueOf(in.read());
            out.write(("FuncF received: " + value).getBytes(StandardCharsets.UTF_8));
            out.flush();

            while (attemptsCounter < maxAttempts) {
                Optional<Integer> result;

                ExecutorService service = Executors.newCachedThreadPool();
                try {
                    result = IntOps.trialF(Integer.parseInt(value));

                    if (result.isPresent()) {
                        out.write(("Returned: " + result.get()).getBytes(StandardCharsets.UTF_8));
                        out.flush();

                        out.write(("stop").getBytes(StandardCharsets.UTF_8));
                        out.flush();
                        break;
                    }else {
                        attemptsCounter++;
                        if (attemptsCounter == maxAttempts) {
                            out.write(("EXCEPTION IntOps.trialF Hard fail").getBytes(StandardCharsets.UTF_8));
                            out.flush();
                            break;
                        } else {
                            out.write("IntOps.trialF Soft fail".getBytes(StandardCharsets.UTF_8));
                            out.flush();
                            Thread.sleep(2000);
                        }
                    }

                } finally {
                    service.shutdown();
                }
            }


        } catch (IOException | InterruptedException e) {
            out.write(("EXCEPTION \"" + e + "\"").getBytes(StandardCharsets.UTF_8));
            out.flush();

            out.close();
        }
    }


}
