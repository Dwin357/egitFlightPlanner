package flightPlanner;

import java.util.HashMap;


public class DestinationAirport {
	
	private HashMap<String, Connection> connections;
	private HashMap<String, Layover> layovers;
	private String name;

	public DestinationAirport(String name) {
		super();
		this.name = name;
		this.connections = new HashMap<String, Connection>();
		this.layovers = new HashMap<String, Layover>();
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
	
	public HashMap<String, Connection> getConnections(){
		return connections;
	}
	
	public HashMap<String, Layover> getLayovers(){
		return layovers;
	}
	
	public void addConnection(Connection connection){
		getConnections().put(connection.getDestination(), connection);
	}
	
	public void addLayover(Connection connection, String label){
		getLayovers().put(label,  new Layover(connection));
	}
	
	public Layover getLayover(String label){
		return layovers.get(label);
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
