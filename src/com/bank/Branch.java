package com.bank;

public class Branch {
	private static int counter = 1;
	private int branchID;
	private String branchName;
	private String branchLocation;
	
	public Branch(String branchName, String branchLocation) {
		this.branchID = Branch.getNextID();
		this.branchName = branchName;
		this.branchLocation = branchLocation;
	}

	private static int getNextID() {
		return counter++;
	}	
	
	public int getBranchID() {
		return branchID;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchLocation() {
		return branchLocation;
	}

	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}
	
	@Override
	public String toString() {
		return String.format("|%-4d|%-20s|%-40s|" , branchID, branchName, branchLocation);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchID;
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
		Branch other = (Branch) obj;
		if (branchID != other.branchID)
			return false;
		return true;
	}
}
