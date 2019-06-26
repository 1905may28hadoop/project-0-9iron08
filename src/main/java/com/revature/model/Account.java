package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account
{
	private long accountID;
	private float balance;
	private String currencyType;
	private long userID;
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public Account(long accountID, float balance, String currencyType, long userID) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.currencyType = currencyType;
		this.userID = userID;
	}
	public Account(ResultSet resultSet) throws SQLException{
		this(
				resultSet.getInt("accountID"),
				resultSet.getFloat("balance"),
				resultSet.getString("currencyType"),
				resultSet.getInt("userID")
				);
	}
	
}
