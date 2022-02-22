package notation;

import java.util.ArrayList;

import exceptions.QueueOverflowException;
import exceptions.QueueUnderflowException;
/**
 * A generic class for Queue that implements the QueueInterface and its methods.
 * @author Wayne Bonifacio
 *
 * @param <T>
 */
public class MyQueue<T> implements QueueInterface<T> {
	private int first;
	private int last;
	private int size;
	private int numOfElements;
	private Object[] elements;
	
	public MyQueue() {
		size = 24;
		elements = new Object[size];
	}
	
	/**
	 * Creates a MyQueue that has the default size/capacity
	 * @param capacity
	 */
	public MyQueue(int capacity) {
		this.size = capacity;
		this.first = -1;
		this.last = -1;
		this.numOfElements = 0;
		elements = new Object[capacity];
	}
	
	
	/**
	 * Checks if the queue is empty.
	 * @return true/false depending if the queue is empty or not.
	 */
	public boolean isEmpty() {
		return numOfElements == 0;
	}
	
	/**
	 * Checks if the queue is full.
	 * @return true/false depending if the queue is full or not.
	 */
	public boolean isFull() {
		return size == numOfElements;
	}
	
	/**
	 * It removes and returns the entry at the front of the queue.
	 * @return the queue's front entry.
	 * @throws QueueUnderflowException if the queue is empty.
	 */
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		@SuppressWarnings("unchecked")
		T frontQueue = (T) elements[first];
		
		if(frontQueue == null) {
			return null;
		}
		elements[first] = null;
		first++;
		numOfElements--;
		
		return frontQueue;
		
	}
	
	/**
	 * Checks the number of elements in the queue.
	 * @return the number of elements in the queue.
	 */
	public int size() {
		return numOfElements;
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		}
		
		if(isEmpty()) {
			first = 0;
			last = 0;
		} else {
			last++;
		}
		numOfElements++;
		elements[last] = e;
		
		return true;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int index = first; index <= last; index++) {
			str.append(elements[index]);
		}
		
		return str.toString();
	}
	
	
	/**
	 * Returns the string representation of the elements in the Queue, the
	 * beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	public String toString(String delimiter) {
		StringBuilder str = new StringBuilder();
		for(int index = first; index < last; index++) {
			str.append(elements[index] + delimiter);
		}
		str.append(elements[last]);
		
		return str.toString();
	}
	
	/**
	 * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	 * is the first element in the Queue
	 * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
	 * list reference within your Queue, you will be allowing direct access to the data of
	 * your Queue causing a possible security breech.
	 * @param list elements to be added to the Queue
	 * @throws QueueOverflowException if queue is full
	 */
	public void fill(ArrayList<T> list) {
		for(T copyList: list) {
			try {
				this.enqueue(copyList);
			} catch(QueueOverflowException e) {
				e.getMessage();
			}
		}
	}
	
	

	
	
}
