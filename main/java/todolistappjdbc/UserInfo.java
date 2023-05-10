package todolistappjdbc;

public class UserInfo {
	
	private String user_id;
	private String user_password;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public UserInfo(String user_id, String user_password) {
		super();
		this.user_id = user_id;
		this.user_password = user_password;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
