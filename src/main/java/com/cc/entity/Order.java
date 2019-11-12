package com.cc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String orderId;

    private String orderType;

    private String cityId;

    private String platformId;

    private String platformOrderId;

    private String poiId;

    private String senderAddress;

    private String senderPhone;

    private Integer senderLng;

    private Integer senderLat;

    private String senderName;

    private String receiverAddress;

    private String receiverPhone;

    private Integer receiverLng;

    private Integer receiverLat;

    private String receiverName;

    private String remark;

    private BigDecimal pkgPrice;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getPlatformOrderId() {
		return platformOrderId;
	}

	public void setPlatformOrderId(String platformOrderId) {
		this.platformOrderId = platformOrderId;
	}

	public String getPoiId() {
		return poiId;
	}

	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public Integer getSenderLng() {
		return senderLng;
	}

	public void setSenderLng(Integer senderLng) {
		this.senderLng = senderLng;
	}

	public Integer getSenderLat() {
		return senderLat;
	}

	public void setSenderLat(Integer senderLat) {
		this.senderLat = senderLat;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Integer getReceiverLng() {
		return receiverLng;
	}

	public void setReceiverLng(Integer receiverLng) {
		this.receiverLng = receiverLng;
	}

	public Integer getReceiverLat() {
		return receiverLat;
	}

	public void setReceiverLat(Integer receiverLat) {
		this.receiverLat = receiverLat;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getPkgPrice() {
		return pkgPrice;
	}

	public void setPkgPrice(BigDecimal pkgPrice) {
		this.pkgPrice = pkgPrice;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
