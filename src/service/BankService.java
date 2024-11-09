package service;

import java.sql.SQLException;

import dao.AccountDAO;
import dao.AccountDAOImp;
import dao.BankDAO;
import dao.BankDAOImplementation;
import exception.BankingException;
import model.Bank;

public class BankService {
	private final BankDAO bankDAO;
	private final AccountDAO accountDAO;
	
	public BankService() {
		this.bankDAO = new BankDAOImplementation();
		this.accountDAO = new AccountDAOImp();
		
		
	}
	
	public Bank getBankById(int bank_id) throws SQLException, BankingException{
		return bankDAO.getBankById(bank_id);
	}
	

}
