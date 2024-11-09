package model;

import java.sql.Date;

public class WithdrawTransaction extends Transaction {
	
	private String withdrawalMethod;
	
	public WithdrawTransaction(int transactionId, int accountId, String transactionType, double amount,
			Date transactionDate,String Withdraw) {
		super(transactionId, accountId, transactionType, amount, transactionDate);
		this.withdrawalMethod = Withdraw;
	}

	public String getWithdrawalMethod() {
		return withdrawalMethod;
	}

	public void setWithdrawalMethod(String withdrawalMethod) {
		this.withdrawalMethod = withdrawalMethod;
	}

	@Override
	public
	String getTransactionDetails() {
		
		return "Withdraw Transaction Method is :"+this.withdrawalMethod;
	}

	
	
	

}
