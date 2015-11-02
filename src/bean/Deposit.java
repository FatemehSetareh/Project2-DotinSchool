package bean;

import java.util.ArrayList;


public class Deposit {
    private String customer;
    private Integer depositId;
    private Integer initialBalance;
    private Integer upperBound;

    public Deposit(String customer, Integer depositId, Integer initialBalance, Integer upperBound) {
        this.depositId = depositId;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
        this.customer = customer;

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

    public Integer getDepositId() {
        return depositId;
    }

}
