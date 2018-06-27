package com.bank;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BankController {
	Map<Integer, Branch> branches;
	Map<Integer, Customer> customers;
	List<Transaction> transactions;
	Map<Integer, Account> accounts;
	
	public BankController() {
		branches = new HashMap<Integer, Branch>();
		customers = new HashMap<Integer, Customer>();
		transactions = new LinkedList<Transaction>();
		accounts = new HashMap<Integer, Account>();
	}
	
	public Account getMaxBalanceAccount(int branchID) throws EntityNotFoundException {
		int maxBalanceAccountID = 0;
		Branch branch = getBranchByID(branchID);
		int maxBalance = Integer.MIN_VALUE;
		for(Account account : accounts.values()) {
			if(account.getBranchID() == branch.getBranchID()) {
				if(account.getBalance() > maxBalance) {
					maxBalanceAccountID = account.getAccountID();
					maxBalance = account.getBalance();
				}
			}
		}
		if(maxBalanceAccountID == 0) {
			throw new EntityNotFoundException("No Account in given Branch");
		} else {
			return getAccountByID(maxBalanceAccountID);	
		}
	}
	
	public Object[] getTransactionsByAccountID(int accountID) throws EntityNotFoundException {
		List<Transaction> accountTransactions = new LinkedList<Transaction>();
		Account account = getAccountByID(accountID);
		for(Transaction transaction : transactions) {
			if(transaction.getAccountID() == account.getAccountID()) {
				accountTransactions.add(transaction);
			}
		}
		if(accountTransactions.size() == 0) {
			throw new EntityNotFoundException("No transaction for given Acccount");
		} else {
		return accountTransactions.toArray(new Transaction[accountTransactions.size()]);
		}
	}
	
	//create and reads ops
	public int addBranch(String branchName, String branchLocation) {
		Branch newBranch = new Branch(branchName, branchLocation);
		branches.put(newBranch.getBranchID(), newBranch);
		return newBranch.getBranchID();
	}
	
	public int addAccount(AccountType type, int balance, int branchID, int customerID) throws BalanceTooLowException{
		Account newAccount = new Account(type, balance, branchID, customerID);
		addTransaction(TransactionType.OPENING, balance, balance, newAccount.getAccountID(), true,"Self");
		accounts.put(newAccount.getAccountID(), newAccount);
		return newAccount.getAccountID();
	}
	
	public int addCustomer(String customerName, String customerAddress) {
		Customer newCustomer = new Customer(customerName, customerAddress);
		customers.put(newCustomer.getCustomerID(), newCustomer);
		return newCustomer.getCustomerID();
	}
	
	public int addTransaction(TransactionType  type, int amount, int balance, int accountID, boolean success, String target) {
		Transaction newTransaction = new Transaction(type, amount, balance, accountID, success, target);
		transactions.add(newTransaction);
		return newTransaction.getTransactionID();
	}
	
	public Branch getBranchByID(int branchID) throws EntityNotFoundException {
		if(branches.containsKey(branchID)) {
			return branches.get(branchID);
		}
		else {
			throw new EntityNotFoundException("Branch ID " + branchID);
		}
	}
	
	public Customer getCustomerByID(int customerID) throws EntityNotFoundException {
		if(customers.containsKey(customerID)) {
			return customers.get(customerID);
		}
		else {
			throw new EntityNotFoundException("Customer ID " + customerID);
		}
	}
	
	public Account getAccountByID(int accountID) throws EntityNotFoundException {
		if(accounts.containsKey(accountID)) {
			return accounts.get(accountID);
		}
		else {
			throw new EntityNotFoundException("Account ID " + accountID);
		}
	}
	
	public Transaction getTransactionByID(int transactionID) throws EntityNotFoundException {
		for(Transaction transaction : transactions) {
			if(transaction.getTransactionID() == transactionID) {
				return transaction;
			}
		}
		throw new EntityNotFoundException("Transaction ID " + transactionID);
	}
	
	public Object[] getAllTransactions() {
		return transactions.toArray(new Transaction[transactions.size()]);
	}
	
	public Object[] getAllAccounts() {
		return accounts.values().toArray(new Account[accounts.size()]);
	}
	
	public Object[] getAllBranches() {
		return branches.values().toArray(new Branch[branches.size()]);
	}
	
	public Object[] getAllCustomers() {
		return customers.values().toArray(new Customer[customers.size()]);
	}
	
	private boolean hasSufficientBalance(int accountID, int amount) throws EntityNotFoundException {
		Account self = getAccountByID(accountID);
		return self.getBalance() - amount >= self.getAccountType().getMinimumBalance();
	}
	
	//transactions
	public int deposit(int accountID, int amount) throws EntityNotFoundException {
		Account self = getAccountByID(accountID);
		self.setBalance(self.getBalance() + amount);
		addTransaction(TransactionType.DEPOSIT, amount, self.getBalance(), self.getAccountID(), true, "Self");
		return self.getBalance();
	}
	
	public int withdraw(int accountID, int amount) throws BalanceTooLowException, EntityNotFoundException{
		Account self = getAccountByID(accountID);
		if(hasSufficientBalance(accountID, amount)) {
			self.setBalance(self.getBalance() - amount);
			addTransaction(TransactionType.WITHDRAW, amount, self.getBalance(), self.getAccountID(), true, "Self");
		}
		else {
			addTransaction(TransactionType.WITHDRAW, amount, self.getBalance(), self.getAccountID(), false, "Self");
			throw new BalanceTooLowException("Account ID "+accountID);
		}
		return self.getBalance();
	}
	
	public void transfer(int fromAccountID, int toAccountID, int amount) throws EntityNotFoundException, BalanceTooLowException {
		Account fromAccount = this.getAccountByID(fromAccountID);
		Account toAccount = this.getAccountByID(toAccountID);
		if(hasSufficientBalance(fromAccountID, amount)) {
			fromAccount.setBalance(fromAccount.getBalance() - amount);
			addTransaction(TransactionType.TRANSFERTO, amount, fromAccount.getBalance(), fromAccount.getAccountID(), true, Integer.toString(toAccountID));
		}
		else {
			addTransaction(TransactionType.TRANSFERTO, amount, fromAccount.getBalance(), fromAccount.getAccountID(), false, Integer.toString(toAccountID));
			throw new BalanceTooLowException("Account ID "+fromAccountID);
		}
		toAccount.setBalance(toAccount.getBalance() + amount);
		addTransaction(TransactionType.TRANSFERFROM, amount, toAccount.getBalance(), toAccount.getAccountID(), true, Integer.toString(fromAccountID));
	}
	
	// same methods with varargs	
	public int addBranch(String inputs[]) {
		String branchName = inputs[0];
		String branchLocation = inputs[1];
		return addBranch(branchName, branchLocation);
	}
	
	public int addAccount(String inputs[]) throws BalanceTooLowException {
		AccountType type = AccountType.parseAccountType(inputs[0]); 
		int balance = Integer.parseInt(inputs[1]);
		int branchID = Integer.parseInt(inputs[2]);
		int customerID = Integer.parseInt(inputs[3]);
		return addAccount(type, balance, branchID, customerID);
	}
	
	public int addCustomer(String inputs[]) {
		String customerName = inputs[0];
		String customerAddress = inputs[1];
		return addCustomer(customerName, customerAddress);
	}
	
	public int deposit(String inputs[]) throws EntityNotFoundException {
		int accountID = Integer.parseInt(inputs[0]);
		int amount = Integer.parseInt(inputs[1]);
		return deposit(accountID, amount);
	}

	public int withdraw(String inputs[]) throws BalanceTooLowException, EntityNotFoundException{
		int accountID = Integer.parseInt(inputs[0]);
		int amount = Integer.parseInt(inputs[1]);
		return withdraw(accountID, amount);
	}
	
	public void transfer(String inputs[]) throws EntityNotFoundException, BalanceTooLowException {
		int fromAccountID = Integer.parseInt(inputs[0]);
		int toAccountID = Integer.parseInt(inputs[1]);
		int amount = Integer.parseInt(inputs[2]);
		transfer(fromAccountID, toAccountID, amount);
	}
}
