package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.MaxiumMonetaryAmountExceededException;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.CloseStreams;
import com.revature.service.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public Account getAccountID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createAccount(Account account) {
		PreparedStatement statement = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("INSERT INTO accounts VALUES(?,?,?,?)");

			statement.setLong(1, account.getAccountID());
			statement.setFloat(2, account.getBalance());
			statement.setString(3, account.getCurrencyType());
			statement.setLong(4, account.getUserID());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			CloseStreams.close(statement);
		}
		return true;
	}

	@Override
	public boolean updateAccount(Account account) {

		return false;
	}

	@Override
	public Account getAccountWithUserID(User user) {
		Account account = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM accounts WHERE userid = ?");
			statement.setLong(1, user.getId());
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			account = new Account(resultSet.getLong("accountID"), resultSet.getFloat("balance"),
					resultSet.getString("currencyType"), resultSet.getLong("userID"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
		}
		return account;
	}

	public List<Account> getAccounts(User user) {
		Statement statement = null;
		ResultSet resultSet = null;
		List<Account> accounts = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.createStatement();

			resultSet = statement.executeQuery("SELECT * FROM accounts");

			while (resultSet.next()) {
				accounts.add(new Account(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return accounts;
	}

	public boolean accountExists(User user) {
		List<Account> accounts = new ArrayList<>();
		accounts.addAll(getAccounts(user));

		for (int i = 0; i < accounts.size(); i++) {
			try {
				if (accounts.get(i).getUserID() == user.getId()) {
					return true;
				} else {
					continue;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;

	}

	public boolean withdrawAmount(User user, float money) {
		Account account = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		float balanceBefore;
		float balanceAfter;
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT balance FROM accounts WHERE userid = ?");
			statement.setLong(1, user.getId());
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			balanceBefore = resultSet.getFloat("balance");
			balanceAfter = balanceBefore - money;
			if (balanceAfter < -9999.99)
				throw new MaxiumMonetaryAmountExceededException("");
			statement = conn.prepareStatement("UPDATE accounts SET balance = ? where userID = ?");
			statement.setFloat(1, balanceAfter);
			statement.setLong(2, user.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (MaxiumMonetaryAmountExceededException e) {
			System.out.println("Error! The inputed amount of money would be greater than that allowed.");
			return false;
		}finally {
			CloseStreams.close(statement);
		}
	}

	public boolean depositAmount(User user, float money) {
		Account account = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		float balanceBefore;
		float balanceAfter;
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT balance FROM accounts WHERE userid = ?");
			statement.setLong(1, user.getId());
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			balanceBefore = resultSet.getFloat("balance");
			balanceAfter = balanceBefore + money;
			if (balanceAfter > 9999.99)
				throw new MaxiumMonetaryAmountExceededException("");
			statement = conn.prepareStatement("UPDATE accounts SET balance = ? where userID = ?");
			statement.setFloat(1, balanceAfter);
			statement.setLong(2, user.getId());
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (MaxiumMonetaryAmountExceededException e) {
			System.out.println("Error! The inputed amount of money would be greater than that allowed.");
			return false;
		} finally {
			CloseStreams.close(statement);
		}
	}
}
