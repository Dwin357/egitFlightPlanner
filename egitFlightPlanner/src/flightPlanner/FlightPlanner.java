package flightPlanner;
// Next Steps / Coding Assignment :: 10/28
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class FlightPlanner {
	
	private HashMap<String, Airport> airports;
	private OptimizedLookupTable efficientRoutes;
	
	//////////////  remove when done ///////////////
	
	public HashMap<String, Airport> getAirports(){
		return airports;
	}
	
	
	/////////////////////////////////////////////////
	
	public FlightPlanner(String filePath) {
		super();
		airports = new HashMap<String, Airport>();
		fromCSVPath(filePath);
		efficientRoutes = new OptimizedLookupTable(airports);
		
	}
	
	public FlightPlanner(File file) {
		super();
		airports = new HashMap<String, Airport>();
		try {
			fromCSVFile(file);
		} catch (FileNotFoundException e){
			System.out.println("bad file provided at load: " + e);
		}
	}
	
	public int fuelCostForTrip(String trip){
		int fuelCost = 0;
		String[] stops = trip.split("-");
		Airport departingFrom = airports.get(stops[0]);
		for(int i = 1; i < stops.length; i++){
			if (departingFrom != null && departingFrom.hasConnection(stops[i])) {
				fuelCost += departingFrom.connectionFuelCost(stops[i]);
				departingFrom = airports.get(stops[i]);
			} else {
				return -1;
			}
		}
		return fuelCost;
	}
	
	private void fromCSVPath(String csvPath) {
		try {
			fromCSVFile(new File(csvPath));
		} catch (FileNotFoundException e) {
			System.out.println("bad file path: " + e);
		}
	}
	
	private void fromCSVFile(File csv) 
	  throws FileNotFoundException {
		Scanner sc = new Scanner(csv);
		String line = sc.next();
		if (!Headers(line)){
			addFlight(line);
		}
		while(sc.hasNext()){
			addFlight(sc.next());
		}
		sc.close();
	}
	
	private boolean Headers(String fileLine){
		// as this becomes able to recognize more complex csvs, this will come up
		return true;
	}
	
	private void addFlight(String line){
		Flight connection = Flight.flightFromLine(line);
		Airport airport = findOrCreateAirport(connection.getOrigin());
		airport.addOrCreateConnection(connection, "direct");
	}	
	
	private Airport findOrCreateAirport(String name){	
		Airport airport = airports.get(name);
		if(airport == null){
			airport = new Airport(name);
			airports.put(name, airport);
		}	
		return airport;
	}
	
	public String[] collectAirports(){
//		this is just for testing load
		String[] ports = airports.keySet().toArray(new String[airports.keySet().size()]);
		return ports;
	}
	
	
}
