package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dao.TransactionDAO;
import dao.TransactionDAOImplementation;
import exception.InsufficientFundException;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.TransactionHistoryUtil;

public class TransactionService {
	private final TransactionDAO transactionDAO;
	private final ExecutorService executorService;
	
	public TransactionService() {
		this.transactionDAO = new TransactionDAOImplementation();
		this.executorService = Executors.newFixedThreadPool(5);
		
		
	}
	
	public Future<?> deposit(int accountId, double amount) throws IOException{
		
		return executorService.submit(()->{
			try {
			transactionDAO.deposit(accountId, amount);
			TransactionHistoryUtil.saveTransaction("Deposit ", accountId,amount);
		}
			catch( InvalidTransactionAmountException | SQLException | TransactionFailureException e) {
				System.err.println("Error During Deposit"+ e.getMessage());
			}
		
		
	});
	}
	
public Future<?> withdraw(int accountId, double amount) throws IOException, InvalidTransactionAmountException, SQLException{
		
		return executorService.submit(()->{
			try {
			transactionDAO.withdraw(accountId, amount);
			TransactionHistoryUtil.saveTransaction("Withdraw ", accountId,amount);
		}
			catch( TransactionFailureException | InsufficientFundException e) {
				System.err.println("Error During Withdraw"+ e.getMessage());
			}
		
		
	});
	}

public Future<?> transfer_funds(int id1, int id2 ,double amount) throws IOException, InvalidTransactionAmountException, SQLException, TransactionFailureException, InsufficientFundException{
	
	return executorService.submit(()->{
		try {
			transactionDAO.transfer_Funds(id1, id2, amount);
		} catch (InvalidTransactionAmountException e) {
			System.out.println("Insufficient Balance");
			e.printStackTrace();
		}
		TransactionHistoryUtil.saveTransaction("Transfer Funds ",id1,amount);
	
	
});
}

	public void shutDownExecutorService() {
		executorService.shutdown();
		
	}
}
