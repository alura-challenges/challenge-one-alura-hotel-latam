package database.dto;

public class UserDataDTO {
	
	private Integer id;
	private String login;
	private String password;
	
	public UserDataDTO(int id,String login, String password) {
		this.id=id;
		this.login=login;
		this.password=password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("{id: %s, login: %s, password: %s}", 
							this.id, this.login, this.password);
	}
}
