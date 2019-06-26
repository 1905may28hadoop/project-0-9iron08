package com.revature.model;

import java.sql.Timestamp;

public class Transaction 
{
	private long TransactionID;
	private Timestamp timestamp;
	private float amount;
	private float balanceBefore;
	private float balanceAfter;
	private long accountID;
	public long getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(long transactionID) {
		TransactionID = transactionID;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getBalanceBefore() {
		return balanceBefore;
	}
	public void setBalanceBefore(float balanceBefore) {
		this.balanceBefore = balanceBefore;
	}
	public float getBalanceAfter() {
		return balanceAfter;
	}
	public void setBalanceAfter(float balanceAfter) {
		this.balanceAfter = balanceAfter;
	}
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	public Transaction(long transactionID, Timestamp timestamp, float amount, float balanceBefore, float balanceAfter,
			long accountID) {
		super();
		TransactionID = transactionID;
		this.timestamp = timestamp;
		this.amount = amount;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
		this.accountID = accountID;
	}
	
	 
	
	
	
}
