package flightPlanner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TripAccumulatorTest {
	private TripAccumulator subject;

	@Before
	public void setUp() throws Exception {
		FlightPlanner planner = new FlightPlanner("testGraph");
		subject = new TripAccumulator(planner.getAirports());
	}
	
	@Test
	public void testCase1() {
		assertEquals(9, subject.fuelCost("A-B-C"));
	}

	@Test
	public void testCase2(){
		assertEquals(5, subject.fuelCost("A-D"));
	}
	
	@Test
	public void testCase3(){
		assertEquals(13, subject.fuelCost("A-D-C"));
	}
	
	@Test
	public void testCase4(){
		assertEquals(22, subject.fuelCost("A-E-B-C-D"));
	}
	
	@Test
	public void testCase5(){
		assertEquals(-1,subject.fuelCost("A-E-D"));
	}
	
	@Test
	public void testCase6(){
		assertEquals(2, subject.countTripsWithinParamsInclusive("C", "C", "layovers", 3));
	}
	
	@Test
	public void testCase7(){
		assertEquals(3, subject.countTripsMatchingParams("A", "C", "layovers", 4));
	}

	@Test
	public void testCase10(){
		assertEquals(7, subject.countTripsWithinParamsExclusive("C", "C", "fuel", 30));
	}
}