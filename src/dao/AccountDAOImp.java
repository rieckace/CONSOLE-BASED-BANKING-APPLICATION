package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import exception.InvalidAccountTypeException;
import model.Account;
import utility.DBConnection;

public class AccountDAOImp implements AccountDAO {

@Override
	public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {
		String sql = "Insert into Account(Customer_ID,Bank_ID,ACCOUNT_TYPE,Balance) VALUES(?,?,?,?)";		
try (
	Connection con = DBConnection.getConnection();
	PreparedStatement ps = con.prepareStatement(sql);
		){
	ps.setInt(1,account.getCustomerId());
	ps.setInt(2, account.getBank().getBankId());
	ps.setString(3,account.getAccountType());
	ps.setDouble(4, account.getBalance());
	
	
	int result = ps.executeUpdate();
		if(result == 0) {
			throw new InvalidAccountTypeException("Account type not recognized.");
		}
	}
}

//Update Account
	@Override
	public void updateAccount(Account account) throws SQLException, InvalidAccountTypeException {
    String sql = "UPDATE Account SET Customer_ID = ?, Bank_ID = ?, ACCOUNT_TYPE = ?, Balance = ? WHERE Account_ID = ?";
    
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, account.getCustomerId());
        ps.setInt(2, account.getBank().getBankId());
        ps.setString(3, account.getAccountType());
        ps.setDouble(4, account.getBalance());
        ps.setInt(5, account.getAccountId());

        int result = ps.executeUpdate();
        if (result == 0) {
            throw new InvalidAccountTypeException("Account not found or type not recognized.");
        }
    }
}
	
//	Delete Account
    @Override
    public void deleteAccount(int accountId) throws SQLException {
        String sql = "DELETE FROM Account WHERE Account_ID = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, accountId);
            ps.executeUpdate();
        }
    }
}

