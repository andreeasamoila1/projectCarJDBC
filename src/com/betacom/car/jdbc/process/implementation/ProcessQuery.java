package com.betacom.car.jdbc.process.implementation;

import java.util.List;
import java.util.Optional;

import com.betacom.car.jdbc.dao.VehicleDAO;
import com.betacom.car.jdbc.models.Vehicle;

import com.betacom.car.jdbc.process.interfaces.SQLProcess;
import com.betacom.car.jdbc.singleton.SQLConfiguration;
import com.betacom.car.jdbc.utilities.SQLManager;

public class ProcessQuery implements SQLProcess{
	private SQLManager db = new SQLManager();
	VehicleDAO vehicleDAO = new VehicleDAO();
	
	public boolean execute() {
		boolean rc = true;
		try {
			
			SQLConfiguration.getInstance().setAutoCommit();   // autocommit settato
			
			List<String> tablesList = db.listOfTable("project_car");
			tablesList.forEach(t -> System.out.println(t));
	
			List<Vehicle> vehiclesList = vehicleDAO.findAll();
			
			vehiclesList.forEach(vehicle -> System.out.println(vehicle));
			
			System.out.println("Select with 1 parameters");
			
			vehiclesList = vehicleDAO.findGeneric("vehicles.brand", new Object[] {"Honda"})  ;
			vehiclesList.forEach(vehicle -> System.out.println(vehicle));
			
			System.out.println("Select with 2 parameters");		
			vehiclesList = vehicleDAO.findGeneric("vehicles.brand-and-model", new Object[] {"Toyota" , "Corolla"})  ;
			vehiclesList.forEach(vehicle -> System.out.println(vehicle));
			
			System.out.println("findById");		
			Optional<Vehicle> vehicle = vehicleDAO.findById(new Object[] {26});	
			if (vehicle.isEmpty())
				System.out.println("Vehicle not found" );
			else
				System.out.println(vehicle.get());  // get is used to indicate isEmpty tested
			
			System.out.println("count with 2 parameters");		
			Long vehicles = vehicleDAO.count("vehicles.brand-and-model", new Object[] {"Toyota" , "Corolla"})  ;
			System.out.println("Count response:" + vehicles);
		
		
		
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			rc = false;
		}
		
		return rc;
	}
}
