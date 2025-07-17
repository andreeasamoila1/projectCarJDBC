package com.betacom.car.jdbc.process;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.models.Bike;
import com.betacom.car.jdbc.singleton.SQLConfiguration;

public class ProcessBike {

	public void execute(String[] params) throws AcademyException{
		System.out.println("Begin Process Bike");
		if (params.length != ProcessJDBC.BIKE_PARAMETERS)
			throw new AcademyException("Errore with the bike parameters");

		
		Bike bike = new Bike();

		bike = (Bike)new VehicleControl().verify(bike, params);

		if (!SQLConfiguration.getInstance().isValidValue("type", params[ProcessJDBC.TYPE]))
			throw new AcademyException("Invalide type");
		bike.setType(params[ProcessJDBC.TYPE]);			
		

		if (!SQLConfiguration.getInstance().isValidValue("sospentionType", params[ProcessJDBC.SUSPENSION_TYPE]))
			throw new AcademyException("Suspention type invalid");
		bike.setSuspensionType(params[ProcessJDBC.SUSPENSION_TYPE]);
		
		if (!SQLConfiguration.getInstance().isValidValue("folding", params[ProcessJDBC.FOLDING]))
			throw new AcademyException("error");
		bike.setFolding(Boolean.parseBoolean(params[ProcessJDBC.FOLDING]));
		
		if (!SQLConfiguration.getInstance().isValidValue("brakeType", params[ProcessJDBC.BRAKE_TYPE]))
			throw new AcademyException("Brake type invalid");
		bike.setSuspensionType(params[ProcessJDBC.BRAKE_TYPE]);
		

		bike = (Bike) SQLConfiguration.getInstance().insertVehicle(bike);
		
		System.out.println(".... Bike added");

	}
}
