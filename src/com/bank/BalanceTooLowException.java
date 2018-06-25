package com.bank;

@SuppressWarnings("serial")
public class BalanceTooLowException extends Exception {
	public BalanceTooLowException(String message) {
		super(message+" : Insufficient Balance");
	}
}
