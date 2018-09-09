package com.citco.email.dao;

import java.util.ArrayList;

public interface IAuditLogger {
	void doLogAudit(String str);
	void doLogAudit(ArrayList<String> array);
}