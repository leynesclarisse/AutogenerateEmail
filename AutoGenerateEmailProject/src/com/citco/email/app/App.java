package com.citco.email.app;

import java.util.HashMap;
import java.util.Map;

import com.citco.email.bfo.GeneratePasswordServices;
import com.citco.email.bfo.MailboxService;
import com.citco.email.bfo.MainService;
import com.citco.email.bfo.ViewRecordsServices;
import com.citco.email.utils.ConsoleUtil;
import com.citco.email.utils.Validation;

public class App{

	ConsoleUtil consoleUtil = new ConsoleUtil();
	Validation validation = new Validation();
	private Map<String,Runnable> commands = new HashMap<String,Runnable>();
	GeneratePasswordServices generatePasswordServices = new GeneratePasswordServices();
	MailboxService mailboxService = new MailboxService();
	ViewRecordsServices recordsServices = new ViewRecordsServices();
	
	public static void main(String[] args) {
		new App().main();
	}
	public void main(){
		this.commands.put("1", ()->{
			consoleUtil.printLn("=================== [A D D  A C C O U N T] =========================");
			this.addName();
		});
		this.commands.put("2", ()->{
			consoleUtil.printLn("=================== [C H A N G E  P A S S W O R D] =========================");
			generatePasswordServices.changePassword();		
		});
		this.commands.put("3", ()->{
			consoleUtil.printLn("=================== U P D A T E [MAILBOX CAPACITY] =========================");
			mailboxService.updateMailboxCapacity();
		});
		this.commands.put("4", ()->{
			consoleUtil.printLn("=================== [V I E W  R E C O R D S] =========================");
			recordsServices.viewRecords();
		});
		menu();
	}
	public void menu() {
		String choice;
		boolean checker = false;
		consoleUtil.print("\n-------- M E N U --------"
				+ "\n[1] Add Account"
				+ "\n[2] Change Password"
				+ "\n[3] Set Mailbox Capacity"
				+ "\n[4] View Records");
		do {
			consoleUtil.printLn("\nEnter choice: ");
			choice = consoleUtil.readLine();
			Runnable run = this.commands.getOrDefault(choice,null);
			if(run == null) {
				checker = true;
				consoleUtil.err("Invalid choice!");
			}else {
				run.run();
			}
		}while(checker);
		
	}
	
	public void addName() {
		String firstName,lastName,department,mailboxCapacity,alternateEmail;
		MainService mainService = new MainService();
		boolean checker = false;
		
		consoleUtil.print("Enter First Name: ");
		firstName = consoleUtil.readLine();
		
		consoleUtil.print("Enter Last Name: ");
		lastName = consoleUtil.readLine();
		
		consoleUtil.print("Aternative Email: ");
		alternateEmail = consoleUtil.readLine();
		
		do {
			consoleUtil.print("Mailbox Capacity: ");
			mailboxCapacity = consoleUtil.readLine();
		}while(!validation.validInput(mailboxCapacity));
		
		consoleUtil.print("Choose Department:"
							+ "\n[1] Sales"
							+ "\n[2] Development"
							+ "\n[3] Accounting"
							+ "\n[Leave blank if none.]\n");
		
		do {
			consoleUtil.printLn("\nEnter department: ");
			department = consoleUtil.readLine();
		
			if(department.equals("1")) {
				checker = true;
				department = "sales";
				mainService.mainService(firstName, lastName, department, mailboxCapacity,alternateEmail);
			}else if(department.equals("2")) {
				checker = true;
				department = "development";
				mainService.mainService(firstName, lastName, department, mailboxCapacity,alternateEmail);
			}else if(department.equals("3")) {
				checker = true;
				department = "accounting";
				mainService.mainService(firstName, lastName, department, mailboxCapacity,alternateEmail);
			}else if(department.equals("")) {
				checker = true;
				department = " ";
				mainService.mainService(firstName, lastName, department, mailboxCapacity,alternateEmail);
			}else {
				checker = false;
				consoleUtil.err("Invalid choice!");
			}
	
		}while(!checker);
		
	}
	
}

