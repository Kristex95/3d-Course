import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        final File socketFile = new File(new File(System.getProperty("java.io.tmpdir")), "junixsocket-test.sock");
        System.out.println(socketFile);

        try (AFUNIXSocket sock = AFUNIXSocket.connectTo(AFUNIXSocketAddress.of(socketFile)); InputStream is = sock.getInputStream(); OutputStream os = sock.getOutputStream()){
            byte[] buf = new byte[128];
            int val = is.read(buf);
            System.out.println(new String(buf, 0, val, "UTF-8"));
        }

    }
}
