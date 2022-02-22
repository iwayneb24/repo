package exceptions;
/**
 * QueueOverflowException is thrown when a enqueue method is called on a full queue.
 * @author Wayne Bonifacio
 *
 */
public class QueueOverflowException extends Exception {

	private static final long serialVersionUID = -4906852797688138958L;

	public QueueOverflowException() {
		super("Enqueue method has been called on a full queue");
	}
}
