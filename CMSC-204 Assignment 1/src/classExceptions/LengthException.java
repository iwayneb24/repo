package classExceptions;
/**
 * LengthException is thrown if the length of the required password is less than
 * 6 characters.
 * 
 * @author Wayne Bonifacio
 *
 */
public class LengthException extends Exception {
	
	private static final long serialVersionUID = -2130207821818275787L;

	public LengthException() {
		super("The password must be at least 6 characters long");
	}
	
	public LengthException(String message) {
		super(message);
	}
}
