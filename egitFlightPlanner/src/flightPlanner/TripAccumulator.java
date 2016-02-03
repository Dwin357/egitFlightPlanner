package flightPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TripAccumulator extends Trip {
	private int layovers;
	private HashMap<String, Airport> airports;
	private ArrayList<Layover> routes;

	private TripAccumulator(String origin, String destination, int limit, int layovers, HashMap<String, Airport>airports) {
		super(origin, destination, limit);
		this.layovers = layovers;
		this.airports = airports;
		this.routes   = null;
	}
	
	///////////////////////  Instance  /////////////////
	
	private TripAccumulator(String[] args, HashMap<String, Airport>airports){
		super(args[0], args[1], Integer.parseInt(args[2]));
		this.layovers = Integer.parseInt(args[3]);
		this.airports = airports;
		this.routes   = null;
	}
	
	public int getLimit(){
		return fuelCost;
	}

	public int getLayovers(){
		return layovers;
	}
	
	public HashMap<String, Airport> getAirports(){
		return airports;
	}
	
	public ArrayList<Layover> getRoutes(){
		return routes;
	}
	
	public String[] getArgs(){
		String[] args = {"","","",""};
		args[0] = this.getOrigin();
		args[1] = this.getDestination();
		args[2] = Integer.toString(this.getLimit());
		args[3] = Integer.toString(this.getLayovers());
		return args;
	}
	
//	public static TripAccumulator tripsWithinNLayovers(String origin, String destination, int N, HashMap<String, Airport> ariports){
//		// this is supposed to be a depth first search
//		ArrayList<Trip> trips = new ArrayList<Trip>();
//		String[] args = {origin, destination, null, Integer.toString(N)};
		
		
//		String location = origin;
//		for(int i=0; i<=N; i++){
//			
//		}
//		return trips;
//	}
	
	private boolean isAtLimit(){
		return (getLimit() == 0);
	}
	
	private boolean isAtDestination(){
		return (getOrigin().equals(getDestination()));
	}
	
	private boolean isBaseCase(){
		boolean condition = false;
		if(isAtLimit()){
			if(isAtDestination()){
				condition = true;
			}
		}
		return condition;
	}
	
	private void setBaseCase(){
		this.routes = new ArrayList<Layover>();
		Layover location = Layover.origin(this.getOrigin());
		routes.add(location);
	}
	
	private void accumulate(TripAccumulator accumulatedTrips){
		ArrayList<Layover> pathsTraveled = accumulatedTrips.getRoutes();
		
	}
	

	////////////  Class   ////////////////////////////////
	
	private static TripAccumulator recursiveAccumulate(TripAccumulator location){
		TripAccumulator accumulator = (TripAccumulator) location.clone();
		if(location.isBaseCase()){
			accumulator.setBaseCase();
		} else {
			// call method again w/ decremented args, adding return to your return
			HashMap<String, Airport> airports = location.getAirports();
			for(Map.Entry<String, Airport> airport : airports.entrySet()){
				TripAccumulator nextLocation = TripAccumulator.nextLocation(airport.getKey(), location);
				accumulator.accumulate(TripAccumulator.recursiveAccumulate(nextLocation));
			}
		}
		return accumulator;
	}
	
	private static TripAccumulator nextLocation(String destination, TripAccumulator departure){
		// this needs to decrement the args and pass back a new instance
	}
}
