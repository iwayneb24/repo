import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_GFA_TEST {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;


	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(1,linkedString.getSize());
	}
	

}
