package com.songkiet.floydShortestpath;


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
 
        	for (int j=0; j < edgeNum; j++) {
        		int start = scan.nextInt();
        		int end = scan.nextInt();
        		int weight = scan.nextInt();
        		
        		Edge edge = new Edge(start, end, weight);
        		
        		g.addEdge(edge);
        		
        		//Edge edge2 = new Edge(vEnd, vStart, weight);
        		//vEnd.addAdjacency(edge2);
        		
        	}
        	
        	long [][] shortPaths = ShorttestPath.floyd(g);
        	
        	int noOfQuestion = scan.nextInt();
        	for (int i=0; i< noOfQuestion; i++) {
        		int vStart = scan.nextInt();
        		int vEnd = scan.nextInt();
        		
        		long sumWeight = shortPaths[vStart-1][vEnd-1];
        		
        		if (sumWeight == Integer.MAX_VALUE) {
        			sumWeight = -1;
        		}
        		System.out.println(sumWeight);
        	}
        	
        	
        //}
        
        scan.close();
    }
}

class Edge implements Comparable<Edge> {
	private int weight;
	int start;
	int destination;
	
	public Edge(int start, int destination) {
		this.start = start;
		this.destination = destination;
	}
	
	public Edge(int start, int destination, int weight) {
		this.start = start;
		this.destination = destination;
		this.weight = weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getStart() {
		return start;
	}
	public int getDestination() {
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
		return destination;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(weight, o.weight);
	}
}


class Graph {
	private Edge[][] adjMatrix;
	int vertexNum;
	
	public Graph(int vertexNum) {
		this.vertexNum = vertexNum;
		adjMatrix = new Edge[vertexNum][vertexNum];
	}
	
	public void addEdge(Edge v) {
		int start = v.getStart();
		int end = v.getDestination();
		
		adjMatrix[start-1][end-1] = v;
	}
	
	public Edge getEdge(int start, int end) {
		return adjMatrix[start -1][end -1];
	}
	
	public int vertexNum() {
		return vertexNum;
	}
	
	public List<Edge> getAdjacencies(int vertex) {
		
		List<Edge> adjEdges = new ArrayList<Edge>();
		
		for (int i=0; i<vertexNum; i++) {
			Edge edge = adjMatrix[vertex - 1][i];
			if (edge != null) {
				adjEdges.add(edge);
			}
		}

		return adjEdges;
	}
}

class ShorttestPath {

	public static long[][] floyd(Graph g) {
		
		int vertexNum = g.vertexNum();
		long[][] shorttestPath = new long[vertexNum][vertexNum];

		for (int i=0; i < vertexNum; i++) {
			for (int j=0; j < vertexNum; j++) {
				
				Edge e = g.getEdge(i+1, j+1);
				
				if (i != j) {
					if (e != null) {
						shorttestPath[i][j] = e.getWeight();
					} else {
						shorttestPath[i][j] = Integer.MAX_VALUE;
					}
	
				} else if (i == j) {
					shorttestPath[i][j] = 0;
				}
			}
		}
		
		for (int i=0; i < vertexNum; i++) {
			for (int j=0; j < vertexNum; j++) {
				for (int k=0; k < vertexNum; k++) {
					if (shorttestPath[j][k] > shorttestPath[j][i] + shorttestPath[i][k]) {
						shorttestPath[j][k] = shorttestPath[j][i] + shorttestPath[i][k];
					}
				}
			}
		}
		
		return shorttestPath;
	}
	

}


