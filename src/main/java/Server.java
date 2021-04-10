import com.sun.java.accessibility.util.AccessibilityListenerList;

import java.io.*;
import java.net.*;


public class Server {

    public static void main(String[] args) throws Exception {

        int port;
        Integer n1, n2, sum;

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        //get input from user
        System.out.println("Inserisci una porta: ");
        port = Integer.parseInt(userInput.readLine());

        ServerSocket clientSocket = new ServerSocket(port);

        while (true){

            Socket connection = clientSocket.accept();

            /* Inizializza lo stream di output verso la socket */
            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());

            /* Inizializza lo stream di input dalla socket */
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            System.out.println("[+] Client connesso: " +
                            connection.getInetAddress().getHostAddress() + ":" +
                            connection.getPort()
                    );

            String clientMessage = inFromClient.readLine();

            System.out.println("Numeri ricevuto: " + clientMessage);

            n1 = Integer.parseInt(clientMessage.split(" ")[0]);
            n2 = Integer.parseInt(clientMessage.split(" ")[1]);
            sum = n1 + n2;

            outToClient.writeBytes(sum.toString() + "\n");

            connection.close();

        }

    }
}
