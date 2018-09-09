package com.citco.email.dao;

import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractFileAuditDao extends AbtractFileDao  implements IAuditLogger{
	
	@Override
	String getLocation() {
		return "P:\\VDI_profile\\Cleynes\\Desktop\\clarisse leynes\\Eclipse\\EmailForNewHires\\src\\com\\citco\\email\\Reports\\EmployeeReports.csv";
	}
	
	@Override
	public void doLogAudit(String str) {
		try {
			super.appendTextToFile(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doLogAudit(ArrayList<String> array) {
		try {
			super.appendTextToFile(array);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
