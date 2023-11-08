/*Gleb Berkez
 * Assignment 9
 * Due: April 10, 2023
 * Create your own LinkedList, sort it,
 * and replace a node inside it.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class BerkezGlebAssignment9 {

	public static void main(String[] args) throws IOException {
		
	
		//Create instance of TourLinkedList
		TourLinkedList<Integer> concertTour = new TourLinkedList<Integer>();
		
		//Open file to read from to to insert into concertTour
		Scanner scanTour = new Scanner(new File( "concertTour.txt" ));
		
		
		//Loop through file to add all locales to concerTour
		while(scanTour.hasNext()) {
			
			int stop = scanTour.nextInt();
			String type = scanTour.next();
			String name = scanTour.next();
			String activity = scanTour.nextLine();
			
			Locale locale2 = new Locale(stop, type, name, activity);
			
			concertTour.addLocale(locale2);
		}
		
		//Print unsorted list
		concertTour.printList();
		concertTour.bubbleSort();
		
		
		//Replace locals from concertTourReplace
		Scanner scanReplace = new Scanner(new File("concertTourReplace.txt"));
		
		int stop = scanReplace.nextInt();
		String type = scanReplace.next();
		String name = scanReplace.next();
		String activity = scanReplace.nextLine();
		
		Locale localeReplace = new Locale(stop, type, name, activity);
		
		concertTour.replaceLocale(localeReplace);
		concertTour.printList();
		
		System.out.println(concertTour.getTail());
		
		
		
		
		

	}//end main

}//end BerkezGlebAssignment9

//Locale Class - represents 1 step in concert tour
class Locale{
	
	//Private variables
	private int stop;
	private String type;
	private String name;
	private String activity;
	
	//Constructor
	public Locale(int stop, String type, String name, String activity){
		
		//initialize private variables
		this.stop = stop;
		this.type = type;
		this.name = name;
		this.activity = activity;
	}
	
	//Getters and Setters
	public int getStop() {
		return stop;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getActivity() {
		return activity;
	}
	
	
	//toSTring() method
	public String toString() {				//
		
		
		String string = 
			String.format("%s" + "%25s" + "%50s", this.getName(), this.type, this.activity);
		return string;
	}//end toString
	
}//end Class Locale



//TourLinkedList Class - dynamic linked list of the concert tour
class TourLinkedList<Integer> {
	
	
	//Private Variables
	private Node head;
	private Node tail;
	private int size;
		
	//Private inner Node class
	//Node class - a self-referencing class to be place in LinkedList
	private static class Node<Locale>{
		
		//Private Variables
		private Locale locale;
		private Node next;
		
		//Constructor
		public Node(Locale locale) {
			
			//Initialize private variables
			this.locale = locale;
			next = null;
			
		}//end constructor
	}//end Inner Class Node
	
	
	//Constructor
	public TourLinkedList(){
		
		//initialize private variables
		this.head = null;
		this.tail = null;
		this.size = 0;
	}//end constructor
	
	//Public Variables
	public void addLocale(Locale localeToAdd) {
		
		//create a new node
		Node<Locale> node = new Node<>(localeToAdd);
		
		//Place the node at the tail
		if(tail == null) {
			head = node;
			tail = node;
			size = 0;
		}
		else {
			tail.next = node;
			tail = tail.next;
		}
		size++;
	}
	
	public void replaceLocale (Locale replacementLocale) {
		
		//obtain replacement locales
		//Locale replacementLocale = replacementLocale;
		Locale replacedLocale = replacementLocale;
		
		//find the same stop number as the replacementLocale
		Node<Locale>current = head;
		for(int i = 0; i < size; i++) {
			
			if(current.locale.getStop() == replacedLocale.getStop()) {
				current.locale = replacedLocale;
			}
		}
	}
	
	public void bubbleSort() {
		
		//performs bubble sort on list
		//uses swapNodeData method
		
		Node<Locale> current = head;
		Node<Locale> currentPlusOne = head.next;
		
		int currentStop = current.locale.getStop();
		int currentStopPlusOne = currentPlusOne.locale.getStop();
		
		for (int pass = 1; pass < size; pass++) {
			
			for (int j = 0; j < size-1; j++) { 
				
				
				
				if (current.locale.getStop() > currentPlusOne.locale.getStop()) { 
					
					
					Node<Locale> temp = current;
					current = currentPlusOne;
					currentPlusOne = temp;
					
					current = currentPlusOne;
					currentPlusOne = current.next;
				
					}
				} // for each comparison
			} // for each pass
		} // bubbleSort
		


	
	public String getTail() {
		return head.next.locale.toString();
	}
	public void printList() {
		
		//calls Locale's print method
		//prints head to end
		
		
		//Banner
		System.out.println("Locale\t\tType\t\tActivity\n"
				+ "------------------------------------");
		
		//Initialize variables for for-loop
		
		String printList = "";
		Node<Locale> current = head;
		String string = "";
		for(int i = 0; i < size; i++) {
			
			string = current.locale.toString();
			printList = String.format(printList + "%30s"  + "\n", string);
			current = current.next;
			
		}
		
		//Print list
		System.out.println(printList);
		
		
	}//end printList
	
}

