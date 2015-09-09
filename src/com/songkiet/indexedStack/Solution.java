package com.songkiet.indexedStack;

import java.util.*;

public class Solution {

	public static void main(String[] args) {

		IndexedStack<Item> stack = new IndexedStack<Item>();
		
		for (int i=0; i< 5000000; i++) {
		
			stack.push(new Item(i));
			
			if (i % 100000 == 0) {
				long tStart = System.nanoTime();
				Item item = stack.find(0);
				long tEnd = System.nanoTime();
				long tRes = tEnd - tStart; // time in nanoseconds
				
				System.out.println(i + "       " + tRes + "       " + item.getId());
			}
		}
	}
}

class Item implements Comparable<Item> {
	private int id;
	
	public Item(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Item o) {
		
		if (this.id < o.id) {
			return -1;
		} else if (this.id > o.id) {
			return 1;
		} else {
			return 0;
		}
	}

}

class IndexedStack<T> {

	private static class Element<T> {
		private T data;
		private Element<T> next;
		
		public Element(T e) {
			this.setData(e);
		}
		public Element<T> getNext() {
			return next;
		}

		public void setNext(Element<T> next) {
			this.next = next;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
	}
	
	private TreeMap<T, Element<T>> tree;
	
	Element<T> top; // top of stack
	
	public IndexedStack() {
		tree = new TreeMap<T, Element<T>>();
	}
	
	public void push(T element) {
		
		Element<T> newItem = new Element<T>(element);
		newItem.setNext(top);
		top = newItem;
		tree.put(element, newItem);
		
	}

	public T pop() {
		
		Element<T> popedItem = top;
		top = popedItem.getNext();
		tree.remove(popedItem.getData());

		return popedItem.getData();
	}

	public T peek() {
		
		Element<T> popedItem = top;

		if (popedItem != null) {
			return popedItem.getData();
		} else {
			return null;
		}
	}
	
	public T find(int id) {
		Element<T> item = tree.get(new Item(id));
		
		if (item != null) {
			return item.getData();
		} else {
			return null;
		}
	}
	
	public Set<T> iterator() {
		return tree.keySet();
	}
}
