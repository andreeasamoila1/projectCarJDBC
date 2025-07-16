package com.betacom.car.jdbc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.jdbc.models.Bike;
import com.betacom.car.jdbc.models.Car;
import com.betacom.car.jdbc.models.Vehicle;
import com.betacom.car.jdbc.singleton.SQLConfiguration;
import com.betacom.car.jdbc.utilities.SQLManager;

public class BikeDTO {

	private SQLManager db = new SQLManager();

	public int insert(String qryName, Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters, true);
		
		return numero;
	}

	public int update(String qryName, Object[] parameters) throws Exception{
		int numero = 0;

		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);

		numero = db.update(qry, parameters);

		return numero;
	}

	public int delete(String qryName, Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
/*
 * type varchar(50) not null,
    suspension_type varchar(50) not null, -- e.g., senza, mono, di
    folding boolean not null,
    brake_type varchar(50) not null,
    vehicle_id int not null unique  primary key,
    foreign key (vehicle_id) references vehicles(id_vehicles)
 */
	public List<Bike> findAll() throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery("bikes");
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry);
		
			return lD.stream()
					.map(row -> new Bike(
							(String)row.get("type"), 
							(String)row.get("suspension_type"), 
							(Boolean)row.get("folding"),
							(String)row.get("brake_type"), 
							(Vehicle)row.get("vehicle_id"))).collect(Collectors.toList());
			
		}
		
	public List<Bike> findGeneric(String qryName,Object[] parameters) throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery(qryName);
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry, parameters);
		
			return lD.stream()
					.map(row -> new Bike(
							(String)row.get("type"), 
							(String)row.get("suspension_type"), 
							(Boolean)row.get("folding"),
							(String)row.get("brake_type"), 
							(Vehicle)row.get("vehicle_id"))).collect(Collectors.toList());
		
	}
	
	
	public Optional<Bike>   findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("bikes.byVehicle_id");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Bike(
					(String)row.get("type"), 
					(String)row.get("suspension_type"), 
					(Boolean)row.get("folding"),
					(String)row.get("brake_type"), 
					(Vehicle)row.get("vehicle_id")));
		}
	}
	
	public Long count(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		return db.count(qry, parameters);
	
	}
}
