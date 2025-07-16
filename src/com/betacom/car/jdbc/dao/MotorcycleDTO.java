package com.betacom.car.jdbc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.jdbc.models.Bike;
import com.betacom.car.jdbc.models.Car;
import com.betacom.car.jdbc.models.Motorcycle;
import com.betacom.car.jdbc.models.Vehicle;
import com.betacom.car.jdbc.singleton.SQLConfiguration;
import com.betacom.car.jdbc.utilities.SQLManager;

public class MotorcycleDTO {

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
 *  plate varchar(20) not null unique primary key,
    body_style varchar(50) not null, -- e.g., naked
    engine_capacity int not null,
    has_abs boolean not null,
    transmission_type varchar(50) not null,
    number_of_gears int not null,
    vehicle_id int not null unique,
    foreign key (vehicle_id) references vehicles(id_vehicles)
 */
	public List<Motorcycle> findAll() throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery("motorcycles");
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry);
		
			return lD.stream()
					.map(row -> new Motorcycle(
							(String)row.get("plate"), 
							(String)row.get("body_style"), 
							(Integer)row.get("engine_capacity"), 
							(Boolean)row.get("has_abs"), 
							(String)row.get("transmission_type"), 
							(Integer)row.get("number_of_gears"), 
							(Vehicle)row.get("vehicle_id"))).collect(Collectors.toList());
			
		}
		
	public List<Motorcycle> findGeneric(String qryName,Object[] parameters) throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery(qryName);
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry, parameters);
		
			return lD.stream()
					.map(row -> new Motorcycle(
							(String)row.get("plate"), 
							(String)row.get("body_style"), 
							(Integer)row.get("engine_capacity"), 
							(Boolean)row.get("has_abs"), 
							(String)row.get("transmission_type"), 
							(Integer)row.get("number_of_gears"), 
							(Vehicle)row.get("vehicle_id"))).collect(Collectors.toList());
	}
	
	
	public Optional<Motorcycle>   findByPlate(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("motorcycles.byPlate");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Motorcycle(
					(String)row.get("plate"), 
					(String)row.get("body_style"), 
					(Integer)row.get("engine_capacity"), 
					(Boolean)row.get("has_abs"), 
					(String)row.get("transmission_type"), 
					(Integer)row.get("number_of_gears"), 
					(Vehicle)row.get("vehicle_id")));
		}
	}
	
	public Long count(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		return db.count(qry, parameters);
	
	}
}
