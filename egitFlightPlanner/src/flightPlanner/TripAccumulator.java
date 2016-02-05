package flightPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TripAccumulator extends Trip {
	private HashMap<String, Airport> airports;
	private ArrayList<Layover> routes;

	public TripAccumulator(String origin, String destination, HashMap<String, Airport>airports) {
		super(origin, destination, 0);
		this.airports = airports;
		this.routes   = null;
	}
	
	public TripAccumulator(String[] args, HashMap<String, Airport>airports){
		super(args[0], args[1], 0);
		this.airports = airports;
		this.routes   = null;
	}
	
	//////////  Instance  /////////////
	
//	public ArrayList<Layover> accumulate(){
//		ArrayList<Layover> viableRoutes = new ArrayList<Layover>();
//		String[] args = getArgs();
//		viableRoutes.addAll(recursiveAccumulate(args));
//		return viableRoutes;
//	}
	
	public ArrayList<Layover> accumulateWithinFuelLimitN(int n){
		ArrayList<Layover> viableRoutes = new ArrayList<Layover>();
		String[] args = getArgs(n, "fueLimit");
		viableRoutes.addAll(recursiveAccumulate(args));
		return viableRoutes;
	}
	
	public ArrayList<Layover> accumulateWithinLayoverLimitN(int n){
		ArrayList<Layover> viableRoutes = new ArrayList<Layover>();
		String[] args = getArgs(n, "layoverLimit");
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
		String attribute = oldArgs[3];
		String[] newArgs = {"", "", "", ""};
		switch(attribute){
		case "fueLimit":
			newArgs = decrementFuelArgs(oldArgs, flight);
			break;
		case "layoverLimit":
			newArgs = decrementLayoverArgs(oldArgs, flight);
			break;
		}
		return newArgs;
	}
	
	public String[] decrementFuelArgs(String[] oldArgs, Connection flight){
		String currentLocation 	    = flight.getDestination();
		String desiredDestination   = oldArgs[1];
		String RemainingLimit	 	= decrementFueLimit(oldArgs[2], flight.getFuelCost());
		String limitType 			= oldArgs[3];
		String[] newArgs = {currentLocation, desiredDestination, RemainingLimit, limitType};
		return newArgs;
	}
	
	public String[] decrementLayoverArgs(String[] oldArgs, Connection flight){
		String currentLocation 	    = flight.getDestination();
		String desiredDestination   = oldArgs[1];
		String RemainingLimit	 	= decrementLayoverLimit(oldArgs[2]);
		String limitType 			= oldArgs[3];
		String[] newArgs = {currentLocation, desiredDestination, RemainingLimit, limitType};
		return newArgs;
	}
	
	public String decrementFueLimit(String fueLimit, int fuelCost){
		String rtn = Integer.toString( Integer.parseInt(fueLimit) - fuelCost );
		return rtn;
	}
	
	public String decrementLayoverLimit(String layoverLimit){
		String rtn = Integer.toString( Integer.parseInt(layoverLimit) - 1 );
		return rtn;
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
		Integer limit 	 = string2IntNullGuard(args[2]);
		return (limit >= 0);
	}
	
	public void notateCurrentLocation(ArrayList<Layover> viablePaths, Airport flyingFrom){
		// right now I'm treating this like it will mutate the object, might need to reassign thou 
		for(Layover path : viablePaths){
			String layoverCity = path.getOrigin();
			Connection flight = flyingFrom.getConnection(layoverCity);
			path.prependConnection(flight);
		}
	}
	
	public String[] getArgs(int limitN, String limitType){
		String[] args = {"","","",""};
		args[0] = this.getOrigin();
		args[1] = this.getDestination();
		args[2] = Integer.toString(limitN);
		args[3] = limitType;
		return args;
	}
	
	public int getLimit(){
		return fuelCost;
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
