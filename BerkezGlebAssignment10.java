/* Gleb Berkez
 * CS 1450 - 003
 * Assignment #10 
 * Due Date: May 1, 2023
 * Purpose: Build a binary tree,
 * sort through it using recursion
 * and by using iteration.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class BerkezGlebAssignment10 {

	public static void main(String[] args) throws IOException {
		
		//OPEN TEST FILE
		Scanner scanner = new Scanner(new File("parrots.txt"));
		
		//Initialize Binary Tree
		BinaryTree parrotTree = new BinaryTree();
		
		//Insert Parrots into ParrotTree
		//Assign Parrot variables from file and create Parrot Class
		int parrotID;
		String parrotName;
		String parrotSongPhrase;
		
		//while loop to insert all parrots in binary tree
		while(scanner.hasNext()) {
			
			//constructor variables
			parrotID = scanner.nextInt();
			parrotName = scanner.next();
			parrotSongPhrase = scanner.nextLine();
			
			//Parrot Class
			Parrot parrot = new Parrot(parrotID, parrotName, parrotSongPhrase);
			
			//BinaryTree insert method
			parrotTree.insert(parrot);
			
		}
		
		//print tree level order style
		parrotTree.levelOrder();
		
		//visit leaves method to print the leaves of the binary tree
		parrotTree.visitLeaves();

		
		//close scanner
		scanner.close();
	}//end main

}//BerkezGleb Class

class Parrot implements Comparable<Parrot>{
	
	//private variables
	private int id;
	private String name;
	private String songPhrase;
	
	//Constructor
	public Parrot(int id, String name, String songPhrase) {
		this.id = id;
		this.name = name;
		this.songPhrase = songPhrase;
	}
	
	//Public Variables
	
	//Getters
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSongPhrase() {
		return songPhrase;
	}

	//Public Methods
	
	public String sing() {
		return songPhrase;
	}
	
	@Override
	public int compareTo(Parrot otherParrot) {
		
		return this.getId() > otherParrot.getId() ? 1 : this.getId() < otherParrot.getId() ? -1 : 0;
		
	}//end compareTo
	
}//end Parrot Class

//Binary Tree Class
class BinaryTree{
	
	//private variable
	TreeNode root;
	
	//Constructor
	public BinaryTree() {
		root = null;
	}
	
	//Public Methods
	public boolean insert(Parrot parrot) {
		
		// If first node in the tree - then create root
		if (root == null) {
			root = new TreeNode(parrot);
		}
		else {
			// Locate the parent for the new node
			// Need a couple of references to help
			TreeNode parent = null;
			TreeNode current = root;
			
			// While haven't reached bottom of tree
			while(current != null) {
				
				// Determine if value will be inserted to left or right of current
				if (parrot.compareTo(current.data)==-1) {
						parent = current;
						current = current.left;
					}
					else if (parrot.compareTo(current.data) == 1) {
						parent = current;
						current = current.right;
					}
					else {
						// Found a duplicate node - do not insert
						return false;
					}
			} // while
			
			if ((parrot.compareTo(parent.data))<1) {
				parent.left = new TreeNode(parrot);
			}
			else {
				parent.right = new TreeNode(parrot);
			}
			
		}//else
	
		return true;
	} // insert
	
	//levelOrder method - prints binary tree level order style
	public void levelOrder() {
		//banner
		System.out.println("Parrot Christmas Song\n"
				+ "---------------------------------------------");
		if(root != null) {
			
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			
			while(!queue.isEmpty()) {
				
				TreeNode tempNode = queue.remove();
				//print value in this node
				System.out.println(tempNode.data.sing());
				
				if(!(tempNode.left == null)) {
					
					queue.add(tempNode.left);
					
				}
				
				if(!(tempNode.right == null)) {
					
					queue.add(tempNode.right);
					
				}
			}//end while
		}//end if
	}//end levelOrder method
	
	//visitLeaves methods - one public one private
	public void visitLeaves() {
		//banner
		System.out.println("\nParrots on Leaf Nodes\n"
				+ "------------------------------------");
		visitLeaves(root);
		
	}
	
	//recursive method
	private void visitLeaves(TreeNode aNode) {
		
		//base case
		if (aNode == null) {
			return;
				
		}
		
		//recursion
		if (aNode.left == null && aNode.right == null) {
		    System.out.println(aNode.data.getName());
		}
		else {
		    visitLeaves(aNode.left);
		    visitLeaves(aNode.right);
		}
		
	}
	//Private Inner Class
	private class TreeNode{
		//private variables
		private Parrot data;
		private TreeNode left;
		private TreeNode right;
		
		//constructor
		public TreeNode(Parrot parrot) {
			this.data = parrot;
			left = null;
			right = null;
			
		}//TreeNode constructor
	}//class TreeNode
}//Class BinaryTree
