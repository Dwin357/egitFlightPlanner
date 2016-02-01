package flightPlanner;

import java.util.Scanner;

public class Interface {
	private FlightPlanner flightPlanner;
	private Scanner sc;

	public Interface(String file){
		sc = new Scanner(System.in);
		flightPlanner = new FlightPlanner(file);
	}
	
	public Interface(){
		sc = new Scanner(System.in);
		flightPlanner = new FlightPlanner(promptForFile());
	}
	
	public void run(){
		// a loop running around a case statement
	}
	
	private String promptForFile(){
		System.out.println("please enter the file name of the flights-graph");
		return sc.next();
	}

}
