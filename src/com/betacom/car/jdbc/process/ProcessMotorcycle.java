package com.betacom.car.jdbc.process;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.models.Motorcycle;
import com.betacom.car.jdbc.singleton.SQLConfiguration;

public class ProcessMotorcycle {


	public void execute(String[] params) throws AcademyException{
		System.out.println("Begin Process Motorcycle");
		if (params.length != ProcessJDBC.MOTORCYCLE_PARAMETERS)
			throw new AcademyException("Error with the motorcycle parameters");

		
		Motorcycle motorcycle = new Motorcycle();

		motorcycle = (Motorcycle)new VehicleControl().verify(motorcycle, params);

		if (!SQLConfiguration.getInstance().isValidValue("plateMotorcycle", params[ProcessJDBC.PLATE_MOTORCYCLE]))
			throw new AcademyException("Invalide motorcycle plate");
		motorcycle.setPlate(params[ProcessJDBC.PLATE_MOTORCYCLE]);			
		

		if (!SQLConfiguration.getInstance().isValidValue("bodyStyleMotorcycle", params[ProcessJDBC.BODY_STYLE_MOTORCYCLE]))
			throw new AcademyException("Motorcycle body style invalid");
		motorcycle.setBodyStyle(params[ProcessJDBC.BODY_STYLE_CAR]);
		
		if (!SQLConfiguration.getInstance().isValidValue("engineCapacityMotorcycle", params[ProcessJDBC.ENGINE_CAPACITY_MOTORCYCLE]))
			throw new AcademyException("Engine capacity motorcycle invalid");
		motorcycle.setEngineCapacity(Integer.parseInt(params[ProcessJDBC.ENGINE_CAPACITY_MOTORCYCLE]));
		
		if (!SQLConfiguration.getInstance().isValidValue("hasNavigationSystem", params[ProcessJDBC.HAS_ABS]))
			throw new AcademyException("Abs invalid");
		motorcycle.setHasABS(Boolean.parseBoolean(params[ProcessJDBC.HAS_ABS]));

		if (!SQLConfiguration.getInstance().isValidValue("transmissionType", params[ProcessJDBC.TRANSMISSION_TYPE]))
			throw new AcademyException("Transmission invalid");
		motorcycle.setTransmissionType(params[ProcessJDBC.TRANSMISSION_TYPE]);
		
		if (!SQLConfiguration.getInstance().isValidValue("numberOfGearsMotorcycle", params[ProcessJDBC.NUMBER_OF_GEARS_MOTORCYCLE]))
			throw new AcademyException("Number of gears motorcycle invalid");
		motorcycle.setTransmissionType(params[ProcessJDBC.NUMBER_OF_GEARS_MOTORCYCLE]);
		
		

		motorcycle = (Motorcycle) SQLConfiguration.getInstance().insertVehicle(motorcycle);
		
		System.out.println(".... Motorcycle added");

	}
}
