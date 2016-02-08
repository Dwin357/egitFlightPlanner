package flightPlanner;

public class Layover extends Trip {
	private String layovers;
	
	public Layover(String origin, String destination, int fuelCost, String layovers){
		super(origin, destination, fuelCost);
		this.layovers = layovers;
	}
	
	public Layover(Connection flight){
		super(flight.getOrigin(), flight.getDestination(), flight.getFuelCost());
		this.layovers = "";
	}
	
	public Layover(Layover flight){
		super(flight.getOrigin(), flight.getDestination(), flight.getFuelCost());
		this.layovers = flight.getLayovers();
	}
	
	public String getFullPath(){
		String fullPath = this.getOrigin()+"-"+this.getLayovers()+"-"+this.getDestination();
		return fullPath;
	}
	
	public String getLayovers(){
		return layovers;
	}
	
	public int layoverStopCount(){
		if ((getLayovers() == null) || (getLayovers().equals(""))){
			return 0;
		} else {
			String[] stops = getLayovers().split("-");
			return stops.length;
		}
	}
	
	public void appendLayover(Layover newLayover){
		this.destination 	= newLayover.getDestination();
		this.fuelCost		= getFuelCost() + newLayover.getFuelCost();
		
		if(getLayovers() == null){
			this.layovers = "";
		} else if(getLayovers().equals("")){
			this.layovers = newLayover.getOrigin();
		} else {
			this.layovers = getLayovers() + "-" + newLayover.getOrigin();
		}
	}
	
	public void appendConnection(Connection newConnection){
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
	
	public void prependConnection(Connection newConnection){
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
	
	/////////////   class  /////////////
	public static Layover origin(String location){
		String origin 	   = location;
		String destination = location;
		int fuelCost	   = 0;
		String layovers	   = null;
		
		Layover layover = new Layover(origin, destination, fuelCost, layovers);
		return layover;	
	}
	
	public static Layover infinite(String location){
		String origin 		= location;
		String destination  = location;
		int fuelCost		= 999999;
		String layovers 	= null;
		return new Layover(origin, destination, fuelCost, layovers);
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

	public static Layover concat(Layover leg1, Layover leg2){
		Layover result = new Layover(leg1);
		result.appendLayover(leg2);
		return result;
	}
	
}
