/**
 * Represents an town as a node of a graph. 
 * The Town class holds the name of the town and a list of adjacent towns, and 
 * other fields as desired, and the traditional methods 
 * (constructors, getters/setters, toString, etc.). It will implement the 
 * Comparable interface These are the minimum methods that are needed. 
 * Please feel free to add as many methods as you need.
 * 
 * @author Wayne Bonifacio
 *
 */
public class Town implements Comparable<Town>{
	
	private String name;
	
	/**
	 * Constructor. Requires town's name.
	 * @param name
	 */
	public Town(String name) {
		this.name = name;
	}
	
	/**
	 * Copy constructor.
	 * @param templateTown an instance of Town
	 */
	public Town(Town templateTown) {
		this.name = templateTown.getName();
	}
	
	/**
	 * Compare to method
	 * @return 0 if names are equal, a positive or negative number if the names
	 * are not equal.
	 */
	@Override
	public int compareTo(Town o) {
		return this.name.compareTo(o.name);
	}
	
	/**
	 * Returns the town's name
	 * @return town's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * To string method
	 * @return the town name
	 */
	public String toString() {
		return this.name;
	}
	
	/**
	 * @return the hashcode for the name of the town
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * @return true if the town names are equal, false if not.
	 */
	public boolean equals(Object obj) {
		Town tempTown = (Town) obj;
		return this.name.equals(tempTown.name);
		
	}
	
}
