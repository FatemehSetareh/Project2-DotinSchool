package bean;

import java.util.ArrayList;


public class Deposit {
    private String customer;
    private Integer depositId;
    private Integer initialBalance;
    private Integer upperBound;
    private boolean lock;

    public Deposit(String customer, Integer depositId, Integer initialBalance, Integer upperBound, boolean lock) {
        this.depositId = depositId;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
        this.customer = customer;
        this.lock = lock;

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

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
