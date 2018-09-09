package com.citco.email.bfo;

public class MainService extends AbstractAccount {
	public void mainService(String fname, String lname, String department, String mailboxCapacity, String alternateEmail) {
		super.doGenerate(fname, lname, department, mailboxCapacity, alternateEmail);
	}
}

