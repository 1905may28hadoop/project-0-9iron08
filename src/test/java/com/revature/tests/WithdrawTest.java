package com.revature.tests;


import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.revature.controller.InputControl;
import com.revature.model.User;
import com.revature.repository.AccountDAOImpl;
import com.revature.repository.UserDAOImpl;


public class WithdrawTest {

	private static Logger log = Logger.getLogger(AccountDAOImpl.class);
	private static AccountDAOImpl account = new AccountDAOImpl();
	@Before
	public void runsBeforeEachTest()
	{
		log.info("Before method running");
	}
	
	@Test
	public void bigDepositResultsInFalse()
	{
		AccountDAOImpl accDAO = new AccountDAOImpl();
		User testUser = new UserDAOImpl().getUser("9iron");
		log.debug("test: deposited money cant go over 6");
		assertEquals(false,accDAO.depositAmount(testUser,9999999));
	}
	
	@Test 
	public void testWhetherNonExistantAccountReturnsFalse()
	{
		
		AccountDAOImpl accDAO = new AccountDAOImpl();
		User testUser = new UserDAOImpl().getUser("DwayneDoodle");
		log.debug("test: user \"DwayneDoodle\"returns false");
		assertEquals(false,accDAO.accountExists(testUser));
	}
}
