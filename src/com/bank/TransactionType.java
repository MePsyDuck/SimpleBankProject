package com.bank;

public enum TransactionType {
	OPENING("Opening Balance"), DEPOSIT("Deposit"), WITHDRAW("Withdrawal"), TRANSFERTO("Transfer to"), TRANSFERFROM("Transfer from");
	private String description;
	
	private TransactionType(String desc) {
		this.description = desc;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
