package com.order.service;

import com.order.dto.OrderDto;

import java.util.List;

public interface OrderService {
	public String createOrder(OrderDto orderDto);
	
	public List<OrderDto> getOrders();
	
	public String updateOrder(String id);
}
