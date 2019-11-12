package com.cc.config.database;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class SplitDruidProperties {
	
	
	private String type = "com.alibaba.druid.pool.DruidDataSource";
	private String driver = "com.mysql.jdbc.Driver";
	//private String driver = "com.mysql.cj.jdbc.Driver";
	private String bjOrderMasterUrl = "jdbc:mysql://192.168.85.140:3306/bjorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	private String shOrderMasterUrl = "jdbc:mysql://192.168.85.140:3306/shorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	private String szOrderMasterUrl = "jdbc:mysql://192.168.85.140:3306/szorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	private String hzOrderMasterUrl = "jdbc:mysql://192.168.85.140:3306/hzorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	
	private String bjOrderSlaveUrl = "jdbc:mysql://192.168.85.142:3306/bjorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	private String shOrderSlaveUrl = "jdbc:mysql://192.168.85.142:3306/shorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	private String szOrderSlaveUrl = "jdbc:mysql://192.168.85.142:3306/szorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	private String hzOrderSlaveUrl = "jdbc:mysql://192.168.85.142:3306/hzorder?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true";
	
	
	
	private String username = "root";
	private String password = "zxy8900461";
	
	
	
	
	
	private int initialSize = 5;
	private int minIdle = 1;
	private int maxIdle = 10;
	private int maxActive = 100;
	private int maxWait = 60000;
	private int timeBetweenEvictionRunsMillis = 60000;
	private int minEvictableIdleTimeMillis = 300000;
	private String validationQuery = "SELECT 1 FROM DUAL";
	private boolean testWhileIdle = true;
	private boolean testOnBorrow= false;
	private boolean testOnReturn= false;
	private boolean poolPreparedStatements= true;
	private int maxPoolPreparedStatementPerConnectionSize = 20;
	//private String[] filters= {"stat","wall","log4j"};
	private boolean useGlobalDataSourceStat = true;
	
	
}
