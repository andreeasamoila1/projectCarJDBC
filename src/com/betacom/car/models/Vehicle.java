package com.betacom.car.models;

public class Vehicle {

	private Integer id;
	private String vehicleType; 
	private String brand; 
	private String model;
	private Integer numberWheels; 
	private Double wheelSize;
	private String fuelType;  
	private String transmission; 
	private String category; 
	private String color;
	private Integer year; 
	private String frameMaterial;
	private Integer maxSpeed;
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getNumberWheels() {
		return numberWheels;
	}
	public void setNumberWheels(Integer numberWheels) {
		this.numberWheels = numberWheels;
	}
	public Double getWheelSize() {
		return wheelSize;
	}
	public void setWheelSize(Double wheelSize) {
		this.wheelSize = wheelSize;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getFrameMaterial() {
		return frameMaterial;
	}
	public void setFrameMaterial(String frameMaterial) {
		this.frameMaterial = frameMaterial;
	}
	public Integer getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	
}
