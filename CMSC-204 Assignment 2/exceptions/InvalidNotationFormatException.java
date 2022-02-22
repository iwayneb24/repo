package exceptions;
/**
 * The InvalidNotationFormatException is thrown when the Notation format is incorrect.
 * @author Wayne Bonifacio
 *
 */
public class InvalidNotationFormatException extends Exception {

	private static final long serialVersionUID = 3976980250780601551L;

	public InvalidNotationFormatException() {
		super("Notation format is incorrect");
	}
}
