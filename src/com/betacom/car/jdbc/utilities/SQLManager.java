package com.betacom.car.jdbc.utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.singleton.SQLConfiguration;

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
	
	/*
	 * Table list
	 */
	
	// Declares a method to return the list of table names in a specific database.
	public List<String> listOfTable(String dbName) throws AcademyException {
		
		// Initializes an empty list lT to store table names.
		List<String> lT = new ArrayList<String>();
		try {
			// Gets DatabaseMetaData from the JDBC connection (gives metadata like tables, columns, etc.).
			DatabaseMetaData dbMD = SQLConfiguration.getInstance().getConnection().getMetaData(); // retrieve metadata from database
			
			// Retrieves a list of tables from the metadata.
			// Filters by dbName; other parameters are set to null (wildcards).
			ResultSet res = dbMD.getTables(dbName, null, null, null); // load result into resultset
			
			/*
			 * build result
			 */
			// Iterates through each row in the ResultSet.
			while (res.next()) {
				
				// Retrieves the table name from the current row and adds it to the list.
				lT.add(res.getString("TABLE_NAME"));   // res.get.... legge parametri della riga
			}
			
			
		} catch (SQLException e) {
			throw new AcademyException(e.getMessage());
		}
		// Returns the list of table names.
		return lT;
	}
	
	/*
	 * execute select with JDBC without parameters
	 */
	// Executes a SELECT query with no parameters, returns result as a list of maps.
	public List<Map<String, Object>> list(String qry) throws AcademyException{
		try {
			
			// Prepares the SQL statement (qry) using the current connection.
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);  // statement compilation
			
			// Executes the SELECT query and stores the result in res.
			ResultSet res = cmd.executeQuery();
			
			// Converts the result to a list of maps using resultsetToList.
			return resultsetToList(res);
			
		} catch (Exception e) {
			// If there's an error, it throws a custom exception.
			throw new AcademyException(e.getMessage());
		}		
	}

	// Overloaded version of list() that supports parameters in the query.
	public List<Map<String, Object>> list(String qry, Object[] params) throws AcademyException{
		try {
			
			//Prepares the SQL query.
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);  // statement compilation
			
			// Sets the parameters using a helper method createSet.
			cmd = createSet(cmd, params);
			
			// Executes the query and gets the result set.
			ResultSet res = cmd.executeQuery();
			
			// Converts and returns the result
			return resultsetToList(res);
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}

	/*
	 * execute count with JDBC with parameters
	 */
	// Executes a COUNT(*) query around your query to return the number of matching rows.
	public Long count(String qry, Object[] params) throws AcademyException{
		try {
			
			//Builds a query that wraps your original query inside a subquery and counts the rows.
			String qryCount = "select count(*) as numero from ( " + qry + " ) as numero";
			
			// Prepares and fills the parameters.
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qryCount);  // statement compilation
			cmd = createSet(cmd, params);
			
			// Executes the query and moves the cursor to the first (and only) result.
			ResultSet res = cmd.executeQuery();
			res.next();
			
			// Returns the row count as a Long.
			return (Long) res.getObject("numero");
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	/*
	 * execute select with result single row .
	 */
	// Executes a query expected to return one single row.
	public Map<String, Object> get(String qry, Object[] params) throws AcademyException{
		try {
			
			// Prepares, sets parameters, executes query, and converts one row to a Map.
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);  // statement compilation
			cmd = createSet(cmd, params);
			
			ResultSet res = cmd.executeQuery();
			return resultsetToMap(res);
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	/*
	 * update function
	 * this function is used for insert
	 * 							 update
	 *                           delete
	 */
	// Calls the main update method with viewPK = false.
	public int update(String qry, Object[] params) throws AcademyException{
		return update(qry, params, false);
	}
	
	/*
	 * Update with return primary key inserted (viewPK = true);
	 */
	// Main method to execute INSERT/UPDATE/DELETE statements.
	public int update(String qry, Object[] params, boolean viewPK) throws AcademyException{
		
		// Prepares the statement with or without support for retrieving generated keys.
		int rc = 0;         // init records count
		try {
			PreparedStatement cmd = null;
			if (viewPK)
				cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry,
						Statement.RETURN_GENERATED_KEYS);     // return generated key
			else
				cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(qry);  // statement compilation
			
			//Binds parameters and executes the update. rc is the number of affected rows.
			cmd = createSet(cmd, params);  // update preparated statements with parameters
			rc = cmd.executeUpdate();  // execute update operations
									   // rc = rows number implicated		
			
			/*
			 * retrieve value of auto increment
			 */
			// If viewPK is true, it tries to retrieve the auto-generated primary key.
			if (viewPK) {
				try(ResultSet generatedKeys = cmd.getGeneratedKeys()){   // with getGeneratedKeys we can access to generated key
					if (generatedKeys.next()) {                          // we create resultset to retrieve generated key
						rc = generatedKeys.getInt(1);
					} else {
						throw new SQLException("Create failed, no iD obtained");
					}
				}
			}
			
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
		
		
		// Returns number of affected rows or the generated key.
		return rc;
		
	}
	

	/*
	 * insert parameters in PreparedStatement
	 */
	private PreparedStatement createSet(PreparedStatement cmd, Object[] params) {
		// Begins parameter binding. JDBC uses 1-based indexing.
		int pIdx = 1;
		
		// Iterates over the parameters and sets each one in the statement.
		for (Object o:params) {
			try {
				cmd.setObject(pIdx++, o);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Returns the filled PreparedStatement.
		return cmd;
	}

	
	
	/*
	 * transform resultset in list<map>
	 * 		map: key -> column name
	 * 		     value -> column value
	 */
	// Gets metadata to find number of columns.
	private List<Map<String, Object>> resultsetToList(ResultSet rs) throws SQLException{
		ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();   // retrieve metadata resulset
		int columns = md.getColumnCount();         // retrieve query column number
		
		// Initializes result list.
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>(); // init result
		
		// Builds each row as a map, then adds to the list.
		while(rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>(); // init row
			for (int i=1;i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i)); // load map with key = query metadata 
															   //               value = reultset value
			}
			rows.add(row);
		}
		
		// Returns the list of all rows.
		return rows;
		
	}
	/*
	 * transform resultset in MAP (single row)
	 */
	private Map<String, Object> resultsetToMap(ResultSet rs) throws SQLException{
		ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();   // retrieve metadata resulset
		int columns = md.getColumnCount();         // retrieve query column number
		
		// If there's no row, return null.
		if (!rs.next())   // no row found
			return null;
		
		//Reads only one row and returns it as a map. 
		Map<String, Object> row = new HashMap<String, Object>(); // init row
		for (int i=1;i <= columns; ++i) {
			row.put(md.getColumnName(i), rs.getObject(i)); // load map with key = query metadata 
														   //               value = reultset value
		}
	
		return row;
		
	}
	

}
