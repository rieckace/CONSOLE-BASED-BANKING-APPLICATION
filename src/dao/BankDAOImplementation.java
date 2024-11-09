package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exception.BankingException;
import model.Bank;
import utility.DBConnection;

public class BankDAOImplementation implements BankDAO{

	@Override
	public Bank getBankById(int bank_id) throws SQLException, BankingException {
		
		String sql = "Select * from Bank where Bank_id = ?";

		try (
			Connection con = DBConnection.getConnection();
			PreparedStatement ps =  con.prepareStatement(sql);
		)
		{
			ps.setInt(1,bank_id);
			ResultSet rs = ps.executeQuery();

//			ResultSet result = ps.executeQuery();
				if(rs.next()) {
					return new Bank(bank_id,rs.getString("Bank_name"),rs.getString("bank_branch"));
				}
				throw new BankingException("Bank not found for ID: "+bank_id);
			}	
	}

}
