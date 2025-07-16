package com.betacom.car.jdbc.models;

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
	
	public Vehicle() {
		super();
	}
	public Vehicle(Integer id, String vehicleType, String brand, String model, Integer numberWheels, Double wheelSize,
			String fuelType, String transmission, String category, String color, Integer year, String frameMaterial,
			Integer maxSpeed) {
		super();
		this.id = id;
		this.vehicleType = vehicleType;
		this.brand = brand;
		this.model = model;
		this.numberWheels = numberWheels;
		this.wheelSize = wheelSize;
		this.fuelType = fuelType;
		this.transmission = transmission;
		this.category = category;
		this.color = color;
		this.year = year;
		this.frameMaterial = frameMaterial;
		this.maxSpeed = maxSpeed;
	}
	
	
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
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vehicleType=" + vehicleType + ", brand=" + brand + ", model=" + model
				+ ", numberWheels=" + numberWheels + ", wheelSize=" + wheelSize + ", fuelType=" + fuelType
				+ ", transmission=" + transmission + ", category=" + category + ", color=" + color + ", year=" + year
				+ ", frameMaterial=" + frameMaterial + ", maxSpeed=" + maxSpeed + "]";
	}





	
}
