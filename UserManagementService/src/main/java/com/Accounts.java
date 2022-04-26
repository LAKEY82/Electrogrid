package com;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement

public class Accounts {
	private int accountid;
	private int accountno;
	private String premisesid;
	private String areaoffice;
	private String tarifftype;
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public String getPremisesid() {
		return premisesid;
	}
	public void setPremisesid(String premisesid) {
		this.premisesid = premisesid;
	}
	public String getAreaoffice() {
		return areaoffice;
	}
	public void setAreaoffice(String areaoffice) {
		this.areaoffice = areaoffice;
	}
	public String getTarifftype() {
		return tarifftype;
	}
	public void setTarifftype(String tarifftype) {
		this.tarifftype = tarifftype;
	}
	public Accounts(int accountid, int accountno, String premisesid, String areaoffice, String tarifftype) {
		super();
		this.accountid = accountid;
		this.accountno = accountno;
		this.premisesid = premisesid;
		this.areaoffice = areaoffice;
		this.tarifftype = tarifftype;
	}
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}

}
