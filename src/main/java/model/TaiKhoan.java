package model;

public class TaiKhoan {
	private String username;
	private String password;
	private String user_id;
	private boolean userType;
	private String salt;
	//constructor
	public TaiKhoan() {
	}

	public TaiKhoan(String username, String password, String user_id, boolean userType, String salt) {
		this.username = username;
		this.password = password;
		this.user_id = user_id;
		this.userType = userType;
		this.salt = salt;
	}
	//setter and getter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public boolean isUserType() {
		return userType;
	}

	public void setUserType(boolean userType) {
		this.userType = userType;
	}
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	//toString
	@Override
	public String toString() {
		return "TaiKhoan [" + (username != null ? "username=" + username + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (user_id != null ? "user_id=" + user_id + ", " : "") + "userType=" + userType + "]";
	}
	
	
}
