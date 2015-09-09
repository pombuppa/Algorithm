package com.songkiet.kruskalMST;


import java.util.*;

import javax.swing.text.html.MinimalHTMLWriter;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //int testNum = scan.nextInt();
        
        long sum = 0L;
        //for (int i=0; i < testNum; i++) {
        	
        	int vertexNum = scan.nextInt();
        	int edgeNum = scan.nextInt();
 
        	Graph g = new Graph(vertexNum);
 
        	for (int j=0; j < vertexNum; j++) {
        		Vertex v = new Vertex(j+1);
        		g.addVertex(v);
        	}
        	for (int j=0; j < edgeNum; j++) {
        		int start = scan.nextInt();
        		int end = scan.nextInt();
        		int weight = scan.nextInt();
        		
        		Vertex vStart = g.getVertex(start);
        		Vertex vEnd = g.getVertex(end);
        		
        		Edge edge = new Edge(vStart, vEnd, weight);
        		vStart.addAdjacency(edge);
        		
        		Edge edge2 = new Edge(vEnd, vStart, weight);
        		vEnd.addAdjacency(edge2);
        		
        	}
        	
        	int searchVertex = scan.nextInt();
        	
        	Vertex start = g.getVertex(searchVertex);
        	MinimumSpanningTree.kruskal(g, start);
        	
        	//MinimumSpanningTree.sumWeight(g);
        	
        //}
        
        scan.close();
    }
}

class Edge implements Comparable<Edge> {
	private int weight;
	Vertex start;
	Vertex destination;
	
	public Edge(Vertex start, Vertex destination) {
		this.start = start;
		this.destination = destination;
	}
	
	public Edge(Vertex start, Vertex destination, int weight) {
		this.start = start;
		this.destination = destination;
		this.weight = weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getStart() {
		return start;
	}
	public Vertex getDestination() {
		return destination;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj != null && obj instanceof Edge) {
			Edge another = ((Edge)obj);
			if (this.destination == another.destination && this.start == another.start) {
				return true;
			} else if (this.destination == another.start && this.start == another.destination) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		if (destination != null) {
			return destination.getId();
		} else {
			return 0;
		}
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(weight, o.weight);
	}
}

class Vertex implements Comparable<Vertex> {
	
	private List<Edge> adjacencies;
	private int id;

	// These properties is not actually vertex's but to store the processing result from operation such as BFS,MST
	// The vertex is considered dirty, when these value is modified.
	private int distance;
	private Vertex previous;
	private boolean movedToMST;
	
	public Vertex(int id) {
		this.id = id;
		adjacencies = new ArrayList<Edge>();
		
		distance = Integer.MAX_VALUE;
		previous = null;
		movedToMST = false;
	}
	
	public void addAdjacency (Edge adjacency) {
		
		if (adjacencies.contains(adjacency)) {
			int index = adjacencies.indexOf(adjacency);
			
			Edge existingEdge = adjacencies.get(index);
			if (existingEdge.getWeight() <= adjacency.getWeight()) {
				return;
			} else {
				adjacencies.remove(index);
			}
		}
		
		adjacencies.add(adjacency);
	}
	
	public int getId() {
		return id;
	}
	
	public Vertex getPrev() {
		return previous;
	}
	
	public List<Edge> getAdjacencies() {
		return adjacencies;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex o) {
		
		return Integer.compare(distance, o.distance);
	}

	public void setPrev(Vertex curVertex) {
		this.previous = curVertex;
		
	}

	public boolean isMovedToMST() {
		return movedToMST;
	}

	public void setMovedToMST(boolean movedToMST) {
		this.movedToMST = movedToMST;
	}
}

class Graph {
	private List<Vertex> vertices;
	
	public Graph(int vertexNum) {
		vertices = new ArrayList<Vertex>(vertexNum);
	}
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public Vertex getVertex(int id) {
		if (id <= vertices.size()) {
			return vertices.get(id - 1);
		} else {
			return null;
		}
	}
	
	public int vertexNum() {
		return vertices.size();
	}
	
	public List<Vertex> getVetices() {
		return vertices;
	}
	
	public PriorityQueue<Edge> getEdges() {
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
		
		for (Vertex v : vertices) {
			for (Edge e : v.getAdjacencies()) {
				
				if (!edges.contains(e)) {
					edges.add(e);
				}
			}
		}
		
		return edges;
	}
}

class MinimumSpanningTree {

	public static void kruskal(Graph g, Vertex source) {
		
		PriorityQueue<Edge> queue = g.getEdges();
		LinkedList<Edge> mst = new LinkedList<Edge>();
		
		UnionFind<Vertex> uf = new UnionFind<Vertex>(g.getVetices());
		
		int weightSum = 0;
		
		while (!queue.isEmpty()) {
			Edge e = queue.poll();
			
			if (uf.find(e.getStart()) != uf.find(e.getDestination())) {
				uf.union(e.getStart(), e.getDestination());
				mst.add(e);
				weightSum = weightSum + e.getWeight();
			}
		}
		
		System.out.println(weightSum);
	}
	
	public static void sumWeight(Graph g) {
		
		int sum = 0;
		for (Vertex v : g.getVetices()) {
			
			sum = sum + v.getDistance();
		}
		
		System.out.println(sum);
	}
}

class UnionFind<T> {
	
	private Map<T,T> parents;
	private Map<T, Integer> ranks;
	
	public UnionFind(List<T> elements) {
		parents = new HashMap<T, T>();
		ranks = new HashMap<T, Integer>();
		
		for (T e: elements) {
			parents.put(e, e);
			ranks.put(e, 0);
		}
	}
	
	public void addElement(T e) {
		parents.put(e, e);
		ranks.put(e, 0);
	}

	public T find(T e) {
		
		if (!parents.containsKey(e)) {
			return null;
		}
		
		T parent = parents.get(e);
		if (parent.equals(e)) {
			return e;
		} else {
			T newParent = find(parent);
			parents.put(e, newParent);
			return newParent;
		}
	}
	
	public void union(T e1, T e2) {
		if (!parents.containsKey(e1) || !parents.containsKey(e2) ) {
			return;
		}
		
		T parent1 = find(e1);
		T parent2 = find(e2);
		
		if (parent1.equals(parent2)) {
			return;
		}
		
		int rank1 = ranks.get(parent1);
		int rank2 = ranks.get(parent2);
		
		if (rank1 < rank2) {
			parents.put(parent1, parent2);
		} else if (rank1 > rank2) {
			parents.put(parent2, parent1);
		} else {
			parents.put(parent2, parent1);
			ranks.put(parent1, rank1 + 1);
		}
	}
}
