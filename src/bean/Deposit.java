package bean;

import java.util.ArrayList;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Deposit {
    private Integer customer;
    private Integer depositId;
    private Integer initialBalance;
    private Integer upperBound;

    public static Deposit search(Transaction transaction, ArrayList<Deposit> deposits) {
        for (Deposit deposit : deposits) {
            if (transaction.getTransactionDeposit().equals(deposit.depositId)) {
                return deposit;
            }
        }
        return null;
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

}
