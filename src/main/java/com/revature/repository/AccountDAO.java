package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.CloseStreams;
import com.revature.service.ConnectionUtil;

public interface AccountDAO {
	Account getAccountID(long id);
	
	Account getAccountWithUserID(User user);
	
	boolean createAccount(Account account);

	boolean updateAccount(Account account);
	
	static List<Account> getAccounts() {
		return null;
	}
}
