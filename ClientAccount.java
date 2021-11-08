import java.util.ArrayList;

/**
 * @author fietkiewicz
 *
 */
public class ClientAccount extends Account {
	private String profile;
	
	/**
	 * @param username
	 * @param password
	 */
	public ClientAccount(String username, String password, String profile) {
		super(username, password);
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}
	
}
