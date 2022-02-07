package classExceptions;
/**
 * WeakPasswordException is thrown when the required password contains 
 * 6 to 9 characters which are otherwise valid.
 * 
 * @author Wayne Bonifacio
 *
 */
public class WeakPasswordException extends RuntimeException {
	private static final long serialVersionUID = -2738304578553934504L;
	
	public WeakPasswordException() {
		super("The password is OK but weak- it contains fewer than "
				+ "10 characters.");
	}
	
	public WeakPasswordException(String message) {
		super(message);
	}
}
