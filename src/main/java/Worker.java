import java.net.*;
import java.io.*;

public class Worker extends Thread {
    private Socket connection = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private Integer n1, n2, sum;

    public Worker(Socket s) throws Exception {
        connection = s;
        inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        outToClient = new DataOutputStream(connection.getOutputStream());

    }

    public void run() {
        String clientMessage = null;

        System.out.println(String.format("[+] Processo %d creato.", this.getId()));
        System.out.println("[+] Client connesso: " +
                connection.getInetAddress().getHostAddress() + ":" +
                connection.getPort()
        );

        try {
            clientMessage = inFromClient.readLine();

            System.out.println("[+] Numeri ricevuti: " + clientMessage);

            n1 = Integer.parseInt(clientMessage.split(" ")[0]);
            n2 = Integer.parseInt(clientMessage.split(" ")[1]);
            sum = n1 + n2;

            outToClient.writeBytes(sum.toString() + "\n");
            connection.close();

        }
        catch(Exception e) {
            e.printStackTrace();
            //System.err.println(e);
        }

    }

}
