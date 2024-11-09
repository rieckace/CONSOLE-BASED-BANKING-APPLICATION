package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHistoryUtil {
	private static final String FILE_PATH = "Transaction_History.txt";

private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

//Method to save the transactions Details

public static void saveTransaction(String transactionType, int accountId, double amount) {
	
	String timeStamp = dateFormat.format(new Date());
	String record = String.format("%s | %s | Account ID:%d | Amount: %.2f", timeStamp,transactionType, accountId, amount);
	
	try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,true))){
		writer.write(record);
		writer.newLine();
		
	}catch(IOException e) {
		System.err.println("Error occurred writing to transaction history file. ");
	}
	
}
	
//	Method to retrieve and display transaction history
	
     final List<String> retriveTransactionHistory (){
        
        List<String> history = new ArrayList<>(); 
        
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            
            String line ; 
            while((line = reader.readLine()) != null){
                history.add(line) ;
            }
        }
        catch(IOException e){
            System.err.println("Error reading transaction history file");
        }
        
        
        return history ;
    }

    
		
	}





