/**
 * @author fietkiewicz
 *
 */
public abstract class Account {
	private String username;
	private String password;
	private int id;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean verifyPassword(String password) {
		return this.password.equals(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "username: " + username + ", " + this.getClass();
	}
	
	/**
	 * @param username
	 * @param password
	 */
	public Account(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	
}
