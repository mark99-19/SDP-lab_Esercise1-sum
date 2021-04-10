import java.net.*;
import java.io.*;

public class MultiServer {

    public static void main(String[] args) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(true){
            Socket connection = welcomeSocket.accept();
            Worker theThread = new Worker(connection);
            theThread.start();
        }
    }

}