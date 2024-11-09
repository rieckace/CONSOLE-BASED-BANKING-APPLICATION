package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import exception.BankingException;
import exception.InsufficientFundException;
import exception.InvalidAccountTypeException;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;

public class Main {
public static void main(String[] args) throws IOException, SQLException, InvalidAccountTypeException, BankingException, NumberFormatException, InterruptedException, ExecutionException, InvalidTransactionAmountException, TransactionFailureException, InsufficientFundException {
	BankController bc = new BankController();
	bc.start();
	
}
}
