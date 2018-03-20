package com.iiitb.ooadvoid.pojo;

import java.util.Date;

public class FlopkartOrder
{
	private Integer id;
	private String shippingAddress;
	private Integer userId;
	private String itemId;
	private Integer orderId;
	private String status;
	private String orderDate;
	private Integer totalAmount;

	public Integer getId()
	{
		return id;
	}

	public String getShippingAddress()
	{
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	public Integer getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(String orderDate)
	{
		this.orderDate = orderDate;
	}

	public Integer getTotalAmount()
	{
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount)
	{
		this.totalAmount = totalAmount;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

}
