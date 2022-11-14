import org.newsclub.net.unix.*;

import java.io.*;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        final File socketFile = new File(new File(System.getProperty("java.io.tmpdir")), "junixsocket-test.sock");
        System.out.println(socketFile);
        try (AFUNIXServerSocket server = AFUNIXServerSocket.newInstance()) {
            server.bind(AFUNIXSocketAddress.of(socketFile));
            System.out.println("server: " + server);

            try (AFUNIXSocket sock = server.accept(); InputStream is = sock.getInputStream(); OutputStream os = sock.getOutputStream()){
                os.write("Hello Server".getBytes("UTF-8"));
                os.flush();
            }
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
