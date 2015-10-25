package bean;

import main.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.MyJsonParser;

import java.io.*;
import java.net.*;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    private Socket server;
    private RandomAccessFile serverLog;

    public Server(int portNumber) throws IOException, ParseException {
        serverSocket = new ServerSocket(portNumber);
        serverLog = new RandomAccessFile(new File("serverLog.xml"), "rw");
    }

    @Override
    public void run() {
       // MyJsonParser myJsonParser = new MyJsonParser("core.json");
       // myJsonParser.parseJson();
      //  myJsonParser.updateJson();
        try {
            while (true) {

                System.out.println("Server : Waiting for client on port : " + serverSocket.getLocalPort());
                server = serverSocket.accept();
                System.out.println("Server : Got connection from : " + server.getInetAddress());
                Client client = new Client(InetAddress.getLocalHost().getHostName(),Main.portNumber, server);
                client.start();

                BufferedReader serverReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter serverWriter = new PrintWriter(server.getOutputStream());
                serverWriter.println("Server : Welcome to my server");
                serverWriter.flush();

                String message = serverReader.readLine();
                while (!(message == null || message.equalsIgnoreCase("exit"))) {
                    System.out.println("Server : MessageReceived: " + message);
                    serverWriter.println(message);
                    serverWriter.flush();
                    message = serverReader.readLine();
                }
                server.close();
            }

        } catch (SocketTimeoutException e) {
            System.out.println("Server : Connection timed out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

