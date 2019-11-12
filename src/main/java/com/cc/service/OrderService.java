package com.cc.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import com.cc.config.database.SelectConnection;
import com.cc.entity.Order;
import com.cc.mapper.OrderMapper;
import com.cc.utils.FastJsonConvertUtil;
import com.cc.utils.Pair;
import com.cc.utils.SelectorUtil;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@SelectConnection(readOnly = true)
	public Order shardSelectByPrimaryKey(String uuid){
		return orderMapper.shardSelectByPrimaryKey(uuid);
	}
	
	@SelectConnection
	public int shardInsert(String uuid, Order order){
		JSONObject json = FastJsonConvertUtil.convertObjectToJSONObject(order);
		Map<String, Object> params = FastJsonConvertUtil.convertJSONToObject(json, Map.class);
		return orderMapper.shardInsert(params);
	}
	
	@SelectConnection
	public int shardUpdateByPrimaryKey(String uuid, Order tradeDetail){ 
		JSONObject json = FastJsonConvertUtil.convertObjectToJSONObject(tradeDetail);
		Map<String, Object> params = FastJsonConvertUtil.convertJSONToObject(json, Map.class);
		
		return orderMapper.shardUpdateByPrimaryKey(params);
	}
	@SelectConnection()
	public boolean shardDelete(String uuid){
		boolean ok = orderMapper.shardDeleteByPrimaryKey(uuid);
		return ok;
	}

}
