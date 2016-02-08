package flightPlanner;

import java.util.HashMap;


public class DepartureAirport {
	
	private HashMap<String, DestinationAirport> destinations;
	private String name;

	public DepartureAirport(String name) {
		super();
		this.name = name;
		this.destinations = new HashMap<String, DestinationAirport>();
	}
	
	public boolean hasDestination(String subject) {
		return (destinations.get(subject) != null);
	}
	
	public DestinationAirport getDestination(String request){
		return destinations.get(request);
	}
	
	public HashMap<String, DestinationAirport> getDestinations(){
		return destinations;
	}
	
	public DestinationAirport findOrCreateDestinationAirport(Connection connection){
		DestinationAirport destination = getDestinations().get(connection.getDestination());
		if(destination == null){
			destination = new DestinationAirport(connection.getOrigin());
			getDestinations().put(connection.getDestination(), destination);
		}
		return destination;
	}
	
//	public void addDestination(DestinationAirport connection){
//		getConnections().put(connection.getDestination(), connection);
//	}
	
	
	public String getName(){
		return name;
	}
	
	
}

