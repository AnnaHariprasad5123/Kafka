package com.order.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "order")
public class Order {
	@Id
	private String id;
	
	@Field(name = "item_name")
	private String item;
	
	private Double price;
	
	private String status;
	
	@Field(name = "user_id")
	private Integer userId;
}
