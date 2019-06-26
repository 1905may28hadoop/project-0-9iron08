package com.revature.repository;

import java.util.List;

import com.revature.model.User;

public interface UserDAO {
	User getUserID(long id);

	User getUser(String typename);

	boolean createUser(User user);

	boolean updateUser(User user);

	static List<User> getUsers() {

		return null;
	}
}
