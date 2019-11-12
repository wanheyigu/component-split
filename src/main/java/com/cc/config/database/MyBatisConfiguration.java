package com.cc.config.database;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cc.config.database.DataBaseContextHolder.DataBaseType;


@Configuration
//启用事务注解(此处关闭事务)@EnableTransactionManagement
//当DataSourceConfiguration加载完成后加载MyBatisConfiguration
//将DataSourceConfiguration中定义的8个数据源加载到MyBatis；
@AutoConfigureAfter(value = {DataSourceConfiguration.class,SelectConnectionInterceptor.class})
public class MyBatisConfiguration {
	
	@Autowired
	private SelectConnectionInterceptor selectConnectionInterceptor;

	@Resource(name= "bjorder-master")
	private DataSource bjOrderMasterDataSource;
	
	@Resource(name= "shorder-master")
	private DataSource shOrderMasterDataSource;
	
	@Resource(name= "szorder-master")
	private DataSource szOrderMasterDataSource;
	
	@Resource(name= "hzorder-master")
	private DataSource hzOrderMasterDataSource;
	
	@Resource(name= "bjorder-slave")
	private DataSource bjOrderSlaveDataSource;
	
	@Resource(name= "shorder-slave")
	private DataSource shOrderSlaveDataSource;
	
	@Resource(name= "szorder-slave")
	private DataSource szOrderSlaveDataSource;
	
	@Resource(name= "hzorder-slave")
	private DataSource hzOrderSlaveDataSource;
	
	/*
	 * 实现动态切换数据源，需要一个代理完成，DynamicDataSource自己创建，通过实现mybatis的AbstractRoutingDataSource
	 * 就可以完成数据源的动态切换；
	 */
	@Bean("dynamicDataSource")
	public DynamicDataSource roundRobinDataSourceProxy(){
		
		Map<Object, Object> targetDataSource = new HashMap<Object, Object>();
		
		targetDataSource.put(DataBaseType.BJORDER_MASTER, bjOrderMasterDataSource);
		targetDataSource.put(DataBaseType.SHORDER_MASTER, shOrderMasterDataSource);
		targetDataSource.put(DataBaseType.SZORDER_MASTER, szOrderMasterDataSource);
		targetDataSource.put(DataBaseType.HZORDER_MASTER, hzOrderMasterDataSource);
		targetDataSource.put(DataBaseType.BJORDER_SLAVE, bjOrderSlaveDataSource);
		targetDataSource.put(DataBaseType.SHORDER_SLAVE, shOrderSlaveDataSource);
		targetDataSource.put(DataBaseType.SZORDER_SLAVE, szOrderSlaveDataSource);
		targetDataSource.put(DataBaseType.HZORDER_SLAVE, hzOrderSlaveDataSource);
		
		//	实例化动态数据源
		DynamicDataSource proxy = new DynamicDataSource();
		//	盛放所以需要切换的数据源
		proxy.setTargetDataSources(targetDataSource);
		//	设置默认的数据源
		proxy.setDefaultTargetDataSource(bjOrderMasterDataSource);
		
		return proxy;
	}
	/*
	 * 加载完成后创建sqlSessionFactory，将数据源交由MyBatis管理，将数据源池化；
	 * 将动态切换代理加入到sqlSessionFactory中；
	 */
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) {
		
		System.err.println("----------------执行--------------");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		//添加mybatis自定义拦截器
		bean.setPlugins(new Interceptor[] {selectConnectionInterceptor});
		bean.setDataSource(dynamicDataSource);
		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:com/cc/mapping/*.xml"));
			SqlSessionFactory sqlSessionFactory = bean.getObject();
			sqlSessionFactory.getConfiguration().setCacheEnabled(Boolean.TRUE);
			return sqlSessionFactory;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	@Bean
//	public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) throws Exception {
//	     return new DataSourceTransactionManager(dynamicDataSource);
//	}
	
	
	
}
