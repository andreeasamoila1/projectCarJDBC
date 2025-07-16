package com.betacom.car.jdbc.process.implementation;

import java.util.List;
import java.util.Optional;

import com.betacom.car.jdbc.dao.VehicleDAO;
import com.betacom.car.jdbc.models.Vehicle;
import com.betacom.car.jdbc.process.interfaces.SQLProcess;
import com.betacom.car.jdbc.singleton.SQLConfiguration;

public class ProcessUpdate implements SQLProcess{

	@Override
	public boolean execute() {
		boolean rc = true;
		VehicleDAO vechicleDAO = new VehicleDAO();
		try {

			SQLConfiguration.getInstance().setAutoCommit();   // autocommit settato

			Object[] params = new Object[] {
					"Car", "Honda", "Civic", 4, 14.8, "Gasoline", "Automatic", "Sedan", "Red", 2022, "Aluminum", 174
			};
			int id = vechicleDAO.insert("vehicles.insert", params);

			System.out.println("PK del cliente inserito:" + id);

			List<Vehicle> vehicleList = vechicleDAO.findAll();
			vehicleList.forEach(vehicle -> System.out.println(vehicle));

			params = new Object[] {
					"Car", "Mercedes", "G-Clas",
					id
			};

			int numero = vechicleDAO.update("vehicles.update", params);

			Optional<Vehicle> vehicle = vechicleDAO.findById(new Object[] {id});
			if (vehicle.isEmpty())
				System.out.println("Vehicle " + id + " not found");
			else
				System.out.println(vehicle.get());




			numero = vechicleDAO.delete("vehicles.delete", new Object[] {id});
			System.out.println("Numero di righe cancellate:" + numero);


		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			rc = false;
		}

		return rc;
	}
}
