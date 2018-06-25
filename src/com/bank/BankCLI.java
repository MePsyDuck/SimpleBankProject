package com.bank;

import java.util.Scanner;

public final class BankCLI {
	public static MenuChoice mainMenuChoices[] = {MenuChoice.ADD, MenuChoice.VIEW, MenuChoice.TRANSACT, MenuChoice.EXIT}; 
	public static MenuChoice addMenuChoices[] = {MenuChoice.ADDACCOUNT, MenuChoice.ADDBRANCH, MenuChoice.ADDCUSTOMER, MenuChoice.MAINMENU, MenuChoice.EXIT};
	public static MenuChoice viewMenuChoices[] = {MenuChoice.VIEWACCOUNT, MenuChoice.VIEWBRANCH, MenuChoice.VIEWCUSTOMER, MenuChoice.VIEWTRANSACTION, MenuChoice.VIEWMAXBALANCEACCOUNT,
												 MenuChoice.VIEWALLACCOUNTS, MenuChoice.VIEWALLCUSTOMERS, MenuChoice.VIEWALLBRANCHES, MenuChoice.VIEWALLTRANSACTIONS, MenuChoice.VIEWACCOUNTTRANSACTIONS,
												 MenuChoice.MAINMENU, MenuChoice.EXIT};
	public static MenuChoice transactMenuChoices[] = {MenuChoice.DEPOSIT, MenuChoice.WITHDRAW, MenuChoice.TRANSFER, MenuChoice.MAINMENU, MenuChoice.EXIT};
	public static MenuChoice customMenuChoices[] = {};
	
	private static int screenSize = 100;
	private static Scanner sc = new Scanner(System.in); 
	
	private BankCLI() {
		super();
	}
	
	public static void printSeparator() {
		for(int i=0; i<screenSize; i++) {
			System.out.print("=");
		}
		System.out.println("");
	}
	
	public static void printSeparator(int size) {
		for(int i=0; i<size; i++) {
			System.out.print("=");
		}
		System.out.println("");
	}
	
	public static void successMessage(boolean success ,String message) {
		if(success) {
			System.out.println("Success! " + message);
		} else {
			System.out.println("Unsuccessful : "+ message);
		}
	}
	
	public static MenuChoice menu(MenuChoice ...choices) {
		printSeparator();
		System.out.println("Choose one of the following options");
		int i = 1;
		for(MenuChoice choice : choices) {
			System.out.println((i++) + ") " + choice);
		}
		int chosenOption;
		try {
			chosenOption = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			chosenOption = -1;
		}
		if(chosenOption > choices.length || chosenOption < 1) {
				System.out.println("Wrong Choice");
			menu(choices);
		}else {
			return choices[chosenOption-1];
		}
		return MenuChoice.EXIT;
	}
	
	public static String[] addAccountMenu() {
		String[] inputs = new String[4];
		printSeparator();
		System.out.println("Enter Account Type: ");
		for(AccountType acc: AccountType.values())
			System.out.println(acc.ordinal() + 1 + ") " + acc);
		inputs[0] = sc.nextLine();		
		System.out.println("Enter Opening Balance: ");
		inputs[1] = sc.nextLine();
		System.out.println("Enter Branch ID: ");
		inputs[2] = sc.nextLine();
		System.out.println("Enter Customer ID: ");
		inputs[3] = sc.nextLine();
		return inputs;
	}
	
	public static String[] addCustomerMenu() {
		String[] inputs = new String[2];
		printSeparator();
		System.out.println("Enter Customer Name: ");
		inputs[0] = sc.nextLine();
		System.out.println("Enter Customer Address: ");
		inputs[1] = sc.nextLine();
		return inputs;
	}
	
	public static String[] addBranchMenu() {
		String[] inputs = new String[2];
		printSeparator();
		System.out.println("Enter Branch Name ");
		inputs[0] = sc.nextLine();
		System.out.println("Enter Branch Location ");
		inputs[1] = sc.nextLine();
		return inputs;
	}
	
	public static int getIDMenu() {
		printSeparator();
		System.out.println("Enter the ID: ");
		return Integer.parseInt(sc.nextLine());
	}
	
	public static String[] depositMenu() {
		String[] inputs = new String[2]; 
		printSeparator();
		System.out.println("Enter the account ID: ");
		inputs[0] = sc.nextLine();
		System.out.println("Enter the amount: ");
		inputs[1] = sc.nextLine();
		return inputs;
	}
	
	public static String[] withdrawMenu() {
		String[] inputs = new String[2]; 
		printSeparator();
		System.out.println("Enter the account ID: ");
		inputs[0] = sc.nextLine();
		System.out.println("Enter the amount: ");
		inputs[1] = sc.nextLine();
		return inputs;
	}
	
	public static String[] transferMenu() {
		String[] inputs = new String[3]; 
		printSeparator();
		System.out.println("Enter the account ID for sender: ");
		inputs[0] = sc.nextLine();
		System.out.println("Enter the account ID for reciever: ");
		inputs[1] = sc.nextLine();
		System.out.println("Enter the amount: ");
		inputs[2] = sc.nextLine();
		return inputs;
	}
	
	public static void display(Object ...ob) {
		int tableWidth = 0;
		if(ob[0] instanceof Transaction ) {
			tableWidth = 80;
			printSeparator(tableWidth);
			System.out.printf("|%-4s|%-25s|%-15s|%-10s|%-10s|%-9s|","ID","Description","Result","Amount","Balance","AccountID");
		} else if(ob[0] instanceof Account) {
			tableWidth = 54;
			printSeparator(tableWidth);
			System.out.printf("|%-4s|%-16s|%-10s|%-8s|%-10s|", "ID", "Type", "Balance", "BranchID", "CustomerID");
		} else if(ob[0] instanceof Customer) {
			tableWidth = 68;
			printSeparator(tableWidth);
			System.out.printf("|%-4s|%-20s|%-40s|", "ID", "Name", "Address");
		} else if(ob[0] instanceof Branch) {
			tableWidth = 68;
			printSeparator(tableWidth);
			System.out.printf("|%-4s|%-20s|%-40s|" , "ID", "Name", "Location");
		}
		System.out.println("");

		for(Object o : ob) {
			System.out.println(o);
		}
		printSeparator(tableWidth);
	}
	
}
