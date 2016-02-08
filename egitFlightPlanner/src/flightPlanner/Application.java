package flightPlanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		String txtName = "/home/dwin357/Desktop/graph.txt";
		
		ArrayList<String> test = new ArrayList<String>();
		test.add("A");
		test.add("B");
		test.add("C");
		System.out.println("of A " +test.indexOf("A"));
		System.out.println("of C " +test.indexOf("C"));
		
		System.out.println("size " + test.size());
		
		String[][] q = new String[2][2];
		q[1][1] = "set";
		System.out.println(q[2][2] == null);
		System.out.println(q[1][1].equals("set"));
//		
//		FlightPlanner planner = new FlightPlanner("testGraph");
//		String[] sample = {"C", "C", null, "3"};
////		TripAccumulator subject = new TripAccumulator(sample, planner.getAirports());
//		TripAccumulator subject = new TripAccumulator("C", "C", planner.getAirports());
//		ArrayList<Layover> rtn = subject.accumulateWithinLayoverLimitN(4);
//		for(Layover layover : rtn){
//			System.out.println(layover.getFullPath());
//		}
		
//		HashMap<String, Airport> test = planner.getAirports();
		
//		String[] testCase = {"a", "b", null, "c"};
//		String[] alt = testCase;
//		int bomb = Integer.parseInt(alt[2]);
////		System.out.println(alt);
//		for(int i = 0; i < 4; i++){
//			if((alt[i]!=null)){
//				System.out.println(alt[i]);
//			}
//		}
		
//		for(Map.Entry<String, Airport> airport : test.entrySet()){ 
//			System.out.println(airport.getKey() + " key value");
//			System.out.println(airport.getValue().getName() + " method on obj");
//			System.out.println(airport.getValue().hasConnection("D") + " connects to D?");
//		}

		
		
		String text = "CamelCase";
//		text.for
	
//		String test = "";
//		int lastIndex = test.length();
//		System.out.println(lastIndex);
//		System.out.println(test.substring(0, lastIndex-1));
		
		
////		String csvName = "csvGraph.csv";
//		File txt = new File(txtName);
////		File csv = new File(csvName);
//		
//		
//		try {
////			CSVParser parser = CSVParser.parse(txt, Charset.forName("UTF-8"), CSVFormat.RFC4180);
////			for(CSVRecord csvRecord : parser) {
////			     System.out.println(csvRecord);
////			 }
//		
//			Scanner txtSc = new Scanner(txt);
//			while(txtSc.hasNext()){
//				System.out.println(txtSc.next());
//			}
//			txtSc.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("graph in the package folder didn't work"); 
//		} catch (IOException e) {
//			System.out.println("reeroe");
//		}
//		
//		try {
//			Scanner csvSc = new Scanner(csv);
//			while(csvSc.hasNext()){
//				System.out.println(csvSc.next());
//			}
//			csvSc.close();
//		} catch (FileNotFoundException e){
//			System.out.println("graphData.txt under src folder didn't work");
//		}
		
//		Interface portal;
//		if(args.length > 0){
//			portal = new Interface(args[0]);
//		} else {
//			portal = new Interface();
//		}
//		portal.run();
	}

}
