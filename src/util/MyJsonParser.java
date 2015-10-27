package util;

import bean.Deposit;
import main.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


//**************** parse the json file and update the port number
public class MyJsonParser {
    public static ArrayList<Deposit> depositsArray;
    private String jsonFilePath;

    public MyJsonParser(String jsonFilePath) {
        this.depositsArray = new ArrayList<Deposit>();
        this.jsonFilePath = jsonFilePath;
    }

    public void parseJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonFilePath));
        Integer serverPort = (Integer) jsonObject.get("port");
        System.out.println("\n serverPort" + serverPort);
        JSONArray deposits = (JSONArray) jsonObject.get("deposits");
        Iterator i = deposits.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Integer customer = (Integer) innerObj.get("customer");
            Integer depositId = (Integer) innerObj.get("id");
            Integer initialBalance = (Integer) innerObj.get("initialBalance");
            Integer upperBound = (Integer) innerObj.get("upperBound");
            System.out.println("\n customer" + customer
                    + "\n depositId" + depositId
                    + "\n initialBalance" + initialBalance
                    + "\n upperBound" + upperBound);

            Deposit deposit = new Deposit();
            depositsArray.add(deposit);
        }

        String serverOutLog = (String) jsonObject.get("outLog");
        System.out.println("serverOutLog" + serverOutLog);
    }

//    public void updateJson() {
//        jsonObject.put("port", Main.portNumber.toString());
//    }

}


