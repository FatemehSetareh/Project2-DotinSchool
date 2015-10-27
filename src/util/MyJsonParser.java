package util;

import bean.Deposit;
import main.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by ${Dotin} on ${4/25/2015}.
 */


//**************** parse the json file and update the port number
public class MyJsonParser {
    private JSONParser jsonParser;
    private JSONObject jsonObject;
    private Integer serverPort;
    private Integer customer;
    private Integer depositId;
    private Integer initialBalance;
    private Integer upperBound;
    public static ArrayList<Deposit> depositsArray;

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
        serverPort = (Integer) jsonObject.get("port");
        System.out.println("\n serverPort" + serverPort);
        JSONArray deposits = (JSONArray) jsonObject.get("deposits");
        Iterator i = deposits.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            customer = (Integer) innerObj.get("customer");
            depositId = (Integer) innerObj.get("id");
            initialBalance = (Integer) innerObj.get("initialBalance");
            upperBound = (Integer) innerObj.get("upperBound");
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

    public void updateJson() {
        jsonObject.put("port", Main.portNumber.toString());
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getDepositId() {
        return depositId;
    }

    public void setDepositId(Integer depositId) {
        this.depositId = depositId;
    }

    public Integer getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Integer initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Integer getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Integer upperBound) {
        this.upperBound = upperBound;
    }

    public ArrayList<Deposit> get_deposits() {
        return depositsArray;
    }

    public void set_deposits(ArrayList<Deposit> _deposits) {
        this.depositsArray = _deposits;
    }
}


