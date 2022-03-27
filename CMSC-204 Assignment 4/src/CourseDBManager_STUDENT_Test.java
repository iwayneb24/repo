import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {
	private CourseDBManagerInterface cdm = new CourseDBManager();

	@Before
	public void setUp() throws Exception {
		cdm = new CourseDBManager();
	}

	@After
	public void tearDown() throws Exception {
		cdm = null;
	}

	
	@Test
	public void testAddToDB() {
		try {
			cdm.add("PSY1010", 11999, 3, "PS108", "Sigmund Freud");
			assertEquals(1, cdm.showAll().size());
		} catch (Exception e) {
			fail("This should not have caused an Exception");
		}
	}

	
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("PSY1010 11999 3 PS108 Sigmund Freud");
			inFile.print("CMSC131 30504 4 SC450 Joey Bag-O-Donuts");
			inFile.close();

			assertEquals(0, cdm.showAll().size());
			cdm.readFile(inputFile);
			assertEquals(2, cdm.showAll().size());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}


	@Test
	public void testShowAll() {
		cdm.add("PSY1010", 11999, 3, "PS108", "Sigmund Freud");
		cdm.add("CMSC131", 30504, 4, "SC450", "Joey Bag-O-Donuts");
		cdm.add("SLM2020", 10000, 4, "KT400", "Kentrell Gaulden");
		ArrayList<String> list = cdm.showAll();

		assertEquals(list.get(1),
				"\nCourse:PSY1010 CRN:11999 Credits:3 Instructor:Sigmund Freud Room:PS108");
		assertEquals(list.get(2),
				"\nCourse:CMSC131 CRN:30504 Credits:4 Instructor:Joey Bag-O-Donuts Room:SC450");
		assertEquals(list.get(0),
				"\nCourse:SLM2020 CRN:10000 Credits:4 Instructor:Kentrell Gaulden Room:KT400");
	}

	
	@Test
	public void testGet() {
		cdm.add("PSY1010", 11999, 3, "PS108", "Sigmund Freud");
		cdm.add("SLM2020", 10000, 4, "KT400", "Kentrell Gaulden");
		ArrayList<String> list = cdm.showAll();
		
		assertEquals(list.get(0), cdm.get(10000).toString());

		assertEquals(list.get(1), cdm.get(11999).toString());

	}
	
	
	
}
