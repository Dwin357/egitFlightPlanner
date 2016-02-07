package flightPlanner;

import java.util.HashMap;


public class Airport {
	
	private HashMap<String, Connection> connections;
	private String name;

	public Airport(String name) {
		super();
		this.name = name;
		this.connections = new HashMap<String, Connection>();
	}
	
	public boolean hasConnection(Flight destination, String listing){
		boolean answer = false;
		if (hasConnection(destination)){
			Connection connection = connections.get(destination.getDestination());
			answer = connection.hasListing(listing);
		}
		return answer;
	}
	
	public boolean hasConnection(Flight subject){
		return hasConnection(subject.getDestination());
	}
	
	public boolean hasConnection(String subject) {
		return (connections.get(subject) != null);
	}
	
	public Connection getConnection(String request){
		return connections.get(request);
	}
	
	public HashMap<String, Connection> getConnections(){
		return connections;
	}
	
	public void addOrCreateConnection(Flight flight){
		addOrCreateConnection("direct", flight);
	}
	public void addOrCreateConnection(String flightType, Flight flight){
		if(hasConnection(flight.getDestination())){
			Connection connection = connections.get(flight.getDestination());
			connection.addFlight(flightType, flight);
		} else {
			Connection connection = Connection.connectionFromFlight(flightType, flight);
			connections.put(flight.getDestination(), connection);
		}
	}
	
	public String getName(){
		return name;
	}
	
//	public int connectionFuelCost(String connectionName){
//		return connections.get(connectionName).getFuelCost();
//	}

	public String[] gatherConnections(){
		String[] ports = connections.keySet().toArray(new String[connections.keySet().size()]);
		return ports;
	}
}
