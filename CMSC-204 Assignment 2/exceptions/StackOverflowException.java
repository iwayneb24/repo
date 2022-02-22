package exceptions;
/**
 * StackOverflowException is thrown when a push method is called on a full stack.
 * @author Wayne Bonifacio
 *
 */
public class StackOverflowException extends Exception {

	private static final long serialVersionUID = -2035389643643535912L;

	public StackOverflowException() {
		super("Push method has been called on a full stack");
	}
}
