package com.betacom.car.jdbc.process;

import java.util.List;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.process.implementation.ProcessQuery;
import com.betacom.car.jdbc.process.implementation.ProcessUpdate;
import com.betacom.car.jdbc.process.interfaces.SQLProcess;
import com.betacom.car.jdbc.singleton.SQLConfiguration;
import com.betacom.car.jdbc.utilities.SQLManager;

public class ProcessJDBC {


	public final static int VEHICLE_TYPE = 0;
	public final static int BRAND = 1;
	public final static int MODEL = 2;
	public final static int NUMBER_WHEELS = 3;
	public final static int WHEEL_SIZE = 4;
	public final static int FUEL_TYPE = 5;
	public final static int TRANSMISSION = 6;
	public final static int CATEGORY = 7;
	public final static int COLOR = 8;
	public final static int YEAR = 9;
	public final static int FRAME_MATERIAL = 10;
	public final static int MAX_SPEED = 11;
	//car
	public final static int NUMBER_OF_DOORS = 12;
	public final static int PLATE_CAR = 13;
	public final static int BODY_STYLE_CAR = 14;
	public final static int ENGINE_CAPACITY_CAR = 15;
	public final static int NUMBER_OF_GEARS_CAR = 16;
	public final static int HAN_NAVIGATION_SYSTEM = 17;
	public final static int HAS_PARKING_SENSORS = 18;
	//motorcycle
	public final static int PLATE_MOTORCYCLE = 19;
	public final static int BODY_STYLE_MOTORCYCLE = 20;
	public final static int ENGINE_CAPACITY_MOTORCYCLE = 21;
	public final static int HAS_ABS = 22;
	public final static int TRANSMISSION_TYPE = 22;
	public final static int NUMBER_OF_GEARS_MOTORCYCLE = 23;
	//bike
	public final static int TYPE = 24;
	public final static int SUSPENSION_TYPE = 25;
	public final static int FOLDING = 26;
	public final static int BRAKE_TYPE = 27;
	
	public final static int CAR_PARAMETERS = 28;
	public final static int MOTORCYCLE_PARAMETERS = 29;
	public final static int BIKE_PARAMETERS = 30;
	
	
	
	
	public boolean execute() {
		System.out.println("Begin Process JDBC");
	
		SQLProcess pro = null;
		
		
		
		try {
			SQLConfiguration.getInstance().getConnection();
			System.out.println("Connection with db ok");

			System.out.println("Process QUERY ****************************");
			pro = new ProcessQuery();		
			pro.execute();
			
			System.out.println("Process UPDATE ****************************");
			pro = new ProcessUpdate();
			pro.execute();

			SQLConfiguration.getInstance().closeConnection();
			System.out.println("Connection is closed....");
		} catch (AcademyException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
