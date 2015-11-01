package util;

import bean.Deposit;
import bean.Transaction;


public class Processor {
    private Transaction transaction;

    public Processor(Transaction transaction) {
        this.transaction = transaction;
    }

    public void process() {
        Deposit deposit = Deposit.search(transaction, MyJsonParser.depositsArray);
        transaction.validateRequest(deposit);
        transaction.calculateResponse(deposit);

    }

}
