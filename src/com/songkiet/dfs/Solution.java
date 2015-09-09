package com.songkiet.dfs;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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
        		
        		Vertex vStart = g.getVertex(start);
        		Vertex vEnd = g.getVertex(end);
        		
        		Edge edge = new Edge(vStart, vEnd);
        		vStart.addAdjacency(edge);
        		
        		Edge edge2 = new Edge(vEnd, vStart);
        		vEnd.addAdjacency(edge2);
        		
        	}
        	
        	// Choose arbitrary vertex
        	Vertex start = g.getVertex(1);

        	int edgeRemovedCount = SplitTree.dfs(g, start);
        	
        	System.out.println(edgeRemovedCount);
        	
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
		return destination.getDistance();
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(weight, o.weight);
	}
}

class Vertex implements Comparable<Vertex> {
	
	private List<Edge> adjacencies;
	private int id;
	private int distance;
	private Vertex previous;
	private boolean discovered;
	private int nodeCount;
	private List<Vertex> childs;
	
	public Vertex(int id) {
		this.id = id;
		adjacencies = new ArrayList<Edge>();
		distance = Integer.MAX_VALUE;
		
		childs = new ArrayList<Vertex>();
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

	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public List<Vertex> getChilds() {
		return childs;
	}

	public void addChild(Vertex v) {
		this.childs.add(v);
	}
}

class Graph {
	private List<Vertex> vertices;
	private int vertexNum;
	
	public Graph(int vertexNum) {
		this.vertexNum = vertexNum;
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
		return vertexNum;
	}

}

class SplitTree {
	public static int dfs(Graph g, Vertex source) {
		
		Stack<Vertex> stack = new Stack<Vertex>();
		Stack<Vertex> postorder = new Stack<Vertex>();
		
		List<Edge> removedEdge = new ArrayList<Edge>();
		
		source.setDistance(0);
		stack.push(source);
		
		while (!stack.isEmpty()) {
			Vertex curVertex = stack.pop();
			
			if (!curVertex.isDiscovered()) {

				postorder.push(curVertex);
				
				for (Edge e : curVertex.getAdjacencies()) {
					Vertex neibor = e.getDestination();
					
					if (curVertex.getPrev() != neibor) {
						stack.add(neibor);
						neibor.setPrev(curVertex);
						curVertex.addChild(neibor);
					}
				}
				
				curVertex.setDiscovered(true);
			}
		}
		
		for (int i=0; i< g.vertexNum(); i++) {
			Vertex v = g.getVertex(i+1);
			if (v != null) {
				v.setDiscovered(false);
			}
		}
		
		int numChild = 0;
		while (!postorder.isEmpty()) {
			Vertex curVertex = postorder.pop();
			
			int nodeCount = 1;
			
			for (Vertex child : curVertex.getChilds()) {
				
				//if (child.isDiscovered()) {
				//	continue;
				//}
				
				int childNum = child.getNodeCount();
				
				if (childNum > 0 && childNum % 2 == 0) {
					
					removedEdge.add(new Edge(curVertex, child));
				} else {
					nodeCount = nodeCount + childNum;
				}
			}
			
			curVertex.setNodeCount(nodeCount);
			curVertex.setDiscovered(true);
		}
		
		return removedEdge.size();
	}
}