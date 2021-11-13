//Author:Jack Peng
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.net.*;
import java.io.*;

class AccountsReaderTest {
	static HashMap<String, String> dataSet = new HashMap<String, String>();	
	@Test
	void test() {
			dataSet = AccountsReader.readFile("accounts.xml");
			String id = dataSet.get("id");
			String username = dataSet.get("Username");
			String password = dataSet.get("Password");
			String profile = dataSet.get("Profile");
			assert id.equals("12");
			assert username.equals("client");
			assert password.equals("clientpass");
			assert profile.equals("This is client");
			
	}
}
