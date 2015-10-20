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

    public Client(int portNumber) {
        this.portNumber = portNumber;
        this.serverName = "127.0.0.1";
    }

    public void run() throws IOException {
        System.out.println("Connecting to " + serverName + " on port " + portNumber);
        Socket client = new Socket(InetAddress.getLocalHost(), portNumber);
        System.out.println("Just connected to " + client.getLocalSocketAddress());

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
