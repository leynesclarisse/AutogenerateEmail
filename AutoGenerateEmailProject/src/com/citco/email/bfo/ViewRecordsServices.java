package com.citco.email.bfo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import com.citco.email.dao.AbstractFileAuditDao;
import com.citco.email.dao.GeneralReportDao;
import com.citco.email.dao.IAuditLogger;
import com.citco.email.dao.IFileReaderCallback;
import com.citco.email.utils.ConsoleUtil;

public class ViewRecordsServices extends AbstractFileAuditDao {
	ConsoleUtil consoleUtil = new ConsoleUtil();
	IAuditLogger auditLogger = new GeneralReportDao();
	String newCapacity, oldCapacity;
	ArrayList<String> records = new ArrayList<String>();
	AtomicBoolean flag = new AtomicBoolean(false);

	public void viewRecords() {

		IFileReaderCallback iFileReaderCallback = new IFileReaderCallback() {

			@Override
			public void onLineRead(String str) {
				String[] data = str.split(",");
				consoleUtil.print("%s, %s, %s, %s, %s, %s \n", data[0],data[1],data[2],data[3],data[4],data[5],data[6]);
			}
		};

		try {
			super.readFileLineByLine(iFileReaderCallback);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
