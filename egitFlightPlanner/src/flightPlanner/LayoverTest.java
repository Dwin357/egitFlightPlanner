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
		Layover subject = Layover.addConnection(sample1, sample2);
		assertEquals("Dallas-Ohare-Denver", subject.getFullPath());
	}
	
	@Test
	public void testGetLayovers(){
		Layover subject1 = Layover.addConnection(sample1, sample2);
		Layover subject2 = Layover.addConnection(subject1,  sample3);
		assertEquals("Ohare", subject1.getLayovers());
		assertEquals("Ohare-Denver", subject2.getLayovers());
	}
	
	@Test
	public void testGetOrigin(){
		Layover subject = Layover.addConnection(sample1, sample2);
		assertEquals("Dallas", subject.getOrigin());
	}
	
	@Test
	public void testGetDestination(){
		Layover subject = Layover.addConnection(sample1, sample2);
		assertEquals("Denver", subject.getDestination());
	}
	
	@Test
	public void testGetFuelCost(){
		Layover subject = Layover.addConnection(sample1, sample2);
		assertEquals(500, subject.getFuelCost());
	}

	@Test
	public void testAddConnectionConnectionConnection() {
		Layover subject = Layover.addConnection(sample1, sample2);
		assertEquals("Dallas", subject.getOrigin());
		assertEquals("Denver", subject.getDestination());
		assertEquals("Ohare", subject.getLayovers());
		assertEquals(500, subject.getFuelCost());		
	}

	@Test
	public void testAddConnectionLayoverConnection() {
		Layover original = Layover.addConnection(sample1, sample2);
		Layover subject  = Layover.addConnection(original, sample3);
		assertEquals("Dallas", subject.getOrigin());
		assertEquals("Seatle", subject.getDestination());
		assertEquals("Ohare-Denver", subject.getLayovers());
		assertEquals(600, subject.getFuelCost());
	}

}
