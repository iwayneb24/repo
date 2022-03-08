import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SortedDoubleLinkedList_STUDENT_Test {
	
	private class ProPlayer {
		String name;
		int jerseyNum;
		int yearDrafted;

		public ProPlayer(String name, int jerseyNum, int yearDrafted) {
			this.name = name;
			this.jerseyNum = jerseyNum;
			this.yearDrafted = yearDrafted;
		}

		public String getName() {
			return name;
		}

		public int getNum() {
			return jerseyNum;
		}

		public int getYear() {
			return yearDrafted;
		}

		public String toString() {
			return(getName() + " " + getNum() + " " + getYear());
		}

	}

	private class PlayerComparator implements Comparator<ProPlayer> {

		@Override
		public int compare(SortedDoubleLinkedList_STUDENT_Test.ProPlayer o1,
				SortedDoubleLinkedList_STUDENT_Test.ProPlayer o2) {
			return o1.getName().compareTo(o2.getName());
		}

	}
	
	SortedDoubleLinkedList<ProPlayer> sortedLinkedPlayer;
	PlayerComparator comparator;
	
	public ProPlayer p1 = new ProPlayer("Kyrie", 11, 2011);
	public ProPlayer p2 = new ProPlayer("Ja", 12, 2019);
	public ProPlayer p3 = new ProPlayer("Zion", 1, 2019);
	public ProPlayer p4 = new ProPlayer("Kobe", 24, 1996);
	public ProPlayer p5 = new ProPlayer("Jordan", 23, 1984);
	//Order: p2, p5, p4, p1, p3
	
	@Before
	public void setUp() throws Exception {
		comparator = new PlayerComparator();
		sortedLinkedPlayer = new SortedDoubleLinkedList<ProPlayer>(comparator);
		
	}
	
	@After
	public void tearDown() throws Exception {
		comparator = null;
		sortedLinkedPlayer = null;
		
	}
	
	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedPlayer.addToEnd(p5);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
		      sortedLinkedPlayer.addToFront(p1);
		      assertTrue("Did not throw an UnsupportedOperationException", false);
		    } catch (UnsupportedOperationException e) {
		      assertTrue("Successfully threw an UnsupportedOperationException", true);
		    } catch (Exception e) {
		      assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		    }

	}
	
	
	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedPlayer.add(p1);
		sortedLinkedPlayer.add(p2);
		sortedLinkedPlayer.add(p3);
		sortedLinkedPlayer.add(p4);
		//Order: p2, p4, p1, p3
		ListIterator<ProPlayer> iterator = sortedLinkedPlayer.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(p2, iterator.next());
		assertEquals(p4, iterator.next());
		assertEquals(p1, iterator.next());
		assertEquals(true, iterator.hasNext());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		sortedLinkedPlayer.add(p1);
		sortedLinkedPlayer.add(p2);
		sortedLinkedPlayer.add(p3);
		sortedLinkedPlayer.add(p4);
		//Order: p2, p4, p1, p3
		ListIterator<ProPlayer> iterator = sortedLinkedPlayer.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(p2, iterator.next());
		assertEquals(p4, iterator.next());
		assertEquals(p1, iterator.next());
		assertEquals(p3, iterator.next());
		assertEquals(false, iterator.hasNext());
		
		assertEquals(true, iterator.hasPrevious());
		assertEquals(p3, iterator.previous());
		assertEquals(p1, iterator.previous());
		assertEquals(p4, iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedPlayer.add(p5);
		sortedLinkedPlayer.add(p3);
		sortedLinkedPlayer.add(p2);
		sortedLinkedPlayer.add(p4);
		//Order: p2, p5, p4, p3
		
		ListIterator<ProPlayer> iterator = sortedLinkedPlayer.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(p2, iterator.next());
		assertEquals(p5, iterator.next());
		assertEquals(p4, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(p3, iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedPlayer.add(p5);
		sortedLinkedPlayer.add(p3);
		sortedLinkedPlayer.add(p2);
		sortedLinkedPlayer.add(p4);
		//Order: p2, p5, p4, p3
		ListIterator<ProPlayer> iterator = sortedLinkedPlayer.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testAddPlayer() {
		//Original Order: p2, p5, p4, p1, p3
		sortedLinkedPlayer.add(p1);
		sortedLinkedPlayer.add(p5);
		sortedLinkedPlayer.add(p4);
		//Order: p5, p4, p1
		assertEquals(p5, sortedLinkedPlayer.getFirst());
		assertEquals(p1, sortedLinkedPlayer.getLast());
		sortedLinkedPlayer.add(p2);
		sortedLinkedPlayer.add(p3);
		assertEquals(p2, sortedLinkedPlayer.getFirst());
		assertEquals(p3, sortedLinkedPlayer.getLast());
		
		assertEquals(p3,sortedLinkedPlayer.retrieveLastElement());
		assertEquals(p1, sortedLinkedPlayer.getLast());
	}
	
	@Test
	public void testRemoveFirstPlayer() {
		//Original Order: p2, p5, p4, p1, p3
		sortedLinkedPlayer.add(p1);
		sortedLinkedPlayer.add(p5);
		//Order: p5, p1
		assertEquals(p5, sortedLinkedPlayer.getFirst()); //p5
		assertEquals(p1, sortedLinkedPlayer.getLast()); //p1
		sortedLinkedPlayer.add(p4);
		//Order when p4 added: p5, p4, p1
		assertEquals(p5, sortedLinkedPlayer.getFirst()); //p5
		// remove the first
		sortedLinkedPlayer.remove(p5, comparator);
		assertEquals(p4, sortedLinkedPlayer.getFirst());
	}
	
	@Test
	public void testRemoveEndPlayer() {
		//Original Order: p2, p5, p4, p1, p3
		sortedLinkedPlayer.add(p3);
		sortedLinkedPlayer.add(p4);
		//Order when added: p4, p3
		assertEquals(p4, sortedLinkedPlayer.getFirst());
		assertEquals(p3, sortedLinkedPlayer.getLast());
		sortedLinkedPlayer.add(p1);
		sortedLinkedPlayer.add(p2);
		//Order when added: p2, p4, p1 ,p3
		assertEquals(p3, sortedLinkedPlayer.getLast());
		//remove from the end of the list
		sortedLinkedPlayer.remove(p3, comparator);
		assertEquals(p1, sortedLinkedPlayer.getLast());
		//remove again from the end of the list to check if its the right order
		sortedLinkedPlayer.remove(p1, comparator);
		assertEquals(p4, sortedLinkedPlayer.getLast());
	}
	
	@Test
	public void testRemoveMiddlePlayer() {
		//Original Order: p2, p5, p4, p1, p3
		sortedLinkedPlayer.add(p1);
		sortedLinkedPlayer.add(p5);
		//Order when added: p5, p1
		assertEquals(p5, sortedLinkedPlayer.getFirst());
		assertEquals(p1, sortedLinkedPlayer.getLast());
		sortedLinkedPlayer.add(p2);
		//Order when added: p2, p5, p1
		assertEquals(p2, sortedLinkedPlayer.getFirst());
		assertEquals(p1, sortedLinkedPlayer.getLast());
		assertEquals(3,sortedLinkedPlayer.getSize());
		//remove from middle of list
		sortedLinkedPlayer.remove(p5, comparator);
		assertEquals(p2, sortedLinkedPlayer.getFirst());
		assertEquals(p1, sortedLinkedPlayer.getLast());
		//Order when middle is removed: p2, p1
		assertEquals(2,sortedLinkedPlayer.getSize());
	}


	

}



	
