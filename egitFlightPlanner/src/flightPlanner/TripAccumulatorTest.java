package flightPlanner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TripAccumulatorTest {
	private HashMap<String, Airport> dataResource;
	private TripAccumulator publicSubject;

	@Before
	public void setUp() throws Exception {
		FlightPlanner planner = new FlightPlanner("testGraph");
		dataResource  = planner.getAirports();
		publicSubject = new TripAccumulator("C", "C", 2, 3, dataResource);
	}

	
	
	@Test
	public void testAccumulate() {
		
		ArrayList<Layover> output = publicSubject.accumulate();
		System.out.println(output);
		
//		assertEquals(1, output.size());
	}

	
	@Test
	public void testInt2StringNullGuard(){
		assertNull(TripAccumulator.int2StringNullGuard(null));
		assertEquals("3", TripAccumulator.int2StringNullGuard(3));
	}
	
	@Test
	public void testString2IntNullGuard(){
		assertNull(TripAccumulator.string2IntNullGuard(null));
		assertEquals((Integer) 3, TripAccumulator.string2IntNullGuard("3"));
	}
	
	@Test
	public void testDecrementArgs(){
		// {"currentLocation", "targeLocation", "remainingFuel", "remainingLayovers"}
		String[] inputArgs 	 = {"A", "D", "5", "6"};
		String[] nullFuel  	 = {"A", "D", null, "6"};
		String[] nullLayover = {"A", "D", "5", null};
		Connection adjustment = new Connection("A", "B", 3);
		
		String[] expectedArgs 	  = {"B", "D", "2", "5"};
		String[] expectedNullFuel = {"B", "D", null, "5"};
		String[] expectedNullLay  = {"B", "D", "2", null};
		
		assertArrayEquals(expectedArgs, publicSubject.decrementArgs(inputArgs, adjustment));
		assertArrayEquals(expectedNullFuel, publicSubject.decrementArgs(nullFuel, adjustment));
		assertArrayEquals(expectedNullLay, publicSubject.decrementArgs(nullLayover, adjustment));
	}
	
	@Test
	public void testCheckBounds(){
		String[] inputTrue 	 	  = {"A", "D", "5", "6"};
		String[] nullFuelTrue  	  = {"A", "D", null, "6"};
		String[] nullLayoverTrue  = {"A", "D", "5", null};
		
		String[] inputFalse  	  = {"A", "D", "5", "-1"};
		String[] inputFalse2  	  = {"A", "D", "-1", "6"};
		String[] inputFalse3  	  = {"A", "D", "-2", "-1"};
		String[] nullFuelFalse 	  = {"A", "D", null, "-1"};
		String[] nullLayoverFalse = {"A", "D", "-8", null};
		
		assertTrue(publicSubject.paramInBounds(inputTrue));
		assertTrue(publicSubject.paramInBounds(nullFuelTrue));
		assertTrue(publicSubject.paramInBounds(nullLayoverTrue));
		
		assertFalse(publicSubject.paramInBounds(inputFalse));
		assertFalse(publicSubject.paramInBounds(inputFalse2));
		assertFalse(publicSubject.paramInBounds(inputFalse3));
		assertFalse(publicSubject.paramInBounds(nullFuelFalse));
		assertFalse(publicSubject.paramInBounds(nullLayoverFalse));
	}
	
	@Test
	public void testNotateCurrentLocation(){
		
	}
}
