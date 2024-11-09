package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import exception.BankingException;
import exception.InsufficientFundException;
import exception.InvalidAccountTypeException;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import model.Bank;
import model.CurrentAccount;
import model.SavingsAccount;
import service.AccountService;
import service.BankService;
import service.TransactionService;

public class BankController {
	private final AccountService accountService;
	private final BufferedReader br;
	private final BankService bankService;
	private final TransactionService transactionService;

	public BankController() {
		this.accountService = new AccountService();
		this.br = new BufferedReader(new InputStreamReader(System.in));
		this.bankService = new BankService();
		this.transactionService = new TransactionService();
	}
	
	public void displayMenu() {
		System.out.println("-------------Welcome to Global IME Bank----------------");
//		System.out.println("Choose the operation: ");
		System.out.println("1.Create Account");
		System.out.println("2.Update Account");
		System.out.println("3.Delete Account");
		System.out.println("4.Deposit Balance");
		System.out.println("5.Withdraw Balance");
		System.out.println("6.Transfer Balance");
		System.out.println("0.Exit");
		System.out.println("Choose the operation: ");	
	}
	
	
	
	public void createAccount() throws IOException, SQLException, InvalidAccountTypeException, BankingException {
		System.out.println("Enter Customer Id: ");
		int custID = Integer.parseInt(br.readLine());
		System.out.println("Enter Bank Id: ");
		int bankID = Integer.parseInt(br.readLine());
		
		Bank bank =bankService.getBankById(bankID);
		
		System.out.println("Enter Account Type(Savings/Current): ");
		String accountType = br.readLine();
		
		System.out.println("Enter Initial Balance: ");
		double bal = Double.parseDouble(br.readLine());
		
		if("Savings".equalsIgnoreCase(accountType)) {
			System.out.println("Enter Interest Rate: ");
			double interest = Double.parseDouble(br.readLine());
			accountService.createAccount(new SavingsAccount(0,custID,bank,accountType,bal,interest));
		}
		
		else if("Current".equalsIgnoreCase(accountType)) {
			System.out.println("Enter OverDraft Limit: ");
			double overdraft = Double.parseDouble(br.readLine());
			accountService.createAccount(new CurrentAccount(0,custID,bank,accountType,bal,overdraft));
			
		}
		else {
			System.out.println("Invalid Account Type");
		}
	}
	
	
//	Update account method 
//	   private void updateAccount() throws IOException, SQLException, InvalidAccountTypeException, BankingException {
//	        System.out.println("Enter Account ID to update: ");
//	        int accountId = Integer.parseInt(br.readLine());
//
//	        System.out.println("Enter new Balance: ");
//	        double newBalance = Double.parseDouble(br.readLine());
//
//	        System.out.println("Enter new Account Type (Savings/Current): ");
//	        String newAccountType = br.readLine();
//
//	        if ("Savings".equalsIgnoreCase(newAccountType)) {
//	            System.out.println("Enter new Interest Rate: ");
//	            double newInterestRate = Double.parseDouble(br.readLine());
//	            
//	            accountService.updateAccount(new SavingsAccount(accountId, 0, null, newAccountType, newBalance, newInterestRate));
//	        } else if ("Current".equalsIgnoreCase(newAccountType)) {
//	            System.out.println("Enter new Overdraft Limit: ");
//	            
//	            double newOverdraftLimit = Double.parseDouble(br.readLine());
//	            accountService.updateAccount(new CurrentAccount(accountId, 0, null, newAccountType, newBalance, newOverdraftLimit));
//	        } else {
//	            throw new InvalidAccountTypeException("Invalid account type provided for update.");
//	        }
//	    }

	   
//	   Delete account method
	    private void deleteAccount() throws SQLException, InvalidAccountTypeException, IOException {
	        System.out.println("Enter the Account ID of the account to be deleted: ");
	        int accountId = Integer.parseInt(br.readLine());

	        accountService.deleteAccount(accountId);
	        System.out.println("Account deleted successfully.");
	    }
	    
	    
	    public void start() throws IOException, SQLException, InvalidAccountTypeException, BankingException, NumberFormatException, InterruptedException, ExecutionException, InvalidTransactionAmountException, TransactionFailureException, InsufficientFundException {
	        boolean running = true;
	        while (running) {
	            displayMenu();
	            int choice = Integer.parseInt(br.readLine());
	            switch (choice) {
	                case 1:
	                    createAccount();
	                    break;
	                case 2:
//	                    updateAccount();
	                	System.out.print(running);
	                    break;
	                case 3:
	                    deleteAccount();
	                    break;
	                case 4:
	                	deposit();
	                	break;
	                case 5:
	                	withdraw();
	                	break;
	                case 6:
	                	transfer_funds();
	                	break;
	                case 0:
	                    running = false;
	                    transactionService.shutDownExecutorService();
	                    System.out.println("Exiting...See you Again!!");
	                    break;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	                    break;
	            }
	        }
	    }

		private void transfer_funds() throws NumberFormatException, IOException, InterruptedException, ExecutionException, InvalidTransactionAmountException, SQLException, TransactionFailureException, InsufficientFundException {
			System.out.println("Enter Account ID from where to transfer amount: ");
			int id1 = Integer.parseInt(br.readLine());	
			
			System.out.println("Enter Account ID to tansfer the amount: ");
			int id2 = Integer.parseInt(br.readLine());	
			
			System.out.println("Enter the amount to be transferred: ");
			double amount = Double.parseDouble(br.readLine());
			
			
			Future<?> future = transactionService.transfer_funds(id1,id2, amount);
			future.get();  //Wait for  the deposit  operation to complete

			
		}

		private void withdraw() throws NumberFormatException, IOException, InterruptedException, ExecutionException {
			System.out.println("Enter Account ID: ");
			int id = Integer.parseInt(br.readLine());	
			
			System.out.println("Enter the amount to be Deposited: ");
			double amount = Double.parseDouble(br.readLine());
			
			
			Future<?> future = transactionService.deposit(id, amount);
			future.get();  //Wait for  the deposit  operation to complete
						
		}

		private void deposit() throws NumberFormatException, IOException, InterruptedException, ExecutionException, InvalidTransactionAmountException, SQLException {
			System.out.println("Enter Account ID: ");
			int id = Integer.parseInt(br.readLine());	
			
			System.out.println("Enter the amount to be Withdrawn: ");
			double amount = Double.parseDouble(br.readLine());
			
			
			Future<?> future = transactionService.withdraw(id, amount);
			future.get();  //Wait for  the deposit  operation to complete
			
		}
}
