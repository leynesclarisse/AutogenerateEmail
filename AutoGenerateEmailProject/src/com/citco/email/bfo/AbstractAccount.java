package com.citco.email.bfo;


import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

import com.citco.email.dao.AbstractFileAuditDao;
import com.citco.email.dao.GeneralReportDao;
import com.citco.email.dao.IAuditLogger;
import com.citco.email.dao.IFileReaderCallback;
import com.citco.email.domain.EmailDomain;
import com.citco.email.domain.TemporaryDomain;
import com.citco.email.utils.ConsoleUtil;

public abstract class AbstractAccount extends AbstractFileAuditDao{
	
	IAuditLogger auditLogger = new GeneralReportDao();
	EmailDomain domain = new EmailDomain();
	ConsoleUtil consoleUtil = new ConsoleUtil();
	TemporaryDomain tempDomain = new TemporaryDomain();
	
	
	public void doGenerate(String fname, String lname, String department, String mailboxCapacity, String alternateEmail) {
		String password,emailAddress;
		GeneratePasswordServices generatePassword = new GeneratePasswordServices();
		
		tempDomain.setFname(fname);
		tempDomain.setLname(lname);
		tempDomain.setCompany("citco");
		
		consoleUtil.printLn("\n=======================================");
		consoleUtil.printLn("Name: %s $s", fname, lname);
		ReportHandler handler = new ReportHandler();
		handler.getEmail(tempDomain, department);
		try {
			super.readFileLineByLine(handler);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		emailAddress = handler.getEmail(tempDomain, department);
		consoleUtil.printLn("Email: %s",emailAddress);
		consoleUtil.printLn("Alternate Email: %s",alternateEmail);
		consoleUtil.printLn("Mailbox Capacity: $s", mailboxCapacity);
		password = generatePassword.generatePassword();
		consoleUtil.printLn("Password: %s", password);
		consoleUtil.printLn("=======================================");
		auditLogger.doLogAudit(String.format("%s,%s,%s,%s,%s,%s,%s", fname, lname, emailAddress, password, department, mailboxCapacity, alternateEmail));

	}
	
	private class ReportHandler extends AbstractGenerateEmailServices implements IFileReaderCallback{

		AtomicInteger counter = new AtomicInteger(0);
		
		@Override
		public void onLineRead(String str) {
			String firstName = str.split(",")[0];
			String lastName = str.split(",")[1];
			String department = str.split(",")[4];
			if(domain.getFname().equals(firstName) && domain.getLname().equals(lastName) && domain.getDepartment().equals(department)) {
				counter.addAndGet(1);
			}
		}
		
		public String getEmail(TemporaryDomain tempDomain, String department) {
			String userEmail,ctr;
			if(counter.intValue() > 0) {
				ctr = String.valueOf(counter.intValue());
				if(department.equals("")) {
					userEmail = super.generateEmailBlankDepartment(tempDomain, ctr);
					domain.setFname(tempDomain.getFname());
					domain.setLname(tempDomain.getLname());
					return userEmail;
				}else {
					userEmail = super.generateEmail(tempDomain, ctr, department);
					domain.setFname(tempDomain.getFname());
					domain.setLname(tempDomain.getLname());
					domain.setDepartment(department);
					return userEmail;
				}
			}else {
				if(department.equals("")) {
					userEmail = super.generateEmailBlankDepartment(tempDomain);
					domain.setFname(tempDomain.getFname());
					domain.setLname(tempDomain.getLname());
					return userEmail;
				}else {
					userEmail = super.generateEmail(tempDomain, department);
					domain.setFname(tempDomain.getFname());
					domain.setLname(tempDomain.getLname());
					domain.setDepartment(department);
					return userEmail;
				}
			}
		}

	}
}

