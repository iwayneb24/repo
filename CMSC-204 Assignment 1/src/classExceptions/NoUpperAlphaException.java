package classExceptions;
/**
 * NoUpperAlphaException is thrown when the required password does not contain
 * at least one uppercase alphabetic character.
 * 
 * @author Wayne Bonifacio
 *
 */
public class NoUpperAlphaException extends Exception {
	
	private static final long serialVersionUID = 4323582169370144240L;

	public NoUpperAlphaException() {
		super("The password must contain at least one uppercase alphabetic "
				+ "character");
	}
	
	public NoUpperAlphaException(String message) {
		super(message);
	}
}
