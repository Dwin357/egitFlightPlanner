package flightPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class TripAccumulator {
	private HashMap<String, DestinationAirport> airports;

	public TripAccumulator(HashMap<String, DestinationAirport> airports){
		this.airports = airports;
	}
	
	public int fuelCost(String path){
		String[] stops = path.split("-");
		int fuelUsed = 0;
		String currentLocation = stops[0];
		
		for(int i=1; i < stops.length; i++){
			DestinationAirport departingFrom = airports.get(currentLocation);
			Connection flight = departingFrom.getConnection(stops[i]);
			if(flight == null){
				return -1;
			}
			fuelUsed += flight.getFuelCost();
			currentLocation = flight.getDestination();
		}
		return fuelUsed;
	}
	
	public int countTripsWithinParamsExclusive(String origin, String destination, String limitingFeature, int limit){
		ArrayList<Layover> viablePaths = tripsWithinParamsInclusive(origin, destination, limitingFeature, limit-1);
		return viablePaths.size();
	}
	
	public int countTripsWithinParamsInclusive(String origin, String destination, String limitingFeature, int limit){
		ArrayList<Layover> viablePaths = tripsWithinParamsInclusive(origin, destination, limitingFeature, limit);
		return viablePaths.size();		
	}
	
	public ArrayList<Layover> tripsWithinParamsInclusive(String origin, String destination, String limitingFeature, int limit){
		ArrayList<Layover> viablePaths = new ArrayList<Layover>();
		for(int i=limit; i > 0; i--){
			viablePaths.addAll(tripsMatchingParams(origin, destination, limitingFeature, i));
		}
		return viablePaths;
	}
	
//	public void printArrayList(ArrayList<Layover> viablePaths){
//		for debugging
//		for(Layover layover : viablePaths){
//			System.out.println(layover.getFullPath()+" "+layover.getFuelCost());
//		}
//	}
	
	public ArrayList<Layover> tripsMatchingParams(String origin, String destination, String limitingFeature, int limit){
		ArrayList<Layover> viablePaths = new ArrayList<Layover>();
		viablePaths.addAll(recurrsiveAccumulation(origin, destination, limitingFeature, Integer.toString(limit)));
		return viablePaths;
	}

	public int countTripsMatchingParams(String origin, String destination, String limitingFeature, int limit){
		ArrayList<Layover> viablePaths = tripsMatchingParams(origin, destination, limitingFeature, limit);
		return viablePaths.size();		
	}	
	
	public int optimizedPath(String origin, String destination, String optimizedLimit){
		
		return -1;
	}
	
	private ArrayList<Layover> recurrsiveAccumulation(String origin, String destination, String limitingFeature, String limit){
		ArrayList<Layover> viablePaths = new ArrayList<Layover>();
		if(Integer.parseInt(limit) == 0 ){
			if(origin.equals(destination)){
				viablePaths.add(setBaseCase(origin));
			}
		} else {
			if (Integer.parseInt(limit) > 0){
				DestinationAirport flyingFrom = getAirports().get(origin);
				for(Map.Entry<String, Connection> connection : flyingFrom.getConnections().entrySet()){
					String decrementedLimit = decrementLimit(limitingFeature, limit, connection.getValue());
					viablePaths.addAll(
							recurrsiveAccumulation(
									connection.getValue().getDestination(), 
									destination, 
									limitingFeature, 
									decrementedLimit
							)
					);
				}
				notateCurrentLocation(viablePaths, flyingFrom);
			}
		}
		return viablePaths;
	}
	
	private Layover setBaseCase(String currentLocation){
		Layover location = Layover.origin(currentLocation);
		return location;
	}
	
	public void notateCurrentLocation(ArrayList<Layover> viablePaths, DestinationAirport flyingFrom){
		for(Layover path : viablePaths){
			String layoverCity = path.getOrigin();
			Connection flight = flyingFrom.getConnection(layoverCity);
			path.prependConnection(flight);
		}
	}
	
	private String decrementLimit(String limitingFeature, String limitAmount, Connection flight){
		String newLimit = "";
		switch(limitingFeature){
		case "layovers":
			newLimit = Integer.toString(Integer.parseInt(limitAmount) - 1);
			break;
		case "fuel":
			newLimit = Integer.toString(Integer.parseInt(limitAmount) - flight.getFuelCost());
			break;
		}
		return newLimit;
	}
	
	
	private HashMap<String, DestinationAirport> getAirports(){
		return airports;
	}
}