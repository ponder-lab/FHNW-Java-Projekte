package le;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
        	System.out.println("Waiting for connection...");
            final Socket s = serverSocket.accept();
            System.out.println("Client connected: " + s.getInetAddress());
            Thread t = new Thread() {
                public void run() {
                    handleRequest(s);
                }
            };
            t.start();
        }
    }

    static void handleRequest(Socket s) {
    	System.err.println("Handling request: " + Thread.currentThread().getName());
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
    	System.err.println("Finished request processing.");
    }
}
