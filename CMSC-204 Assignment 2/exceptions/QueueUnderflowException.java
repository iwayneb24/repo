package exceptions;
/**
 * QueueUnderflowException is thrown when a dequeue method is called on an empty queue.
 * @author Wayne Bonifacio
 *
 */
public class QueueUnderflowException extends Exception {

	private static final long serialVersionUID = 4543364693314001636L;

	public QueueUnderflowException() {
		super("Dequeue method has been called on an empty queue");
	}

}
