
import java.io.File;
import java.util.Scanner;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class BerkezGlebAssignment6 {

	public static void main(String[] args) throws IOException {
		
		// Name of file to read from
		final String PLANES_FILE_NAME = "Worksheet1Planes.txt";	

		// Setup a file reference variable to refer to text file
		File planesFileName = new File(PLANES_FILE_NAME);

		// First value in file is the number of planes in the file
		Scanner planesFile = new Scanner (planesFileName);
		int numPlanesInFile = planesFile.nextInt();
		GenericStackF19<PlaneW1> planeStack = new GenericStackF19<>();
		//create planes stack
		
			
			
			
		
		
		
		
		
		//****************************************
		// Worksheet1 Question #2: 
		// Write code to create a stack of planes
		//*****************************************
		// ADD YOUR CODE HERE
		

		
		// Create plane objects from the details provided in the file
		for(int i = 0; i < numPlanesInFile; i++) {
			
			// Read information from file and create a plane
			String type = planesFile.next();
			double capacity = planesFile.nextDouble();

			PlaneW1 plane = new PlaneW1(type, capacity);		
			planeStack.push(plane);
			// Print the plane's details
			System.out.println(plane.print());
	
			//****************************************
			// Worksheet1 Question #3: 
			// Write code to add the plane to the stack
			//****************************************
			// ADD YOUR CODE HERE
			  
			
		} // for each plane
		
		
		//****************************************
		// Worksheet1 Question #4
		// Write code to print the type of each plane on the stack.  
		// Use a while loop to perform this task.
		//****************************************
		// ADD YOUR CODE HERE
		
		while(!planeStack.isEmpty()) {
			
			PlaneW1 popped = planeStack.pop();
			System.out.println(popped.getType());
			
		}
		

		
		//****************************************
		// Worksheet1 Question #5
		// Write code to examine the top element on the stack  
		//****************************************
		// ADD YOUR CODE HERE
		
		
		
		planesFile.close();
		
		//*******************************************
		// Worksheet2 Question #1 
		// Queue in main - queue is NOT nested inside a class
		// Practice creating a queue and adding the values 12, 34, 64, 45
		//*******************************************
		// ADD CODE HERE

		
		// Test your non-nested queue by displaying the values in the queue
		System.out.println("Values in a queue that is not nested in a class");
		Queue<Integer> queueNotNested = new LinkedList<>();
		queueNotNested.offer(12);
		queueNotNested.offer(34);
		queueNotNested.offer(64);
		queueNotNested.offer(45);
		
		int queueLength = queueNotNested.size();
		for (int i = 0; i < queueLength; i++) {
			System.out.println("aQueue[" + i + "] = " + queueNotNested.remove());
		}

		//********************************
		// Worksheet2 Question #2
		// See IntegerQueue Class below
		//********************************
		
		
		//********************************
		// Worksheet2 Question #3a 
		// Use this code to test your IntegerQueue class
		// The queue is now nested inside a class called IntegerQueue
		// Create an object of type IntegerQueue and add the values 12, 34, 64, 45
		//********************************
		// ADD CODE HERE
		IntegerQueueW2 nestedQueue = new IntegerQueueW2();
		nestedQueue.offer(12);
		nestedQueue.offer(34);
		nestedQueue.offer(64);
		nestedQueue.offer(45);
		
		

		
		//********************************
		// Worksheet2 Question #3b
		// Test your nested queue by displaying the values in the queue
		//********************************
		// ADD CODE HERE		
		int size = nestedQueue.size();
		for(int i = 0; i < size; i++) {
			int removedInt = nestedQueue.remove();
			System.out.println(removedInt);
		}
		
// ***********************************
		// Worksheet4 Question 4a
		// Write the declaration for a priority queue of planes (PlanW4 objects)
		// ***********************************
		// ADD CODE HERE
		PriorityQueue<PlaneW4> planeQueue = new PriorityQueue<PlaneW4>();
		

		
		// Setup some hard coded planes to use in the queue questions!
		// Create two hard coded planes - hard coding is the way to go when learning!
		// Of course we would not do this in an assignment!  I'm doing it for testing purposes.
		PlaneW4 plane1 = new PlaneW4("Boeing767", 120000);  
		PlaneW4 plane2 = new PlaneW4("MD11", 180000);    

		// Add some containers to each plane
		// Note this time I'm adding the containers with a call to addContainer method on the plane.
		// Create 2 hard coded container objects and add them to plane1's queue
		plane1.addContainer(new ContainerW4("AP12345"));
		plane1.addContainer(new ContainerW4("AP882277"));
		
		// Create 3 hard coded previous owner objects and add them to car2's queue
		plane2.addContainer(new ContainerW4("AP1122"));
		plane2.addContainer(new ContainerW4("AP3456"));
		plane2.addContainer(new ContainerW4("AP7788"));

		// ***********************************
		// Worksheet4 Question #4c 
		// Write code to add plane1 and plane2 to the priority queue
		// ***********************************
		// ADD CODE HERE 
		PriorityQueue<Integer> intQueue = new PriorityQueue<Integer>();
		PriorityQueue<PlaneW4> runway = new PriorityQueue<PlaneW4>();
		runway.offer(plane1);
		runway.offer(plane2);
		
		System.out.println("Plane #1 is in priority queue");	
		System.out.println("Plane #2 is in priority queue");

		
		PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        pQueue.offer(60);

        pQueue.offer(10);

        pQueue.offer(50);

        pQueue.remove();

        pQueue.offer(40);

        pQueue.offer(70);

       

        while (!pQueue.isEmpty()) {

              System.out.println(pQueue.remove());

       }
        
       System.out.println(pQueue);
		
		
		
	} // main

} // Worksheet1

