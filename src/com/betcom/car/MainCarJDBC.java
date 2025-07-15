package com.betcom.car;

import com.betcom.car.process.ProcessJDBC;

public class MainCarJDBC {

	public static void main(String[] args) {
		if (new ProcessJDBC().execute()) {
			System.out.println("Fine normale");
		} else {
			System.out.println("Error found");
		}

	}

}
