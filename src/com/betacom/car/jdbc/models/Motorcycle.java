package com.betacom.car.jdbc.models;

public class Motorcycle extends Vehicle{
	private String plate; //deve essere univoca...
	private String bodyStyle; // naked
	private Integer engineCapacity;
	private boolean hasABS;
	private String transmissionType;
	private int numberOfGears;
	private Vehicle id;
	
	

	public Motorcycle() {
		super();
	}




	public Motorcycle(String plate, String bodyStyle, Integer engineCapacity, boolean hasABS, String transmissionType,
			int numberOfGears, Vehicle id) {
		super();
		this.plate = plate;
		this.bodyStyle = bodyStyle;
		this.engineCapacity = engineCapacity;
		this.hasABS = hasABS;
		this.transmissionType = transmissionType;
		this.numberOfGears = numberOfGears;
		this.id = id;
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
	public boolean isHasABS() {
		return hasABS;
	}
	public void setHasABS(boolean hasABS) {
		this.hasABS = hasABS;
	}
	public String getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	public int getNumberOfGears() {
		return numberOfGears;
	}
	public void setNumberOfGears(int numberOfGears) {
		this.numberOfGears = numberOfGears;
	}
	@Override
	public String toString() {
		return "Motorcycle [plate=" + plate + ", bodyStyle=" + bodyStyle + ", engineCapacity=" + engineCapacity
				+ ", hasABS=" + hasABS + ", transmissionType=" + transmissionType + ", numberOfGears=" + numberOfGears
				+ ", getVehicleType()=" + getVehicleType() + ", getId()=" + getId() + ", getBrand()=" + getBrand()
				+ ", getModel()=" + getModel() + ", getNumberWheels()=" + getNumberWheels() + ", getWheelSize()="
				+ getWheelSize() + ", getFuelType()=" + getFuelType() + ", getTransmission()=" + getTransmission()
				+ ", getCategory()=" + getCategory() + ", getColor()=" + getColor() + ", getYear()=" + getYear()
				+ ", getFrameMaterial()=" + getFrameMaterial() + ", getMaxSpeed()=" + getMaxSpeed() + "]";
	}

}
