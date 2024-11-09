package model;

public class CurrentAccount extends Account{
	private double overdraft_Limit;
	
 public CurrentAccount(int accountId, int customerId, Bank bank, String accountType, double balance,double OverDraftLimit ) {
		super(accountId, customerId, bank, accountType, balance);
		this.overdraft_Limit = OverDraftLimit;
		
	}
// Getter and Setter
public double getOverdraft_Limit() {
	return overdraft_Limit;
}

public void setOverdraft_Limit(double overdraft_Limit) {
	this.overdraft_Limit = overdraft_Limit;
}
@Override
public String getAccountDetails() {
	
	return "Current Account with Overdraft Limit "+ this.overdraft_Limit;
}

@Override
public String toString() {
	return "Current Account Details: Account Id"+this.getAccountId() + 
			"Customer Id: "+this.getCustomerId()+
			"Bank: "+ this.getBank()+
			"Account Type: "+this.getAccountType()+
			"Balance: "+this.getBalance()+
			"Overdraft_Limit= " + this.getOverdraft_Limit() ;
}


 

}
