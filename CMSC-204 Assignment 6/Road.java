/**
 * The class Road that can represent the edges of a Graph of Towns. The class 
 * must implement Comparable. The class stores references to the two 
 * vertices(Town endpoints), the distance between vertices, and a name, and the 
 * traditional methods (constructors, getters/setters, toString, etc.), and a 
 * compareTo, which compares two Road objects. Since this is a undirected graph, 
 * an edge from A to B is equal to an edge from B to A.
 * 
 * @author Wayne Bonifacio
 *
 */
public class Road implements Comparable<Road> {
	
	private Town source, destination;
	private int distance;
	private String roadName;;
	
	/**
	 * 
	 * @param source - One town on the road
	 * @param destination - Another town on the road
	 * @param degrees - Weight of the edge, i.e., distance from one town to the
	 * other
	 * @param name - Name of the road
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.distance = degrees;
		this.roadName = name;
	}
	
	/**
	 * 
	 * @param source - One town on the road
	 * @param destination - Another town on the road
	 * @param name - Name of the road
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.distance = 1;
		this.roadName = name;
	}
	
	/**
	 * @return 0 if the road names are the same, a positive or negative number
	 * if the road names are not the same
	 */
	@Override
	public int compareTo(Road o) {
		return o.roadName.compareTo(this.roadName);
	}
	
	/**
	 * Returns true only if the edge contains the given town
	 * @param town - a vertex of the graph
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town) {
		return this.source.equals(town) || this.destination.equals(town);
	}
	
	/**
	 * Returns true f each of the ends of the road r is the same as the ends of 
	 * this road. Remember that a road that goes from point A to point B is 
	 * the same as a road that goes from point B to point A.
	 * @param r - road object to compare it to
	 */
	public boolean equals(Object r) {
		if(r == null) {
			return false;
		}
		
		if(r == this) {
			return true;
		}
		
		if(!(r instanceof Road)) {
			return false;
		}
		
		Road road = (Road) r;
		return (this.source.equals(road.source) && 
				this.destination.equals(road.destination)) ||
				(this.source.equals(road.destination) && 
						this.destination.equals(road.source));
	}
	
	/**
	 * Returns the second town on the road
	 * @return A town on the road
	 */
	public Town getDestination() {
		return this.destination;
	}
	
	/**
	 * Returns the road name
	 * @return The name of the road
	 */
	public String getName() {
		return this.roadName;
	}
	
	/**
	 * Returns the first town on the road
	 * @return A town on the road
	 */
	public Town getSource() {
		return this.source;
	}
	
	/**
	 * Returns the distance of the road
	 * @return the distance of the road
	 */
	public int getWeight() {
		return this.distance;
	}
	
	/**
	 * To string method.
	 */
	public String toString() {
		return source.getName() + " via " + roadName + " to " + 
				destination.getName() + " " + distance + " mi";
	}

}
