package flightPlanner;

public class Connection {
	private String origin;
	private String destination;
	private int fuelCost;
	private Connection next;
	private String layover;
	
	public Connection(String origin, String destination, int fuelCost,
			String layover) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.fuelCost = fuelCost;
		this.next = null;
		this.layover = layover;
	}
	public Connection(String origin, String destination, int fuelCost,
			Connection next) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.fuelCost = fuelCost;
		this.next = next;
		this.layover = null;
	}
	public Connection(String origin, String destination, int fuelCost) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.fuelCost = fuelCost;
		this.next = null;
		this.layover = null;
	}
	public Connection getNext() {
		return next;
	}
	public void setNext(Connection next) {
		this.next = next;
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
	public String getLayover() {
		return layover;
	}
	

}