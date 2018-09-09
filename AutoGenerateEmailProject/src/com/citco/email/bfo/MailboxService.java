package com.citco.email.bfo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import com.citco.email.dao.AbstractFileAuditDao;
import com.citco.email.dao.GeneralReportDao;
import com.citco.email.dao.IAuditLogger;
import com.citco.email.dao.IFileReaderCallback;
import com.citco.email.utils.ConsoleUtil;

public class MailboxService extends AbstractFileAuditDao {

	ConsoleUtil consoleUtil = new ConsoleUtil();
	IAuditLogger auditLogger = new GeneralReportDao();
	String newCapacity, oldCapacity;
	ArrayList<String> records = new ArrayList<String>();
	AtomicBoolean flag = new AtomicBoolean(false);

	public void updateMailboxCapacity() {

		consoleUtil.printLn("Enter your email:");
		String email = consoleUtil.readLine();
		IFileReaderCallback iFileReaderCallback = new IFileReaderCallback() {

			@Override
			public void onLineRead(String str) {

				String[] data = str.split(",");
				String finalRecord = "";
				if (data[2].toString().equals(email)) {
					consoleUtil.printLn("Enter new mailbox capacity:");
					newCapacity = consoleUtil.readLine();

					data[6] = newCapacity + "\n";
					for (int i = 0; i < data.length -1 ; i++)
						finalRecord += data[i] + ",";

					System.out.println(data[data.length - 1]);
					finalRecord += data[data.length - 1];

					records.add(finalRecord);
				}
				else {
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

