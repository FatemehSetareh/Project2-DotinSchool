package util;

import bean.Deposit;
import bean.Transaction;

import java.util.ArrayList;


/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Process {
    private Transaction transaction;
    public ArrayList<Transaction> responses;

    public Process(Transaction transaction) {
        this.transaction = transaction;
        responses = new ArrayList<Transaction>();
    }

    public void run() {
        Deposit deposit = Deposit.search(transaction, MyJsonParser.depositsArray);
        transaction.checkRequestValidity(deposit);
        transaction.calculateResponse(deposit);
        responses.add(transaction);

    }

}
