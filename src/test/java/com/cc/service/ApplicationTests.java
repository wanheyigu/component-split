package com.cc.service;


import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cc.Application;
import com.cc.entity.Order;
import com.cc.utils.FastJsonConvertUtil;
import com.cc.utils.KeyUtil;
import com.cc.utils.SelectorUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SelectorUtil selectorUtil;
	
	@Test
	public void shardInsertTest() throws Exception {
		String key = KeyUtil.generatorUUID();
		String randomAddress = selectorUtil.getRandomAddress();//取地址代码作为ID前四位
		String newDate = selectorUtil.getDate();//取当前时间的年月作为ID5到10位
		System.err.println("key: " + key);
		Date currentDateTime = new Date(); 
		Order order = new Order();
		order.setOrderType("99");
		order.setPkgPrice(new BigDecimal(100.0));
		order.setPlatformId("007");
		order.setPlatformOrderId("113333234567890");
		order.setPoiId("333399933");
		order.setRemark("订单备注");
		order.setCityId("190000");
		order.setSenderAddress("国贸");
		order.setSenderName("八斗鸡");
		order.setSenderPhone("8888888");
		order.setSenderLng(8743221);
		order.setSenderLat(1234233);
		
		order.setReceiverAddress("双井");
		order.setReceiverName("张三");
		order.setReceiverPhone("9999999");
		order.setReceiverLng(2929292);
		order.setReceiverLat(1232112);
		
		order.setOrderId(randomAddress+newDate+key);
		order.setCreateBy("sys-init");
		order.setCreateTime(currentDateTime);
		order.setUpdateBy("sys-init");
		order.setUpdateTime(currentDateTime);
		orderService.shardInsert(randomAddress+newDate+key, order);
	}
	/*
	@Test
	public void shardSelectTest() throws Exception {
		
		//Order order = orderService.shardSelectByPrimaryKey("order", "b54aeede-021f-11ea-98a1-408d5c8126e3");
		Order order = orderService.shardSelectByPrimaryKey("1003201911bc0cf3c2-0394-11ea-8cff-408d5c8126e3");
		System.err.println(FastJsonConvertUtil.convertObjectToJSONObject(order));
	}
	
	@Test
	public void shardDelectTest() throws Exception {
		boolean ok = orderService.shardDelete("1002201911a70bef16-03c1-11ea-a2f6-408d5c8126e3");
		System.err.println("删除数据"+ok);
	}
	
	
	@Test
	public void shardUpdateTest() throws Exception {
		String key = KeyUtil.generatorUUID();
		String randomAddress = selectorUtil.getRandomAddress();//取地址代码作为ID前四位
		String newDate = selectorUtil.getDate();//取当前时间的年月作为ID5到10位
		System.err.println("key: " + key);
		Date currentDateTime = new Date(); 
		Order order = new Order();
		order.setOrderType("99");
		order.setPkgPrice(new BigDecimal(100.0));
		order.setPlatformId("099");
		order.setPlatformOrderId("113333234567890");
		order.setPoiId("333399933");
		order.setRemark("订单备注");
		order.setCityId("19999999");
		order.setSenderAddress("国贸");
		order.setSenderName("战斗机");
		order.setSenderPhone("8888888");
		order.setSenderLng(8743221);
		order.setSenderLat(1234233);
		
		order.setReceiverAddress("双井");
		order.setReceiverName("张三");
		order.setReceiverPhone("9999999");
		order.setReceiverLng(2929292);
		order.setReceiverLat(1232112);
		
		order.setOrderId("1001201911296f8cc1-03bd-11ea-a6eb-408d5c8126e3");
		order.setCreateBy("sys-init");
		order.setCreateTime(currentDateTime);
		order.setUpdateBy("sys-init");
		order.setUpdateTime(currentDateTime);
		orderService.shardUpdateByPrimaryKey("1001201911296f8cc1-03bd-11ea-a6eb-408d5c8126e3", order);
	}*/	

}
