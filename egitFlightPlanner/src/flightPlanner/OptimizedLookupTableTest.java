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

	
//	@Test
//	public void testCase8(){
//		assertEquals(9, subject.optimizedPath("A", "C", "fuel"));
//	}
//	
//	@Test
//	public void testCase9(){
//		assertEquals(9, subject.optimizedPath("B", "B", "fuel"));
//	}
//	
	
	@Test
	public void testBuildOfLookupTable(){
		assertEquals(5, subject.getListOfAirportNames().size());
	}

}
