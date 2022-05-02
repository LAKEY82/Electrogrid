package com;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement

public class Complaints {
	
		public Complaints() {
		super();
		// TODO Auto-generated constructor stub
	}
		public Complaints(int complaintid, int accountno, String username, int userphone, String complaint, String date) {
		super();
		this.complaintid = complaintid;
		this.accountno = accountno;
		this.username = username;
		this.userphone = userphone;
		this.complaint = complaint;
		this.date = date;
	}
		private int complaintid;
		private int accountno;
		private String username;
		private int userphone;
		private String complaint;
		private String date;
		public int getComplaintid() {
			return complaintid;
		}
		public void setComplaintid(int complaintid) {
			this.complaintid = complaintid;
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
		public int getUserphone() {
			return userphone;
		}
		public void setUserphone(int userphone) {
			this.userphone = userphone;
		}
		public String getComplaint() {
			return complaint;
		}
		public void setComplaint(String complaint) {
			this.complaint = complaint;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		
}


