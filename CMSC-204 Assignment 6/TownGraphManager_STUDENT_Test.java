import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] town;

	@Before
	public void setUp() throws Exception {
		graph = new TownGraphManager();
		town = new String[6];

		for (int i = 1; i < 6; i++) {
			town[i] = "Town_" + i;
			graph.addTown(town[i]);
		}

		graph.addRoad(town[1], town[5], 3, "Road_1");
		graph.addRoad(town[1], town[3], 4, "Road_2");
		graph.addRoad(town[2], town[4], 5, "Road_3");
		graph.addRoad(town[2], town[5], 4, "Road_4");
		graph.addRoad(town[3], town[4], 2, "Road_5");


	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		graph.addRoad(town[1], town[4], 5, "Road_7");
		roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_7", roads.get(5));

	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_4", graph.getRoad(town[2], town[5]));
		assertEquals("Road_1", graph.getRoad(town[1], town[5]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_8"));
		graph.addTown("Town_8");
		assertEquals(true, graph.containsTown("Town_8"));
	}



	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_5"));
		assertEquals(false, graph.containsTown("Town_10"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[1], town[3]));
		assertEquals(false, graph.containsRoadConnection(town[1], town[4]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_2", roads.get(1));
		assertEquals("Road_3", roads.get(2));
		assertEquals("Road_5", roads.get(4));

	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[4]));
		graph.deleteRoadConnection(town[2], town[4], "Road_3");
		assertEquals(false, graph.containsRoadConnection(town[2], town[4]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_4"));
		graph.deleteTown(town[4]);
		assertEquals(false, graph.containsTown("Town_4"));
	}



	@Test
	public void testAllTowns() {
		ArrayList<String> towns = graph.allTowns();

		assertEquals("Town_1", towns.get(0));
		assertEquals("Town_2", towns.get(1));
		assertEquals("Town_3", towns.get(2));
		assertEquals("Town_4", towns.get(3));

	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1], town[5]);
		assertNotNull(path);
		assertTrue(path.size() > 0);
		assertEquals("Town_1 via Road_1 to Town_5 3 mi", path.get(0).trim());

	}


	
}
