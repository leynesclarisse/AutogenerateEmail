package com.citco.email.dao;

public class GeneralReportDao extends AbstractFileAuditDao {
	@Override
	String getLocation() {
		return "P:\\VDI_profile\\Cleynes\\Desktop\\clarisse leynes\\Eclipse\\EmailForNewHires\\src\\com\\citco\\email\\Reports\\EmployeeReports.csv";
	}
}