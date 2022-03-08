import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<ProPlayer> linkedPlayer;
	PlayerComparator comparator;

	public ProPlayer player1 = new ProPlayer("Kyrie Irving", "Brooklyn Nets", 2011);
	public ProPlayer player2 = new ProPlayer("Kobe Bryant", "LA Lakers", 1996);
	public ProPlayer player3 = new ProPlayer("Lebron James", "LA Lakers", 2003);
	public ProPlayer player4 = new ProPlayer("Michael Jordan", "Chicago Bulls", 1984);
	public ProPlayer player5 = new ProPlayer("Ja Morant", "Memphis Grizzlies", 2019);

	@Before
	public void setUp() throws Exception {
		linkedPlayer = new BasicDoubleLinkedList<ProPlayer>();
		linkedPlayer.addToEnd(player2);
		linkedPlayer.addToEnd(player3);
		linkedPlayer.addToEnd(player4);
		comparator = new PlayerComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedPlayer = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(3, linkedPlayer.getSize());
	}

	@Test
	public void testAddToEnd() {
		assertEquals(player4, linkedPlayer.getLast());
		linkedPlayer.addToEnd(player5);
		assertEquals(player5, linkedPlayer.getLast());
	}

	@Test
	public void testAddToFront() {
		assertEquals(player2, linkedPlayer.getFirst());
		linkedPlayer.addToFront(player1);
		assertEquals(player1, linkedPlayer.getFirst());
	}

	@Test
	public void testGetFirst() {
		assertEquals(player2, linkedPlayer.getFirst());
		linkedPlayer.addToFront(player1);
		assertEquals(player1, linkedPlayer.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals(player4, linkedPlayer.getLast());
		linkedPlayer.addToEnd(player5);
		assertEquals(player5, linkedPlayer.getLast());
	}

	@Test
	public void testToArrayList() {
		ArrayList<ProPlayer> list;
		linkedPlayer.addToFront(player1);
		linkedPlayer.addToEnd(player5);
		list = linkedPlayer.toArrayList();
		assertEquals(player1, list.get(0));
		assertEquals(player2, list.get(1));
		assertEquals(player3, list.get(2));
		assertEquals(player4, list.get(3));
		assertEquals(player5, list.get(4));
	}

	@Test
	public void testIteratorSuccessfulNext() {
		linkedPlayer.addToFront(player1);
		linkedPlayer.addToEnd(player5);
		ListIterator<ProPlayer> iterator = linkedPlayer.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(player1, iterator.next());
		assertEquals(player2, iterator.next());
		assertEquals(player3, iterator.next());
		assertEquals(player4, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(player5, iterator.next());


	}

	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedPlayer.addToFront(player1);
		linkedPlayer.addToEnd(player5);
		ListIterator<ProPlayer> iterator = linkedPlayer.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(player1, iterator.next());
		assertEquals(player2, iterator.next());
		assertEquals(player3, iterator.next());
		assertEquals(player4, iterator.next());
		assertEquals(player5, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(player5, iterator.previous());
		assertEquals(player4, iterator.previous());
		assertEquals(player3, iterator.previous());
		assertEquals(player2, iterator.previous());
		assertEquals(player1, iterator.previous());
	}

	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedPlayer.addToFront(player1);
		linkedPlayer.addToEnd(player5);
		ListIterator<ProPlayer> iterator = linkedPlayer.iterator();		
		assertEquals(true, iterator.hasNext());
		assertEquals(player1, iterator.next());
		assertEquals(player2, iterator.next());
		assertEquals(player3, iterator.next());
		assertEquals(player4, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(player5, iterator.next());

		try {
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
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedPlayer.addToFront(player1);
		linkedPlayer.addToEnd(player5);
		ListIterator<ProPlayer> iterator = linkedPlayer.iterator();		
		assertEquals(true, iterator.hasNext());
		assertEquals(player1, iterator.next());
		assertEquals(player2, iterator.next());
		assertEquals(player3, iterator.next());
		assertEquals(player4, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(player5, iterator.next());

		assertEquals(true, iterator.hasPrevious());
		assertEquals(player5, iterator.previous());
		assertEquals(player4, iterator.previous());
		assertEquals(player3, iterator.previous());
		assertEquals(player2, iterator.previous());
		assertEquals(player1, iterator.previous());

		try{
			//no more elements in list
			iterator.previous();
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
	public void testIteratorUnsupportedOperationException() {
		linkedPlayer.addToFront(player1);
		ListIterator<ProPlayer> iterator = linkedPlayer.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(player1, iterator.next());
		assertEquals(player2, iterator.next());

		try {
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}

	}

	@Test
	public void testRemove() {
		assertEquals(3, linkedPlayer.getSize());
		assertEquals(player2, linkedPlayer.getFirst());
		assertEquals(player4, linkedPlayer.getLast());
		linkedPlayer.remove(player2, comparator);
		assertEquals(2, linkedPlayer.getSize());
		assertEquals(player3, linkedPlayer.getFirst());
		assertEquals(player4, linkedPlayer.getLast());
		linkedPlayer.addToFront(player1);
		assertEquals(player1, linkedPlayer.getFirst());
		assertEquals(player4, linkedPlayer.getLast());
		linkedPlayer.remove(player4, comparator);
		assertEquals(2, linkedPlayer.getSize());
		assertEquals(player1, linkedPlayer.getFirst());
		assertEquals(player3, linkedPlayer.getLast());

	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(3, linkedPlayer.getSize());
		assertEquals(player2, linkedPlayer.getFirst());
		assertEquals(player4, linkedPlayer.getLast());
		linkedPlayer.addToFront(player1);
		assertEquals(player1, linkedPlayer.getFirst());
		assertEquals(player1, linkedPlayer.retrieveFirstElement());
		assertEquals(player2, linkedPlayer.getFirst());
		assertEquals(3, linkedPlayer.getSize());
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(3, linkedPlayer.getSize());
		assertEquals(player2, linkedPlayer.getFirst());
		assertEquals(player4, linkedPlayer.getLast());
		linkedPlayer.addToEnd(player5);
		assertEquals(player5, linkedPlayer.getLast());
		assertEquals(player5, linkedPlayer.retrieveLastElement());
		assertEquals(player4, linkedPlayer.getLast());
		assertEquals(3, linkedPlayer.getSize());
	}


	private class ProPlayer {
		String name;
		String nbaTeam;
		int yearDrafted;

		public ProPlayer(String name, String nbaTeam, int yearDrafted) {
			this.name = name;
			this.nbaTeam = nbaTeam;
			this.yearDrafted = yearDrafted;
		}

		public String getName() {
			return name;
		}

		public String getTeam() {
			return nbaTeam;
		}

		public int getYear() {
			return yearDrafted;
		}

		public String toString() {
			return (getName() + ", " + getTeam() + ", " + getYear());
		}
	}

	private class PlayerComparator implements Comparator<ProPlayer> {
		@Override
		public int compare(ProPlayer first, ProPlayer second) {
			return first.toString().compareTo(second.toString());
		}
	}
}
