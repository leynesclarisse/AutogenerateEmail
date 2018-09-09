package com.citco.email.domain;

public class EmailDomain {
	private String fname;
	private String lname;
	private String email;
	private String mailboxCapacity;
	private String password;
	private String department;
	
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailboxCapacity() {
		return mailboxCapacity;
	}
	public void setMailboxCapacity(String mailboxCapacity) {
		this.mailboxCapacity = mailboxCapacity;
	}
	
}