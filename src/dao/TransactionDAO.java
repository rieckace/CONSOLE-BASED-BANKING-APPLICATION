package dao;

import java.sql.SQLException;

import exception.InsufficientFundException;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;

public interface TransactionDAO {
	void deposit(int accountId, double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException;
	void withdraw(int accountId, double amount) throws InsufficientFundException, TransactionFailureException;
	void transferFunds(int fromAccount, int toAccount, double amount) throws TransactionFailureException, InvalidTransactionAmountException;
//	void transfer_funds(int id1, int id2, double amount);
	void transfer_Funds(int id2, int id1, double amount) throws InvalidTransactionAmountException;

}
