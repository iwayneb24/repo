/**
 * CourseDBElement implements Comparable interface and consists of five attributes: 
 * the Course ID (a String), the CRN (an int), the number of credits (an int), 
 * the room number (a String), and the instructor name (a String). Normally the 
 * CourseDBElement will be an object consisting of these five attributes and 
 * is referred to as a CDE.
 * 
 * @author Wayne Bonifacio
 *
 */
public class CourseDBElement implements Comparable {
	
	private String courseId;
	private int CRN;
	private int credits;
	private String roomNum;
	private String instructor;
	
	public CourseDBElement() {
		this.courseId = "";
		CRN = 0;
		credits = 0;
		this.courseId = "";
		this.instructor = "";
	}
	
	public CourseDBElement(String courseId, int crn, int credits, 
			String roomNum, String instructor) {
		this.courseId = courseId;
		this.CRN = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	/**
	 * @return the course ID.
	 */
	public String getID() {
		return courseId;
	}
	
	/**
	 * @param courseId - ID of the course.
	 */
	public void setID(String courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * @return the CRN which is the unique number that represents the course.
	 */
	public int getCRN() {
		return CRN;
	}
	
	/**
	 * @param crn - unique number that represents the course.
	 */
	public void setCRN(int crn) {
		this.CRN = crn;
	}
	
	/**
	 * @return the number of credits of the course.
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * @param credits - number of credits of the course.
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	/**
	 * @return the room number of the course.
	 */
	public String getRoomNum() {
		return roomNum;
	}
	
	/**
	 * @param roomNum - room number as a String.
	 */
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	/**
	 * @return the name of the instructor.
	 */
	public String getInstructor() {
		return instructor;
	}
	
	/**
	 * @param instructor - name of the instructor.
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * Compares the CRN, which is the unique number that represents the course.
	 * @param element - the element to compared.
	 */
	public int compareTo(CourseDBElement element) {
		return Integer.compare(this.getCRN(), element.getCRN());
	}
	
	/**
	 * @return a hash code value of the CRN in String. 
	 */
	public int hashCode() {
		String crnStr = String.valueOf(getCRN());
		long hash = 0;
		int prime = 31;
		for(int i = 0; i < crnStr.length(); i++) {
			hash = prime * hash + crnStr.charAt(i);
		}
		return (int) hash;
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one. 
	 * @param obj the reference object with which to compare.
	 * @return true if this object is the same as the obj argument; 
	 * false otherwise.
	 */
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof CourseDBElement)) {
			return false;
		}
		CourseDBElement other = (CourseDBElement) obj;
		return this.getCRN() == other.CRN;
	}
	
	
	public String toString() {
		return "\nCourse:" + courseId + " CRN:" + CRN + " Credits:" + credits + 
				" Instructor:" + instructor + " Room:" + roomNum ;
	}
	
	

	

}
