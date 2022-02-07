package classExceptions;
/**
 * NoLoweAlphaException is thrown when the required password does not contain
 * at least one lowercase alphabetic character.
 * 
 * @author Wayne Bonifacio
 *
 */
public class NoLowerAlphaException extends Exception {

	private static final long serialVersionUID = -3044051172911734336L;

	public NoLowerAlphaException() {
		super("The password must contain at least one lowercase "
				+ "alphabetic character");
	}
	
	public NoLowerAlphaException(String message) {
		super(message);
	}
}
