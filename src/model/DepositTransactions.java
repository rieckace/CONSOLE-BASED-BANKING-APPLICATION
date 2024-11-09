package model;

import java.sql.Date;

public class DepositTransactions extends Transaction{
	private String depositMethod;

	public DepositTransactions(int transactionId, int accountId, String transactionType, double amount,
			Date transactionDate, String deposit) {
		super(transactionId, accountId, transactionType, amount, transactionDate);
		this.depositMethod = deposit;
	}

//		Getters and setters
	public String getDepositMethod() {
		return depositMethod;
	}

	public void setDepositMethod(String depositMethod) {
		this.depositMethod = depositMethod;
	}

	@Override
	public String getTransactionDetails() {
		
		return "Deposit Transaction Method is :"+ this.depositMethod;
	}

	
	
	
	
	
	

}
