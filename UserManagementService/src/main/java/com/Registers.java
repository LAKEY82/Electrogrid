package com;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Registers {
	private int id;
	private String userName;
	private String password;
	private String email;
	private String registeredAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegisterdAt() {
		return registeredAt;
	}
	public void setRegisteredAt(String registeredAt) {
		this.registeredAt = registeredAt;
	}
	public Registers(int id, String userName, String password, String email, String registeredAt) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.registeredAt = registeredAt;
	}
	public Registers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
