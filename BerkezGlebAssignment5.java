/* Gleb Berkez
 * CS 1450 - 003
 * Assignment #5
 * Due Date: February 27
 * Purpose: Use generic classes to
 * manipulate stacks
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

public class BerkezGlebAssignment5 {

	public static void main(String[] args) throws IOException {
		
		//Create scanner for integer file
		Scanner scanInteger = new Scanner (new File("numbersSpring.txt"));
		GenericStack<Integer> stack = new GenericStack();
		
		//Print original integer file
		while(scanInteger.hasNext()){
			
			int fileInt = scanInteger.nextInt();
			stack.push(fileInt);
			
		}//while loop
		
		System.out.println("Values read from file and pushed onto number stack:\n"
				+ "---------------------------------------------------------");
		printStack(stack);
		
		
		System.out.println("Number stack sorted: Lowest to Highest:\n"
				+ "---------------------------------------------------------");
		//sortStack(stack);
		//int smallestElement = removeSmallestElement(stack);
		sortStack(stack);
		//printStack(stack);
	
		
		
		
		
	}//END MAIN

	//printStack method: prints stack in signature from top to bottom
	public static <E> void printStack(GenericStack<E> stack) {

		//temp stack
		GenericStack<E> tempStack = new GenericStack(); 
		
									
		int size = stack.getSize();				//size of stack
		E poppedVariable;						//last variable of stack removed and returned
		
		
		
		//Move popped variable to tempStack and print variable
		for(int i = 0; i < size; i++) {
			
			poppedVariable = stack.pop();
			tempStack.push(poppedVariable);
			System.out.println(poppedVariable);
		}
		
		//Move variables form tempStack back to stack
		for(int i = 0; i < size; i++) {
			
			poppedVariable = tempStack.pop();
			stack.push(poppedVariable);
		}
	}//end printStack method
	
	//Sort stack method: sorts stack from lowest on top to highest on bottom
	public static <E extends Comparable<E>> void sortStack (GenericStack<E> stack) {
		
		GenericStack<E> tempStack = new GenericStack();
		
		
		int size = stack.getSize();
		
		
		E smallestElement1 = removeSmallestElement(stack);
		E smallestElement2 = removeSmallestElement(stack);
		E smallestElement3 = removeSmallestElement(stack);
		
		System.out.println(smallestElement1 + " " +smallestElement2 + " " +smallestElement3);
		for(int i = 0; i < stack.getSize(); i++) {
			
			System.out.println(stack.pop());
		}
		
		//find smallest element in stack and push to sorted stack 
		//for(int i = 0; i < size; i ++) {
			
		//}
		
		
	}	
	//sortStack
	
	//Remove smallest element method
	public static <E extends Comparable<E>> E removeSmallestElement(GenericStack<E> stack) {
		
		//Create new stack to compare two objects
		GenericStack<E> tempStack = new GenericStack();	
		
		
		int size = stack.getSize();
		
		
		E poppedVariable = stack.pop();
		E smallestVariable = stack.peek();
		tempStack.push(poppedVariable);
		
	
			
		if(poppedVariable.compareTo(smallestVariable)<0) {
			smallestVariable = poppedVariable;
			
		}
		
		for(int i = 0; i < stack.getSize()-1; i++) {
			poppedVariable = stack.pop();
			if(poppedVariable.compareTo(smallestVariable)< 0) {
				smallestVariable = poppedVariable;
			}
			else {
				tempStack.push(poppedVariable);
			}
		}
		
		
		
		int newSize = tempStack.getSize();
		//push variables back onto stack
		for(int i = 0; i < newSize; i ++) {
			
			poppedVariable = tempStack.pop();
			stack.push(poppedVariable);
			
		}
		
		return smallestVariable;
		
		
	}//remove smallest element
	
	
	
}//End BerkezGleb Class



//Generic class for a stack
class GenericStack<E>{
	//private variables
	private ArrayList<E> stack;
	
	//constructor
	public GenericStack() {
		stack = new ArrayList<>();
	}
	
	//public methods
	public int getSize() {			//returns number of objects in stack
		
		return stack.size();
		
	}//getSize
	
	public boolean isEmpty() {		//returns boolean true if stack is empty, false if not
		
		if(stack.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}//isEmpty
	
	public E peek() {			//returns object top of stack
		int lastElement = stack.size()-1;
		return stack.get(lastElement);
		
	}//peek
	
	public void push(E variable) {			//adds object to the top of stack
		
		stack.add(variable);
		
	}//push
	
	public E pop() {				//removes and returns object at top
		//find last object
		int indexLastObject = stack.size()-1;
		
		
		
		E lastObject = stack.get(indexLastObject);
		stack.remove(lastObject);
		return lastObject;
		
		
	}//pop
	
}//end GenericStack