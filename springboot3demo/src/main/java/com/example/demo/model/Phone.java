package com.example.demo.model;

public class Phone {

	private Integer id;

	private String brand;

	private String color;

	public Phone() {
	}
	
	public Phone(Integer id, String brand, String color) {
		super();
		this.id = id;
		this.brand = brand;
		this.color = color;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
