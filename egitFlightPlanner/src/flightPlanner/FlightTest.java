package flightPlanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlightTest {
	
	public Flight subject;

	@Before
	public void setUp() throws Exception {
		subject =  new Flight("Ohare", "Dallas", 200);
	}
	
	@Test
	public void testFlightFromLine(){
		String expectedArgs = "HardCase400";
		String alternateArgs = "CommaTest32,";
		Flight subject1 = Flight.flightFromLine(expectedArgs);
		Flight subject2 = Flight.flightFromLine(alternateArgs);
		
		assertEquals("Hard", subject1.getOrigin());
		assertEquals("Case", subject1.getDestination());
		assertEquals("Test", subject2.getDestination());
		assertEquals(400, subject1.getFuelCost());
	}
	
	@Test
	public void testGetOrigin(){
		assertEquals("Ohare", subject.getOrigin());
	}
	
	@Test
	public void testGetDestination(){
		assertEquals("Dallas", subject.getDestination());
	}
	
	@Test
	public void testGetFuelCost(){
		assertEquals(200, subject.getFuelCost());
	}
}
