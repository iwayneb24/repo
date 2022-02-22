package notation;

import java.util.ArrayList;

import exceptions.StackOverflowException;
import exceptions.StackUnderflowException;
/**
 * A generic class for Stack that implements the StackInterface and its methods.
 * @author Wayne Bonifacio
 *
 * @param <T>
 */
public class MyStack<T> implements StackInterface<T> {
	
	private int first;
	private int last;
	private int size;
	private int numOfElements;
	private Object[] elements;
	
	/**
	 * Provide two constructors
	 * 1. takes in an int as the size of the stack
	 * 2. default constructor - uses default as the size of the stack
	 */
	public MyStack() {
		this.size = 24;
		this.elements = new Object[size];
	}
	
	/**
	 * Creates a MyStack that has the default size/capacity
	 * @param capacity
	 */
	public MyStack(int capacity) {
		this.size = capacity;
		this.first = -1;
		this.last = -1;
		this.numOfElements = 0;
		elements = new Object[capacity];
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return numOfElements == 0;
	}
	
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		return size == numOfElements;
	}
	
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		@SuppressWarnings("unchecked")
		T topEntry = (T) elements[last];
		
		if(topEntry == null) {
			return null;
		}
		elements[last] = null;
		last--;
		numOfElements--;
		
		return topEntry;
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		@SuppressWarnings("unchecked")
		T topEntry = (T) elements[last];
		return topEntry;
	}
	
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size() {
		return numOfElements;
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
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
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int index = first; index <= last; index++) {
			str.append(elements[index]);
		}
		return str.toString();
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		StringBuilder str = new StringBuilder();
		for(int index = first; index < last; index++) {
			str.append(elements[index] + delimiter);
		}
		str.append(elements[last]);
		return str.toString();
	}
	
	/**
	 * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	 * is the first bottom element of the Stack
	 * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	 * list reference within your Stack, you will be allowing direct access to the data of
	 * your Stack causing a possible security breech.
	 * @param list elements to be added to the Stack from bottom to top
	 * @throws StackOverflowException if stack gets full
	 */
	@Override
	public void fill(ArrayList<T> list) {
		for(T copyList : list) {
			try {
				this.push(copyList);
			} catch(StackOverflowException e) {
				e.getMessage();
			}
		}
	}

	
}
