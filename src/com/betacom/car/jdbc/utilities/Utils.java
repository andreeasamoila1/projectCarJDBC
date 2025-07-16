package com.betacom.car.jdbc.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<String> readRecord(String path){
		List<String> r = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(path))){
			String line = reader.readLine();
			while (line != null) {
				r.add(line);
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
		
	}
}
