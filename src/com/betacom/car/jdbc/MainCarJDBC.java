package com.betacom.car.jdbc;

import static com.betacom.car.jdbc.utilities.Utils.readRecord;

import java.util.List;

import com.betacom.car.jdbc.exception.AcademyException;
import com.betacom.car.jdbc.process.ProcessJDBC;

public class MainCarJDBC {

	public static void main(String[] args) {
		
		String path = "input_car.txt";
		List<String> params = readRecord(path);
		
		try {
			if (new ProcessJDBC().execute(params)) {
				System.out.println("Fine normale");
			} else {
				System.out.println("Error found");
			}
		} catch (AcademyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
