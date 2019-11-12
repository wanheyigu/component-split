package com.cc.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		//	动态的去切换数据源的类型
		return DataBaseContextHolder.getDataBaseType();
	}

}
