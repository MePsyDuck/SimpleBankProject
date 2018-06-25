package com.bank;

public enum AccountType {
	SAVINGS("Savings Account", 1000), CURRENT("Current Account", 0), LOAN("Loan Account", Integer.MIN_VALUE);
	private String description;
	private int minBalance;
	
	public int getMinimumBalance() {
		return this.minBalance;
	}
	
	private AccountType(String desc, int minBalance) {
		this.description = desc;
		this.minBalance = minBalance;
	}
	
	public static AccountType parseAccountType(String s) {
		int op = Integer.parseInt(s);
		switch(op) {
		case 1: return SAVINGS;
		case 2: return CURRENT;
		case 3: return LOAN;
		default: return SAVINGS;
		}		
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
