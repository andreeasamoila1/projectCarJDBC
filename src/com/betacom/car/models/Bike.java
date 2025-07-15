package com.betacom.car.models;

public class Bike extends Vehicle{
	private String type;
	private String suspensionType; 
	private Boolean folding;
	private String brakeType;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuspensionType() {
		return suspensionType;
	}
	public void setSuspensionType(String suspensionType) {
		this.suspensionType = suspensionType;
	}
	public Boolean getFolding() {
		return folding;
	}
	public void setFolding(Boolean folding) {
		this.folding = folding;
	}
	public String getBrakeType() {
		return brakeType;
	}
	public void setBrakeType(String brakeType) {
		this.brakeType = brakeType;
	}
	@Override
	public String toString() {
		return "Bike [type=" + type + ", suspensionType=" + suspensionType + ", folding=" + folding + ", brakeType="
				+ brakeType + ", getVehicleType()=" + getVehicleType() + ", getId()=" + getId() + ", getBrand()="
				+ getBrand() + ", getModel()=" + getModel() + ", getNumberWheels()=" + getNumberWheels()
				+ ", getWheelSize()=" + getWheelSize() + ", getFuelType()=" + getFuelType() + ", getTransmission()="
				+ getTransmission() + ", getCategory()=" + getCategory() + ", getColor()=" + getColor() + ", getYear()="
				+ getYear() + ", getFrameMaterial()=" + getFrameMaterial() + ", getMaxSpeed()=" + getMaxSpeed()
				+ "]";
	}
	
	
}
