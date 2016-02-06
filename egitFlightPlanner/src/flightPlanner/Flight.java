package flightPlanner;

public class Flight extends Trip {
	public Flight(String origin, String destination, int fuelCost) {
		super(origin, destination, fuelCost);
	}
	public Flight(String[] arg) {
		super(arg[0], arg[1], Integer.parseInt(arg[2]));
	}

	public static Flight flightFromLine(String line){
		line = chompComma(line);	
	
		String[] workInProgress = splitDistance(line);
		int distance = Integer.parseInt(workInProgress[0]);
	
		workInProgress = splitLocations(workInProgress[1]);
		String from = workInProgress[0];
		String to = workInProgress[1];
		
		Flight connection = new Flight(from, to, distance);
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