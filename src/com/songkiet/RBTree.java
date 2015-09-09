package com.songkiet;

public class RBTree<T> {
	
	private static final boolean BLACK = false;
	private static final boolean RED = true;
	
	private RBNode NIL = new RBNode(null, null, null, null, BLACK);
	private RBNode root = NIL;
	
	private class RBNode {
		private RBNode left;
		private RBNode right;
		private RBNode parent;
		private Comparable<T> element;
		private boolean color;
		
		public RBNode(RBNode left, RBNode right, RBNode parent,	Comparable<T> element, boolean color) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.element = element;
			this.color = color;
		}
	}
	
	private void letfRotate(RBNode root) {
		RBNode pivot = root.right;
		
		root.right = pivot.left;
		if (root.right != NIL) {
			root.right.parent = root;
		}

		if (root.parent == NIL) {
			this.root = pivot;
		} else if (root.parent.left == root) {
			root.parent.left = pivot;
		} else if (root.parent.right == root) {
			root.parent.right = pivot;
		}
		pivot.parent = root.parent;
		
		root.parent = pivot;
		pivot.left = root;
		
	}
	
	private void leftRotate(RBNode root) {
		RBNode pivot = root.left;
		
		root.left = pivot.right;
		if (root.left != NIL) {
			root.left.parent = root;
		}
		
		if (root.parent == NIL) {
			this.root = pivot;
		} else if (root.parent.left == root) {
			root.parent.left = pivot;
		} else if (root.parent.right == root) {
			root.parent.right = pivot;
		}
		pivot.parent = root.parent;
		
		root.parent = pivot;
		pivot.right = root;
		
	}
	
	public Comparable<T> search(Comparable<T> e) {

		RBNode node = searchNode(e);
		if (node != NIL) {
			return node.element;
		} else {
			return null;
		}
	}
	
	private RBNode searchNode(Comparable<T> e) {
		T element = (T) e;
		RBNode curNode = this.root;
		while (curNode != NIL) {
			int compareResult = curNode.element.compareTo(element);
			if (compareResult < 0) {
				curNode = curNode.left;
			} else if (compareResult > 0) {
				curNode = curNode.right;
			} else {
				if (curNode != NIL) {
					return curNode;
				} else {
					return NIL;
				}
			}
		}
		
		return NIL;
	}
	
	public void insert(Comparable<T> e) {
		RBNode insertNode = new RBNode(null, null, null, e, RED);
		RBNode parentNode = this.NIL;
		RBNode curNode = this.root;
		
		while (curNode != NIL) {
			
			parentNode = curNode; // After exit loop, curNode will be NIL so we need to backup.
			
			T element = (T) curNode.element;
			int compareResult = insertNode.element.compareTo(element);
			if (compareResult < 0) {
				curNode = curNode.left;
			} else if (compareResult > 0) {
				curNode = curNode.right;
			} else if (compareResult == 0) {
				return;
			}
		}
		
		insertNode.parent = parentNode;
		if (insertNode.parent == NIL) {
			this.root = insertNode;
		} else {
			if (insertNode.element.compareTo((T) parentNode.element) < 0) {
				parentNode.left = insertNode;
			} else {
				parentNode.right = insertNode;
			}
		}
		
		insertFixup(insertNode);
	}
	
	public void delete(Comparable<T> e) {
		RBNode node = searchNode(e);
		if (node != NIL) {
			deleteNode(node);
		}
	}
	
	private void deleteNode(RBNode deleteNode) {
		
		RBNode parentNode = deleteNode.parent;
		deleteNode.parent = NIL;
		
		if (deleteNode.left == NIL && deleteNode.right == NIL) {
			if (parentNode.left == deleteNode) {
				parentNode.left = NIL;
			} else {
				parentNode.right = NIL;
			}
		} else if (deleteNode.left == NIL) {
			if (parentNode.left == deleteNode) {
				parentNode.left = deleteNode.right;
			} else {
				parentNode.right = deleteNode.right;
			}
		} else if (deleteNode.right == NIL) {
			if (parentNode.left == deleteNode) {
				parentNode.left = deleteNode.left;
			} else {
				parentNode.right = deleteNode.left;
			}
		} else {
			RBNode predecessor = deleteNode.left;
			
			while (predecessor.left != NIL) {
				predecessor = predecessor.left;
			}
			
			deleteNode.element = predecessor.element;
			
			if (predecessor.left != NIL) {
				deleteNode(predecessor);
			}
		}
	}

	private void insertFixup(RBNode insertNode) {
		
		while (insertNode.parent.color == RED) {
			
			
		}
	}

}
