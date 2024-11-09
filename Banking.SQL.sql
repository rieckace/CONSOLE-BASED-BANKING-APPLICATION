-- create database BANKINGAPP;
-- use BANKINGAPP;
-- create table BANK( 
-- 	Bank_id int primary key auto_increment,
--     Bank_Name varchar(100) NOT NULL,
--     Bank_Branch varchar(100) NOT NULL
--     );
--     
--     DESC Bank;
--     
--     INSERT INTO BANK(Bank_Name, Bank_branch)
--     VALUES('Global IME','Main Branch');
--     
-- 	INSERT INTO BANK(Bank_Name, Bank_branch)
--     VALUES('Himalayan Bank Ltd','Main Branch');
--     
--     SELECT * FROM bank;
--     
--     CREATE TABLE Account(
--     Account_Id INT PRIMARY KEY unique auto_increment,
--     Customer_ID INT NOT NULL,
--     BANK_ID INT,
--     ACCOUNT_TYPE varchar(50) NOT NULL,
--     Balance Decimal(15,2) NOT NULL,
--     Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     foreign key (BANK_ID) REFERENCES BANK(BANK_id)
--     );
--     
--     
--     CREATE TABLE Savings_Account(
--     Account_Id INT Primary Key,
--     Inrerest_Rate decimal(5,2) NOT NULL,
--     foreign key (Account_Id) references Account(Account_Id)
--     );
--     
--      CREATE TABLE Current_Account(
--     Account_Id INT Primary Key,
--     Overdraft_Limit decimal(15,2) NOT NULL,
--     foreign key (Account_Id) references Account(Account_Id)
--     );
--     
--     
--     
--     select * from bank;
--     create table Transaction(
--     Transaction_Id INT primary Key auto_increment,
--     Account_Id INT NOT NULL,
--     Transaction_Type varchar(50) not null,
--     Amount Decimal(15,2) not null,
--     Transaction_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     foreign key (Account_Id) references Account(Account_Id)
--     );
--     
--     create table DepositTransaction(
--     Transaction_ID int primary key,
--     Deposit_Method varchar(50) not null,
--     foreign key(Transaction_Id) references Transaction(Transaction_Id)
--     );
--     
--     create table WithdrawTransaction(
--     Transaction_ID int primary key,
--     Withdraw_Method varchar(50) not null,
--     foreign key(Transaction_Id) references Transaction(Transaction_Id)
--     );
--     
    
   DELIMITER $$

CREATE PROCEDURE transfer_funds(
    IN fromAccount INT, 
    IN toAccount INT, 
    IN Amount DECIMAL(15,2)
)
BEGIN
    DECLARE from_balance DECIMAL(15,2);
    DECLARE insufficient_funds CONDITION FOR SQLSTATE '45000';
    DECLARE transaction_type_out VARCHAR(10) DEFAULT 'out';
    DECLARE transaction_type_in VARCHAR(10) DEFAULT 'in';

 
    SELECT balance INTO from_balance 
    FROM Account 
    WHERE Account_Id = fromAccount;
    
  
    IF from_balance >= Amount THEN
       
        UPDATE Account
        SET Balance = Balance - Amount
        WHERE Account_Id = fromAccount;

        UPDATE Account
        SET Balance = Balance + Amount
        WHERE Account_Id = toAccount;

        -- Record the transaction for fromAccount
        INSERT INTO Transaction(Account_Id, Transaction_Type, Amount) 
        VALUES (fromAccount, transaction_type_out, Amount);

        -- Record the transaction for toAccount
        INSERT INTO Transaction(Account_Id, Transaction_Type, Amount) 
        VALUES (toAccount, transaction_type_in, Amount);
    ELSE
        -- Raise error if insufficient funds
        SIGNAL insufficient_funds
        SET MESSAGE_TEXT = 'Insufficient funds in fromAccount';
    END IF;
END$$
 
DELIMITER ;

    show procedure status where Db = 'BANKINGAPP';accountAccount_Id
    
    -- Withdraw Procedure 
DELIMITER $$

CREATE PROCEDURE withdraw(
    IN fromAccount INT,
    IN Amount DECIMAL(15,2)
)
BEGIN
    DECLARE from_balance DECIMAL(15,2);
    DECLARE insufficient_funds CONDITION FOR SQLSTATE '45000';
    DECLARE transaction_type_out VARCHAR(10) DEFAULT 'withdraw';

    -- Get balance of fromAccount
    SELECT Balance INTO from_balance 
    FROM Account 
    WHERE Account_Id = fromAccount;

    -- Check if sufficient funds exist
    IF from_balance >= Amount THEN
        -- Deduct amount from fromAccount
        UPDATE Account
        SET Balance = Balance - Amount
        WHERE Account_Id = fromAccount;

        -- Record the withdrawal transaction
        INSERT INTO Transaction(Account_Id, Transaction_Type, Amount) 
        VALUES (fromAccount, transaction_type_out, Amount);
    ELSE
        -- Raise error if insufficient funds
        SIGNAL insufficient_funds
        SET MESSAGE_TEXT = 'Insufficient funds in fromAccount';
    END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE deposit(
    IN toAccount INT,
    IN Amount DECIMAL(15,2)
)
BEGIN
    DECLARE transaction_type_in VARCHAR(10) DEFAULT 'deposit';

    -- Check if the account exists
    IF EXISTS (SELECT 1 FROM Account WHERE Account_Id = toAccount) THEN
        -- Add amount to toAccount
        UPDATE Account
        SET Balance = Balance + Amount
        WHERE Account_Id = toAccount;

        -- Record the deposit transaction
        INSERT INTO Transaction(Account_Id, Transaction_Type, Amount) 
        VALUES (toAccount, transaction_type_in, Amount);
    ELSE
        -- Raise an error if the account does not exist
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Account not found for deposit';
    END IF;
END$$; 

DELIMITER ;

    
    
    select * from bank;
    select * from account;
    
--     create database sample;
--     use sample;
--     
--     create table users(
--     username varchar(100),
--     password varchar(100),
--     user_id int
--     );
--     
--     
--     update users
--     set username = 'Mukesh' where user_id = 123;
--    
--     delete from users
-- 	where username = 'Nitesh' ;
--      select * from users;
     
   