package util;

import bean.Deposit;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


public class MyJsonParser {
    public static ArrayList<Deposit> depositsArray;
    private String jsonFilePath;
    private Integer serverPort;

    public MyJsonParser(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        this.depositsArray = new ArrayList<Deposit>();
    }

    public void parseJson() throws IOException {
        JsonReader reader = new JsonReader(new FileReader(jsonFilePath));
        reader.beginObject();
        reader.nextName();
        serverPort = reader.nextInt();
        //System.out.println("serverPort : " + serverPort);
        reader.nextName();
        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            reader.nextName();
            String customer = reader.nextString();
            //System.out.println("customer : " + customer);
            reader.nextName();
            Integer depositId = reader.nextInt();
            //System.out.println("depositId : " + depositId);
            reader.nextName();
            BigDecimal initialBalance = new BigDecimal(reader.nextString());
            //System.out.println("initialBalance : " + initialBalance);
            reader.nextName();
            BigDecimal upperBound = new BigDecimal(reader.nextString());
            //System.out.println("upperBound : " + upperBound);
            reader.endObject();

            Deposit deposit = new Deposit(customer, depositId, initialBalance, upperBound);
            depositsArray.add(deposit);
            //System.out.println("object" + depositsArray.toString());
        }

        reader.endArray();
        reader.nextName();
        String serverOutLog = reader.nextString();
        System.out.println("parsing json file finished :)");
    }

    public Integer getServerPort() {
        return serverPort;
    }
}