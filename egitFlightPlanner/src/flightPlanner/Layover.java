package flightPlanner;

public class Layover extends Trip {
	private String layovers;
	
	public Layover(String origin, String destination, int fuelCost, String layovers){
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
	
	
	public static Layover addConnections(Flight firstLeg, Flight secondLeg){
		// ideally this should throw an error if the two connections don't match up
		String origin 	   = firstLeg.getOrigin();
		String destination = secondLeg.getDestination();
		int fuelCost 	   = firstLeg.getFuelCost() + secondLeg.getFuelCost();
		String layovers    =  firstLeg.getDestination() ;
				
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;
	}
	
	public void appendConnection(Flight newConnection){
		this.destination 	= newConnection.getDestination();
		this.fuelCost		= getFuelCost() + newConnection.getFuelCost();
		
		if(getLayovers() == null){
			this.layovers = "";
		} else if(getLayovers().equals("")){
			this.layovers = newConnection.getOrigin();
		} else {
			this.layovers = getLayovers() + "-" + newConnection.getOrigin();
		}
	}
	
	public void prependConnection(Flight newConnection){
		this.origin 		= newConnection.getOrigin();
		this.fuelCost		= getFuelCost() + newConnection.getFuelCost();
			
		if(getLayovers()==null){
			// moving from 1 point to two
			this.layovers = "";
		} else if(getLayovers().equals("")){
			// moving from 2 points to 3
			this.layovers = newConnection.getDestination();
		} else{
			// moving from 3+ points to n+1
			this.layovers = newConnection.getDestination() + "-" + getLayovers();
		}
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
