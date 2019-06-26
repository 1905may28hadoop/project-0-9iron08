package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User
{
	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastname;
	private String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(long id, String userName, String password, String firstName, String lastname, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
	}
	public User(ResultSet resultSet) throws SQLException
	{
		this(
			resultSet.getLong("userID"),
			resultSet.getString("username"),
			resultSet.getString("password"),
			resultSet.getString("firstName"),
			resultSet.getString("lastName"),
			resultSet.getString("email")
			);
	}
	
	
	 
}
