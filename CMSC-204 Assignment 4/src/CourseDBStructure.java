
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * CourseDBStructure Referred as CDS, Implements the CourseDBStructureInterface.
 * It implements a hashtable with buckets. Each bucket will be an array of linked lists of CourseDBElements.  
 * Each CourseDBElement object will have a hash code that is calculate based on the CRN, 
 * since the CRN is unique for courses. The add method of CourseDBStructure will 
 * take a CourseDBElement object and add it to the data structure based on the calculated hashcode. 
 * If a linked list at the relevant hash code doesn’t exist (the bucket is empty), 
 * create a LinkedList with the first element being the CourseDBElement object and 
 * add it to the HashTable. If the LinkedList already exists, 
 * add the CourseDBElement object to the existing list. 
 * @author Wayne Bonifacio
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface {
	
	protected LinkedList<CourseDBElement>[] hashTable;
	
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int size) {
		hashTable = new LinkedList[size-1];
	}
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String str, int size) {
		hashTable = new LinkedList[size];
	}
	
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	@Override
	public void add(CourseDBElement element) {
		int index = hashIndex(element);
		
		if(hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
			hashTable[index].add(element);
		} else {
			hashTable[index].add(element);
		}
	}
	
	private int hashIndex(CourseDBElement element) {
		 int hashIndex = element.hashCode() % hashTable.length;
		    if (hashIndex < 0) {
		      hashIndex += hashTable.length;
		    }
		    return hashIndex;
	}
	
	
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement tempElem = new CourseDBElement();
		
		tempElem.setCRN(crn);
		int index = hashIndex(tempElem);
		LinkedList<CourseDBElement> list = hashTable[index];
		
		return list.stream().filter(c -> c.getCRN() == crn).findAny().
				orElseThrow(IOException::new);
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
		CourseDBStructure cds = new CourseDBStructure(4);
		for (int i = 0; i < cds.getTableSize(); i++) {
			if (cds.hashTable[i] != null) {
				temp.addAll(cds.hashTable[i]);
			}
		}
		
		strList = (ArrayList<String>) temp.stream().map(s -> s.toString()).collect(Collectors.toList());
		return strList;
		
	}
	
	
		
	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	* @return number of indexes in the array
	*/
	@Override
	public int getTableSize() {
		return hashTable.length;
	}
	

	

}
