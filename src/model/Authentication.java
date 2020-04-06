package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Authentication")
@XmlType(name = "Authentication")
@XmlAccessorType(XmlAccessType.NONE)
public class Authentication {
	private String username;
	private String password;
	
	public Authentication() { }
	
	public Authentication(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@XmlElement(name = "Username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement(name = "Password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean checkAuthentication(Authentication authentication) {
		return username.equals(authentication.getUsername()) && password.equals(authentication.getPassword());
	}
}
