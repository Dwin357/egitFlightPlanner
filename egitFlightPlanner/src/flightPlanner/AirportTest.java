package flightPlanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AirportTest {
	
	private Airport subjectWithConnection;
	private Airport subjectWithoutConnection;	
	private Connection sample_connection1;
	private Connection sample_connection2;
	private Connection sample_connection3;
	
	@Before
	public void setUp() throws Exception {
		subjectWithConnection = new Airport("Ohare");
		sample_connection1 = new Connection("Ohare", "Dallas", 200);
		sample_connection2 = new Connection("Ohare", "Phoneix", 350);
		subjectWithConnection.addConnection(sample_connection1);
		subjectWithConnection.addConnection(sample_connection2);
		
		subjectWithoutConnection = new Airport("Rockford");
		sample_connection3 = new Connection("Rockford", "Leguardia", 250);
	}

	@Test
	public void testAddConnection() {
		subjectWithoutConnection.addConnection(sample_connection3);
		assertTrue(subjectWithoutConnection.hasConnection(sample_connection3));
	}
	
	@Test
	public void testGetConnection() {
		assertEquals(sample_connection1, subjectWithConnection.getConnection("Dallas"));
	}
	
	@Test
	public void testHasConnectionConnection() {
		assertFalse(subjectWithConnection.hasConnection(sample_connection3));
		assertFalse(subjectWithoutConnection.hasConnection(sample_connection1));
		assertTrue(subjectWithConnection.hasConnection(sample_connection1));
	}

	@Test
	public void testHasConnectionString() {
		assertFalse(subjectWithConnection.hasConnection("Leguardia"));
		assertFalse(subjectWithoutConnection.hasConnection("Dallas"));
		assertTrue(subjectWithConnection.hasConnection("Dallas"));
	}

	@Test
	public void testGetName(){
		assertEquals("Ohare", subjectWithConnection.getName());
	}
}
