package com.Atm;
import java.util.*;
import com.Atm.*;
public class Account {
	
	/**
	 * The name of the account
	 */
	
	private String name;


	/**
	 * The account ID number
	 */
	
	private String userid;
	

	/**
	 * TheUser object that owns this account
	 */
	
	private User holder;
	

	/**
	 * The list of transaction for this account
	 */
	public ArrayList<Transaction> transaction;
	
	/**
	 * Create a new Account
	 * @param name      the name of the account
	 * @param holder    the User object that holds this account
	 * @param theBank   the bank that issues the account
	 */

	public Account(String name, User holder, Bank theBank) {
		super();
		//set the account name and holder
		this.name = name;
		this.holder = holder;
		//get new account UUID
		this.userid = theBank.getNewAccountUUID();
		
		//init transactions
		this.transaction = new ArrayList<Transaction>();	
	}
	
	/**
	 * Get the account ID
	 * @return the userid
	 */
	
	public String getUUID() {
		return this.userid;
	}
	
	/**
	 * Get summary line for the account 
	 * @return the String summary
	 */
	public String getSummaryLine() {
		
		//get the accounts balance
		
		double balance=this.getBalance();
		
		//format the summary line depending on the whether the balance is negative
		
		if (balance >=0) {
			
			return String.format("%s: $%.02f : %s",this.userid,balance,this.name);
			
		}else {
			return String.format("%s: $(%.02f) : %s",this.userid,balance,this.name);		
		}
	}
	
	/**
	 * Get the balance of this account by adding the amounts of the transactions
	 * @return the balance value
	 */
	
	public double getBalance() {
		double balance=0;
		for(Transaction t: this.transaction) {
			
			balance+=t.getAmount();
			
		}
		
		return balance;
	}
	
	/**
	 * Print the transaction history of the account
	 */
	public void printTransHistory() {
		System.out.printf("\nTransaction History for account %s\n",this.userid);
		
		for(int t=this.transaction.size()-1; t >=0; t--) {
			System.out.println(this.transaction.get(t).getSummaryLine());
		}
		
		System.out.println();
	}
	
	/**
	 * Add a new transaction in this account
	 * @param amount the amount transacted
	 * @param memo   the transaction memo
	 */
	public void addTransaction(double amount,String memo) {
		//create new transaction object and add it to our list
		
		Transaction newTrans=new Transaction(amount,memo,this);
		this.transaction.add(newTrans);
		
	}
}
