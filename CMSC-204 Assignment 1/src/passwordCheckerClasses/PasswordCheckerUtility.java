package passwordCheckerClasses;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classExceptions.InvalidSequenceException;
import classExceptions.LengthException;
import classExceptions.NoDigitException;
import classExceptions.NoLowerAlphaException;
import classExceptions.NoSpecialCharacterException;
import classExceptions.NoUpperAlphaException;
import classExceptions.UnmatchedException;
import classExceptions.WeakPasswordException;
/**
 * The Utility Class is used to verify the requirements of the password String
 * contains to be valid
 * @author Wayne Bonifacio
 *
 */
public class PasswordCheckerUtility {
	/**
	 * Returns true if the password is valid, meaning if the length of the 
	 * password is more than 6, if the password contain at least one upper case letter,
	 * if the password contain at least one lower case letter, if the password 
	 * contains more than 6 but less than 9, if it contains at least one of the 
	 * special characters, and if the password contains no longer than 2 of the same
	 * characters in sequence.
	 * @param psswrd
	 * @return if the password is valid or invalid
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 * @throws WeakPasswordException
	 */
	public static boolean isValidPassword(String psswrd) throws 
	LengthException, NoUpperAlphaException, NoLowerAlphaException, 
	NoDigitException, NoSpecialCharacterException, InvalidSequenceException,
	WeakPasswordException {
		return isValidLength(psswrd) && hasLowerAlpha(psswrd)
				&& hasUpperAlpha(psswrd) && hasDigit(psswrd)
				&& hasSpecialChar(psswrd) 
				&& hasSameCharInSequence(psswrd) && !isWeakPassword(psswrd);
	}


	/**
	 * Returns true if the length of the password is more than 6
	 * @param psswrd
	 * @return true if password is at least 6 characters long
	 * @throws LengthException
	 */
	public static boolean isValidLength(String psswrd) throws LengthException {
		if(psswrd.length() >= 6) {
			return true;
		}
		throw new LengthException("The password must be at least 6 characters long");
	}

	/**
	 * Returns true if the password contains more than 6 but less than 9
	 * characters
	 * @param psswrd
	 * @return false if the password is weak
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String psswrd) throws WeakPasswordException {
		if(hasBetweenSixAndNine(psswrd)) {
			throw new WeakPasswordException("The password is OK but weak");
		}
		return false;
	}

	/**
	 * Returns true if password contains 6-9 characters
	 * @param psswrd
	 * @return false if password does not contains 6-9 characters
	 */
	private static boolean hasBetweenSixAndNine(String psswrd) {
		if(psswrd.length() >= 6 && psswrd.length() <= 9) {
			return true;
		}
		return false;
	}

	/**
	 * Returns an arraylist of invalid passwords
	 * @param psswrd
	 * @return an array of invalid passwords with error messages
	 * @throws NoSpecialCharacterException 
	 * @throws NoLowerAlphaException 
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> psswrd) {
		ArrayList<String> invalidPasswords = new ArrayList<>();
		for (String s : psswrd) {

			try {
				isValidPassword(s);
			} catch (NoLowerAlphaException e) {
				invalidPasswords.add(s.concat(" -> The password must contain at"
						+ "least one lowercase alphabetic character"));
				
			} catch (NoSpecialCharacterException e) {
				invalidPasswords.add(s.concat(" -> The password must contain at"
						+ "least one special character"));
				
			} catch (WeakPasswordException e) {
				invalidPasswords.add(s.concat(" -> The password is OK but weak"
						+ "- it contains fewer than 10 characters."));
			} catch (LengthException e) {
				invalidPasswords.add(s.concat(" -> The password must be at least"
						+ " 6 characters long."));
			} catch (NoUpperAlphaException e) {
				invalidPasswords.add(s.concat(" -> The password must contain at"
						+ " least one upper alphabetic character"));
			} catch (NoDigitException e) {
				invalidPasswords.add(s.concat(" -> The password must contain at"
						+ " least one digit"));
			} catch (InvalidSequenceException e) {
				invalidPasswords.add(s.concat(" -> The password cannot contain "
						+ "more than two of the same character in sequence"));
			}

		}
		return invalidPasswords;
	}

	/**
	 * Returns true if the password contain at least one upper case letter
	 * @param psswrd
	 * @return true if password has at least one upper case alphabet
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String psswrd) throws NoUpperAlphaException {
		Pattern upperAlpha = Pattern.compile("[A-Z]");
		Matcher upper = upperAlpha.matcher(psswrd);
		if(upper.find()) {
			return true;
		}
		throw new NoUpperAlphaException("The password must contain at least "
				+ "one uppercase alphabetic character");
	}

	/**
	 * Returns true if the password contain at least one lower case letter
	 * @param psswrd
	 * @return true if password has at least one lower case alphabet
	 * @throws NoLowerAlphaException
	 */
	private static boolean hasLowerAlpha(String psswrd) throws NoLowerAlphaException {
		Pattern lowerAlpha = Pattern.compile("[a-z]");
		Matcher lower = lowerAlpha.matcher(psswrd);
		if(lower.find()) {
			return true;
		}
		throw new NoLowerAlphaException("The password must contain at least "
				+ "one lowercase alphabetic character");
	}


	/**
	 * Return true if password has at least one of these special characters: 
	 * (!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~) 
	 * @param psswrd
	 * @return true if it contains at least one of the special characters
	 */
	private static boolean hasSpecialChar(String psswrd) throws NoSpecialCharacterException {
		Pattern special = Pattern.compile("\\W");
		Matcher match = special.matcher(psswrd);
		if (match.find()) {
			return true;
		}
		throw new NoSpecialCharacterException("The password must contain at least "
				+ "one special character");
	}

	/**
	 * Return true if the password contains no longer than 2 of the same
	 * characters in sequence.
	 * @param psswrd
	 * @return true if password contains at most 2 of the same characters in sequence
	 * @throws InvalidSequenceException
	 */
	private static boolean hasSameCharInSequence(String psswrd) throws InvalidSequenceException {
		char[] eachChar = psswrd.toCharArray(); // Converts the password string into an array of char
		for(int index =0; index < eachChar.length; index++) {
			Character chars = eachChar[index];
			if (index >= 3) {
				if(chars.equals(eachChar[index-1]) 
						&& chars.equals(eachChar[index-2])) {
					throw new InvalidSequenceException("Password must have no "
							+ "more than 2 of the same character in a sequence.");
				}
			}
		}
		return true;
	}

	/**
	 * Returns true if the password contains at least one digit
	 * @param psswrd
	 * @return true if there is at least one digit in the password
	 * @throws NoDigitException
	 */
	private static boolean hasDigit(String psswrd) throws NoDigitException {
		Pattern num = Pattern.compile("[0-9]");
		Matcher match = num.matcher(psswrd);
		if(match.find()) {
			return true;
		}
		throw new NoDigitException("Password must contain at least one digit");
	}

	public static void comparePasswords(String psswrd, String psswrdConfirm) throws UnmatchedException {
		if (!comparePasswordsWithReturn(psswrd, psswrdConfirm)) {
			throw new UnmatchedException("Passwords do not match");
		}
	}

	public static boolean comparePasswordsWithReturn(String psswrd, String psswrdConfirm) {
		if (psswrd.equals(psswrdConfirm)) {
			return true;
		}
		return false;
	}


	
	
	



	
	
}
