package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.service.CloseStreams;
import com.revature.service.ConnectionUtil;

public class UserDAOImpl implements UserDAO
{

	@Override
	public User getUserID(long id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;
		try (Connection conn = ConnectionUtil.getConnection())
		{
			statement = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			statement.setLong(1, id);
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			user = new User(
					resultSet.getLong("id"),
					resultSet.getString("username"),
					resultSet.getString("password"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("email")
					);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			CloseStreams.close(statement);
		}
		return user;
	}

	@Override
	public User getUser(String username) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;
		try (Connection conn = ConnectionUtil.getConnection())
		{
			statement = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			statement.setString(1, username);
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			user = new User(
					resultSet.getLong("userID"),
					resultSet.getString("username"),
					resultSet.getString("password"),
					resultSet.getString("firstName"),
					resultSet.getString("lastName"),
					resultSet.getString("email")
					);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			CloseStreams.close(statement);
		}
		return user;
	}

	@Override
	public boolean createUser(User user) {
				PreparedStatement statement = null;
				try (Connection conn = ConnectionUtil.getConnection()) {
					statement = conn.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?)");
					statement.setLong(1, user.getId());
					statement.setString(2, user.getUserName());
					statement.setString(3, user.getPassword());
					statement.setString(4, user.getFirstName());
					statement.setString(5, user.getLastname());
					statement.setString(6,user.getEmail());

					statement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				finally
				{
					CloseStreams.close(statement);
				}
				return true;
	}

	@Override
	public boolean updateUser(User user) {
		
		return false;
	}
	
	public List<User> getUsers()
	{
		Statement statement = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.createStatement();
			
			resultSet = statement.executeQuery("SELECT * FROM users");
			
			while (resultSet.next()) {
				users.add(new User(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return users;
	}
	
	public boolean userExists(String username)
	{
		List<User> users = new ArrayList<>();
		users.addAll(getUsers());
		for(int i = 0; i < users.size();i++)
		if(users.get(i).getUserName().equals(username))
		{
			return true;
		}
		else
		{
			continue;
		}
		return false;
	}

}
