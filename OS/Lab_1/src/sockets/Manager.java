package sockets;

import java.io.IOException;

public class Manager {
    public static void main(String[] args) throws IOException {
        Process server = Runtime.getRuntime().exec("java -cp src\\sockets\\Server");
        Process client = Runtime.getRuntime().exec("java -cp src\\sockets\\Client");
    }
}
