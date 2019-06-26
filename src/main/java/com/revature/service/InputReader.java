package com.revature.service;

import java.util.Scanner;

public class InputReader 
{
	private static Scanner scanner = new Scanner(System.in);
	
	public static int getInt()
	{
		return scanner.nextInt();
	}
	
	public static String getStr()
	{
		return scanner.next();
	}
	
	public static float getFloat()
	{
		return scanner.nextFloat();
	}
}
