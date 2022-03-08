import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This generic double-linked list that implements the Iterable interface and relies on a head 
 * (reference to first element of the list) and tail (reference to the last element of the list). 
 * Both are set to null when the list is empty. Both point to the same element when there is only one element in the list. 
 * The class must only define the following entities:
 * 1) an inner class Node, The inner Node class has only three fields: data, the prev and next references.
 * 2) an inner class named DoubleLinkedListIterator that implements ListIterator (for the iterator method), 
 * This inner class implements only the hasNext(), next(), hasPrevious() and previous() methods of ListIterator, 
 * all other methods can throw the UnsupportedOperationException:
 * All the entities are defined as protected so they can be accessed by the subclasses.
 * 
 * @author Wayne Bonifacio
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node head, tail;
	protected int size;
	
	
	public class Node {
		protected T data;
		protected Node next;
		protected Node prev;
		
		public Node(T data) {
			this.next = null;
			this.prev = null;
			this.data = data;
		}
	}
	
	
	/**
	 * Constructor to set to initialize head, tail and size to null, null and 0
	 */
	public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/**
	 * Returns the number of nodes in the list.
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Adds an element to the end of the list and updated the size of the list
	 * @param data - data to be added to the list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node newTail = new Node(data);
		
		if(size != 0) {
			tail.next = newTail;
			newTail.prev = tail;
			tail = newTail;
		} else {
			head = newTail;
			tail = head;
		}
		size++;
		return this;	
	}
	
	/**
	 * Adds an element to the front of the list and updated the size of the list
	 * @param data - the data to be added to the list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node newHead = new Node(data);
		
		if(size != 0) {
			head.prev = newHead;
			newHead.next = head;
			head = newHead;
		} else {
			head = newHead;
			tail = head;
		}
		size++;
		return this;
	}
	
	/**
	 * Returns but does not remove the first element from the list. If there's
	 * no elements, returns null.
	 * @return the data element or null if there's no elements.
	 */
	public T getFirst() {
		if(size != 0) {
			return head.data;
		}
		return null;
	}
	
	/**
	 * Returns but does not remove the last element from the list. If there's
	 * no elements, returns null.
	 * @return the data element or null if there's no elements.
	 */
	public T getLast() {
		if(size != 0) {
			return tail.data;
		}
		return null;
	}
	
	/**
	 * This method returns an object of the DoubleLinkedListIterator.
	 * @return a ListIterator object
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException, 
	NoSuchElementException {
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * Removes the first instance of the targetData from the list.
	 * Notice that you must remove the elements by performing a single traversal over the list.
	 * You may not use any of the other retrieval methods associated with the class in order to complete the removal process.
	 * You must use the provided comparator (do not use equals) to find those elements that match the target.
	 * Do not implement this method using iterators.
	 * @param targetData - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return a node containing the targetData or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node currNode = head;
		while(currNode != null) {
			if(comparator.compare(targetData, currNode.data) == 0) {
				if(currNode == head) {
					head = currNode.next;
				} else if(currNode == tail) {
					tail = currNode.prev;
				} else {
					currNode.prev.next = currNode.next;
				}
				size--;
				return this;
			}
			currNode = currNode.next;
		}
		return this;
	}
	
	/**
	 * Removes and returns the first element from the list. 
	 * If there are no elements the method returns null. 
	 * Do not implement this method using iterators.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		if(size == 0) {
			return null;
		}
		T firstElem = head.data;
		if(size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size--;
		return firstElem;
	}
	
	/**
	 * Removes and returns the last element from the list. 
	 * If there are no elements the method returns null. 
	 * Do not implement implement this method using iterators.
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		if(size == 0) {
			return null;
		}
		T lastElem = tail.data;
		if(size == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.prev = null;
		}
		size--;
		return lastElem;
	}
	
	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> tempArray = new ArrayList<>();
		Node currNode = head;
		while(currNode != null) {
			tempArray.add(currNode.data);
			currNode = currNode.next;
		}
		return tempArray;
	}
	
	
	public class DoubleLinkedListIterator implements ListIterator<T> {
		
		protected Node current = head;
		protected Node last;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() throws NoSuchElementException {
			if(hasNext()) {
				last = current;
				current = current.next;
				return last.data;
			}
			throw new NoSuchElementException("No next elements available in List");
		}

		@Override
		public boolean hasPrevious() {
			return last != null;
		}

		@Override
		public T previous() throws NoSuchElementException {
			if(hasPrevious()) {
				current = last;
				last = last.prev;
				return current.data;
			}
			throw new NoSuchElementException("No previous elements available in List");
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T data) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void add(T data) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
	}
	

}



