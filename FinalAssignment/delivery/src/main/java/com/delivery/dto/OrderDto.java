package com.delivery.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
	private Integer id;
	private String item;
	private Double price;
	private String status;
	private Integer userId;
}