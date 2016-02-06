package flightPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OptimizedLookupTable {
	private Map<String, Map<String, Map<String, Layover>>> lookupTable; 
	private ArrayList<String> listOfAirportNames;

	public OptimizedLookupTable(HashMap<String, Airport> airports) {
		listOfAirportNames = new ArrayList<String>();
		lookupTable = new HashMap<>();
//		setLookupTable(airports);
	}
	
	public Layover optimizedPath(String origin, String destination, String optimizationFeature){
		
		return null;
	}
	
	
	
//	private void setLookupTable(HashMap<String, Airport> airports){
//		for(Map.Entry<String, Airport> airportEntry : airports.entrySet()){
//			// for each airport do
//			Airport airport = airportEntry.getValue();
//			addAirportNameIfUnique(airport.getName());
//			
//		}
//		
//	}
	
	private void addAirportNameIfUnique(String name){
		if(!listOfAirportNames.contains(name)){
			listOfAirportNames.add(name);
		}
	}

}
