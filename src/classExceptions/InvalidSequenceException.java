package classExceptions;
/**
 * InvalidSequenceException is thrown when the required password contains 
 * more than two of the same character in sequence.
 * 
 * @author Wayne Bonifacio
 *
 */
public class InvalidSequenceException extends Exception {
	
	private static final long serialVersionUID = -5982827445963852289L;

	public InvalidSequenceException() {
		super("The password cannot contain more than two of the same "
				+ "character in sequence");
	}
	
	public InvalidSequenceException(String message) {
		super(message);
	}
}
