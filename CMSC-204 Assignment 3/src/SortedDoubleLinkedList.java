import java.util.Comparator;
import java.util.ListIterator;
/**
 * Implements a generic sorted double list using a provided Comparator. 
 * It extends BasicDoubleLinkedList class.
 * @author Wayne Bonifacio
 *
 * @param <T> - data element type
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> 
implements Iterable<T> {

	private Comparator<T> comprtr;
	
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param comprtr - Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(Comparator<T> comprtr) {
		this.comprtr = comprtr;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data - the data to be added to the list 
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		Node newNode = new Node(data);
		Node currNode = head;
		
		if(size == 0) {
			head = newNode;
			tail = head;
			size++;
			return this;
		} else if(comprtr.compare(head.data, data) > 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			size++;
			return this;
		} else {
			while(comprtr.compare(currNode.data, data) < 0) {
				if(currNode.next == null) {
					currNode.next = newNode;
					newNode.prev = currNode;
					tail = newNode;
					size++;
					return this;
				} else {
					currNode = currNode.next;
				}
			}
			currNode.prev.next = newNode;
			newNode.prev = currNode.prev;
			currNode.prev = newNode;
			newNode.next = currNode;
			size++;
			return this;
		}
	}
	
	/**
	 * This operation is invalid for a sorted list. 
	 * An UnsupportedOperationException will be generated using the message 
	 * "Invalid operation for sorted list."
	 * 
	 * @param data - the data for the Node within the linked list
	 * @return reference to the current object
	 * @throws UnsupportedOperationException if method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This operation is invalid for a sorted list. 
	 * An UnsupportedOperationException will be generated using the message 
	 * "Invalid operation for sorted list."
	 * 
	 * @param data - the data for the Node within the linked list
	 * @return reference to the current object
	 * @throws UnsupportedOperationException if method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return an iterator positioned at the head of the list
	 */
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}
	
	/**
	 * Implements the remove operation by calling the super class remove method.
	 * @param data - the data element to be removed
	 * @param comparator - the comparator to determine equality of data elements
	 * @return a node containing the data or null
	 */
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}
	
	
	
	
	
	
	
	
	
}
