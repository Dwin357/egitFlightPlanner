package flightPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OptimizedLookupTable {
	private HashMap<String, DepartureAirport> lookupTable; 
	private ArrayList<String> listOfAirportNames;

	public OptimizedLookupTable(HashMap<String, DestinationAirport> airports) {
		listOfAirportNames = new ArrayList<String>();
		lookupTable = new HashMap<>();
		setLookupTable(airports);
	}
	
	public Layover optimizedPath(String origin, String destination, String optimizationFeature){
		DepartureAirport departing = getDeparting(origin);
		DestinationAirport arriving = departing.getDestination(destination); 
		return arriving.getLayover(optimizationFeature);
	}
	
	
	
	private void setLookupTable(HashMap<String, DestinationAirport> airports){
		ArrayList<Connection> allConnections = stripConnectionsFromOldDataStructure(airports);
		for(Connection connection : allConnections){
			addConnectionToDataStructure(connection);
		}
		floydWarshallLoop();
	}
	
	private void addConnectionToDataStructure(Connection connection){
		addAirportNameIfUnique(connection.getDestination());
		addAirportNameIfUnique(connection.getOrigin());
		DepartureAirport departing = findOrCreateDepartureAirport(connection);
		DestinationAirport arriving = departing.findOrCreateDestinationAirport(connection);
		arriving.addLayover(connection, "direct");
	}
	
	private DepartureAirport findOrCreateDepartureAirport(Connection connection){
		DepartureAirport departing = getLookupTable().get(connection.getOrigin());
		if(departing == null){
			departing = new DepartureAirport(connection.getOrigin());
			getLookupTable().put(connection.getOrigin(), departing);
		}
		return departing;
	}
	
	private void floydWarshallLoop(){
		for(String departing : listOfAirportNames){
			ensureDeparting(departing);
			for(String arriving : listOfAirportNames){
				ensureArriving(departing, arriving);
				for(String alternative : listOfAirportNames){
					
					floydWarshallAssignment(departing, arriving, alternative);
				}
			}
		}
	}
	
	private void floydWarshallAssignment (String departing, String arriving, String alternative){
		if(floydWarshallComparison(departing, arriving, alternative){
			
		}
	}
	
	private boolean floydWarshallComparison(String departing, String arriving, String alternative){
		
		return false;
	}
	
	private ArrayList<Connection> stripConnectionsFromOldDataStructure(HashMap<String, DestinationAirport> airports){
		// this is just b/c I am using this as an experiment for a new +1 level data structure
		ArrayList<Connection> allConnections = new ArrayList<Connection>();
		for(Map.Entry<String, DestinationAirport> portEntry : airports.entrySet()){
			DestinationAirport airport = portEntry.getValue();
			for(Map.Entry<String, Connection> conEntry : airport.getConnections().entrySet()){
				Connection connection = conEntry.getValue();
				allConnections.add(connection);
			}
		}
		return allConnections;
	}
	
	private void addAirportNameIfUnique(String name){
		if(!listOfAirportNames.contains(name)){
			listOfAirportNames.add(name);
		}
	}
	
	public DepartureAirport getDeparting(String name){
		DepartureAirport departing = getLookupTable().get(name);
		return departing;
	}
	
	public HashMap<String, DepartureAirport> getLookupTable(){
		return lookupTable;
	}
	
	public ArrayList<String> getListOfAirportNames(){
		return listOfAirportNames;
	}

}
