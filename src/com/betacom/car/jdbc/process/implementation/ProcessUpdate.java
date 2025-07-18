package com.betacom.car.jdbc.process.implementation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
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
		VehicleDAO vehicleDAO = new VehicleDAO();
		try (BufferedReader reader = new BufferedReader(new FileReader("input_car.txt"))) {

			SQLConfiguration.getInstance().setAutoCommit();   // autocommit settato

			
			 String line;
	            while ((line = reader.readLine()) != null) {
	                if (line.trim().isEmpty()) continue;

	                String[] parts = line.split(":", 2);
	                String command = parts[0].trim().toLowerCase();

	                if (command.equals("list")) {
	                    List<Vehicle> vehicles = vehicleDAO.findAll();
	                    vehicles.forEach(System.out::println);
	                    continue;
	                }

	                String[] values = parts[1].split(",");
	                for (int i = 0; i < values.length; i++) {
	                    values[i] = values[i].trim();
	                }

	                switch (command) {
	                    case "add":
	                        Vehicle vehicleToAdd = mapToVehicle(values);
	                        Object[] insertParams = vehicleToParams(vehicleToAdd);
	                        int id = vehicleDAO.insert("vehicles.insert", insertParams);
	                        System.out.println("Inserted with ID: " + id);
	                        break;
	                        
	                    case "update":
	                        if (values.length < 13) {
	                            System.out.println("Invalid update line: " + line);
	                            break;
	                        }
	                        Vehicle vehicleToUpdate = mapToVehicle(values);
	                        int updateId = Integer.parseInt(values[12]);
	                        Object[] updateParams = vehicleToParams(vehicleToUpdate, updateId);
	                        int updatedRows = vehicleDAO.update("vehicles.update", updateParams);
	                        System.out.println("Updated rows: " + updatedRows);
	                        break;

	                    case "delete":
	                        int deleteId = Integer.parseInt(values[1]);
	                        int deletedRows = vehicleDAO.delete("vehicles.delete", new Object[]{deleteId});
	                        System.out.println("Deleted rows: " + deletedRows);
	                        break;

	                    default:
	                        System.out.println("Unknown command: " + command);
	                        
	                        break;
	                }
	            }
	            
	            
	           
			
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			
			rc = false;
		}

		return rc;
	}
	
	private Vehicle mapToVehicle(String[] values) {
        return new Vehicle(
                0, // ID will be set after insert or provided for update
                values[0], values[1], values[2],
                Integer.parseInt(values[3]),
                Double.parseDouble(values[4]),
                values[5], values[6], values[7], values[8],
                Integer.parseInt(values[9]),
                values[10],
                Integer.parseInt(values[11])
        );
    }

    private Object[] vehicleToParams(Vehicle v) {
        return new Object[]{
                v.getVehicleType(), v.getBrand(), v.getModel(),
                v.getNumberWheels(), v.getWheelSize(), v.getFuelType(),
                v.getTransmission(), v.getCategory(),
                v.getColor(), v.getYear(), v.getFrameMaterial(),
                v.getMaxSpeed()
        };
    }

    private Object[] vehicleToParams(Vehicle v, int id) {
        Object[] base = vehicleToParams(v);
        Object[] full = new Object[base.length + 1];
        System.arraycopy(base, 0, full, 0, base.length);
        full[base.length] = id;
        return full;
    }
}
