//Author:Jack Peng
import java.util.HashMap;
import java.io.*;
import java.util.*;

public class AccountsReader {
		static HashMap<String, String> dataSet = new HashMap<String, String>();
    
    	public static void main(String[] args) {
    		readFile("accounts.xml");
    	}

    	public static HashMap<String,String> readFile(String filename){
    			File dataFile = new File(filename);
    			if ( ! dataFile.exists() ) {
    					System.out.println("No data file found.");
    					System.exit(1);
    			}
    			try( Scanner scanner = new Scanner(dataFile) ) {
    				while (scanner.hasNextLine()) {
    					String input = scanner.nextLine();
    					int separatorPosition = input.indexOf('<');
    					int separatorPosition2 = input.indexOf('>');
    					String next = input.substring(separatorPosition + 1, separatorPosition2);
    					if(next.equals("CUSTOMER")) {
    						String input1 = "";
    						while(scanner.hasNextLine() && !input1.equals("/CUSTOMER")){
    							input1 = scanner.nextLine();
    							String currentLine = input1;
    							int separatorPosition3 = input1.indexOf('<');
    							int separatorPosition4 = input1.indexOf('>');
    							int separatorPosition5 = input1.indexOf('<',separatorPosition4+1);
    							input1 = input1.substring(separatorPosition3 + 1, separatorPosition4);
    							if(input1.equals("id")){
            						String value = currentLine.substring(separatorPosition4 + 1, separatorPosition5);
            						dataSet.put("id", value);
            					}
            					else if(input1.equals("Username")){
            						String value = currentLine.substring(separatorPosition4 + 1, separatorPosition5);
            						dataSet.put("Username", value);
            					}
            					else if(input1.equals("Password")){
            						String value = currentLine.substring(separatorPosition4 + 1, separatorPosition5);
            						dataSet.put("Password", value);
            					}
            					else if(input1.equals("Profile")){
            						String value = currentLine.substring(separatorPosition4 + 1, separatorPosition5);
            						dataSet.put("Profile", value);
            					}
    						}
    					}
    						else if(next.equals("ADMINISTRATOR")) {
    						String input1 = "";
    						while(scanner.hasNextLine() && !input1.equals("/ADMINISTRATOR")){
    							input1 = scanner.nextLine();
    							String currentLine = input1;
    							int separatorPosition3 = input1.indexOf('<');
    							int separatorPosition4 = input1.indexOf('>');
    							int separatorPosition5 = input1.indexOf('<',separatorPosition4+1);
    							input1 = input1.substring(separatorPosition3 + 1, separatorPosition4);
    							if(input1.equals("id")){
            						String value = currentLine.substring(separatorPosition4 + 1, separatorPosition5);
            						dataSet.put("id", value);
            					}
            					else if(input1.equals("Username")){
            						String value = currentLine.substring(separatorPosition4 + 1, separatorPosition5);
            						dataSet.put("Username", value);
            					}
            					else if(input1.equals("Password")){
            						String value = currentLine.substring(separatorPosition4 + 1, separatorPosition5);
            						dataSet.put("Password", value);
            					}
    						}
    					}
    						
    					
    					}
    					
    			}
    			catch (IOException e) {
    				System.out.println("Error in data file.");
    				System.exit(1);
    			}
    			
    			for (String key : dataSet.keySet()) {
    				System.out.print("Key = " + key + ", ");
    				System.out.println("Data =" + dataSet.get(key));
    			}
    			return dataSet;
    	}
}