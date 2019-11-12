package com.cc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cc.utils.Pair;

@Component
//数据源切换实现
public class SelectorUtil {
	
	//@Autowired
    static DataBaseUnie dataBaseUnie = new DataBaseUnie();
	
	//传入uuid按照地域和时间查找表名
	public static Pair<String, String> getDataBaseAndTable(String uuid) {
		//取uuid前四位
		String dataBase = dataBaseUnie.getDataBaseUnie(uuid.substring(0, 4))+"order";
		//定位表
		String selectTable = dataBaseUnie.getDataBaseUnie(uuid.substring(0, 4))+uuid.substring(4, 10);
		System.err.println("----------- SelectorUtil: selectDataBase: " + dataBase + " ----------------");
		System.err.println("----------- SelectorUtil: selectTable: " + selectTable + " ----------------");
		return new Pair<String, String>(dataBase, selectTable);
	}
	
	//模拟用户IP取用户区域地址作为UUID生产的前四位，此处用随机生产
	public static String getRandomAddress() {
		String randomAddress = "";
		String[] address = {"1001", "1002", "1003", "1004"};
		int index = (int) (Math.random() * address.length);
		randomAddress = address[index];

		return randomAddress;
	}
	//取年月作为UUID生产的5至10位
	public static String getDate() {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");//设置日期格式
		String nowDate = df.format(new Date());

		return nowDate;
	}
	
	//单元测试：通过KeyUtil随机生成uuid，每次结果不同
	public static void main(String[] args) {
		/* hash取值测试代码块
		String uuid = KeyUtil.generatorUUID();
		int code = Math.abs(uuid.hashCode());
		System.err.println("code: " + code);
		
		Pair<int, int> pair = getDataBaseAndTableNumber(uid);
		int selectDataBaseNumber = (code/5)%4 + 1;
		int selectTableNumber = code%5;
		
		System.err.println("selectDataBaseNumber: " + p.getObject1());
		System.err.println("selectTableNumber: " + p.getObject2());
		 */
		/*地域取值代码块
		String uuid = KeyUtil.generatorUUID();
		//int code = Math.abs(uuid.hashCode());
		//System.err.println("code: " + code);
		
		String uid = "1001201911"+uuid;
		System.out.println("uid============="+uid);
		Pair<String, String> p = getDataBaseAndTable(uid);
		
		//int selectDataBaseNumber = (code/5)%4 + 1;
		//int selectTableNumber = code%5;
		
		System.err.println("selectDataBaseNumber: " + p.getObject1());
		System.err.println("selectTableNumber: " + p.getObject2());
		*/
		
	}
	
}
