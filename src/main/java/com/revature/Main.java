package com.revature;

import com.revature.controller.InputControl;
import com.revature.service.ConnectionUtil;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main 
{
	public static void main(String[] args) 
	{
		ConnectionUtil.getConnection();
		InputControl InCtrl = new InputControl();
		InCtrl.loadMenu();

	}
}
