package bean;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Client {
    Socket client;
    int portNumber;
    String serverName;

    public Client(String serverName, int portNumber) {
        this.portNumber = portNumber;
        this.serverName = serverName;
    }

    public void run() throws IOException {
        System.out.println("Connecting to server ...");
        Socket client = new Socket(InetAddress.getLocalHost(), portNumber);
        System.out.println("Just connected to server " + client.getLocalSocketAddress());

//        DataInputStream clientInputStream = new DataInputStream(client.getInputStream());
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//        String serverMessageString = bufferedReader.readLine();
//        System.out.println("Message from server is : " + serverMessageString);

        DataOutputStream clientOutputStream = new DataOutputStream(client.getOutputStream());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        bufferedWriter.write("Hello from client");
        bufferedWriter.flush();
        bufferedWriter.close();

        client.close();
    }


}
