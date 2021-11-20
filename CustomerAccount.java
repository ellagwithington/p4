import java.util.ArrayList;

/**
 * @author fietkiewicz
 *
 */
public class CustomerAccount extends Account {
	private String profile;
	
	/**
	 * @param username
	 * @param password
	 */
	public CustomerAccount(int id, String username, String password, String profile) {
		super(id, username, password);
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}
	
}
