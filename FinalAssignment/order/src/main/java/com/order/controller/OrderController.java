package com.order.controller;

import com.order.dto.OrderDto;
import com.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.order.utils.Constants.ENDPOINT;

@Slf4j
@RestController
@RequestMapping(ENDPOINT)
public class OrderController {
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping
	public String createOrder(@RequestBody OrderDto orderDto) {
		log.info("Received request to create order: {}", orderDto);
		String result = orderService.createOrder(orderDto);
		log.info("Result : {}", result);
		return result;
	}
	
	@GetMapping
	public List<OrderDto> getAllOrders() {
		log.info("Received request to get all orders.");
		List<OrderDto> orders = orderService.getOrders();
		log.info("Returning {} orders.", orders.size());
		return orders;
	}
}
