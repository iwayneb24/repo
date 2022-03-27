import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the CourseDBManagerInterface that is provided. 
 * The data manager allows the user to read the courses from a file or to enter the data 
 * by hand and uses an Alert to print out the database elements.The input is read from 
 * a file or read from the textfields and is added to the data structure through the add method.
 * The add method uses the CDS ‘s add method. The CourseDBManager is also referred to as a CDM.
 * @author Wayne Bonifacio
 *
 */
public class CourseDBManager implements CourseDBManagerInterface {

	private CourseDBStructure cds;

	public CourseDBManager() {
		cds = new CourseDBStructure(10);
	}

	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		cds.add(element);
	}

	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (IOException e) {
			System.out.println("Exception was thrown in Manager get CRN");
			e.getMessage();
		}
		return null;
	}

	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		InputStream readIn = new FileInputStream(input);
		BufferedReader br = new BufferedReader(new InputStreamReader(readIn));
		try {
			List<String[]> list = br.lines().map(s -> s.split(" ")).collect(Collectors.toList());
			for (String[] array : list) {
				if (array.length > 5) {
					StringBuilder instructor = new StringBuilder();
					for (int i = 4; i < array.length; i++) {
						instructor.append(array[i] + " ");
					}
					cds.add(new CourseDBElement(array[0], Integer.valueOf(array[1]), 
							Integer.valueOf(array[2]), array[3],
							instructor.toString().trim()));
				} else {
					cds.add(new CourseDBElement(array[0], Integer.valueOf(array[1]), 
							Integer.valueOf(array[2]), array[3],
							array[4]));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();

		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.getMessage();
			}

		}


	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<CourseDBElement> temp = new ArrayList<>();
		ArrayList<String> strList;
		for (int i = 0; i < cds.getTableSize(); i++) {
			if (cds.hashTable[i] != null) {
				temp.addAll(cds.hashTable[i]);
			}
		}
		strList = (ArrayList<String>) temp.stream().map(s -> s.toString()).collect(Collectors.toList());
		return strList;
	}

	
	
	
}
