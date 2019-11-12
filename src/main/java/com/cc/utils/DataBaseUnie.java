package com.cc.utils;

import java.util.HashMap;
import java.util.Map;

public class DataBaseUnie {
	
	static Map<String,String> m = new HashMap();
	
	public String getDataBaseUnie(String id) {
		
		m.put("1001", "bj");
		m.put("1002", "sh");
		m.put("1003", "sz");
		m.put("1004", "hz");
		
		String dataBase = m.get(id);  
		
		return dataBase;
	}

}
