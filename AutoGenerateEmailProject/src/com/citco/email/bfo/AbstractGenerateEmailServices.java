package com.citco.email.bfo;

import com.citco.email.domain.TemporaryDomain;

public abstract class AbstractGenerateEmailServices{
	private String userEmail;
	
	public String generateEmail(TemporaryDomain temporaryDomain, String department) {
		if(department.equals(" ")) {
			userEmail = temporaryDomain.getFname() + "." + temporaryDomain.getLname() + "@" + "citco.com";
		}else {
			userEmail = temporaryDomain.getFname() + "." + temporaryDomain.getLname() + "@" + department + ".citco.com";	
		}
		return userEmail;
	}
	
	public String generateEmail(TemporaryDomain temporaryDomain, String counter, String department) {
		if(department.equals(" ")) {
			userEmail = temporaryDomain.getFname() + "." + temporaryDomain.getLname() + "_" + counter + "@" + "citco.com";
		}else {
			userEmail = temporaryDomain.getFname() + "." + temporaryDomain.getLname() + "_" + counter + "@" + department + ".citco.com";
		}
		return userEmail;
	}
	
	public String generateEmailBlankDepartment(TemporaryDomain temporaryDomain,String counter) {
		userEmail = temporaryDomain.getFname() + "." + temporaryDomain.getLname() + "_" + counter + "@.citco.com";
		return userEmail;
	}
	
	public String generateEmailBlankDepartment(TemporaryDomain temporaryDomain) {
		userEmail = temporaryDomain.getFname() + "." + temporaryDomain.getLname() + "@.citco.com";
		return userEmail;
	}
}
