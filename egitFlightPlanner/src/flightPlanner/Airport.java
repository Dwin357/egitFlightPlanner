package flightPlanner;

import java.util.HashMap;


public class Airport {
	
	private HashMap<String, Flight> connections;
	private String name;

	public Airport(String name) {
		super();
		this.name = name;
		this.connections = new HashMap<String, Flight>();
	}
	
	public boolean hasConnection(Flight subject){
		return hasConnection(subject.getDestination());
	}
	
	public boolean hasConnection(String subject) {
		return (connections.get(subject) != null);
	}
	
	public Flight getConnection(String request){
		return connections.get(request);
	}
	
	public HashMap<String, Flight> getConnections(){
		return connections;
	}
	
	public void addConnection(Flight newConnection){
		connections.put(newConnection.getDestination(), newConnection);
	}
	
	public String getName(){
		return name;
	}
	
	public int connectionFuelCost(String connectionName){
		return connections.get(connectionName).getFuelCost();
	}

	public String[] gatherConnections(){
		String[] ports = connections.keySet().toArray(new String[connections.keySet().size()]);
		return ports;
	}
}
