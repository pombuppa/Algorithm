package com.songkiet.primMST;


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
        		
        		Edge edge = new Edge(vEnd, weight);
        		vStart.addAdjacency(edge);
        		
        		Edge edge2 = new Edge(vStart, weight);
        		vEnd.addAdjacency(edge2);
        		
        	}
        	
        	int searchVertex = scan.nextInt();
        	
        	Vertex start = g.getVertex(searchVertex);
        	MinimumSpanningTree.prim(g, start);
        	
        	MinimumSpanningTree.sumWeight(g);
        	
        //}
        
        scan.close();
    }
}

class Edge {
	private int weight;
	Vertex destination;
	
	public Edge(Vertex destination) {
		this.destination = destination;
	}
	
	public Edge(Vertex destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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
			return (this.destination == ((Edge)obj).destination);
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
}

class MinimumSpanningTree {

	public static void prim(Graph g, Vertex source) {
		
		int vertexNum = g.vertexNum();

		//int[] distTo = new int[vertexNum];		
		//Edge[] edgeTo = new Edge[vertexNum];
		//boolean[] isTreeNode = new boolean[vertexNum];
		
		//for (int i=0; i < vertexNum; i++) {
		//	distTo[i] = Integer.MAX_VALUE;
		//	isTreeNode[i] = false;
		//}
		
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		
		source.setDistance(0);
		queue.add(source);
		
		while (!queue.isEmpty()) {
			
			Vertex treeNode = queue.poll();
			treeNode.setMovedToMST(true);
			
			for (Edge e : treeNode.getAdjacencies()) {
				
				Vertex destNode = e.getDestination();
				if (destNode.isMovedToMST()) {
					continue;
				}
				
				if (e.getWeight() < destNode.getDistance()) {

					if (queue.contains(destNode)) {
						queue.remove(destNode);
					}

					destNode.setDistance(e.getWeight());
					destNode.setPrev(treeNode);
					
					queue.add(destNode);
					
				}
			}
		}
	}
	
	public static void sumWeight(Graph g) {
		
		int sum = 0;
		for (Vertex v : g.getVetices()) {
			
			sum = sum + v.getDistance();
		}
		
		System.out.println(sum);
	}
}