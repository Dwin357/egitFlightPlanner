package flightPlanner;

import java.util.ArrayList;

public class FlightPlanner {
	
	private ArrayList<Airport> airportList;
	private Connection[][] lookupTable;
	
	public FlightPlanner(String file) {
		super();
		ArrayList<Connection> connectionList = connectionsFromCSV(file);
		ArrayList<Airport> airportList = airportListFromConnectionList(connectionList);
		Connection[][] lookupTable = lookupTableFromConnectionList(connectionList);
		
	}
	
	private ArrayList<Airport> airportListFromConnectionList(ArrayList<Connection> connectionList){
		
		return null;
	}
	
	private Connection[][] lookupTableFromConnectionList(ArrayList<Connection> connectionList){
		
		return null;
	}
	
	private ArrayList<Connection> connectionsFromCSV(String file){
		
		return null;
	}
}
