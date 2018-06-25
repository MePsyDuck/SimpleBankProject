package com.bank;

public class Account {
	private static int counter = 1;
	private int accountID;
	private AccountType accountType;
	private int balance;
	private int branchID;
	private int customerID;
	
	private static int getNextID() {
		return counter++;
	}

	public Account(AccountType accountType, int balance, int branchID, int customerID) {
		this.accountID = Account.getNextID();
		this.accountType = accountType;
		this.balance = balance;
		this.branchID = branchID;
		this.customerID = customerID;
	}

	@Override
	public String toString() {
		return String.format("|%-4d|%-16s|%-10d|%-8d|%-10d|", accountID, accountType, balance, branchID, customerID);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		return true;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public int getAccountID() {
		return accountID;
	}

	public int getBalance() {
		return balance;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
