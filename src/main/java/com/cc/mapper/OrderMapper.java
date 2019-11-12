package com.cc.mapper;

import java.util.Map;
import javax.persistence.Id;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cc.entity.Order;

@Mapper
public interface OrderMapper extends com.cc.config.database.BaseMapper<Order>{
	
	Order shardSelectByPrimaryKey(@Param("orderId")String orderId);
	
	boolean shardDeleteByPrimaryKey(@Param("orderId")String orderId);
	
	int shardInsert(Map<String, Object> params);
	
	int shardUpdateByPrimaryKey(Map<String, Object> params);
}