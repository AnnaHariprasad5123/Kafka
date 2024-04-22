package com.order.service.implementaion;

import com.order.repository.OrderRepository;
import com.order.dto.OrderDto;
import com.order.entity.Order;
import com.order.service.KafkaService;
import com.order.service.OrderService;
import com.order.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.order.utils.Constants.SUCCESS;
import static com.order.utils.Constants.TOPIC_LOCATION;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository orderRepository;
	
	private Converter converter;
	
	private KafkaService kafkaService;
	
	private String previousValue = null;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, Converter converter, KafkaService kafkaService) {
		this.orderRepository = orderRepository;
		this.converter = converter;
		this.kafkaService = kafkaService;
	}
	
	
	
	@Override
	public String createOrder(OrderDto orderDto) {
		log.info("Creating new order: {}", orderDto);
		Order order = orderRepository.save(converter.dtoToEntity(orderDto));
		kafkaService.orderDetails(order.getId());
		log.info("Order created successfully");
		return SUCCESS;
	}
	
	@Override
	public List<OrderDto> getOrders() {
		log.info("Fetching all orders");
		List<OrderDto> orders = orderRepository.findAll().stream()
				.map(order -> converter.entityToDTO(order))
				.toList();
		log.info("Returned {} orders", orders.size());
		return orders;
	}
	
	@Override
	public String updateOrder(String id) {
		log.info("Updating order with ID: {}", id);
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			order.setStatus("done");
			orderRepository.save(order);
			log.info("Order with ID {} updated successfully", id);
			return SUCCESS;
		} else {
			log.error("Order with ID {} not found", id);
			return "Order with ID " + id + " not found";
		}
	}
	
	@KafkaListener(topics = TOPIC_LOCATION)
	public void consumeOrderUpdates(ConsumerRecord<String, String> record) {
		String message = record.value();
		log.info("Consumed message from location topic: " + message);
		
		if ("done".equals(message) && previousValue != null) {
			log.info("Previous value: " + previousValue);
			updateOrder(previousValue);
		}
		
		previousValue = message;
	}
	
}
