package model;

public class SavingsAccount extends Account {
	private double interestRate;
	
	public SavingsAccount(int accountId, int customerId, Bank bank, String accountType, double balance,double interestRate) {
		super(accountId, customerId, bank, accountType, balance);
		this.interestRate = interestRate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String getAccountDetails() {
		
		return "Savings class interest rate "+ this.interestRate;
	}

	@Override
	public String toString() {
		return "Saving Account Details: Account Id"+this.getAccountId() + 
				"Customer Id: "+this.getCustomerId()+
				"Bank: "+ this.getBank()+
				"Account Type: "+this.getAccountType()+
				"Balance: "+this.getBalance()+
				"Interest_Rate= " + this.getInterestRate() ;
	}
	
	

	

}
