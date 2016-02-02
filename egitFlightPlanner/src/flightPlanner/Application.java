package flightPlanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String txtName = "/home/dwin357/Desktop/graph.txt";
		
		FlightPlanner planner = new FlightPlanner("testGraph");
//		planner.
		
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
