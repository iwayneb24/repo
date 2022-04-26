import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	private Town town1, town2, town3;
	private Road road1, road2, road3;

	@Before
	public void setUp() throws Exception {
		town1 = new Town("Leaf");
		town2 = new Town("Rain");
		town3 = new Town("Mist");
		road1 = new Road(town1, town2, "Jiraiya");
		road2 = new Road(town2, town1, "Pain");
		road3 = new Road(town2, town3, "Madara");
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = null;
		road1 = road2 = road3 = null;
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, road1.compareTo(road1));
		assertEquals(6, road1.compareTo(road2));
		assertEquals(-3, road2.compareTo(road3));
	}

	@Test
	public void testContains() {
		assertTrue(road1.contains(town1));
		assertTrue(road1.contains(town2));
		assertFalse(road1.contains(town3));
	}

	@Test
	public void testEqualsObject() {
		assertTrue(road1.equals(road1));
		assertTrue(road1.equals(road2));
		assertFalse(road1.equals(road3));
	}

	@Test
	public void testToString() {
		assertEquals("Leaf via Jiraiya to Rain 1 mi", road1.toString());
		assertEquals("Rain via Pain to Leaf 1 mi", road2.toString());
	}

	@Test
	public void testGetSource() {
		assertEquals(town1, road1.getSource());
		assertEquals(town2, road2.getSource());
		assertEquals(town2, road3.getSource());
	}

	@Test
	public void testGetDestination() {
		assertEquals(town2, road1.getDestination());
		assertEquals(town1, road2.getDestination());
		assertEquals(town3, road3.getDestination());
	}

	@Test
	public void testGetDistance() {
		assertEquals(1, road1.getWeight());
		assertEquals(100, new Road(town3, town2, 100, "Kisame").getWeight());
	}

	@Test
	public void testGetName() {
		assertEquals("Jiraiya", road1.getName());
		assertEquals("Pain", road2.getName());
		assertEquals("Madara", road3.getName());
	}


}
