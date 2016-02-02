package flightPlanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class FlightPlanner {
	
	private HashMap<String, Airport> airports;
	private Connection[][] efficientRoutes;
	
	public FlightPlanner(String filePath) {
		super();
		airportsFromCSV(filePath);
	}
	
	private void airportsFromCSV(String filePath) {
	  try {
		Scanner sc = new Scanner(new File(filePath));
		String line = sc.next();
		if (notHeaders(line)){
			addFlight(line);
		}
		while(sc.hasNext()){
			addFlight(sc.next());
		}
	  } catch (FileNotFoundException e) {
		  
	  }
	}
	
	private boolean notHeaders(String fileLine){
		// as this becomes able to recognize more complex csvs, this will come up
		return false;
	}
	
	private void addFlight(String connectionDetails){
		// 1) parse data from input string
		connectionArgFromLine(connectionDetails);
		// 2) make connection from data
		// 3) find-or-create airport based on origin
		// 4) airport.add(connection)
//		System.out.println(parseDataFromLine(connectionDetails));
	}
	
	private String[] connectionArgFromLine(String line){
		line = chompComma(line);
		
		String[] workInProgress = splitDistance(line);
		String distance = workInProgress[0];
		
		workInProgress = splitLocations(workInProgress[1]);
		String _from = workInProgress[0];
		String _to = workInProgress[1];
		
		String[] connectionArg = {_from, _to, distance};
		return connectionArg;
	}
	
	private String chompComma(String line){
		int indexLast = line.length();
		if (indexLast > 0 && line.substring(indexLast-1, indexLast).equals(",")){
			line = line.substring(0, indexLast-1);
		}
		return line;
	}
	
	private String[] splitDistance(String line){
		String[] temp = line.split("(?<=\\D)(?=\\d)");
		String[] rtn = { temp[1], temp[0] };
		return rtn;
	}
	
	private String[] splitLocations(String line){
		return line.split("(?<!^)(?=[A-Z])");
	}
}
