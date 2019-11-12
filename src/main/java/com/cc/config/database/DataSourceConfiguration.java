package com.cc.config.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
/*
 * 将配置中的数据源注入
 */
@Configuration		//相当于一个xml配置文件
public class DataSourceConfiguration {
	
	private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DataSourceConfiguration.class);
	
	@Autowired
	private SplitDruidProperties splitDruidProperties;
	
	@Value("${druid.type}")
	private Class<? extends DataSource> dataSourceType;
	
	@Bean(name = "bjorder-master")
	@Primary
	public DataSource bjorderMasterDataSource() throws SQLException {
		
		DataSource bjorderMasterDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getBjOrderMasterUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= bjorderMasterDataSource: {} ================", bjorderMasterDataSource);
				
		return bjorderMasterDataSource;
	}
	
	@Bean(name = "shorder-master")
	public DataSource shorderMasterDataSource() throws SQLException {
		DataSource shorderMasterDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getShOrderMasterUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= shorderMasterDataSource: {} ================", shorderMasterDataSource);
		return shorderMasterDataSource;
	}
	
	@Bean(name = "szorder-master")
	public DataSource szorderMasterDataSource() throws SQLException {
		DataSource szorderMasterDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getSzOrderMasterUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= szorderMasterDataSource: {} ================", szorderMasterDataSource);
		return szorderMasterDataSource;
	}
	
	@Bean(name = "hzorder-master")
	public DataSource hzorderMasterDataSource() throws SQLException {
		DataSource hzorderMasterDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getHzOrderMasterUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= hzorderMasterDataSource: {} ================", hzorderMasterDataSource);
		return hzorderMasterDataSource;
	}
	
	@Bean(name = "bjorder-slave")
	public DataSource bjorderSlaveDataSource() throws SQLException {
		DataSource bjorderSlaveDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getBjOrderSlaveUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= bjorderSlaveDataSource: {} ================", bjorderSlaveDataSource);
		return bjorderSlaveDataSource;
	}

	@Bean(name = "shorder-slave")
	public DataSource shorderSlaveDataSource() throws SQLException {
		DataSource shorderSlaveDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getShOrderSlaveUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= shorderSlaveDataSource: {} ================", shorderSlaveDataSource);
		return shorderSlaveDataSource;
	}
	
	@Bean(name = "szorder-slave")
	public DataSource szorderSlaveDataSource() throws SQLException {
		DataSource szorderSlaveDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getSzOrderSlaveUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= szorderSlaveDataSource: {} ================", szorderSlaveDataSource);
		return szorderSlaveDataSource;
	}
	
	@Bean(name = "hzorder-slave")
	public DataSource hzorderSlaveDataSource() throws SQLException {
		DataSource hzorderSlaveDataSource = DataSourceBuilder.create()
				.type(dataSourceType)
				.driverClassName(splitDruidProperties.getDriver())
				.url(splitDruidProperties.getHzOrderSlaveUrl())
				.username(splitDruidProperties.getUsername())
				.password(splitDruidProperties.getPassword())
				.build();
		LOGGER.info("============= hzorderSlaveDataSource: {} ================", hzorderSlaveDataSource);
		return hzorderSlaveDataSource;
	}
	
	@Bean
	public ServletRegistrationBean druidServlet(){
		//spring boot 的方式 自己写一个servlet 
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("allow", "192.168.1.1");
		//reg.addInitParameter("deny", "/deny");
		LOGGER.info("============= init druid servlet ================");
		return reg;
	}
	
	@Bean
	public FilterRegistrationBean druidFilter(){
		FilterRegistrationBean ftr = new FilterRegistrationBean();
		ftr.setFilter(new WebStatFilter());
		ftr.addUrlPatterns("/*");
		ftr.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico, /druid/*");
		LOGGER.info("============= init druid filter ================");
		return ftr;
	}
	
}
