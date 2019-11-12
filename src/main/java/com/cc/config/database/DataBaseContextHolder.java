package com.cc.config.database;

/*
 * 通过一个enum(枚举)定义标示主从库
 */
public class DataBaseContextHolder {
	
	public enum DataBaseType {
		BJORDER_MASTER("bjorder-master"),
		SHORDER_MASTER("shorder-master"),
		SZORDER_MASTER("szorder-master"),
		HZORDER_MASTER("hzorder-master"),
		BJORDER_SLAVE("bjorder-slave"),
		SHORDER_SLAVE("shorder-slave"),
		SZORDER_SLAVE("szorder-slave"),
		HZORDER_SLAVE("hzorder-slave");
		private String code;
		
		private DataBaseType(String code){
			this.code = code;
		}
		
		public String getCode(){
			return code;
		}
	}
	/*
	 * 此处应用ThreadLocal;
	 * 可以优化选用netty的FastThreadLocal，性能会更好；
	 * ThreadLocal中存储的内容是当前请求读取的是那个库，DataBaseType存储的是具体库的指向，
	 * 通过DataBaseType来判断具体数据源，切换数据库；
	 */
	private static final ThreadLocal<DataBaseType> contextHolder = new ThreadLocal<DataBaseType>();
	
	/**
	 * <B>方法名称：</B>设置数据源类型<BR>
	 * <B>概要说明：</B><BR>
	 * 通过此方法设置具体的数据源
	 */
	public static void setDataBaseType(DataBaseType dataBaseType) {
		if(dataBaseType == null) throw new NullPointerException();
		contextHolder.set(dataBaseType);
	}
	
	
	public static DataBaseType getDataBaseType(){
		//如果获取的DataBaseType为null默认选择ORDER1_MASTER("order1-master")，如果不为null则按照获取确定数据源
		return contextHolder.get() == null ? DataBaseType.BJORDER_MASTER : contextHolder.get();
	}
	
	public static void clearDataBaseType(){
		contextHolder.remove();
	}
}
