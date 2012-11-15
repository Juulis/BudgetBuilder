package net.petrusha.homebudget.app;


import net.petrusha.homebudget.persistence.gae.jdo.model.AccountEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HomeBudgetApp {

	private static String command;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//private static BubgetManaget budgetManager;
	
	public static void main(String[] args) {
		waitForInput();
	}



	private static void waitForInput() {
		try {
			command = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		executeCommand();
	}

	private static void executeCommand() {
		
		String[] commandAndArgs = command.trim().split("\\w");
		
		if ("createAccount".equalsIgnoreCase(commandAndArgs[0])) {
			AccountEntity account = new AccountEntity();
			account.setId("someId");
			account.setName("New Account");
			
			System.out.println("Account: " + account.getName());
		}
		
	}

}
