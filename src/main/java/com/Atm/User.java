package com.Atm;

import java.security.MessageDigest;
import java.util.*;
import com.Atm.*;

public class User {
	
	/**
	 * The first name of the user
	 */
	
	private String firstname;
	
	/**
	 * The last name of the user
	 */
	
	
	private String lastname;
	/**
	 * The ID number of the user
	 */
	
	
	private String userid;
	/**
	 * The MD5 hash of the users pin number
	 */
	
	
	private byte pinHash[];
	/**
	 * The list accounts for this user
	 */
	
	private ArrayList<Account> accounts;
	
	/**
	 * Create a new user
	 * @param firstname the user's first name
	 * @param lastname  the user's last name
	 * @param pin       the user's account pin number
	 * @param theBank   the Bank object that the user is a customer of
	 */
	public User(String firstname, String lastname, String pin, Bank theBank) {
		super();
		//set user's name
		this.firstname = firstname;
		this.lastname = lastname;
		
		//store the pin's MD5 hash, rather than the original value,for
		//security reasons
		
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			this.pinHash=md.digest(pin.getBytes());
		} catch (Exception e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
		//get a new,unique universal ID for the user
		
		this.userid=theBank.getNewUserUUID();
		
		//create empty list of accounts
		this.accounts =new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s, %s with ID %s created.\n",lastname,firstname,this.userid);
	}
	
	/**
	 * Add an account for the user
	 * @param anAcct the account to add
	 */ 
	public void addAccount(Account anAcct) {
		
		this.accounts.add(anAcct);
		
	}
	
	/**
	 * Return the user's UUID
	 * @return the userid
	 */
	public String getUUID() {
		return this.userid;
	}
	
	/**
	 * Check weather a given pin matches the true User pin
	 * @param apin  the pin to check
	 * @return      whether the pin is valid or not
	 */
	
	public boolean validatePin(String apin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			return MessageDigest.isEqual(md.digest(apin.getBytes()), this.pinHash);
		} catch (Exception e) {
			System.err.println("error, caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
		
		
		
	}
	
	/**
	 * Return the user's first name
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstname;
	}

	/**
	 * Print summaries for the accounts of this user.
	 */
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary:\n",this.firstname);
		for (int a= 0; a < this.accounts.size(); a++) {
			System.out.printf(" %d) %s\n", a+1,this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
		
		
	}
	
	/**
	 * Get the number of accounts of the user
	 * @return the number of accounts
	 */
	public int numAccounts() {
		
		return this.accounts.size();	
	}
	
	/**
	 * Print transaction hitory for a particular account.
	 * @param acctIdx
	 */
	
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
		
		
	}
	
	/**
	 * Get the balance of a particular account
	 * @param acctIdx the index of the account to use
	 * @return        the balance of the account
	 */
	
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/**
	 * Get the UUID of a particular account
	 * @param acctIdx  the index of the account to use
	 * @return         the UUID of the account   
	 */ 
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	
	/**
	 * Add a transaction to a particular account 
	 * @param acctIdx  the index of the account
	 * @param amount   the amount of the transaction
	 * @param memo     the memo of the transaction
	 */
	public void addAcctTransaction(int acctIdx,double amount,String memo ) {
		this.accounts.get(acctIdx).addTransaction(amount,memo);
		
	}
	
	
	
} 
