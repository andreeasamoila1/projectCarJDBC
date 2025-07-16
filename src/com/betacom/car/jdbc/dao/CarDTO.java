package com.betacom.car.jdbc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.jdbc.models.Car;
import com.betacom.car.jdbc.models.Vehicle;
import com.betacom.car.jdbc.singleton.SQLConfiguration;
import com.betacom.car.jdbc.utilities.SQLManager;

public class CarDTO {
	
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
 * id int auto_increment primary key,
    number_of_doors int not null,
    plate varchar(20) not null unique,
    body_style varchar(50) not null, -- berlina, coupe, suv
    engine_capacity int not null,
    number_of_gears int not null,
    has_navigation_system boolean not null,
    has_parking_sensors boolean not null,
    vehicle_id int not null unique,
    foreign key (vehicle_id) references vehicles(id_vehicles)
 */
	public List<Car> findAll() throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery("cars");
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry);
		
			return lD.stream()
					.map(row -> new Car(
							(Integer)row.get("number_of_doors"), 
							(String)row.get("plate"), 
							(String)row.get("body_style"), 
							(Integer)row.get("engine_capacity"),
							(Integer)row.get("number_of_gears"), 
							(Boolean)row.get("has_navigation_system"), 
							(Boolean)row.get("has_parking_sensors"), 
							(Vehicle)row.get("vehicle_id"))).collect(Collectors.toList());
			
		}
		
	public List<Car> findGeneric(String qryName,Object[] parameters) throws Exception{
			
			String qry = SQLConfiguration.getInstance().getQuery(qryName);
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lD = db.list(qry, parameters);
		
			return lD.stream()
					.map(row -> new Car(
							(Integer)row.get("number_of_doors"), 
							(String)row.get("plate"), 
							(String)row.get("body_style"), 
							(Integer)row.get("engine_capacity"),
							(Integer)row.get("number_of_gears"), 
							(Boolean)row.get("has_navigation_system"), 
							(Boolean)row.get("has_parking_sensors"), 
							(Vehicle)row.get("vehicle_id"))).collect(Collectors.toList());
		
	}
	
	
	public Optional<Car>   findByPlate(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("cars.byPlate");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Car(
					(Integer)row.get("number_of_doors"), 
					(String)row.get("plate"), 
					(String)row.get("body_style"), 
					(Integer)row.get("engine_capacity"),
					(Integer)row.get("number_of_gears"), 
					(Boolean)row.get("has_navigation_system"), 
					(Boolean)row.get("has_parking_sensors"), 
					(Vehicle)row.get("vehicle_id")));
		}
	}
	
	public Long count(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		return db.count(qry, parameters);
	
	}
}
