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

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                System.out.println("Waiting for client on port : " + serverSocket.getLocalPort());
                try {
                    server = serverSocket.accept();
                    System.out.println("Just connected to a device with this IP : " + server.getInetAddress());

                    DataInputStream serverInputStream = new DataInputStream(server.getInputStream());
                    BufferedReader bufferedReader = null;
                    bufferedReader = new BufferedReader((new InputStreamReader(server.getInputStream())));
                    String clientMessageString = null;
                    clientMessageString = bufferedReader.readLine();
                    System.out.println("Message from client : " + clientMessageString);

                } catch (IOException e) {
                    e.printStackTrace();
                }
//                try {
//                    DataOutputStream serverOutputStream = new DataOutputStream(server.getOutputStream());
//                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
//                    bufferedWriter.write("Hello from Server");
//                    bufferedWriter.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}

