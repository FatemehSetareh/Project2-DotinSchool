package bean;

import java.io.Serializable;

public class Transaction implements Serializable {
    private Integer transactionId;
    private String transactionType;
    private Integer transactionAmount;
    private Integer transactionDeposit;
    private boolean requestIsValid;


    public Transaction(Integer transactionId, String transactionType, Integer transactionAmount, Integer transactionDeposit) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDeposit = transactionDeposit;

    }

    public void validateRequest(Deposit deposit) {
        requestIsValid = true;
        if (transactionType.equals("deposit")) {
            if (deposit.getUpperBound() < getTransactionAmount() + deposit.getInitialBalance()) {
                requestIsValid = false;
            }
        } else {
            if (deposit.getInitialBalance() - getTransactionAmount() < 0) {
                requestIsValid = false;
            }
        }
        System.out.println("Validity : " + requestIsValid);
    }

    public void calculateResponse(Deposit deposit) {
        if (requestIsValid) {
            if (transactionType.equals("deposit")) {
                deposit.setInitialBalance(transactionAmount + deposit.getInitialBalance());
            } else {
                deposit.setInitialBalance(deposit.getInitialBalance() - transactionAmount);
            }
        } else {
            System.out.println("request is not valid");
        }
        System.out.println("InitialBalance is after executed request : " + deposit.getInitialBalance());
    }

    public String transactionToString() {
        return transactionId + "," + transactionType + "," + transactionAmount + "," + transactionDeposit + ",";
    }


    public Integer getTransactionDeposit() {
        return transactionDeposit;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }
}
