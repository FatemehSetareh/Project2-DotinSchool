package bean;

import java.util.ArrayList;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Transaction {
    private String terminalId;
    private String terminalType;
    private String serverIp;
    private String serverPort;
    private String outLogPath;
    private Integer transactionId;
    private String transactionType;
    private Integer transactionAmount;
    private Integer transactionDeposit;
    private boolean requestIsValid;
    private Integer response;

    public Transaction() {
        this.terminalId = terminalId;
        this.terminalType = terminalType;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.outLogPath = outLogPath;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDeposit = transactionDeposit;
        this.requestIsValid = true;
        this.response = response;
    }

    public void checkRequestValidity(Deposit deposit) {
        if (transactionId == 1) {
            if ( deposit.getUpperBound()< transactionAmount + deposit.getInitialBalance()) {
                requestIsValid = false;
            }
        } else {
            if(deposit.getInitialBalance() - transactionAmount < 0){
                requestIsValid = false;
            }
        }
    }

    public void calculateResponse(Deposit deposit){
        if(requestIsValid){
            if (transactionId == 1){
                response = transactionAmount + deposit.getInitialBalance();
            }else {
                response = deposit.getInitialBalance() - transactionAmount;
            }
        }else{
            System.out.println("request is not valid");
            response = 0;
        }
    }

    public Integer getTransactionDeposit() {
        return transactionDeposit;
    }

}
