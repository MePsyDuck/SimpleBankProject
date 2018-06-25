package com.bank;

//TODO add confirmation dialog for CLI

public class BankApp {
	public static void main(String args[]) {
		BankController bank = new BankController();
		MenuChoice choice = MenuChoice.MAINMENU;
		System.out.println("Welcome to the Bank");
		
		//add dummy accounts
		bank.addBranch("branchA","Chennai");
		bank.addBranch("branchB","Tambaram");
		bank.addCustomer("customerA","Avadi");
		bank.addCustomer("customerB","Bangalore");
		bank.addAccount(AccountType.SAVINGS, 5000, 1, 1);
		bank.addAccount(AccountType.CURRENT, 10000, 1, 1);
		bank.addAccount(AccountType.SAVINGS, 3500, 2, 1);
		bank.addAccount(AccountType.LOAN, 0, 2, 2);
		bank.addAccount(AccountType.CURRENT, 8000, 1, 2);
		
		while(choice!=MenuChoice.EXIT) {
			
			switch(choice) {
			
			case MAINMENU: choice = BankCLI.menu(BankCLI.mainMenuChoices);
						break;
						
			case ADD: choice = BankCLI.menu(BankCLI.addMenuChoices);
						break;
			
			case VIEW: choice = BankCLI.menu(BankCLI.viewMenuChoices);
						break;
			
			case TRANSACT: choice = BankCLI.menu(BankCLI.transactMenuChoices);
						break;
						
			case CUSTOM: choice = BankCLI.menu(BankCLI.customMenuChoices);
						break;
		
			case ADDACCOUNT: BankCLI.successMessage(true, "New Account ID is " + bank.addAccount(BankCLI.addAccountMenu()));
						choice = MenuChoice.ADD;
						break;
			
			case ADDCUSTOMER: BankCLI.successMessage(true, "New CustomerID is " + bank.addCustomer(BankCLI.addCustomerMenu()));
						choice = MenuChoice.ADD;
						break;
						
			case ADDBRANCH: BankCLI.successMessage(true, "New Branch ID is " + bank.addBranch(BankCLI.addBranchMenu()));
						choice = MenuChoice.ADD;
						break;
						
			case VIEWCUSTOMER: try {
							BankCLI.display(bank.getCustomerByID(BankCLI.getIDMenu()));
						} catch (EntityNotFoundException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.VIEW;			
						}
						break;
						
			case VIEWACCOUNT: try {
							BankCLI.display(bank.getAccountByID(BankCLI.getIDMenu()));
						} catch (EntityNotFoundException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.VIEW;			
						}
						break;
			
			case VIEWBRANCH: try {
							BankCLI.display(bank.getBranchByID(BankCLI.getIDMenu()));
						} catch (EntityNotFoundException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.VIEW;			
						}
						break;
			
			case VIEWMAXBALANCEACCOUNT: try {
							BankCLI.display(bank.getMaxBalanceAccount(BankCLI.getIDMenu()));
						} catch(EntityNotFoundException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.VIEW;
						}
						break;
			
			case VIEWTRANSACTION: try {
							BankCLI.display(bank.getTransactionByID(BankCLI.getIDMenu()));
						} catch (EntityNotFoundException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.VIEW;			
						}
						break;
			
			case VIEWACCOUNTTRANSACTIONS: try {
							BankCLI.display(bank.getTransactionsByAccountID(BankCLI.getIDMenu()));
						} catch (EntityNotFoundException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.VIEW;
						}
						break;
						
			case VIEWALLTRANSACTIONS: BankCLI.display(bank.getAllTransactions());
						choice = MenuChoice.VIEW;				
						break;
						
			case VIEWALLACCOUNTS: BankCLI.display(bank.getAllAccounts());
						choice = MenuChoice.VIEW;
						break;
						
			case VIEWALLBRANCHES: BankCLI.display(bank.getAllBranches());
						choice = MenuChoice.VIEW;
						break;
			
			case VIEWALLCUSTOMERS: BankCLI.display(bank.getAllCustomers());
						choice = MenuChoice.VIEW;
						break;
							
			case DEPOSIT: 	try {
							BankCLI.successMessage(true,"New Account Balance : " + bank.deposit(BankCLI.depositMenu()));
						} catch (EntityNotFoundException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.TRANSACT;
						}
						break;
							
			case WITHDRAW: 	try {
							BankCLI.successMessage(true,"New Accont Balance : " + bank.withdraw(BankCLI.withdrawMenu()));
						} catch (EntityNotFoundException | BalanceTooLowException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.TRANSACT;
						}
						break;
							
			case TRANSFER:  	try {
							bank.transfer(BankCLI.transferMenu());
							BankCLI.successMessage(true, "Transfer successful");
						} catch (EntityNotFoundException | BalanceTooLowException e) {
							BankCLI.successMessage(false, e.getMessage());
						} finally {
							choice = MenuChoice.TRANSACT;
						}
						break;
						
			default: choice = MenuChoice.MAINMENU;
			}
		}
		System.out.println("Have a nice day!");
	}
}
