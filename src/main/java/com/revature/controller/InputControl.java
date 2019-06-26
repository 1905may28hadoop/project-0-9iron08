package com.revature.controller;

import com.revature.exception.InvalidInputException;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repository.AccountDAOImpl;
import com.revature.repository.UserDAOImpl;
import com.revature.service.CloseStreams;
import com.revature.service.InputReader;

public class InputControl implements Controller {

	public static User currentUser;
	public static String username;

	@Override
	public void getInput() {
		if (InputReader.getInt() == 1) {
			displayOptions(1);
		}
		else
		{
			loadMenu();
		}
			//else if (InputReader.getInt() == 3) {
			//createUser();
		//}
	}

	@Override
	public int displayOptions(int option) {
		if (option == 1) {
			loginScreen();
		} else if (option == 3) {
			System.out.println("What would you like to do?");
			System.out.println("1. To view your balance");
			//System.out.println("2. To view transaction history");
			System.out.println("2. To deposit money");
			System.out.println("3. To withdraw money");
			try {
				switch (InputReader.getInt()) {
				case 1:
					getBalance(username);
					break;
//				case 2:
//					getTransactionHistory(username);
//					break;
				case 2:
					deposit(username);
					break;
				case 3:
					withdraw(username);
					break;
				default:
					throw new InvalidInputException("");
				}
			} catch (InvalidInputException e) {
				System.out.println("Invalid input detected, was either a string or a invalid option...\nExiting......");
			}
		}
		return 2;
	}

	@Override
	public void loadMenu() {
		System.out.println("Welcome! Please select from one of the following options:");
		System.out.println("1. for Login");
		//System.out.println("2. for creating an account(not yet implemented)");
		getInput();
	}

	@Override
	public void loginScreen() {
		UserDAOImpl userTest = new UserDAOImpl();
		System.out.println("Please enter your username: ");
		String input = InputReader.getStr();
		int invalidAttempts = 0;
		boolean loginSuccess = false;
		if (userTest.userExists(input) == true) {
			System.out.println("User '" + input + "' found!");
			System.out.println("Please enter your password: ");
			do {

				if (userTest.getUser(input).getPassword().equals(InputReader.getStr())) {
					System.out.println("login successful!");
					username = input;
					currentUser = userTest.getUser(username);
					loginSuccess = true;
				} else if (invalidAttempts == 3) {
					System.out.println("Number of attempts exceeded....");
					sysExit();
				} else {
					System.out.println("Invalid Password!");
					invalidAttempts++;
					System.out.println("Number of attempts remaining: " + (3 - invalidAttempts) + "/3");
				}
			} while (!loginSuccess);
			displayOptions(3);
		} else {
			System.out.println("Usename not found, please try again:");
			loginScreen();
		}

	}

	@Override
	public void createUser() {
		//TODO: implement user creation
	}

	public void getBalance(String user) {
		AccountDAOImpl account = new AccountDAOImpl();
		String input;
		if (account.accountExists(currentUser)) {
			System.out.println("Your balance is: " + account.getAccountWithUserID(currentUser).getBalance());
			System.out.println("Type 'BACK' to go back to the account menu or type 'EXIT' to exit the program...");
			input = InputReader.getStr();
			try {

				if (input.equalsIgnoreCase("BACK")) {

					displayOptions(3);
				} else if (input.equalsIgnoreCase("EXIT")) {
					sysExit();
				}
				else
				{
					throw new InvalidInputException("");
				}
			} catch (InvalidInputException e) {
				System.out.println("Invalid input taken:\n");
				sysExit();
			}
		} else {
			System.out.println("No account for " + username + " found...");
			System.out.println("Would you like to create an account with the minimum amount of $5.00 USD?s");
			System.out.println("Type 'Y' to create an account or 'N' to exit");
			input = InputReader.getStr();
			Account newAccount = new Account(account.getAccounts(currentUser).size() + 1, 5.0f, "USD",
					currentUser.getId());
			System.out.println("Would you like to do another transaction? (Y/N)");
			if (InputReader.getStr().equalsIgnoreCase("Y")) {
				if (account.createAccount(newAccount)) {
					System.out.println("Account creation successful! Returing to option menu...");
					displayOptions(3);
				}
			} else {
				sysExit();
			}
			displayOptions(3);
		}
	}

	public void withdraw(String user) {
		try {
			AccountDAOImpl account = new AccountDAOImpl();
			System.out.println("How much would you like to withdraw?");
			float input = InputReader.getFloat();
			account.withdrawAmount(currentUser, input);
			System.out.println("successfully withdrew " + input);
			System.out.println("Would you like to do another transaction? (Y/N)");
			if (InputReader.getStr().equalsIgnoreCase("Y")) {
				displayOptions(3);
			} else {
				sysExit();
			}
		} catch (Exception e) {
			System.out.println("withdrawal failed");
			return;
		}
	}

	public void deposit(String user) {
		try {
			AccountDAOImpl account = new AccountDAOImpl();
			System.out.println("How much would you like to deposit?");
			float input = InputReader.getFloat();
			account.depositAmount(currentUser, input);
			System.out.println("successfully deposited " + input);
			System.out.println("Would you like to do another transaction? (Y/N)");
			if (InputReader.getStr().equalsIgnoreCase("Y")) {
				displayOptions(3);
			} else {
				sysExit();
			}
		} catch (Exception e) {
			System.out.println("deposit failed");
			return;
		}
	}
//
//	public void getTransactionHistory(String user) {
//
//	}

	public void sysExit() {
		System.out.println("Exiting...");
		System.exit(0);
	}
}
