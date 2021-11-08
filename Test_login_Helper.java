// Author: Chris Fietkiewicz
// Creates ServerSocket for JUnit test for login method in StoreServer.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Test_login_Helper {
    public static void main(String[] args) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(new AdminAccount("admin", "password", accounts));
		accounts.add(new ClientAccount("client123", "neverguess", "Best client ever."));
		int LISTENING_PORT = 32007;
		ServerSocket listener;  // Listens for incoming connections.
        Socket client;      // For communication with the connecting program.
        try {
	        listener = new ServerSocket(LISTENING_PORT);
	        for (int i = 0; i < 4; i++) { // Planning to run 4 tests
	            System.out.println("Listening on port " + LISTENING_PORT);
	           	client = listener.accept(); 
	    		BufferedReader incoming = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    		PrintWriter outgoing = new PrintWriter(client.getOutputStream());
	    		StoreServer.login(accounts, incoming, outgoing);
	    		client.close();
	    		listener.close();
	        }
        }
        catch (Exception e) {
            System.out.println("Error:  " + e);
            return;
        }
    }
}
