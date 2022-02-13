package gradebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradeBookTest {
	private GradeBook g1, g2;
	
	@Before
	public void setUp() throws Exception {
		g1 = new GradeBook(5);
		g1.addScore(72);
		g1.addScore(84);
		g1.addScore(91);
		g1.addScore(99);
		
		g2 = new GradeBook(5);
		g2.addScore(68);
		g2.addScore(75);
		g2.addScore(96);
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}
	
	@Test
	public void testAddScore() {
		assertTrue(g1.toString().equals("72.0 84.0 91.0 99.0 "));
		assertFalse(g1.toString().equals("72 84 91 99 "));
		assertEquals(4, g1.getScoresSize());
		
		assertTrue(g2.toString().equals("68.0 75.0 96.0 "));
		assertFalse(g2.toString().equals("68 75 96 "));
		assertEquals(3, g2.getScoresSize());
	}
	
	@Test
	public void testSum() {
		assertEquals(346, g1.sum(), 0.0001);
		assertEquals(239, g2.sum(), 0.0001);
	}
	
	@Test
	public void testMinimum() {
		assertEquals(72, g1.minimum(), .001);
		assertEquals(68, g2.minimum(), .001);
	}
	
	@Test
	public void testFinalScore() {
		assertEquals(274, g1.finalScore(), .001);
		assertEquals(171, g2.finalScore(), .001);
	}
	
	
	
	
	
	

}
