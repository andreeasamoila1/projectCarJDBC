package com.betacom.car.jdbc.process;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.models.Car;
import com.betacom.car.jdbc.singleton.SQLConfiguration;

public class ProcessCar {

	public void execute(String[] params) throws AcademyException{
		System.out.println("Begin Process Car");
		if (params.length != ProcessJDBC.CAR_PARAMETERS)
			throw new AcademyException("Error with the car parameters");

		
		Car car = new Car();

		car = (Car)new VehicleControl().verify(car, params);

		if (!SQLConfiguration.getInstance().isValidValue("numberOfDoors", params[ProcessJDBC.NUMBER_OF_DOORS]))
			throw new AcademyException("Invalide number of doors");
		car.setNumberOfDoors(Integer.parseInt(params[ProcessJDBC.NUMBER_OF_DOORS]));			
		

		if (!SQLConfiguration.getInstance().isValidValue("bodyStyleCar", params[ProcessJDBC.BODY_STYLE_CAR]))
			throw new AcademyException("Car body style invalid");
		car.setPlate(params[ProcessJDBC.BODY_STYLE_CAR]);
		
		if (!SQLConfiguration.getInstance().isValidValue("engineCapacity", params[ProcessJDBC.ENGINE_CAPACITY_CAR]))
			throw new AcademyException("Engine capacity invalid");
		car.setEngineCapacity(Integer.parseInt(params[ProcessJDBC.ENGINE_CAPACITY_CAR]));
		
		if (!SQLConfiguration.getInstance().isValidValue("numberOfGearsCar", params[ProcessJDBC.NUMBER_OF_GEARS_CAR]))
			throw new AcademyException("Number of gears car invalid");
		car.setNumberOfGears(Integer.parseInt(params[ProcessJDBC.NUMBER_OF_GEARS_CAR]));
		
		if (!SQLConfiguration.getInstance().isValidValue("hasNavigationSystem", params[ProcessJDBC.HAS_NAVIGATION_SYSTEM]))
			throw new AcademyException("Navigation system invalid");
		car.setHasNavigationSystem(Boolean.parseBoolean(params[ProcessJDBC.HAS_NAVIGATION_SYSTEM]));
		
		if (!SQLConfiguration.getInstance().isValidValue("hasParkingSensors", params[ProcessJDBC.HAS_PARKING_SENSORS]))
			throw new AcademyException("Parking sensors invalid");
		car.setHasParkingSensors(Boolean.parseBoolean(params[ProcessJDBC.HAS_PARKING_SENSORS]));
		

		car = (Car) SQLConfiguration.getInstance().insertVehicle(car);
		
		System.out.println(".... Car added");

	}
}
