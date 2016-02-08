package flightPlanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OptimizedLookupTableTest {
	private OptimizedLookupTable subject;
	
	@Before
	public void setUp() throws Exception {
		FlightPlanner planner = new FlightPlanner("testGraph");
		subject = new OptimizedLookupTable(planner.getAirports());
	}

	
	@Test
	public void testCase8(){
		assertEquals(9, subject.optimizedFuelPath("A", "C").getFuelCost());
	}
	
	@Test
	public void testCase9(){
		assertEquals(9, subject.optimizedFuelPath("B", "B").getFuelCost());
	}
	
	
	@Test
	public void testBuildOfLookupTable(){
		assertEquals(5, subject.getListOfAirportNames().size());
	}

}
