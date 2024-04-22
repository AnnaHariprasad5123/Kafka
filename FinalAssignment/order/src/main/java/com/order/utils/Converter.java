package com.order.utils;

import com.order.dto.OrderDto;
import com.order.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Converter {
	ModelMapper modelMapper;
	@Autowired
	public Converter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	public OrderDto entityToDTO(Order order){
		return modelMapper.map(order, OrderDto.class);
	}
	public Order dtoToEntity(OrderDto orderDto){
		return modelMapper.map(orderDto, Order.class);
	}
}
