package com.bank;

public class Transaction {
	private static int counter = 1;
	private int transactionID;
	private TransactionType transactionType;
	private boolean success;
	private int amount;
	private int balance;
	private int accountID;
	private String targetAccount;
	
	
	public int getAccountID() {
		return this.accountID;
	}
	
	public int getTransactionID() {
		return this.transactionID;
	}

	private static int getNextID() {
		return Transaction.counter++;
	}

	public Transaction(TransactionType transactionType, int amount, int balance, int accountID, boolean success, String targetAccount) {
		this.transactionID = Transaction.getNextID();
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
		this.accountID = accountID;
		this.success = success;
		this.targetAccount = targetAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + transactionID;
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
		Transaction other = (Transaction) obj;
		if (transactionID != other.transactionID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("|%-4d|%-25s|%-15s|%-10d|%-10d|%-9d|",
					transactionID, transactionType + " : "+ targetAccount, ((success)?"Successful":"Unsuccessful"), amount, balance, accountID);
	}
	
}
