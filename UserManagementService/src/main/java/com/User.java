package com;

public class User {
	private int userid;
	private int accountno;
	private String username;
	private String useremail;
	private int userphone;
	private String useraddress;
	private String usernic;
	
	public User(int userid, int accountno, String username, String useremail, int userphone, String useraddress,
			String usernic) {
		super();
		this.userid = userid;
		this.accountno = accountno;
		this.username = username;
		this.useremail = useremail;
		this.userphone = userphone;
		this.useraddress = useraddress;
		this.usernic = usernic;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public int getUserphone() {
		return userphone;
	}
	public void setUserphone(int userphone) {
		this.userphone = userphone;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getUsernic() {
		return usernic;
	}
	public void setUsernic(String usernic) {
		this.usernic = usernic;
	}
}