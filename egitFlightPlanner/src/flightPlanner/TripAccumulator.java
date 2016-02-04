package flightPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TripAccumulator extends Trip {
	private int layovers;
	private HashMap<String, Airport> airports;
	private ArrayList<Layover> routes;

	public TripAccumulator(String origin, String destination, int fueLimit, int layoverLimit, HashMap<String, Airport>airports) {
		super(origin, destination, fueLimit);
		this.layovers = layoverLimit;
		this.airports = airports;
		this.routes   = null;
	}
	
	public TripAccumulator(String[] args, HashMap<String, Airport>airports){
		super(args[0], args[1], TripAccumulator.string2IntNullGuard(args[2]));
		this.layovers = TripAccumulator.string2IntNullGuard(args[3]);
		this.airports = airports;
		this.routes   = null;
	}
	
	//////////  Instance  /////////////
	
	public ArrayList<Layover> accumulate(){
		ArrayList<Layover> viableRoutes = new ArrayList<Layover>();
		String[] args = getArgs();
		viableRoutes.addAll(recursiveAccumulate(args));
		return viableRoutes;
	}
	
	public static String int2StringNullGuard(Integer n){
		if(n != null){
			return Integer.toString(n);
		} else {
			return (String) null;
		}
	}
	
	public static Integer string2IntNullGuard(String n){
		if(n != null){
			return Integer.parseInt(n);
		} else {
			return (Integer) null;
		}
	}
	
	public ArrayList<Layover> recursiveAccumulate(String[] args){
		ArrayList<Layover> viableRoutes = new ArrayList<Layover>();
		String currentLocation = args[0];
		
		if(paramInBounds(args)){
			if(isBaseCase(args)){
				viableRoutes.add(setBaseCase(args));
			} else {
				Airport flyingFrom = getAirports().get(currentLocation);
				for(Map.Entry<String, Connection> connection : flyingFrom.getConnections().entrySet()){
					String[] newArgs = decrementArgs(args, connection.getValue());
					viableRoutes.addAll(recursiveAccumulate(newArgs));
				}
				notateCurrentLocation(viableRoutes, flyingFrom);
			}
		}
		return viableRoutes;
	}
	
	public String[] decrementArgs(String[] oldArgs, Connection flight){
		String currentLocation 	     = flight.getDestination();
		String desiredDestination    = oldArgs[1];
		String RemainingFueLimit	 = decrementFueLimit(oldArgs[2], flight.getFuelCost());
		String RemaininglayoverLimit = decrementLayoverLimit(oldArgs[3]);
		String[] newArgs = {currentLocation, desiredDestination, RemainingFueLimit, RemaininglayoverLimit};
		return newArgs;
	}
	
	public String decrementFueLimit(String fueLimit, int fuelCost){
		if(fueLimit == null){
			return null;
		} else {
			String rtn = Integer.toString( Integer.parseInt(fueLimit) - fuelCost );
			return rtn;
		}
	}
	
	public String decrementLayoverLimit(String layoverLimit){
		if(layoverLimit == null){
			return null;
		} else {
			String rtn = Integer.toString( Integer.parseInt(layoverLimit) - 1 );
			return rtn;
		}
	}
	
	public Layover setBaseCase(String[] args){
		String currentLocation = args[0];
		Layover location = Layover.origin(currentLocation);
		return location;
	}
	
	public boolean isBaseCase(String[] args){
		String currentLocation = args[0];
		String desiredLocation = args[1];
		return currentLocation.equals(desiredLocation);
	}
	
	public boolean paramInBounds(String[] args){
		Integer fueLimit 	 = string2IntNullGuard(args[2]);
		Integer layoverLimit = string2IntNullGuard(args[3]);
		if( (fueLimit != null) && (layoverLimit != null)){
			return((fueLimit >=0 ) && (layoverLimit >= 0));
		} else if( fueLimit != null){
			return (fueLimit >= 0);
		} else {
			return (layoverLimit >= 0);
		}
	}
	
	public void notateCurrentLocation(ArrayList<Layover> viablePaths, Airport flyingFrom){
		// right now I'm treating this like it will mutate the object, might need to reassign thou 
		for(Layover path : viablePaths){
			String layoverCity = path.getOrigin();
			Connection flight = flyingFrom.getConnection(layoverCity);
			path.prependConnection(flight);
		}
	}
	
	public String[] getArgs(){
		String[] args = {"","","",""};
		args[0] = this.getOrigin();
		args[1] = this.getDestination();
		args[2] = int2StringNullGuard(this.getFuelLimit());
		args[3] = int2StringNullGuard(this.getLayoverLimit());
		return args;
	}
	
	public int getFuelLimit(){
		return fuelCost;
	}

	public int getLayoverLimit(){
		return layovers;
	}
	
	public HashMap<String, Airport> getAirports(){
		return airports;
	}
	
	
	
	
	
	
	
	//////////////////////////// not using  ////////////////////////
	

	
	
	
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
//	
//	private boolean isAtLimit(){
//		return (getLimit() == 0);
//	}
//	
//	private boolean isAtDestination(){
//		return (getOrigin().equals(getDestination()));
//	}
//	
//	private boolean isBaseCase(){
//		boolean condition = false;
//		if(isAtLimit()){
//			if(isAtDestination()){
//				condition = true;
//			}
//		}
//		return condition;
//	}
//	
//	private void setBaseCase(){
//		this.routes = new ArrayList<Layover>();
//		Layover location = Layover.origin(this.getOrigin());
//		routes.add(location);
//	}
//	
//	private void accumulate(TripAccumulator accumulatedTrips){
//		ArrayList<Layover> pathsTraveled = accumulatedTrips.getRoutes();
//		
//	}
	

	////////////  Class   ////////////////////////////////
	
//	private static TripAccumulator recursiveAccumulate(TripAccumulator location){
//		TripAccumulator accumulator = (TripAccumulator) location.clone();
//		if(location.isBaseCase()){
//			accumulator.setBaseCase();
//		} else {
//			// call method again w/ decremented args, adding return to your return
//			HashMap<String, Airport> airports = location.getAirports();
//			for(Map.Entry<String, Airport> airport : airports.entrySet()){
//				TripAccumulator nextLocation = TripAccumulator.nextLocation(airport.getKey(), location);
//				accumulator.accumulate(TripAccumulator.recursiveAccumulate(nextLocation));
//			}
//		}
//		return accumulator;
//	}
//	
//	private static TripAccumulator nextLocation(String destination, TripAccumulator departure){
//		// this needs to decrement the args and pass back a new instance
//	}
}
