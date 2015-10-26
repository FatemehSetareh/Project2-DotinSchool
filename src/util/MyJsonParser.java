package util;

import main.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class MyJsonParser {

    private JSONParser jsonParser;
    private JSONObject jsonObject;

    public MyJsonParser(String JsonFilePath) {
        this.jsonParser = new JSONParser();
        try {
            this.jsonObject = (JSONObject) jsonParser.parse(new FileReader(JsonFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    public void parseJson() {
        Long serverPort = (Long) jsonObject.get("port");
        System.out.println("\n serverPort" + serverPort);
        JSONArray deposits = (JSONArray) jsonObject.get("deposits");
        Iterator i = deposits.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            String customer = (String) innerObj.get("customer");
            String depositId = (String) innerObj.get("id");
            String initialBalance = (String) innerObj.get("initialBalance");
            String upperBound = (String) innerObj.get("upperBound");
            System.out.println("\n customer" + customer
                    + "\n depositId" + depositId
                    + "\n initialBalance" + initialBalance
                    + "\n upperBound" + upperBound);
        }

        String serverOutLog = (String) jsonObject.get("outLog");
        System.out.println("serverOutLog" + serverOutLog);
    }

    public void updateJson() {
        jsonObject.put("port", Main.portNumber.toString());
    }
}


