package model;

public class Bank {
private int bank_id;
private String bankName;
private String bankBranch;


//Constructors
public Bank(int b, String bN, String bB) {
	super();
	this.bank_id = b;
	this.bankName = bN;
	this.bankBranch = bB;
}

//Getters and Setters
public int getBankId() {
	return bank_id;
}
public void setBankId(int bankid) {
	this.bank_id = bankid;
}

public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}

public String getBankBranch() {
	return bankBranch;
}
public void setBank(String bank) {
	this.bankBranch = bank;
}






}
