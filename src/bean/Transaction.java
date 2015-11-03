package bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transaction implements Serializable {
    private Integer transactionId;
    private String transactionType;
    private BigDecimal transactionAmount;
    private Integer transactionDeposit;
    private boolean requestIsValid;
    private String result;


    public Transaction(Integer transactionId, String transactionType, BigDecimal transactionAmount, Integer transactionDeposit) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDeposit = transactionDeposit;

    }

    public void validateRequest(Deposit deposit) {
        requestIsValid = true;
        if (transactionType.equals("deposit")) {
            if (getTransactionAmount().add(deposit.getInitialBalance()).compareTo(deposit.getUpperBound()) == 1) {
                requestIsValid = false;
            }
        } else if(transactionType.equals("withdraw")){
            if (deposit.getInitialBalance().subtract(getTransactionAmount()).compareTo(BigDecimal.ZERO) == -1) {
                requestIsValid = false;
            }
        }else{
            requestIsValid = false;
            System.out.println("This type of transaction is undefined");
        }
        System.out.println("Validity : " + requestIsValid);
    }

    public void calculateResponse(Deposit deposit) {
        if (requestIsValid) {
            if (transactionType.equals("deposit")) {
                deposit.setInitialBalance(transactionAmount.add(deposit.getInitialBalance()));
                setResult("Success");
            } else {
                deposit.setInitialBalance(deposit.getInitialBalance().subtract(transactionAmount));
                setResult("Success");
            }
        } else {
            System.out.println("request is not valid");
            setResult("Failure");
        }
        System.out.printf("InitialBalance is %s after executing request\n", deposit.getInitialBalance());
    }

    public String transactionToString() {
        return transactionId + "," + transactionType + "," + transactionAmount + "," + transactionDeposit + ",";
    }


    public Integer getTransactionDeposit() {
        return transactionDeposit;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
