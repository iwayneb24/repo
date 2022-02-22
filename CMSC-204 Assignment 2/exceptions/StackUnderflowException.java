package exceptions;
/**
 * StackUnderflowException is thrown when a top or pop method is called on an empty stack.
 * @author Wayne Bonifacio
 *
 */
public class StackUnderflowException extends Exception {
	
	private static final long serialVersionUID = 5702572722911577923L;

	public StackUnderflowException() {
		super("Pop or top method has been called on an empty stack");
	}
}
