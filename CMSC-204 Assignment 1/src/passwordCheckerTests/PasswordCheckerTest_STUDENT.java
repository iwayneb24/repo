package passwordCheckerTests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classExceptions.InvalidSequenceException;
import classExceptions.LengthException;
import classExceptions.NoDigitException;
import classExceptions.NoLowerAlphaException;
import classExceptions.NoUpperAlphaException;
import classExceptions.WeakPasswordException;
import passwordCheckerClasses.PasswordCheckerUtility;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Wayne Bonifacio
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	String password1, password2;

	@Before
	public void setUp() throws Exception {
		String[] p = {"334455BB#", "george2ZZZ#", "4Sal#", "bertha22", "august30", 
				"a2cDe", "ApplesxxxYYzz#", "aa11Bb", "pilotProject", "AAAbb@123"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p)); // puts strings into the ArrayList

	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("th0t"));
			assertTrue("Did not throw lengthException", false);
		} catch (LengthException e) {
			assertTrue("Successfully threw a lengthException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides lengthException", false);
		}

	} 

	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testNoUpperAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("noupper"));
			assertTrue("Did not throw NoUpperAlphaException", false);
		} catch (NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		}

	}

	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testNoLowerAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("NOLOWER"));
			assertTrue("Did not throw NoLowerAlphaException", false);
		} catch (NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides NoLowerAlphaException", false);
		}

	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {
		try {
			boolean weakPsswrd = PasswordCheckerUtility.isWeakPassword("notW3a?");
			assertTrue("Did not throw WeakPasswordException", false);
		} catch (WeakPasswordException e) {
			assertTrue("Successfully threw a WeakPasswordException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception", false);
		}
		
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() {
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("@tripl3SSS"));
			assertTrue("Did not throw an InvalidSequenceException", false);
		} catch (InvalidSequenceException e) {
			assertTrue("Successfully threw an InvalidSequenceExcepetion", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException", false);
		}


	}

	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("NoNumber$"));
			assertTrue("Did not throw a NoDigitException", false);
		} catch (NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides a NoDigitException", false);

		}

	}

	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() {
		try {
			assertEquals(true, PasswordCheckerUtility.isValidPassword("hop3%X0long"));
			assertEquals(true, PasswordCheckerUtility.isValidPassword("ganG!1234."));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw exceptions", false);
		}

	}

	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwords);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "334455BB#");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "george2ZZZ#");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("sequence"));
		
		 
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "4Sal#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));
		
				scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "bertha22");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(4));  
		assertEquals(scan.next(), "august30");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase") );
		
		 
		
		scan = new Scanner(results.get(5));  
		assertEquals(scan.next(), "a2cDe");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long") );
		
		scan = new Scanner(results.get(6));  
		assertEquals(scan.next(), "ApplesxxxYYzz#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(7));  
		assertEquals(scan.next(), "aa11Bb");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );
		
		
		scan = new Scanner(results.get(8)); 
		assertEquals(scan.next(), "pilotProject");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(9));  
		assertEquals(scan.next(), "AAAbb@123");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence") );
	}

	
}
