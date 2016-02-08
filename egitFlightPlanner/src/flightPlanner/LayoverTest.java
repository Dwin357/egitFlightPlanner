package flightPlanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LayoverTest {
	private Connection sample1;
	private Connection sample2;
	private Connection sample3;

	@Before
	public void setUp() throws Exception {
		String[] arg1 = {"Dallas", "Ohare", "200"};
		String[] arg2 = {"Ohare", "Denver", "300"};
		String[] arg3 = {"Denver", "Seatle", "100"};
		
		sample1 = new Connection(arg1);
		sample2 = new Connection(arg2);
		sample3 = new Connection(arg3);
		
	}
	
	@Test
	public void testLayoverFromFlight(){
		Layover subject = new Layover(sample1);
		assertEquals("Dallas", subject.getOrigin());
		assertEquals("Ohare", subject.getDestination());
		assertEquals(200, subject.getFuelCost());
		assertEquals("", subject.getLayovers());
	}
	
	@Test
	public void testLayoverStopCount(){
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals(1, subject.layoverStopCount());
		
		Layover subject2 = Layover.origin("Dallas");
		assertEquals(0, subject2.layoverStopCount());
		
		subject2.appendConnection(sample1);
		assertEquals(0, subject2.layoverStopCount());
	}

	@Test
	public void testGetFullPath() {
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals("Dallas-Ohare-Denver", subject.getFullPath());
	}
	
	@Test
	public void testGetLayovers(){
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals("Ohare", subject.getLayovers());
		
		subject.appendConnection(sample3);
		assertEquals("Ohare-Denver", subject.getLayovers());
	}
	
	@Test
	public void testGetOrigin(){
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals("Dallas", subject.getOrigin());
	}
	
	@Test
	public void testGetDestination(){
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals("Denver", subject.getDestination());
	}
	
	@Test
	public void testGetFuelCost(){
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals(500, subject.getFuelCost());
	}

	@Test
	public void testAddConnections() {
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals("Dallas", subject.getOrigin());
		assertEquals("Denver", subject.getDestination());
		assertEquals("Ohare", subject.getLayovers());
		assertEquals(500, subject.getFuelCost());		
	}

	@Test
	public void testOrigin(){
		Layover subject = Layover.origin("Rockford");
		assertEquals("Rockford", subject.getOrigin());
		assertEquals("Rockford", subject.getDestination());
		assertEquals(null, subject.getLayovers());
		assertEquals(0, subject.getFuelCost());		
	}
	
	@Test
	public void testAppendConnection() {
		Layover subject = Layover.origin("Dallas");
				subject.appendConnection(sample1);
				subject.appendConnection(sample2);
		assertEquals("Ohare", subject.getLayovers());
		
		subject.appendConnection(sample3);		
		assertEquals("Dallas", subject.getOrigin());
		assertEquals("Seatle", subject.getDestination());
		assertEquals("Ohare-Denver", subject.getLayovers());
		assertEquals(600, subject.getFuelCost());
		

	}
	
	@Test
	public void testPrependConnection(){
		Layover subject = Layover.origin("Seatle");
				subject.prependConnection(sample3);
				subject.prependConnection(sample2);
			assertEquals("Denver", subject.getLayovers());
				
				subject.prependConnection(sample1);
		
		assertEquals("Dallas", subject.getOrigin());
		assertEquals("Seatle", subject.getDestination());
		assertEquals("Ohare-Denver", subject.getLayovers());
		assertEquals(600, subject.getFuelCost());
		
		
	}

}
