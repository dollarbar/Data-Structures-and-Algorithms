/* Gleb Berkez
 * CS 1450 - 003
 * Assignment #8
 * Due Date: April 3, 2023
 * Purpose: Learn to use 2D arrays and
 * iterators. More practice with ArrayLists,
 * Queues, and Stacks.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class BerkezGlebAssignment8 {

	public static void main(String[] args) throws IOException {
		//Open all files for reading
		
		//Start with ArrayList for Message and Key file							//Preparing files with ArrayList
		//Read files, create ArrayLists and Iterators
		Scanner scanListMessage = new Scanner(new File("listMessage.txt"));			
		Scanner scanListKey = new Scanner(new File("listKey.txt"));
		
		//create two ArrayLists for message file and key file
		ArrayList<Character> arrayListMessage = new ArrayList<>();		
		ArrayList<Character> arrayListKey = new ArrayList<>();
		
		//Read chars from files onto ArrayLists
		//Filling Message ArrayList 
		String tempString = "";									
		tempString = scanListMessage.next();
		
		for(int i = 0; i < tempString.length(); i++) {
			arrayListMessage.add(tempString.charAt(i));
			
		}
		
		//Filling Key ArrayList with route key
		tempString = scanListKey.next();						
		
		for(int i = 0; i < tempString.length(); i++) {
			arrayListKey.add(tempString.charAt(i));
			
		}
		
		//Declare iterators for arrayListMessage and arrayListKey
		Iterator<Character> iteratorMessageArray = arrayListMessage.iterator();
		Iterator<Character> iteratorKeyArray = arrayListKey.iterator();
		
		
		//Read files, create Queues and iterators
		Scanner scanQueueMessage = new Scanner(new File("queueMessage.txt"));		//Preparing files with Queues
		Scanner scanQueueKey = new Scanner(new File("queuekey.txt"));
		

		//create two Queues for the message file and key file
		Queue<Character> queueMessage = new LinkedList<>();			
		Queue<Character> queueKey = new LinkedList<>();
		
		//Read chars from files into queues
		//Fill Message Queue
		tempString = scanQueueMessage.next();
		for(int i = 0; i < tempString.length(); i++) {
			
			queueMessage.offer(tempString.charAt(i));
		}
		
		tempString = scanQueueKey.next();
		for(int i = 0; i < tempString.length(); i++) {
			
			queueKey.offer(tempString.charAt(i));
		}
		
		//Declare and iterators for queues
		Iterator<Character> iteratorMessageQueue = queueMessage.iterator();
		Iterator<Character> iteratorKeyQueue = queueKey.iterator();
		
		//Instantiate Decoder to decode List Message
		
		//constructor values
		int numRowsArray = scanListKey.nextInt();
		int numColsArray = scanListKey.nextInt();
		int startingRowArray = scanListKey.nextInt();
		int startingColArray = scanListKey.nextInt();
		
		Decoder decoderForArray = new Decoder(numRowsArray, numColsArray, startingRowArray, startingColArray);
		String decodedMessageForArray = decoderForArray.unscramble(iteratorMessageArray, iteratorKeyArray);
		
		//Display decoded message
		System.out.println("For the 1st set of files(listMessage.txt and listKey.txt:\n\n"
				+ decodedMessageForArray);
		
		//Instantiate Decoder to decode Queue Message
		
		//constructor values
		int numRowsQueue = scanQueueKey.nextInt();
		int numColsQueue = scanQueueKey.nextInt();
		int startingRowQueue = scanQueueKey.nextInt();
		int startingColQueue = scanQueueKey.nextInt();
		
		Decoder decoderForQueue = new Decoder(numRowsQueue, numColsQueue, startingRowQueue, startingColQueue);
		String decodedMessageForQueue = decoderForQueue.unscramble(iteratorMessageQueue, iteratorKeyQueue);
		
		//Display decoded message
		System.out.println("\n\nFor the 2nd set of files (queueMessage.txt and queueKey.txt):\n\n"
				+ decodedMessageForQueue);
		
		
		scanListMessage.close();
		scanListKey.close();
		scanQueueMessage.close();
		scanQueueKey.close();
		
		
	}//end main
}//BerkezGleb class



/***CREATE CLASSES NECESSARY FOR PROGRAM
 * DECODER, STACK CLASSES***/



//Decoder Class
class Decoder{
	//Private variables
	private char[][] messageArray;						//2D array for secret messages
	private int startingRow;
	private int startingCol;
	private Stack stack;								//Obtained from class Stack
	
	//Constructor - create 2D array and allocate memory for stack
	public Decoder(int numRows, int numCols, int startingRow, int startingCol) {
		
		//Create 2D array
		messageArray = new char[numRows][numCols]; 
		this.startingRow = startingRow;
		this.startingCol = startingCol;
		
		//Allocate memory for stack
		stack = new Stack();

	}//end Constructor
	
	//Public method unscramble - unscramble the message from message text using key text
	public String unscramble(Iterator<Character> msgIterator, Iterator<Character> keyIterator){
		
		
		String returnString = "The original message is: ";
		
		//find number of rows and columns
		int numRows = messageArray.length;
		int numCols = messageArray[0].length;
		
		//Place msgIterator into 2D array - bottom to top row, left to right
		for(int i = numRows-1; i > -1; i--) {
			for(int j = 0; j < numCols; j++) {
				messageArray[i][j] = msgIterator.next();
				returnString = returnString + messageArray[i][j];
			}
		}
		returnString = returnString + "\n"
				+ "The secret message is: ";							//edit returnString output
		
		//traverse through 2D array based on key directions -u, d, f, b and push onto stack
		//start by pushing first character at starting location onto stack
		stack.push(messageArray[startingRow][startingCol]);
		
		while(keyIterator.hasNext()) {
			switch(keyIterator.next()){
				
				case 'u': 
					stack.push(messageArray[startingRow -1][startingCol]);
					startingRow = startingRow -1;
					break;
				case 'd':
					stack.push(messageArray[startingRow +1][startingCol]);
					startingRow = startingRow +1;
					break;
				case 'f':
					stack.push(messageArray[startingRow][startingCol+1]);
					startingCol = startingCol + 1;
					break;
				default:
					stack.push(messageArray[startingRow][startingCol-1]);
					startingCol = startingCol -1;
					break;
				
			}//end switch
		}//end while
		
		//Reverse the order of elements and display - simply use pop()
		char poppedElement;
		while(!stack.isEmpty()) {
			poppedElement = stack.pop();
			returnString = returnString + " " + poppedElement;
		}
		
		return returnString;
		
	
	}//end unscramble method
}//end Decoder Class



//class Stack
class Stack{
	
	//Private variables
	private ArrayList<Character> stack;
	
	//Constructor
	public Stack() {
		
		//allocate memory to stack
		stack = new ArrayList<Character>();
	}
	
	//Public variables
	
	public boolean isEmpty() {
		
		//if stack is empty, return true
		if(stack.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}//isEmpty
	
	//get size of stack
	public int getSize() {
		return stack.size();
	}
	
	//push method to add to top of stack
	public void push(char value) {
		stack.add(value);
	}
	
	//public method to return and remove element from top of stack - pop()
	public char pop() {
		
		//find element on top of stack, return it, remove it
		
		char lastElement = stack.remove(stack.size()-1);
		return lastElement;
	
		
	}//end pop()
}//end Class Stack