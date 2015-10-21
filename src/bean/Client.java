package bean;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Client extends Thread {
    private Socket client;
    private int portNumber;
    private File clientInformation;
    private  RandomAccessFile clientLog;

    public Client(String serverName, int portNumber) throws FileNotFoundException {
        this.portNumber = portNumber;
        clientInformation = new File("clientInformation.xml");
        clientLog = new RandomAccessFile(new File("clientLog.xml"),"rw");
    }

    @Override
    public void run() {
        try {
            System.out.println("Client : Connecting to server ...");
            Socket client = new Socket(InetAddress.getLocalHost(), portNumber);
            System.out.println("Client : Just connected to server " + client.getLocalSocketAddress());

            PrintWriter clientWriter = new PrintWriter(client.getOutputStream());
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(clientReader.readLine());
            BufferedReader clientConsulReader = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Client : Enter message to echo or Exit to end: ");
                message = clientConsulReader.readLine();
                if (message == null || message.equalsIgnoreCase("exit"))
                    break;
                clientWriter.println(message);
                clientWriter.flush();
                System.out.println("Client : Echo from server: " + clientReader.readLine());
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}