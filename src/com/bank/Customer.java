package com.bank;

public class Customer {
	private static int counter = 1;
	private int customerID;
	private String customerName;
	private String customerAddress;
	
	private static int getNextID() {
		return counter++;
	}

	public Customer(String customerName, String customerAddress) {
		this.customerID = Customer.getNextID();
		this.customerName = customerName;
		this.customerAddress = customerAddress;
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerID;
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
		Customer other = (Customer) obj;
		if (customerID != other.customerID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("|%-4d|%-20s|%-40s|", customerID, customerName, customerAddress);
	}
	
}
