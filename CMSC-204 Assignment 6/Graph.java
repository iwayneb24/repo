import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * A Graph class that implements the GraphInterface. For Graph<V,E>, V is the 
 * vertex type (a Town), E is the edge type (a Road). The graph is stored using
 * an adjacent matrix or an adjacent list. Within the Graph interface is a method 
 * shortestPath, which finds the shortest path from a given source Town to a 
 * destination Town. Since there is a unique shortest path from every vertex to 
 * the source, there is a back-pointer to the previous vertex. The method 
 * shortestPath calls dijkstraShortestPath which finds the shortest path from the 
 * source to every other vertex in the graph.
 *     
 * @author Wayne Bonifacio
 *
 */
public class Graph implements GraphInterface<Town, Road> {
	private Town destination;
	private Set<Town> town = new HashSet<>();
	private Set<Road> road = new HashSet<>();
	private ArrayList<String> shortestPath = new ArrayList<>();
	
	/**
	 * Returns an edge connecting source vertex to target vertex if such
	 * vertices and such edge exist in this graph. Otherwise returns
	 * null. If any of the specified vertices is null
	 * returns null
	 *
	 * In undirected graphs, the returned edge may have its source and target
	 * vertices in the opposite order.
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return an edge connecting source vertex to target vertex.
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null) {
			return null;
		}
		return road.stream().filter(r -> r.contains(sourceVertex) 
				&& r.contains(destinationVertex)).findAny().orElse(null);
	}

	/**
	 * Creates a new edge in this graph, going from the source vertex to the
	 * target vertex, and returns the created edge. 
	 * 
	 * The source and target vertices must already be contained in this
	 * graph. If they are not found in graph IllegalArgumentException is
	 * thrown.
	 *
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight weight of the edge
	 * @param description description for edge
	 *
	 * @return The newly created edge if added to the graph, otherwise null.
	 *
	 * @throws IllegalArgumentException if source or target vertices are not
	 * found in the graph.
	 * @throws NullPointerException if any of the specified vertices is null.
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
			throws IllegalArgumentException, NullPointerException {
		
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		Road newEdge = new Road(sourceVertex, destinationVertex, weight, description);
		road.add(newEdge);
		return newEdge;
		
	}
	
	/**
	 * Adds the specified vertex to this graph if not already present. More
	 * formally, adds the specified vertex, v, to this graph if
	 * this graph contains no vertex u such that
	 * u.equals(v). If this graph already contains such vertex, the call
	 * leaves this graph unchanged and returns false. In combination
	 * with the restriction on constructors, this ensures that graphs never
	 * contain duplicate vertices.
	 *
	 * @param v vertex to be added to this graph.
	 *
	 * @return true if this graph did not already contain the specified
	 * vertex.
	 *
	 * @throws NullPointerException if the specified vertex is null.
	 */
	@Override
	public boolean addVertex(Town v) throws NullPointerException {
		if (v == null)
			throw new NullPointerException();

		if (!containsVertex(v)) {
			town.add(v);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns true if and only if this graph contains an edge going
	 * from the source vertex to the target vertex. In undirected graphs the
	 * same result is obtained when source and target are inverted. If any of
	 * the specified vertices does not exist in the graph, or if is
	 * null, returns false.
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return true if this graph contains the specified edge.
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for(Road r : road) {
			if( r.contains(sourceVertex) && r.contains(destinationVertex)) {
				return true;
			}
		}
		return false;

	}
	
	/**
	 * Returns true if this graph contains the specified vertex. More
	 * formally, returns true if and only if this graph contains a
	 * vertex u such that u.equals(v). If the
	 * specified vertex is null returns false.
	 *
	 * @param v vertex whose presence in this graph is to be tested.
	 *
	 * @return true if this graph contains the specified vertex.
	 */
	@Override
	public boolean containsVertex(Town v) {
		return town.contains(v);
		
	}
	
	/**
	 * Returns a set of the edges contained in this graph. The set is backed by
	 * the graph, so changes to the graph are reflected in the set. If the graph
	 * is modified while an iteration over the set is in progress, the results
	 * of the iteration are undefined.
	 *
	 *
	 * @return a set of the edges contained in this graph.
	 */
	@Override
	public Set<Road> edgeSet() {
		return road;
	}
	
	/**
	 * Returns a set of all edges touching the specified vertex (also
	 * referred to as adjacent vertices). If no edges are
	 * touching the specified vertex returns an empty set.
	 *
	 * @param vertex the vertex for which a set of touching edges is to be
	 * returned.
	 *
	 * @return a set of all edges touching the specified vertex.
	 *
	 * @throws IllegalArgumentException if vertex is not found in the graph.
	 * @throws NullPointerException if vertex is null.
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		if ( vertex == null) {
			throw new NullPointerException();
		}
		Set<Road> edgeSet = new HashSet<>();
		
		for(Road e : road) {
			if(e.contains(vertex)) {
				edgeSet.add(e);
			}
		}
		if(edgeSet.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return edgeSet;

	}
	
	/**
	 * Removes an edge going from source vertex to target vertex, if such
	 * vertices and such edge exist in this graph. 
	 * 
	 * If weight >- 1 it must be checked
	 * If description != null, it must be checked 
	 * 
	 * Returns the edge if removed
	 * or null otherwise.
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight weight of the edge
	 * @param description description of the edge
	 *
	 * @return The removed edge, or null if no edge removed.
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road edge = null;
		for(Road r : road) {
			if(r.contains(destinationVertex) && r.contains(sourceVertex) 
					&& weight >- 1 && description != null) {
				edge = r;
			}
		}
		if(road.remove(edge)) {
			return edge;
		}
		return null;

	}
	
	/**
	 * Removes the specified vertex from this graph including all its touching
	 * edges if present. More formally, if the graph contains a vertex 
	 * u such that u.equals(v), the call removes all edges
	 * that touch u and then removes u itself. If no
	 * such u is found, the call leaves the graph unchanged.
	 * Returns true if the graph contained the specified vertex. (The
	 * graph will not contain the specified vertex once the call returns).
	 *
	 * If the specified vertex is null returns false.
	 *
	 * @param v vertex to be removed from this graph, if present.
	 *
	 * @return true if the graph contained the specified vertex;
	 * false otherwise.
	 */
	@Override
	public boolean removeVertex(Town v) {
		if(v == null) {
			return false;
		}
		return town.remove(v);

	}
	
	/**
	 * Returns a set of the vertices contained in this graph. The set is backed
	 * by the graph, so changes to the graph are reflected in the set. If the
	 * graph is modified while an iteration over the set is in progress, the
	 * results of the iteration are undefined.
	 *
	 *
	 * @return a set view of the vertices contained in this graph.
	 */
	@Override
	public Set<Town> vertexSet() {
		return town;
	}

	/**
	 * Find the shortest path from the sourceVertex to the destinationVertex
	 * call the dijkstraShortestPath with the sourceVertex
	 * @param sourceVertex starting vertex
	 * @param destinationVertex ending vertex
	 * @return An arraylist of Strings that describe the path from sourceVertex
	 * to destinationVertex
	 * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
	 */   
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		destination = destinationVertex;
		dijkstraShortestPath(sourceVertex);
		ArrayList<Road> roadPath = new ArrayList<>();

		boolean anySource = road.stream().anyMatch(r -> r.contains(sourceVertex));
		boolean anyDest = road.stream().anyMatch(r -> r.contains(destinationVertex));

		if (!anySource || !anyDest) {
			return new ArrayList<>();
		}

		for (int i = 0; i < shortestPath.size() - 1; i++) {
			Town source = new Town(shortestPath.get(i));
			Town destination = new Town(shortestPath.get(i + 1));
			Road road = getEdge(source, destination);
			roadPath.add(new Road(source, destination, 
					road.getWeight(), road.getName()));
		}

		shortestPath.clear();

		return roadPath.stream().map(Road::toString).collect
				(Collectors.toCollection(ArrayList::new));

	}
	
