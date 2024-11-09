package dao;

import java.lang.System.Logger.Level;
//import java.lang.System.Logger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import exception.InsufficientFundException;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.DBConnection;

public class TransactionDAOImplementation implements TransactionDAO {

	@Override
	public synchronized void deposit(int accountId, double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException {
		if(amount <= 0) {
			throw new InvalidTransactionAmountException("Deposit Amount must be greater than zero");
		}
		
		try(Connection con = DBConnection.getConnection()){
			
			CallableStatement st = con.prepareCall("{CALL deposit(?,?)}");
			
			st.setInt(1, accountId);
			st.setDouble(2,  amount);
			st.execute();
		}catch(SQLException e) {
			throw new TransactionFailureException("Deposit Failed!!"+e.getMessage());
		}
		
	}

	@Override
	public synchronized void withdraw(int accountId, double amount) throws InsufficientFundException, TransactionFailureException {
	if(amount <= 0) {
		throw new InsufficientFundException("Insufficient Balance_Needs to deposit!!");
	}
	try(Connection con = DBConnection.getConnection()){
		
		CallableStatement st = con.prepareCall("{CALL withdraw(?,?)}");
		
		st.setInt(1, accountId);
		st.setDouble(2,  amount);
		st.execute();
	}catch(SQLException e) {
		throw new TransactionFailureException("Withdraw Failed!!"+e.getMessage());
	}
		
	}

	@Override
	public synchronized void transferFunds(int fromAccount, int toAccount, double amount) throws TransactionFailureException, InvalidTransactionAmountException {
		
		if(amount <= 0) {
			throw new TransactionFailureException("Amount must be greater than zero");
		}
		
		try(Connection con = DBConnection.getConnection()){
			
			CallableStatement st = con.prepareCall("{CALL deposit(?,?)}");
			
			st.setInt(1,fromAccount);
			st.setInt(2, toAccount);
			st.setDouble(3,amount);
			st.execute();
			
		}catch(SQLException e) {
			throw new TransactionFailureException("Deposit Failed!!"+e.getMessage());
		}

	}

	@Override
    public synchronized void transfer_Funds(int id1, int id2, double amount) throws InvalidTransactionAmountException {
        
        if(amount <= 0){
            throw new InvalidTransactionAmountException("tranfer amount must be greater than 0 "); 
        }
        
        try (Connection  con = DBConnection.getConnection()){
            CallableStatement st = con.prepareCall("{CALL transfer_funds(?,?,?)}");
            
               st.setInt(1, id1);
               st.setInt(2, id2) ; 
               st.setDouble(3, amount ) ; 
               
               st.execute();
                       
        }
        catch(SQLException e){
             try {
                 throw new TransactionFailureException("transaction failed: " + e.getMessage()) ;
             } catch (TransactionFailureException ex) {
            	 System.out.println("Transaction Failed");
//            	 Logger.getLogger(TransactionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        
    }

}
