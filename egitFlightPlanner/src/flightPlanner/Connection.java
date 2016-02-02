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
	public Connection(String[] arg) {
		super();
		this.origin = arg[0];
		this.destination = arg[1];
		this.fuelCost = Integer.parseInt(arg[2]);
		this.next = null;
		this.layover = null;
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
	
	public static Connection connectionFromLine(String line){
		line = chompComma(line);	
	
		String[] workInProgress = splitDistance(line);
		int distance = Integer.parseInt(workInProgress[0]);
	
		workInProgress = splitLocations(workInProgress[1]);
		String from = workInProgress[0];
		String to = workInProgress[1];
		
		Connection connection = new Connection(from, to, distance);
		return connection;
	}
	
	private static String chompComma(String line){
		int indexLast = line.length();
		if (indexLast > 0 && line.substring(indexLast-1, indexLast).equals(",")){
			line = line.substring(0, indexLast-1);
		}
		return line;
	}
	
	private static String[] splitDistance(String line){
		String[] temp = line.split("(?<=\\D)(?=\\d)");
		String[] distance = { temp[1], temp[0] };
		return distance;
	}
	
	private static String[] splitLocations(String line){
		return line.split("(?<!^)(?=[A-Z])");
	}

}