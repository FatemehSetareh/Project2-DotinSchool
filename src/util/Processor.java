package util;

import bean.Deposit;
import bean.Transaction;

import java.util.ArrayList;


/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Processor {
    private Transaction transaction;
    public ArrayList<Transaction> responses;

    public Processor(Transaction transaction) {
        this.transaction = transaction;
        responses = new ArrayList<Transaction>();
    }

    public void process() {
        Deposit deposit = Deposit.search(transaction, MyJsonParser.depositsArray);
        transaction.validateRequest(deposit);
        transaction.calculateResponse(deposit);
        responses.add(transaction);

    }

}
