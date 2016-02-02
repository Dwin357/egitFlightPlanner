package flightPlanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConnectionTest {
	
	public Connection simple_subject;
	public Connection connection_subject;
	public Connection layover_subject;

	@Before
	public void setUp() throws Exception {
		simple_subject =  new Connection("Ohare", "Dallas", 200);
		connection_subject = new Connection("Dallas", "Phoneix", 150, simple_subject);
		layover_subject = new Connection("Ohare", "Phoneix", 350, "-Dallas-");
	}
	
	@Test
	public void testConnectionFromLine(){
		String expectedArgs = "HardCase400";
		String alternateArgs = "CommaTest32,";
		Connection subject1 = Connection.connectionFromLine(expectedArgs);
		Connection subject2 = Connection.connectionFromLine(alternateArgs);
		
		assertEquals("Hard", subject1.getOrigin());
		assertEquals("Case", subject1.getDestination());
		assertEquals("Test", subject2.getDestination());
		assertEquals(400, subject1.getFuelCost());
	}
	
	@Test
	public void testGetOrigin(){
		assertEquals("Ohare", simple_subject.getOrigin());
	}
	
	@Test
	public void testGetDestination(){
		assertEquals("Dallas", simple_subject.getDestination());
	}
	
	@Test
	public void testGetFuelCost(){
		assertEquals(200, simple_subject.getFuelCost());
	}
	
	@Test
	public void testGetLayover(){
		assertNull(simple_subject.getLayover());
		assertEquals("-Dallas-", layover_subject.getLayover());
	}

	@Test
	public void testGetNext() {
		assertNull(simple_subject.getNext());
		assertEquals(simple_subject, connection_subject.getNext());
	}
	
	@Test
	public void testSetNext(){
		simple_subject.setNext(layover_subject);
		assertEquals(layover_subject, simple_subject.getNext());
	}

}