	/**
	 * Dijkstra's Shortest Path Method.  Internal structures are built which
	 * hold the ability to retrieve the path, shortest distance from the
	 * sourceVertex to all the other vertices in the graph, etc.
	 * @param sourceVertex the vertex to find shortest path from
	 * 
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		String source = ((Town) sourceVertex).getName();
		// TODO Auto-generated method stub
		List<Town> verts = new ArrayList<Town>(town);

		int[][] adjMatrix = new int[town.size()][town.size()];
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++) {
				if (i == j || !containsEdge(verts.get(i), verts.get(j))) {
					adjMatrix[i][j] = 0;
				} else {
					int distance = getEdge(verts.get(i), verts.get(j)).getWeight();
					adjMatrix[i][j] = adjMatrix[j][i] = distance;
				}
			}
		}

		int startTown = 0;
		for (Town t : verts) {
			if (!t.equals(sourceVertex)) {
				startTown++;
			} else {
				break;
			}
		}

		int endTown = 0;

		for (Town t : verts) {
			if (!t.equals(destination)) {
				endTown++;
			} else {
				break;
			}
		}

		int numTowns = adjMatrix[0].length;
		int[] shortDistances = new int[numTowns];
		boolean[] visited = new boolean[numTowns];

		for (int t = 0; t < numTowns; t++) {
			shortDistances[t] = Integer.MAX_VALUE;
			visited[t] = false;
		}
		shortDistances[startTown] = 0;
		int[] minPathLengths = new int[numTowns];
		minPathLengths[startTown] = -1;

		for (int i = 1; i < numTowns; i++) {
			int nearestTown = -1;
			int minDistance = Integer.MAX_VALUE;

			for (int townIndex = 0; townIndex < numTowns; townIndex++) {
				if (!visited[townIndex] && shortDistances[townIndex] < minDistance) {
					nearestTown = townIndex;
					minDistance = shortDistances[townIndex];
				}
			}

			visited[nearestTown] = true;

			for (int townIndex = 0; townIndex < numTowns; townIndex++) {
				int roadDistance = adjMatrix[nearestTown][townIndex];
				if (roadDistance > 0 && ((minDistance + roadDistance) < shortDistances[townIndex])) {
					minPathLengths[townIndex] = nearestTown;
					shortDistances[townIndex] = minDistance + roadDistance;
				}
			}

		}
		shortPthAlgrthm(endTown, minPathLengths);


	}

	private void shortPthAlgrthm(int sourceVertex, int[] minPathLengths) {
		if (sourceVertex == -1) {
			return;
		}

		shortPthAlgrthm(minPathLengths[sourceVertex], minPathLengths);

		int townIndex = 0;

		for (Town t : town) {
			if (townIndex == sourceVertex) {
				shortestPath.add(t.getName());
			}
			townIndex++;
		}
	}

	

	

}
