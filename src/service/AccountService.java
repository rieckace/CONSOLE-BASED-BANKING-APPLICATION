package service; 

import java.sql.SQLException;

import dao.AccountDAO;
import dao.AccountDAOImp;
import exception.InvalidAccountTypeException;
import model.Account;
import model.SavingsAccount;

public class AccountService {
	private final AccountDAO accountDAO;
	
	public AccountService() {
		this.accountDAO = new AccountDAOImp();
	}
	
	public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {
		accountDAO.createAccount(account);
	}
	
	public void deleteAccount(int accountId) throws SQLException {
		accountDAO.deleteAccount(accountId);
		
	}

	public void updateAccount(SavingsAccount savingsAccount) throws SQLException, InvalidAccountTypeException {
		accountDAO.updateAccount(savingsAccount);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
