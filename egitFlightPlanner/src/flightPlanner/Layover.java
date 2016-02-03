package flightPlanner;

public class Layover extends Trip {
	private String layovers;
	
	private Layover(String origin, String destination, int fuelCost, String layovers){
		super(origin, destination, fuelCost);
		this.layovers = layovers;
	}
	
	public String getFullPath(){
		String fullPath = this.getOrigin()+"-"+this.getLayovers()+"-"+this.getDestination();
		return fullPath;
	}
	
	public String getLayovers(){
		return layovers;
	}
	
	
	public static Layover addConnections(Connection firstLeg, Connection secondLeg){
		// ideally this should throw an error if the two connections don't match up
		String origin 	   = firstLeg.getOrigin();
		String destination = secondLeg.getDestination();
		int fuelCost 	   = firstLeg.getFuelCost() + secondLeg.getFuelCost();
		String layovers    =  firstLeg.getDestination() ;
				
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;
	}
	
	public static Layover appendConnection(Layover currentPath, Connection newConnection){
		String origin 		= currentPath.getOrigin();
		String destination 	= newConnection.getDestination();
		int fuelCost		= currentPath.getFuelCost() + newConnection.getFuelCost();
		String layovers;
		
		if(currentPath.getLayovers() == null){
			layovers = "";
		} else if(currentPath.getLayovers().equals("")){
			layovers = currentPath.getDestination();
		} else {
			layovers = currentPath.getLayovers() + "-" + currentPath.getDestination();
		}
		
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;
	}
	
	public static Layover prependConnection(Layover currentPath, Connection newConnection){
		String origin 		= newConnection.getOrigin();
		String destination 	= currentPath.getDestination();
		int fuelCost		= currentPath.getFuelCost() + newConnection.getFuelCost();
		String layovers;
			
		if(currentPath.getLayovers()==null){
			// moving from 1 point to two
			layovers = "";
		} else if(currentPath.getLayovers().equals("")){
			// moving from 2 points to 3
			layovers = currentPath.getOrigin();
		} else{
			// moving from 3+ points to n+1
			layovers = currentPath.getOrigin() + "-" + currentPath.getLayovers();
		}
		
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;		
	}
	
	public static Layover origin(String location){
		String origin 	   = location;
		String destination = location;
		int fuelCost	   = 0;
		String layovers	   = null;
		
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;	
	}

}
