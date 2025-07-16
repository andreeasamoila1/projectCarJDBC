package com.betacom.car.jdbc;

import static com.betacom.car.jdbc.utilities.Utils.readRecord;

import java.util.List;

import com.betacom.car.jdbc.process.ProcessJDBC;

public class MainCarJDBC {

	public static void main(String[] args) {
		
		if (new ProcessJDBC().execute()) {
			System.out.println("Fine normale");
		} else {
			System.out.println("Error found");
		}

	}

}
