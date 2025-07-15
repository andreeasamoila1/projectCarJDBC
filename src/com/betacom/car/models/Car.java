package com.betacom.car.models;

public class Car extends Vehicle{
	private Integer numberOfDoors;
	private String plate; 
	private String bodyStyle; 
	private Integer engineCapacity;
	private Integer numberOfGears;
	private boolean hasNavigationSystem;
    private boolean hasParkingSensors;
    
	public Integer getNumberOfDoors() {
		return numberOfDoors;
	}
	public void setNumberOfDoors(Integer numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getBodyStyle() {
		return bodyStyle;
	}
	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}
	public Integer getEngineCapacity() {
		return engineCapacity;
	}
	public void setEngineCapacity(Integer engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	public Integer getNumberOfGears() {
		return numberOfGears;
	}
	public void setNumberOfGears(Integer numberOfGears) {
		this.numberOfGears = numberOfGears;
	}
	public boolean isHasNavigationSystem() {
		return hasNavigationSystem;
	}
	public void setHasNavigationSystem(boolean hasNavigationSystem) {
		this.hasNavigationSystem = hasNavigationSystem;
	}
	public boolean isHasParkingSensors() {
		return hasParkingSensors;
	}
	public void setHasParkingSensors(boolean hasParkingSensors) {
		this.hasParkingSensors = hasParkingSensors;
	}
	@Override
	public String toString() {
		return "Car [numberOfDoors=" + numberOfDoors + ", plate=" + plate + ", bodyStyle=" + bodyStyle
				+ ", engineCapacity=" + engineCapacity + ", numberOfGears=" + numberOfGears + ", hasNavigationSystem="
				+ hasNavigationSystem + ", hasParkingSensors=" + hasParkingSensors + ", getVehicleType()="
				+ getVehicleType() + ", getId()=" + getId() + ", getBrand()=" + getBrand() + ", getModel()="
				+ getModel() + ", getNumberWheels()=" + getNumberWheels() + ", getWheelSize()=" + getWheelSize()
				+ ", getFuelType()=" + getFuelType() + ", getTransmission()=" + getTransmission() + ", getCategory()="
				+ getCategory() + ", getColor()=" + getColor() + ", getYear()=" + getYear() + ", getFrameMaterial()="
				+ getFrameMaterial() + ", getMaxSpeed()=" + getMaxSpeed()  + "]";
	}
	
	
    
	
}