package bean;

import java.math.BigDecimal;
import java.util.ArrayList;


public class Deposit {
    private String customer;
    private Integer depositId;
    private BigDecimal initialBalance;
    private BigDecimal upperBound;

    public Deposit(String customer, Integer depositId, BigDecimal initialBalance, BigDecimal upperBound) {
        this.depositId = depositId;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
        this.customer = customer;

    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public Integer getDepositId() {
        return depositId;
    }

}
