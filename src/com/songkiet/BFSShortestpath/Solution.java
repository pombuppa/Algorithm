package com.songkiet.BFSShortestpath;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int testNum = scan.nextInt();
        
        long sum = 0L;
        for (int i=0; i < testNum; i++) {
        	
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
        		
        		Vertex vStart = g.getVertex(start);
        		Vertex vEnd = g.getVertex(end);
        		
        		Edge edge = new Edge(vEnd);
        		vStart.addAdjacency(edge);
        		
        		Edge edge2 = new Edge(vStart);
        		vEnd.addAdjacency(edge2);
        		
        	}
        	
        	int searchVertex = scan.nextInt();
        	
        	Vertex start = g.getVertex(searchVertex);
        	ShortestPath.dijkstra(g, start);
        	
        	for (int j=0; j < vertexNum; j++) {
        		Vertex dest = g.getVertex(j+1);
        		
        		if (dest == start) {
        			continue;
        		}
 
        		//List<Vertex> path = ShortestPath.getShortestPath(dest);
        	
        		int distanceSum = dest.getDistance();
        		
        		if (distanceSum == Integer.MAX_VALUE) {
        			distanceSum = -1;
        		}
        		System.out.print(distanceSum);
        		
        		if (j != vertexNum - 1)
        			System.out.print(" ");
        		
        	}
        	
        	System.out.println("");
        	
        }
        
        scan.close();
    }
    

    
}

class Edge {
	private static final int weight = 6;
	Vertex destination;
	
	public Edge(Vertex destination) {
		this.destination = destination;
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
	private int distance;
	private Vertex previous;
	
	public Vertex(int id) {
		this.id = id;
		adjacencies = new ArrayList<Edge>();
		distance = Integer.MAX_VALUE;
	}
	
	public void addAdjacency (Edge adjacency) {
		
		if (adjacencies.contains(adjacency)) {
			return;
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
}

class ShortestPath {
	public static void dijkstra(Graph g, Vertex source) {
		
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		
		source.setDistance(0);
		queue.add(source);
		
		while (!queue.isEmpty()) {
			Vertex curVertex = queue.poll();
			
			for (Edge e : curVertex.getAdjacencies()) {
				Vertex neibor = e.getDestination();
				
				int weight = e.getWeight();
				
				
				int distance = curVertex.getDistance() + weight;
				
				if (distance < neibor.getDistance()) {
					queue.remove(neibor);
					
					neibor.setDistance(distance);
					neibor.setPrev(curVertex);
					queue.add(neibor);
				}
			}
		}
	}
	
	public static List<Vertex> getShortestPath(Vertex destination) {
		
		List<Vertex> path = new ArrayList<Vertex>();
		
		
		for (Vertex v = destination; v != null; v = v.getPrev()) {
			path.add(v);
		}
		
		Collections.reverse(path);
		return path;
	}
}