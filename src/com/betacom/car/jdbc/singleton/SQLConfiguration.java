package com.betacom.car.jdbc.singleton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.models.Vehicle;
import com.betacom.car.jdbc.utilities.SQLManager;

//This class is responsible for loading configuration and SQL query files, 
//and managing database connectivity 
public class SQLConfiguration {
	private Integer id = 0;


	// Implements the Singleton pattern: only one instance of this class will exist.
	// instance holds the single shared object of SQLConfiguration.
	private static SQLConfiguration instance = null;
	
	// These two objects represent key-value property files
	// prop: general configuration values (like DB URL, username, etc.).
	
	private static Properties prop = new Properties();
	// queries: SQL query strings (e.g., selectUser=SELECT * FROM users WHERE id = ?).
	private static Properties queries = new Properties();	
	
	
	// This will hold the JDBC connection object to interact with the database.
	private Connection connection = null;  // connection to database
	
	private List<Vehicle> vehiclesList = new ArrayList<Vehicle>();
	static Map<String, String[]> controlls = new HashMap<String, String[]>(); // key colore, string[] valori
	
	// Makes the constructor private, preventing external classes from creating instances.
	// Enforces the Singleton pattern (only accessible inside the class).
	private SQLConfiguration() {}
	
	
	// getInstance() is the global access point to the single instance.
	public static SQLConfiguration getInstance() throws AcademyException{
		// If instance is null, it:
		if (instance == null) {
			// Creates a new SQLConfiguration.
			instance = new SQLConfiguration();
			// Loads configuration from .properties files.
			loadConfiguration();
		}
		// If already created, just returns it.
		return instance;
		
		// Throws AcademyException if configuration loading fails.
	}
	
	// Loads two .properties files (one for config, one for SQL queries).
		public static void loadConfiguration() throws AcademyException{
			String path = "constants_car.txt";
			
			// Tries to read two files:
			try {
				 try (BufferedReader reader = new BufferedReader(new FileReader(path))){
					reader.lines()   // crea un stream di lines
			              .map(line -> line.split("="))
			              .filter(parts -> parts.length == 2)
			              .forEach(parts -> controlls.put(parts[0], parts[1].split(",")));
				 } catch (Exception e) {
				        e.printStackTrace();
				    }
				// sql.properties: loads into prop
				InputStream input = new FileInputStream("sql.properties");
				prop.load(input);
				
				// queries.properties: loads into queries
				InputStream sql = new FileInputStream("queries.properties");
				queries.load(sql);
				
				// Uses Java's Properties.load(InputStream) to parse the key-value pairs.
				
			} catch (FileNotFoundException e) {
				// If either file is missing or there's an I/O error, 
				// wraps the exception in a custom exception called AcademyException.
				throw new AcademyException(e.getMessage());
			}  catch (IOException e) {
				throw new AcademyException(e.getMessage());
			}
		}
		
		public boolean isValidValue(String key, String value) {
		    String[] values = controlls.get(key);
		    return values != null && 
		    		Arrays.stream(values)
		    			.anyMatch(it -> value.equalsIgnoreCase(it));
		}

		public Vehicle insertVehicle(Vehicle vehicle) {
			vehicle.setId(++id);
			vehiclesList.add(vehicle);
			return vehicle;
			
		}
		
		public void showVeicoli(){
			vehiclesList.forEach(v -> System.out.println(v));
		}
	
	// Retrieves config values like DB URL, user, etc.
		public String getProperty(String p) {
			// Returns the value of a general configuration property (e.g., db.url) by key.
			return prop.getProperty(p);
		}

		// Retrieves SQL queries by key.
		public String getQuery(String p) {
			// Returns a SQL query string by key (e.g., findUserById might map to 
			// SELECT * FROM users WHERE id = ?).
			return queries.getProperty(p);
		}
		

		public Connection getConnection() throws AcademyException{
			if (connection == null) {
				connection = new SQLManager().getConnection();
			}
			return connection;
		}

		/*
		 * Close connection
		 */
		public void closeConnection() throws AcademyException {
			try {
				if (connection != null) {
				}
			} catch (Exception e) {
				throw new AcademyException(e.getMessage());
			}
		}
		
		public void setAutoCommit() throws SQLException{
			connection.setAutoCommit(true);
		}

		public void setTransaction() throws SQLException{
			connection.setAutoCommit(false);
		}
		
		
}
