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
	
	public boolean hasConnection(Connection subject){
		return hasConnection(subject.getDestination());
	}
	
	public boolean hasConnection(String subject) {
		return (connections.get(subject) != null);
	}
	
	public Connection getConnection(String request){
		return connections.get(request);
	}
	
	public void addConnection(Connection newConnection){
		connections.put(newConnection.getDestination(), newConnection);
	}
	
	public String getName(){
		return name;
	}

}
