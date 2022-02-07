package classExceptions;
/**
 * NoDigitException is thrown when the required password does not contain
 * a numeric character.
 * 
 * @author Wayne Bonifacio
 *
 */
public class NoDigitException extends Exception {
	
	private static final long serialVersionUID = 5777406131014259457L;

	public NoDigitException() {
		super("The password must contain at least one digit");
	}
	
	public NoDigitException(String message) {
		super(message);
	}
}
