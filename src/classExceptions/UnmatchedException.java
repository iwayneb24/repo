package classExceptions;
/**
 * UnmatchedException is for GUI and is thrown when the password and
 * the re-typed password do not match
 * 
 * @author Wayne Bonifacio
 *
 */
public class UnmatchedException extends Exception {

	private static final long serialVersionUID = 356878080668278184L;

	public UnmatchedException() {
		super("The passwords do not match");
	}
	
	public UnmatchedException(String message) {
		super(message);
	}
}
