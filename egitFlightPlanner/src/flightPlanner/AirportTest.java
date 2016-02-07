package flightPlanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AirportTest {
	
	private Airport subjectWithConnection;
	private Airport subjectWithoutConnection;	
	private Flight sample_flight1;
	private Flight sample_flight2;
	private Flight sample_flight3;
	private Flight sample_flight4;
	
	@Before
	public void setUp() throws Exception {
		subjectWithConnection = new Airport("Ohare");
		sample_flight1 = new Flight("Ohare", "Dallas", 200);
		sample_flight2 = new Flight("Ohare", "Phoneix", 350);
//		subjectWithConnection.addOrCreateConnection(sample_connection1, "direct");
//		subjectWithConnection.addOrCreateConnection(sample_connection2, "direct");
//		
		subjectWithoutConnection = new Airport("Rockford");
		sample_flight3 = new Flight("Rockford", "Leguardia", 250);
		sample_flight4 = new Flight("Rockford", "Tampa", 300);
	}
	
	@Test
	public void testAddOrCreateConnection(){
		subjectWithoutConnection.addOrCreateConnection("direct", sample_flight3);
		subjectWithoutConnection.addOrCreateConnection(sample_flight4);
		assertTrue(subjectWithoutConnection.getConnection(sample_flight4));
	}

//	@Test
//	public void testAddConnection() {
//		subjectWithoutConnection.addOrCreateConnection(sample_connection3, "direct");
//		assertTrue(subjectWithoutConnection.hasConnection(sample_connection3));
//	}
	
//	@Test
//	public void testGetConnection() {
//		assertEquals(sample_connection1, subjectWithConnection.getConnection("Dallas"));
//	}
//	
//	@Test
//	public void testHasConnectionConnection() {
//		assertFalse(subjectWithConnection.hasConnection(sample_connection3));
//		assertFalse(subjectWithoutConnection.hasConnection(sample_connection1));
//		assertTrue(subjectWithConnection.hasConnection(sample_connection1));
//	}
//
//	@Test
//	public void testHasConnectionString() {
//		assertFalse(subjectWithConnection.hasConnection("Leguardia"));
//		assertFalse(subjectWithoutConnection.hasConnection("Dallas"));
//		assertTrue(subjectWithConnection.hasConnection("Dallas"));
//	}

	@Test
	public void testGetName(){
		assertEquals("Ohare", subjectWithConnection.getName());
	}
	
//	@Test
//	public void testConnectionFuelCost(){
//		assertEquals(200, subjectWithConnection.connectionFuelCost("Dallas"));
//	}
}
