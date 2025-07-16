package com.betacom.car.jdbc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.jdbc.models.Vehicle;
import com.betacom.car.jdbc.singleton.SQLConfiguration;
import com.betacom.car.jdbc.utilities.SQLManager;

public class VehicleDAO {
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
 * id_vehicles int auto_increment,
	vehicle_type varchar (100) not null,
	brand varchar (100) not null,
	model varchar (100) not null,
	number_wheels int not null,
	wheel_size decimal not null,
	fuel_type varchar (100) not null,
	transmission varchar (100) not null,
	category varchar (100) not null,
	color varchar (100) not null,
	year int not null,
	frame_material varchar (100) not null,
	max_speed int not null,
	primary key(id_vehicles)
 */
	public List<Vehicle> findAll() throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery("vehicles");
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry);
		
			return lD.stream()
					.map(row -> new Vehicle(
							(Integer)row.get("id_vehicles"), 
							(String)row.get("vehicle_type"), 
							(String)row.get("brand"), 
							(String)row.get("model"), 
							(Integer)row.get("number_wheels"),
							((BigDecimal)row.get("wheel_size")).doubleValue(), 
							(String)row.get("fuel_type"), 
							(String)row.get("transmission"), 
							(String)row.get("category"), 
							(String)row.get("color"), 
							(Integer)row.get("year"),  
							(String)row.get("frame_material"), 
							(Integer)row.get("max_speed"))).collect(Collectors.toList());
			
		}
		
	public List<Vehicle> findGeneric(String qryName,Object[] parameters) throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery(qryName);
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry, parameters);
		
			return lD.stream()
					.map(row -> new Vehicle(
							(Integer)row.get("id_vehicles"), 
							(String)row.get("vehicle_type"), 
							(String)row.get("brand"), 
							(String)row.get("model"), 
							(Integer)row.get("number_wheels"),
							((BigDecimal)row.get("wheel_size")).doubleValue(), 
							(String)row.get("fuel_type"), 
							(String)row.get("transmission"), 
							(String)row.get("category"), 
							(String)row.get("color"), 
							(Integer)row.get("year"),  
							(String)row.get("frame_material"), 
							(Integer)row.get("max_speed"))).collect(Collectors.toList());
		
	}
	
	
	public Optional<Vehicle>   findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("vehicles.byId_vehicles");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Vehicle(
					(Integer)row.get("id_vehicles"), 
					(String)row.get("vehicle_type"), 
					(String)row.get("brand"), 
					(String)row.get("model"), 
					(Integer)row.get("number_wheels"),
					((BigDecimal)row.get("wheel_size")).doubleValue(), 
					(String)row.get("fuel_type"), 
					(String)row.get("transmission"), 
					(String)row.get("category"), 
					(String)row.get("color"), 
					(Integer)row.get("year"),  
					(String)row.get("frame_material"), 
					(Integer)row.get("max_speed")));
		}
	}
	
	public Long count(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		return db.count(qry, parameters);
	
	}
}
