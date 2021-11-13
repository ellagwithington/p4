//Author: Jack P
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreThread extends Thread {
	
	private Socket client;
	public ArrayList<Account> accounts = new ArrayList<Account>(); 
	public Account userAccount;
    private BufferedReader incoming;
    private PrintWriter outgoing; 
	
	public StoreThread(Socket client) {
		this.client = client;
	}
	
	public void run() {
	        readAccounts(accounts); 
	        try {
	                System.out.println("Connection from " + client.getInetAddress().toString() );
	                incoming = new BufferedReader(new InputStreamReader(client.getInputStream()));
	                outgoing = new PrintWriter( client.getOutputStream() );
	                System.out.println("Waiting for request...");
	                String request = incoming.readLine();
	                System.out.println("Request: " + request);
	                while (!request.equals("QUIT")) {
	                	switch (request) {
	                		case "LOGIN":
	                			System.out.println("Login..."); // For debugging
	                            login(accounts, incoming, outgoing);
	                			break;
	                		case "ACCOUNT_LIST":
	                			System.out.println("Sending account list..."); // For debugging
	                			sendAccountList(outgoing);
	                			break;
	                		case "PROFILE":
	                			System.out.println("Sending profile..."); // For debugging
	                			sendProfile(outgoing);
	                			break;
	                		case "CHANGE_PASSWORD":
	                			System.out.println("Change password..."); // For debugging
	                			changePassword(userAccount, incoming, outgoing);
	                			break;
	                	}
	                    request = incoming.readLine();
	                    System.out.println("Request: " + request); // For debugging
	                }
	                System.out.println("Quitting"); // For debugging
	                client.close();

	        }
	        catch (Exception e) {
	            System.out.println("Sorry, the server has shut down.");
	            System.out.println("Error:  " + e);
	            return;
	        }

	}
	
	public void readAccounts(ArrayList<Account>accounts) {
		 File dataFile = new File("accounts.txt");
	        if ( ! dataFile.exists() ) {
	            System.out.println("No data file found.");
	            System.exit(1);
	        }
	        try( Scanner scanner = new Scanner(dataFile) ) {
	            while (scanner.hasNextLine()) {
	                String accountEntry = scanner.nextLine();
	                int separatorPosition = accountEntry.indexOf('%');
	                int separatorPosition2 = accountEntry.indexOf('%', separatorPosition + 1);
	                int separatorPosition3 = accountEntry.indexOf('%', separatorPosition2 + 1);
	                if (separatorPosition == -1)
	                    throw new IOException("File is not a valid data file.");
	                String accountType = accountEntry.substring(0, separatorPosition);
	                String username = accountEntry.substring(separatorPosition + 1, separatorPosition2);
	                String password = accountEntry.substring(separatorPosition2 + 1, separatorPosition3);
	                if (accountType.equals("admin"))
	                	accounts.add(new AdminAccount(username, password, accounts));
	                else {
	                    String profile = accountEntry.substring(separatorPosition3 + 1);
	                	accounts.add(new ClientAccount(username, password, profile));
	            	}
	            }
	        }
	        catch (IOException e) {
	            System.out.println("Error in data file.");
	            System.exit(1);
	        }
	}
	
	public void login(ArrayList<Account>accounts,
			BufferedReader incoming, PrintWriter outgoing) {
		 try {
	            String username = incoming.readLine();
	            String password = incoming.readLine();
	            System.out.println("Received: " + username + ", " + password);
	            Account account;
	            String reply = "";
	    		boolean foundUser = false;
	    		for (int i = 0; i < accounts.size(); i++) {
	    			if (accounts.get(i).getUsername().equals(username)) {
	    				foundUser = true;
	    				if (accounts.get(i).verifyPassword(password)) {
	    					account = accounts.get(i);
	    					if (account instanceof AdminAccount) {
	        	    			reply = "ADMIN";
	        	                System.out.println("Found admin account");
	    					}
	    					else {
	        	    			reply = "CLIENT";
	        	                System.out.println("Found client account");
	    					}
	    					userAccount = account; // Set current user
	    				}
	    				else {
	    					reply = "ERROR: Invalid password";
	    				}
	    			}
	    		}
	    		if (!foundUser)
	    			reply = "ERROR: Invalid username";
	            System.out.println("Sending reply...");
	            outgoing.println(reply);
	            outgoing.flush();  // Make sure the data is actually sent!
	        }
	        catch (Exception e){
	            System.out.println("Error: " + e);
	        }
	}
	
	public void sendAccountList(PrintWriter outgoing) {
		for (int i = 0; i < accounts.size(); i++) {
	        outgoing.println(accounts.get(i).getUsername());
			if (accounts.get(i) instanceof AdminAccount)
				outgoing.println("Administrator");
			else
				outgoing.println("Client");
		}
        outgoing.println("DONE");
        outgoing.flush();
	}
	
	public void sendProfile(PrintWriter outgoing) {
		outgoing.println(((ClientAccount) userAccount).getProfile());
    	outgoing.flush();
	}
	
	public void changePassword(Account userAccount,
			BufferedReader incoming, PrintWriter outgoing) {
		try {
    		String oldPassword = incoming.readLine();
    		String newPassword = incoming.readLine();
    		System.out.println("Received: " + oldPassword + ", " + newPassword);
            String reply = "";
			if (userAccount.verifyPassword(oldPassword)) {
				userAccount.setPassword(newPassword);
				// Save new password
		        try {
		        	PrintWriter file = new PrintWriter("accounts.txt");
		            for (int i = 0; i < accounts.size(); i++) {
						if (accounts.get(i) instanceof AdminAccount)
			            	file.println("admin" + "%" + accounts.get(i).getUsername() + "%" + accounts.get(i).getPassword() + "%");
						else
			            	file.println("client" + "%" + accounts.get(i).getUsername() + "%" + accounts.get(i).getPassword() + "%" + ((ClientAccount) accounts.get(i)).getProfile());
					}
		            file.close();
		        }
		        catch (IOException e) {
		            System.out.println("Error writing data file.");
		            System.exit(1);
		        }
				// Send reply to client
				if (userAccount instanceof AdminAccount) {
					reply = "ADMIN";
					System.out.println("Found admin account");
				}
				else {
					reply = "CLIENT";
					System.out.println("Found client account");
				}
			}
			else {
				reply = "ERROR: Invalid password";
			}
    		System.out.println("Sending reply...");
    		outgoing.println(reply);
    		outgoing.flush();  // Make sure the data is actually sent!
    	}
    	catch (Exception e){
    		System.out.println("Error: " + e);
    	}
	}
	
}

