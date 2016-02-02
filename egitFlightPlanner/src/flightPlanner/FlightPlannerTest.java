package flightPlanner;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class FlightPlannerTest {
	
	public FlightPlanner subject;
	
	@Before
	public void setUp() throws Exception {
//		testGraph text is below, the file is found floating in the project directory
//		Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7, HardCase300, BCase360, HardC92
		subject = new FlightPlanner("testGraph");
		
	}
	
	@Test
	public void testFileLoad(){
		String[] expected_return = {"A", "B", "C", "D", "E", "Hard"};
		assertArrayEquals(expected_return, subject.collectAirports());
	}

	@Test
	public void testFuelCostForTrip() {
		assertEquals(9, subject.fuelCostForTrip("A-B-C"));
		assertEquals(5, subject.fuelCostForTrip("A-D"));
		assertEquals(22, subject.fuelCostForTrip("A-E-B-C-D"));
		assertEquals(13, subject.fuelCostForTrip("A-D-C"));
		assertEquals(-1, subject.fuelCostForTrip("A-E-D"));
	}

//	@Test
//	public void 
}
