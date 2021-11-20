import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ellawithington
 *
 */
public class AdminAccount extends Account {
	private ArrayList<Account> accounts;
	
	/**
	 *@param id
	 * @param username
	 * @param password
	 */
	public AdminAccount(int id, String username, String password) {
		super(id, username, password);
	}
}
