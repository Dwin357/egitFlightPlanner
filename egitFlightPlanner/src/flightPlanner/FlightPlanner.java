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
		airports = new HashMap<String, Airport>();
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
		sc.close();
	  } catch (FileNotFoundException e) {
		  
	  }
	}
	
	private boolean notHeaders(String fileLine){
		// as this becomes able to recognize more complex csvs, this will come up
		return false;
	}
	
	private void addFlight(String line){
		Connection connection = Connection.connectionFromLine(line);
		Airport airport = findOrCreateAirport(connection.getOrigin());
		airport.addConnection(connection);
	}	
	
	private Airport findOrCreateAirport(String name){	
		Airport airport = airports.get(name);
		if(airport == null){
			airport = new Airport(name);
			airports.put(name, airport);
		}	
		return airport;
	}
}