//Class representing the generic stack - named it GenericStackF19 to avoid name clashing issues
class GenericStackF19<E>  {

	private ArrayList<E> list;		// Storage for stack is provided by an ArrayList
	
	public GenericStackF19() {
		list = new ArrayList<>();
	}
	
	public boolean isEmpty (){
		return list.isEmpty();
	}
	
	public int getSize(){
		return list.size();
	}

	// Returns the top element on the top without removing it
	// Since using array list to store elements, this means getting the last element in the array
	public E peek(){
		return list.get(getSize() - 1);
	}
	
	// Removed and returns the top element on the stack 
	// Since using array list to store elements, this means removing the last element in the array
	public E pop(){		
		E value = list.get(getSize()-1);
		list.remove(getSize() - 1);
		return value;
	}

	// Place a new element on the top of the stack
	// Since using array list to store elements, this means adding the element to the array
	public void push(E value){
		list.add(value); 
	}
	
} // GenericStackF19


//Represents a plane - named it PlaneW1 due to name clashing issues - so many planes! 
class PlaneW1 { 
	
	private String type;
	private double capacity;
			
	// Create a plane
	public PlaneW1 (String type, double capacity) {
		this.type = type;
		this.capacity = capacity;
	}
			
	public String getType() {
		return type;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
	// Create a string containing details for plane
	public String print() {
		return String.format("%-15s\t%.2f\t", type, capacity);
	}
		
} // PlaneW1


//Represents a semi-truck - named it semiTruckW1 due to name clashing issues - so many planes! 
class SemiTruckW1  {

	private int truckNumber;			// Semi-truck's number

	public SemiTruckW1(int truckNumber) {
		this.truckNumber = truckNumber;
	}
	
	public int getTruckNumber() {
		return truckNumber;
	}
	
} // SemiTruckW1

class IntegerQueueW2 {
	
	private Queue<Integer> queue = new LinkedList<>();
	
	
	
	public int size() {
		// ADD CODE HERE
		return queue.size();
	}
	
	public void offer(Integer value) {
		// ADD CODE HERE
		queue.add(value);
	}
	
	public Integer remove() {
		// ADD CODE HERE
		int removedInt = queue.remove();
		return removedInt;
	}
	
} // IntegerQueue

class PlaneW3 { 
	
	private String type;
	private double capacity;
			
	private Queue<Container> containers = new LinkedList<>();  // Queue of containers that  
	 														   // will be loaded on plane
		
	// Create a plane
	public PlaneW3 (String type, double capacity) {
		this.type = type;
		this.capacity = capacity;
	}
			
	public String getType() {
		return type;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
	public int getContainersSize() {
		// ADD CODE HERE
		return containers.size(); 
	}
	
	public void addContainer (Container container) {
		// ADD CODE HERE
		containers.offer(container);
	}
	
	public Container getContainer () {
		return containers.remove();
	}
		
} // PlaneW3


class CargoTerminalW3 {
	
	private int numberDocks;		// Number of docks where semi-trucks can be loaded
	private int numberStands;		// Number of stands (parking locations) for planes
	private PlaneW3[] tarmac;		// Array representing parking spots that hold planes
	
	
	public CargoTerminalW3 (int numberDocks, int numberStands) {
		this.numberDocks = numberDocks;			// Number of docks in the loading docks array
		this.numberStands = numberStands;		// NUmber of stands in the tarmac array
		tarmac = new PlaneW3[numberStands];		// Allocate memory for tarmac
	}

	
	// Add a plane to the plane array in a specific location
	public void addCargoPlane (int stand, PlaneW3 plane) {
		tarmac[stand] = plane; 
	}

	
	// Returns the plane in the specified location in the array.  
	// When a slot does NOT contain a plane return null.
	public PlaneW3 getPlane(int stand) {
		return tarmac[stand];
	}

	
	//********************************
	// Worksheet3 Question #3
	// Add a container to a plane in stand on the tarmac
	//********************************
	public void addContainerToPlane(Container container, int stand) {
		// ADD CODE HERE
		tarmac[stand].addContainer(container);
	}
		
} // CargoTerminalW3

class Container  {
	
	private String id;			
	
	public Container (String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
} // Container

class ContainerW4  {
	
	private String id;			
	
	public ContainerW4 (String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
} // ContainerW4


//********************************
//Worksheet3 Question #1
//Write the code for each method
//********************************
////REMOVE COMPARABLE!!!!
class PlaneW4 implements Comparable<PlaneW4>{
//class PlaneW4 {	
	private String type;
	private double capacity;
			
	private Queue<ContainerW4> containers = new LinkedList<>();  // Queue of containers that  
	 														   // will be loaded on plane
		
	// Create a plane
	public PlaneW4 (String type, double capacity) {
		this.type = type;
		this.capacity = capacity;
	}
			
	public String getType() {
		return type;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
	
	// ***********************************
	// Worksheet4 Question #4b
	// Copy your answer from Worksheet3 here
	// ***********************************
	public int getContainersSize() {
		// ADD CODE HERE
		return containers.size();
	}

	
	// ***********************************
	// Worksheet4 Question #4b
	// Copy your answer from Worksheet3 here
	// ***********************************	
	public void addContainer (ContainerW4 container) {
		// ADD CODE HERE
		containers.offer(container);
	}
	
	
	// ***********************************
	// Worksheet4 Question #4h 
	// Add the code from question 4g here
	// ***********************************
	@Override
	public int compareTo(PlaneW4 plane) {
		return this.containers.size() > plane.containers.size() ? 1 : this.capacity < plane.containers.size() ? -1 : 0;
	}
	
	
	
	
} // PlaneW4

