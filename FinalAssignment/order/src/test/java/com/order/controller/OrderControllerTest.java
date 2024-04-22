package com.order.controller;

import com.order.dto.OrderDto;
import com.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.order.utils.Constants.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderControllerTest {
	
	@Mock
	private OrderService orderService;
	
	@InjectMocks
	private OrderController orderController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testCreateOrder_ReturnsCreatedOrderId() {
		OrderDto orderDto = new OrderDto(1,"pizza", 100.0,"pending" ,1);
		
		when(orderService.createOrder(orderDto)).thenReturn(SUCCESS);
		
		String response = orderController.createOrder(orderDto);
		assertEquals(SUCCESS, response);
	}
	
	@Test
	void testGetAllOrders_ReturnsListOfOrderDto() {
		List<OrderDto> orderDtoList = Arrays.asList(
				new OrderDto(1,"pizza", 100.0,"pending" ,1),
				new OrderDto(2,"burger", 110.0,"pending" ,2)
				);

		when(orderService.getOrders()).thenReturn(orderDtoList);

		List<OrderDto> response = orderController.getAllOrders();

		assertEquals(orderDtoList.size(), response.size());
	}
}
