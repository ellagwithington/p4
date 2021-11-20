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
	public ClientAccount(int id, String username, String password, String profile) {
		super(id, username, password);
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}
	
}
