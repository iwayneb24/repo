package classExceptions;
/**
 * NoSpecialCharacterException is thrown when the required password 
 * does not contain at least one of the special characters.
 * 
 * @author Wayne Bonifacio
 *
 */
public class NoSpecialCharacterException extends Exception {

	private static final long serialVersionUID = 684711134417106691L;

	public NoSpecialCharacterException() {
		super("The password must contain at least one special character");
	}
	
	public NoSpecialCharacterException(String message) {
		super(message);
	}
}
