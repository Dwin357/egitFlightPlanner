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
	
	
	public static Layover addConnection(Connection firstLeg, Connection secondLeg){
		// ideally this should throw an error if the two connections don't match up
		String origin 	   = firstLeg.getOrigin();
		String destination = secondLeg.getDestination();
		int fuelCost 	   = firstLeg.getFuelCost() + secondLeg.getFuelCost();
		String layovers    =  firstLeg.getDestination() ;
				
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;
	}
	
	public static Layover addConnection(Layover currentPath, Connection newConnection){
		String origin 		= currentPath.getOrigin();
		String destination 	= newConnection.getDestination();
		int fuelCost		= currentPath.getFuelCost() + newConnection.getFuelCost();
		String layovers		= currentPath.getLayovers() + "-" + currentPath.getDestination();
		
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;
	}
	
	

}
