package com.betacom.car.jdbc.process;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.models.Vehicle;
import com.betacom.car.jdbc.singleton.SQLConfiguration;

public class VehicleControl {

	public Vehicle verify(Vehicle vehicle, String[] params) throws AcademyException{
		
		String vehicleType = params[ProcessJDBC.VEHICLE_TYPE].substring(0,1).toLowerCase();
		vehicle.setVehicleType(vehicleType);
		if (!SQLConfiguration.getInstance().isValidValue("vehicleType", params[ProcessJDBC.VEHICLE_TYPE]))
			throw new AcademyException("Vehicle type invalid");
		vehicle.setVehicleType(vehicleType);
		
		
		String brand = params[ProcessJDBC.BRAND].substring(0,1).toLowerCase();
		if (!SQLConfiguration.getInstance().isValidValue("brand", params[ProcessJDBC.BRAND]))
			throw new AcademyException("brand invalid");
		vehicle.setBrand(brand);
		
		String model = params[ProcessJDBC.MODEL].substring(0,1).toLowerCase();
		if (!SQLConfiguration.getInstance().isValidValue("model", params[ProcessJDBC.MODEL]))
			throw new AcademyException("model invalid");
		vehicle.setModel(model);
		
			if (!SQLConfiguration.getInstance().isValidValue("wheelsNumber", params[ProcessJDBC.NUMBER_WHEELS]))
				throw new AcademyException("wheelsNumber invalid");
			vehicle.setNumberWheels(Integer.parseInt(params[ProcessJDBC.NUMBER_WHEELS]));			
			if (!SQLConfiguration.getInstance().isValidValue("wheelSize", params[ProcessJDBC.WHEEL_SIZE]))
				throw new AcademyException("Wheel size invalid");
			vehicle.setWheelSize(Double.parseDouble(params[ProcessJDBC.WHEEL_SIZE]));			
		
		String fuelType = params[ProcessJDBC.FUEL_TYPE].substring(0,1).toLowerCase();
		if (!SQLConfiguration.getInstance().isValidValue("fuelType", params[ProcessJDBC.FUEL_TYPE]))
			throw new AcademyException("Fuel type invalid");
		vehicle.setFuelType(fuelType);
		
		String transmission = params[ProcessJDBC.TRANSMISSION].substring(0,1).toLowerCase();
		if (!SQLConfiguration.getInstance().isValidValue("transmission", params[ProcessJDBC.TRANSMISSION]))
			throw new AcademyException("transmission invalid");
		vehicle.setTransmission(transmission);
		
		String category = params[ProcessJDBC.CATEGORY].substring(0,1).toLowerCase();
		if (!SQLConfiguration.getInstance().isValidValue("category", params[ProcessJDBC.CATEGORY]))
			throw new AcademyException("category invalid");
		vehicle.setCategory(category);
		
		String color = params[ProcessJDBC.COLOR].substring(0,1).toLowerCase();
		if (!SQLConfiguration.getInstance().isValidValue("color", params[ProcessJDBC.COLOR]))
			throw new AcademyException("bracolornd invalid");
		vehicle.setColor(color);

		if (!SQLConfiguration.getInstance().isValidValue("year", params[ProcessJDBC.YEAR]))
			throw new AcademyException("year invalid");
		vehicle.setYear(Integer.parseInt(params[ProcessJDBC.YEAR]));			
	
		
		String frameMaterial = params[ProcessJDBC.FRAME_MATERIAL].substring(0,1).toLowerCase();
		if (!SQLConfiguration.getInstance().isValidValue("frameMaterial", params[ProcessJDBC.FRAME_MATERIAL]))
			throw new AcademyException("frame material invalid");
		vehicle.setFrameMaterial(frameMaterial);
		
		if (!SQLConfiguration.getInstance().isValidValue("maxSped", params[ProcessJDBC.MAX_SPEED]))
			throw new AcademyException("Max speed invalid");
		vehicle.setMaxSpeed(Integer.parseInt(params[ProcessJDBC.MAX_SPEED]));			

		
		
		
		
		return vehicle;
	}
}
