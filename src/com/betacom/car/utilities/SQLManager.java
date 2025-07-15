package com.betacom.car.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.singleton.SQLConfiguration;

//This class contains methods to interact with a SQL database using JDBC.
public class SQLManager {


	// Public method to get a JDBC connection.
	// Throws custom exception AcademyException on failure.
	public Connection getConnection() throws AcademyException{
		
		// Declares a variable con of type Connection, initialized to null.
		Connection con = null;
		
		// Start of a try block to catch any exceptions during connection setup.
		try {
			// Loads the JDBC driver class dynamically using reflection.
			// The class name is fetched from the sql.properties file via SQLConfiguration.
			Class.forName(SQLConfiguration.getInstance().getProperty("driver")); // Load driver with reflection
			/*
			 * open connection with Driver Manager
			 * 	url
			 *  user
			 *  pwd
			 */
			// Starts opening a connection using DriverManager.
			con = DriverManager.getConnection(
					// These three lines fetch the database URL, username, and password from the properties file.
					// A connection is established and stored in con.
					SQLConfiguration.getInstance().getProperty("url"),
					SQLConfiguration.getInstance().getProperty("user"),
					SQLConfiguration.getInstance().getProperty("pwd")
					);
			
			// Returns the open JDBC Connection.
			return con;
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	/*
	 * commit sql statements
	 */
	public void commit() throws AcademyException{
		try {
			SQLConfiguration.getInstance().getConnection().commit();
		} catch (SQLException e) {
			throw new AcademyException(e.getMessage());
		}
	}

	/*
	 * rollback sql statements
	 */
	public void rollback() throws AcademyException{
		try {
			SQLConfiguration.getInstance().getConnection().rollback();
		} catch (SQLException e) {
			throw new AcademyException(e.getMessage());
		}
	}

}
