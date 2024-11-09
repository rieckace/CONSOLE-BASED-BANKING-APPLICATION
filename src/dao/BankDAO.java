package dao;
import model.Bank;
import exception.BankingException;
import java.sql.SQLException;

public interface BankDAO {
//	void getBankById();
	

	Bank getBankById(int bank_id)throws SQLException,BankingException;

}
