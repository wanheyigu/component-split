package com.cc.config.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import com.cc.config.database.DataBaseContextHolder.DataBaseType;
import com.cc.utils.Pair;
import com.cc.utils.SelectorUtil;

/**
 * 核心链路实现
 */
@Aspect
@Component
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class SelectConnectionInterceptor implements Ordered,Interceptor {

	private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SelectConnectionInterceptor.class);
	
	private static String tableName = "";
	
	private static final String SUFFIX_MASTER = "-master";
	
	private static final String SUFFIX_SLAVE = "-slave";
	//注解被调用的时候开始解析
	@Around("@annotation(selectConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, SelectConnection selectConnection) throws Throwable {
		try{
			//1 执行方法前(在加selectConnection的方法执行前进行解析)
			LOGGER.info("--------------- select database source ---------------");
			
			String currentDataBaseName = "";
			//	如果在注解上添加了: name那么就按照其去获取
			if(!StringUtils.isBlank(selectConnection.name())){
				currentDataBaseName = selectConnection.name();
			} else { //未指定注解selectConnection参数，按照传入(调用方法时参数)进行解析
				
				String uuid = (String) proceedingJoinPoint.getArgs()[0];	// uuid
				Pair<String, String> pair = SelectorUtil.getDataBaseAndTable(uuid);
				currentDataBaseName = pair.getObject1();
				tableName = pair.getObject2();
				}
			//判断readOnly，切换主从读写分离
			if(selectConnection.readOnly()){
				currentDataBaseName = currentDataBaseName + SUFFIX_SLAVE;
			} else {
				currentDataBaseName = currentDataBaseName + SUFFIX_MASTER;
			}
			for(DataBaseType type: DataBaseContextHolder.DataBaseType.values()){
				if(!StringUtils.isBlank(currentDataBaseName)){
					String typeCode = type.getCode();
					if(typeCode.equals(currentDataBaseName)){
						DataBaseContextHolder.setDataBaseType(type);//设置切换
						System.err.println("----Interceptor: code :" + DataBaseContextHolder.getDataBaseType().getCode());
					}
				}
			}
			
			//2 开始执行方法
			Object result = proceedingJoinPoint.proceed();
			
			//3 执行方法后
			return result;
		} finally {
			DataBaseContextHolder.clearDataBaseType();
			LOGGER.info("---------------clear database connection---------------");
		}
	}
	
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		LOGGER.info("---------------MyInterceptor---------------");
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性;：MetaObject是Mybatis提供的一个用于方便、
        //优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
        MetaObject metaObject = MetaObject
            .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());
        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
        String id = mappedStatement.getId();
        
        //sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        LOGGER.info("---------------sql语句类型:{}---------------",sqlCommandType);
        //数据库连接信息
//        Configuration configuration = mappedStatement.getConfiguration();
//        ComboPooledDataSource dataSource = (ComboPooledDataSource)configuration.getEnvironment().getDataSource();
//        dataSource.getJdbcUrl();
 
        BoundSql boundSql = statementHandler.getBoundSql();     
        
        //获取到原始sql语句
        String sql = boundSql.getSql();
        //boundSql.getParameterMappings();
        LOGGER.info("----传入的原始sql语句:{}--------",sql);
        /**
         	*  根据语句类型进行sql语句的修改，此处修改基本一致，分类型操作是方便后期优化
         	* 或有不同参数修改需求情况；
         */
        if(sqlCommandType.equals("SELECT")) {
        	 sql= sql.replaceAll("TABLE", tableName);
        	 LOGGER.info("-------修改后sql语句:{}---------",sql);
        	 Field field = boundSql.getClass().getDeclaredField("sql");
             field.setAccessible(true);
             field.set(boundSql, sql);
      
             return invocation.proceed();
        }
        else if(sqlCommandType.equals("DELETE")){
        	sql= sql.replaceAll("TABLE", tableName);
        	LOGGER.info("-------修改后sql语句:{}---------",sql);
        	Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, sql);
     
            return invocation.proceed();
        }
        else if(sqlCommandType.equals("UPDATE")){
        	sql= sql.replaceAll("TABLE", tableName);
        	LOGGER.info("-------修改后sql语句:{}---------",sql);
        	Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, sql);
     
            return invocation.proceed();
        }
        else if(sqlCommandType.equals("INSERT")){
        	sql= sql.replaceAll("TABLE", tableName);
        	LOGGER.info("-------修改后sql语句:{}---------",sql);
        	Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, sql);
     
            return invocation.proceed();
        }
 
        return invocation.proceed();
	}


	@Override
	public Object plugin(Object target) {
		
		if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } 
        return target;
        
	}


	@Override
	public void setProperties(Properties properties) {
		
	}

}


