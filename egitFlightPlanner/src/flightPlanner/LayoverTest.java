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
	public void testGetFullPath() {
		Layover subject = Layover.addConnections(sample1, sample2);
		assertEquals("Dallas-Ohare-Denver", subject.getFullPath());
	}
	
	@Test
	public void testGetLayovers(){
		Layover subject1 = Layover.addConnections(sample1, sample2);
		Layover subject2 = Layover.appendConnection(subject1,  sample3);
		assertEquals("Ohare", subject1.getLayovers());
		assertEquals("Ohare-Denver", subject2.getLayovers());
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
		Layover origin = Layover.origin("Dallas");
		Layover subject1 = Layover.appendConnection(origin, sample1);
		Layover subject2 = Layover.appendConnection(subject1, sample2);
		Layover subject3 = Layover.appendConnection(subject2, sample3);
		
		assertEquals("Dallas", subject3.getOrigin());
		assertEquals("Seatle", subject3.getDestination());
		assertEquals("Ohare-Denver", subject3.getLayovers());
		assertEquals(600, subject3.getFuelCost());
		
		assertEquals("Ohare", subject2.getLayovers());
	}
	
	@Test
	public void testPrependConnection(){
		Layover origin = Layover.origin("Seatle");
		Layover subject1 = Layover.prependConnection(origin, sample3);
		Layover subject2 = Layover.prependConnection(subject1, sample2);
		Layover subject3 = Layover.prependConnection(subject2, sample1);
		
		assertEquals("Dallas", subject3.getOrigin());
		assertEquals("Seatle", subject3.getDestination());
		assertEquals("Ohare-Denver", subject3.getLayovers());
		assertEquals(600, subject3.getFuelCost());
		
		assertEquals("Denver", subject2.getLayovers());
	}

}
