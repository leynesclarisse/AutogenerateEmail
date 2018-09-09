package com.citco.email.bfo;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import com.citco.email.dao.AbstractFileAuditDao;
import com.citco.email.dao.GeneralReportDao;
import com.citco.email.dao.IAuditLogger;
import com.citco.email.dao.IFileReaderCallback;
import com.citco.email.utils.ConsoleUtil;

public class GeneratePasswordServices extends AbstractFileAuditDao {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	IAuditLogger auditLogger = new GeneralReportDao();
	ArrayList<String> records = new ArrayList<String>();
	AtomicBoolean flag = new AtomicBoolean(false);

	public String generatePassword() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String generatedPassword = new String(array, Charset.forName("UTF-8"));
		return generatedPassword;
	}

	public void changePassword() {

		consoleUtil.printLn("Enter your email:");
		String email = consoleUtil.readLine();
		IFileReaderCallback iFileReaderCallback = new IFileReaderCallback() {

			@Override
			public void onLineRead(String str) {
				String[] data = str.split(",");
				String finalRecord = "";
				if (data[2].toString().equals(email)) {
					consoleUtil.printLn("Enter new password:");
					String newPassword = consoleUtil.readLine();

					consoleUtil.printLn("Retype new password:");
					String retypePassword = consoleUtil.readLine();

					if (newPassword.equals(retypePassword)) {
						data[3] = retypePassword;
						for (int i = 0; i < data.length - 1; i++)
							finalRecord += data[i] + ",";

						System.out.println(data[data.length - 1]);
						finalRecord += data[data.length - 1];

						records.add(finalRecord);
						consoleUtil.printLn("Password Match! Your password is successfully updated.");
					}
				} else {
					records.add(str);
				}
			}

		};

		try {
			super.readFileLineByLine(iFileReaderCallback);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		auditLogger.doLogAudit(records);
	}
}

