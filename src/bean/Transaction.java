package bean;

import java.util.ArrayList;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Transaction {
    private Integer transactionId;
    private String transactionType;
    private Integer transactionAmount;
    private Integer transactionDeposit;
    private boolean requestIsValid;
    private Integer response;


    public void validateRequest(Deposit deposit) {
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
            if (transactionType.equals("deposit")){
                //response = transactionAmount + deposit.getInitialBalance();
                deposit.setInitialBalance(transactionAmount + deposit.getInitialBalance());
            }else {
                //response = deposit.getInitialBalance() - transactionAmount;
                deposit.setInitialBalance(deposit.getInitialBalance() - transactionAmount);
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
