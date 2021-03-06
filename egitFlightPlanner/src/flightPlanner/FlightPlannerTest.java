package flightPlanner;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class FlightPlannerTest {
	
	public FlightPlanner simpleSubject;
	public FlightPlanner hardSubject;
	
	@Before
	public void setUp() throws Exception {
//		testGraph text is below, the file is found floating in the project directory
//		Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
//		
		simpleSubject = new FlightPlanner("testGraph");
		hardSubject = new FlightPlanner("hardCase");
		
	}
	
	@Test
	public void testFileLoad(){	
		String[] hardCaseReturn = {"Isolated", "Hard", "Nut", "Case"};
		String[] expectedReturn = {"A", "B", "C", "D", "E"};
		assertArrayEquals(expectedReturn, simpleSubject.collectAirports());
		assertArrayEquals(hardCaseReturn, hardSubject.collectAirports());
	}


//	@Test
//	public void testTripsWithinNLayovers(){
//		assertEquals()
//	}
}
