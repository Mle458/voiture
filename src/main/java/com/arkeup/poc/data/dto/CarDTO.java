package com.arkeup.poc.data.dto;

public class CarDTO {
	
	private Long id;
	private String model;
	private String brand;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public CarDTO(String model, String brand) {
		
		this.model = model;
		this.brand = brand;
	}

	public CarDTO() {
		
	}
	
}
