package bean;

import java.io.Serializable;

public class Transaction implements Serializable,Cloneable{
    private Integer transactionId;
    private String transactionType;
    private Integer transactionAmount;
    private Integer transactionDeposit;
    private boolean requestIsValid;
    private Integer response;

    public Transaction(Integer transactionId, String transactionType, Integer transactionAmount, Integer transactionDeposit) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDeposit = transactionDeposit;
    }

    public void validateRequest(Deposit deposit) {
        if (transactionType.equals("deposit")) {
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

    public String transactionToString(){
        return transactionId + "," + transactionType + "," + transactionAmount + "," + transactionDeposit + ",";
    }

}
