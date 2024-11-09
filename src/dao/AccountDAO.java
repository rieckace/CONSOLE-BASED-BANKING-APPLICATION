package dao;

import java.sql.SQLException;

import exception.InvalidAccountTypeException;
import model.Account;

public interface AccountDAO {
	void createAccount(Account account) throws SQLException, InvalidAccountTypeException;
	void updateAccount(Account account) throws SQLException, InvalidAccountTypeException;
	void deleteAccount(int accountId) throws SQLException;
}
