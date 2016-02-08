package flightPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OptimizedLookupTable {
	private Layover[][] lookupTable; 
	private ArrayList<String> listOfAirportNames;

	public OptimizedLookupTable(HashMap<String, DestinationAirport> airports) {
		listOfAirportNames = new ArrayList<String>();
		setLookupTable(airports);
	}
	
	public Layover optimizedFuelPath(String origin, String destination){
		int originIndex = tableIndexOf(origin);
		int destinationIndex = tableIndexOf(destination);
		return lookupTable[originIndex][destinationIndex];
	}
	
	
	
	private void setLookupTable(HashMap<String, DestinationAirport> airports){
		ArrayList<Connection> allConnections = stripConnectionsFromOldDataStructure(airports);
		lookupTable = new Layover[listOfAirportNames.size()][listOfAirportNames.size()];
		padLookupTableOrigins();	
		for(Connection connection : allConnections){
			addConnectionToDataStructure(connection);
		}
		floydWarshallLoop();
		resetLookupTableOriginsToInfinite();
		floydWarshallLoop();
	}
	
	private void addConnectionToDataStructure(Connection connection){
		int origin = listOfAirportNames.indexOf(connection.getOrigin());
		int destination = listOfAirportNames.indexOf(connection.getDestination());
		lookupTable[origin][destination] = new Layover(connection);
	}
	
	private void padLookupTableOrigins(){
		for(int location=0; location < listOfAirportNames.size(); location++){
			lookupTable[location][location] = Layover.origin(listOfAirportNames.get(location));
		}
	}
	
	private void resetLookupTableOriginsToInfinite(){
		for(int location=0; location < listOfAirportNames.size(); location++){
			lookupTable[location][location] = Layover.infinite(listOfAirportNames.get(location));
		}
	}
	
	private void floydWarshallLoop(){
		for(int departing=0; departing < listOfAirportNames.size(); departing++){
			for(int arriving=0; arriving < listOfAirportNames.size(); arriving++){
				for(int alternative=0; alternative < listOfAirportNames.size(); alternative++){	
					floydWarshallAssignment(departing, arriving, alternative);
				}
			}
		}
	}
	
	private void floydWarshallAssignment (int departing, int arriving, int alternative){
		if(floydWarshallComparison(departing, arriving, alternative)){
			floydWarshallSubstitution(departing, arriving, alternative);
		}
	}
	
	private boolean floydWarshallComparison(int departing, int arriving, int alternative){
		Layover currentRoute = lookupTable[departing][arriving];
		Layover altLeg1 = lookupTable[departing][alternative];
		Layover altLeg2 = lookupTable[alternative][arriving];
		if ((altLeg1 == null) || (altLeg2==null)){
			return false;
		} else if (currentRoute == null){
			return true;
		} else if ( currentRoute.getFuelCost() > altLeg1.getFuelCost()+altLeg2.getFuelCost() ){
			return true;
		} else {
			return false;
		}
	}
	
	private void floydWarshallSubstitution(int departing, int arriving, int alternative){
		Layover leg1 = lookupTable[departing][alternative];
		Layover leg2 = lookupTable[alternative][arriving];
		lookupTable[departing][arriving] = Layover.concat(leg1, leg2);
	}
	
	private ArrayList<Connection> stripConnectionsFromOldDataStructure(HashMap<String, DestinationAirport> airports){
		// this is just b/c I am using this as an experiment for a new +1 level data structure
		ArrayList<Connection> allConnections = new ArrayList<Connection>();
		for(Map.Entry<String, DestinationAirport> portEntry : airports.entrySet()){
			DestinationAirport airport = portEntry.getValue();
			for(Map.Entry<String, Connection> conEntry : airport.getConnections().entrySet()){
				Connection connection = conEntry.getValue();
				addAirportNameIfUnique(connection.getDestination());
				addAirportNameIfUnique(connection.getOrigin());
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
	
	private int tableIndexOf(String name){
		return getListOfAirportNames().indexOf(name);
	}
	
	public Layover[][] getLookupTable(){
		return lookupTable;
	}
	
	public ArrayList<String> getListOfAirportNames(){
		return listOfAirportNames;
	}

}
