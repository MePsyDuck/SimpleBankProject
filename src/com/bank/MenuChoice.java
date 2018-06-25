package com.bank;

public enum MenuChoice {
	MAINMENU("Main Menu"), CUSTOM("Custom menu"), EXIT("Exit"),
	ADD("Add"), VIEW("View"), TRANSACT("Transact"),
	ADDACCOUNT("Add a Account"), ADDCUSTOMER("Add a Customer"), ADDBRANCH("Add a Branch"), 
	VIEWACCOUNT("View a Account"), VIEWCUSTOMER("View a Customer"), VIEWBRANCH("View a Branch"), VIEWTRANSACTION("View a Transaction"), VIEWMAXBALANCEACCOUNT("View Account with maximum balance"),
	VIEWACCOUNTTRANSACTIONS("View transcations by Account"),
	VIEWALLACCOUNTS("View  all  Accounts"), VIEWALLCUSTOMERS("View all Customers"),VIEWALLBRANCHES("View all Branches"), VIEWALLTRANSACTIONS("View all Transaction"),
	DEPOSIT("Deposit to Account"), WITHDRAW("Withdraw from Account"), TRANSFER("Transfer to Account"), 
	;
	String desc;
	private MenuChoice(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
}
