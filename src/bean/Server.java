package bean;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Server {
    private ServerSocket serverSocket;
    private int portNumber;
    private Socket server;

    public Server(int portNumber) throws IOException {
        this.portNumber = portNumber;
        serverSocket = new ServerSocket(portNumber);
    }

    public void run() throws IOException {

        System.out.println("Waiting for client on port : " + serverSocket.getLocalPort());
        server = serverSocket.accept();
        System.out.println("Just connected to a device with this IP : " + server.getInetAddress());

        DataInputStream serverInputStream = new DataInputStream(server.getInputStream());
        BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(server.getInputStream())));

        String clientMessageString = bufferedReader.readLine();
        System.out.println("Message from client : " + clientMessageString);
//
//        DataOutputStream serverOutputStream = new DataOutputStream(server.getOutputStream());
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
//        bufferedWriter.write("Hello from Server");
//        bufferedWriter.flush();
        server.close();
    }

}
