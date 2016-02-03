package flightPlanner;

abstract class Trip {

	protected String origin;
	protected String destination;
	protected int fuelCost;
	
	public Trip(String origin, String destination, int fuelCost){
		this.origin = origin;
		this.destination = destination;
		this.fuelCost = fuelCost;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public int getFuelCost() {
		return fuelCost;
	}

}