import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
	private Town town1, town2;

	@Before
	public void setUp() throws Exception {
		town1 = new Town("Leaf");
		town2 = new Town("Rain");
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = null;
	}

	@Test
	public void testHashCode() {
		assertEquals(town1.hashCode(), new Town("Leaf").hashCode());
		assertNotEquals(town1.hashCode(), new Town("Rain").hashCode());
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, town1.compareTo(town1));
		assertEquals(0, town1.compareTo(new Town("Leaf")));
		assertEquals(-6, town1.compareTo(town2));
	}

	@Test
	public void testEqualsObject() {
		assertEquals(true, town1.equals(town1));
		assertEquals(true, town1.equals(new Town("Leaf")));
		assertEquals(false, town1.equals(town2));
	}

	@Test
	public void testGetName() {
		assertEquals("Leaf", town1.getName());
		assertEquals("Rain", town2.getName());
	}

	@Test
	public void testToString() {
		assertEquals("Leaf", town1.toString());
		assertEquals("Rain", town2.toString());
	}

}
