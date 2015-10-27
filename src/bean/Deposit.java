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

    public Deposit() {
        this.customer = customer;
        this.depositId = depositId;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }

    public static Deposit search(Transaction transaction, ArrayList<Deposit> deposits) {
        for (int i = 0; i < deposits.size(); i++) {
            if (transaction.getTransactionDeposit() == deposits.get(i).depositId) {
                return deposits.get(i);
            }
        }
        return null;
    }

    public Integer getInitialBalance() {
        return initialBalance;
    }

    public Integer getUpperBound() {
        return upperBound;
    }

}
