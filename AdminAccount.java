import java.util.ArrayList;

/**
 * 
 */

/**
 * @author fietkiewicz
 *
 */
public class AdminAccount extends Account {
	private ArrayList<Account> accounts;
	
	/**
	 * @param username
	 * @param password
	 */
	public AdminAccount(String username, String password, ArrayList<Account> accounts) {
		super(username, password);
		this.accounts = accounts;
	}
}
