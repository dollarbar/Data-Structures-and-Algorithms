/* Gleb Berkez
 * CS 1450 - 003
 * Assignment #7 
 * Due Date: March 20, 2023
 * Purpose: Learn how regular and priority 
 * queues work. Also work with queues 
 * nested within classes.
 */


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;


public class BerkezGlebAssignment7 {

	public static void main(String[] args) throws IOException{
		
		//import Scanners for planes and semitrucks files
		Scanner planeScanner = new Scanner(new File("FedExPlanes7.txt"));
		Scanner semiTruckScanner = new Scanner(new File("FedExTrucks7.txt"));
		
		/***Create CargoTerminal object of plane and truck objects***/
		
		//get size of loading dock and tarmac by reading each file
		
		int tarmacSize = planeScanner.nextInt();				//size of tarmac
		int loadingDockSize = semiTruckScanner.nextInt();		//size of loading dock
		
		CargoTerminal terminal = new CargoTerminal(loadingDockSize, tarmacSize);
		
		
		/***create plane objects and truck objects and place into terminal object***/
		
		
		//place trucks inside loading dock of terminal
		while(semiTruckScanner.hasNext()) {
			
			int dockNumber = semiTruckScanner.nextInt();
			SemiTruck semiTruck = new SemiTruck(semiTruckScanner.next(), semiTruckScanner.next());
			terminal.addSemiTruck(dockNumber, semiTruck);
			
		}//while
		
		//place planes inside tarmac of terminal
		while(planeScanner.hasNext()) {
			
			int standNumber = planeScanner.nextInt();				//number of stands
			CargoPlane cargoPlane = new CargoPlane(planeScanner.next(), planeScanner.nextInt(),planeScanner.next(),planeScanner.nextDouble(),planeScanner.next());
			terminal.addCargoPlane(standNumber, cargoPlane);
			
		}//while	
		
		
		
		/***Display trucks and planes in terminal***/
		//Banner
		System.out.println("Loading semi-trucks and planes into cargo terminal...");
		terminal.displayTruckAndPlanes();
		
	
		
		/***SIMULATION BEGINNING***/
		
		//Move all planes in tarmac to taxiways
		Taxiways taxiway = new Taxiways();											//instantiate taxiway
		AirTrafficController airTrafficController = new AirTrafficController();		//instantiate airTrafficController
		airTrafficController.movePlanesToTaxiways(terminal, taxiway); 				//move planes to taxiway
		
		
		//show that tarmac is empty
		
		System.out.println("\n\nShow empty tarmac in cargo terminal....");
		terminal.displayTruckAndPlanes();
		
		//Move planes from taxiway to runway
		
		Runway runway = new Runway();												//instantiate runway
		airTrafficController.movePlanesToRunway(taxiway, runway);					//move planes to runway
		
		//Clear planes for takeoff from runway
		
		airTrafficController.clearedForTakeoff(runway);								//
		
		
		
		
	
		
		planeScanner.close();					//scanner closed
		semiTruckScanner.close();				//scanner closed
		
		
		
		
	}//end main
	
	
}//end Assignment Class

/****Interface and Classes*****/



//CargoTerminal Class: collection of trucks and planes
//sorted in their respective Arrays
class CargoTerminal{
	//Private variables
	private int numberOfDocks;
	private int numberOfStands;
	private SemiTruck[] loadingDock;
	private CargoPlane[] tarmac;
	
	//Constructor
	public CargoTerminal(int numberOfDocks, int numberOfStands) {
		this.numberOfDocks = numberOfDocks;
		this.numberOfStands = numberOfStands;
		this.loadingDock = new SemiTruck[numberOfDocks];
		this.tarmac = new CargoPlane[numberOfStands];
		
	}
	
	//getters
	public int getNumberOfDocks() {
		return numberOfDocks;
	}
	public int getNumberOfStands() {
		return numberOfStands;
	}
	
	//Public variables
	public void addSemiTruck(int dockNumber, SemiTruck semiTruck) {
		loadingDock[dockNumber] = semiTruck;
	}
	public void addCargoPlane(int standNumber, CargoPlane plane) {
		tarmac[standNumber] = plane;
	}
	public SemiTruck getSemiTruck(int dockNumber) {
		return loadingDock[dockNumber];
	}
	public CargoPlane getCargoPlane (int standNumber) {
		return tarmac[standNumber];
	}
	
	public CargoPlane removeCargoPlane (int stand) {
		
		
		if(tarmac[stand] != null) {
			CargoPlane temp = tarmac[stand];
			tarmac[stand] = null;
			return temp;
			
		}
		else {
			return null;
		}
	}
	public void displayTruckAndPlanes() {
		
		//loop through tarmac
		//Output docks and trucks
		for(int i = 1; i < getNumberOfDocks(); i++) {		//display Dock #s top row
			System.out.print("Dock #" + i + "\t\t");
		}
		System.out.println();
		for(int i = 1; i < getNumberOfDocks(); i++) {		//display truck number 2nd row
			if(loadingDock[i] == null) {
				System.out.print("------\t\t");
			}
			else{
				System.out.printf("%-16s", loadingDock[i].getTruckNumber());
			}
		}//for
		System.out.println("\n");					//space
		
		//Output stands and planes
		for(int i = 1; i < getNumberOfStands(); i++) {		//display Stand #s top row
			System.out.print("Stand #" + i + "\t");
		}
		System.out.println();
		for(int i = 1; i < getNumberOfStands(); i++) {		//display flight number 2nd row
			if(tarmac[i] == null) {
				System.out.print("------\t\t");
			}
			else{
				System.out.printf("%-16s", tarmac[i].getFlightNumber());
			}
		}//for
		System.out.println("\n\n");
	}//end displayTruckAndPlanes()
}//end CargoTerminal class

//SemiTruck class: exhibits details of a SemiTruck
class SemiTruck{
	//private variables
	private String truckNumber;
	private String destinationCity;
	
	//constructor
	public SemiTruck(String truckNumber, String destinationCity) {
		this.truckNumber = truckNumber;
		this.destinationCity = destinationCity;
	}
	
	//getters
	public String getTruckNumber() {
		return truckNumber;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	
}//end SemitTruck class

//class CargoPlane: exhibits all the details of a CargoPlane
//including a print method and compareTo
class CargoPlane implements Comparable<CargoPlane>{
	//private variables
	private int flightNumber;
	private String cargoType, planeType, destinationCity;
	private double capacity;
	
	//constructor
	public CargoPlane(String cargoType, int flightNumber, String planeType, double capacity, String destinationCity) {
		this.cargoType = cargoType;
		this.flightNumber = flightNumber;
		this.planeType = planeType;
		this.capacity = capacity;
		this.destinationCity = destinationCity;
	}
	//getters
	public int getFlightNumber() {
		return flightNumber;
	}
	public double getCapacity() {
		return capacity;
	}
	public String getCargoType() {
		return cargoType;
	}
	public String getPlaneType() {
		return planeType;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	
	//public methods
	
	//isUrgent method returns true if CargoPlane type is medical or animal
	public boolean isUrgent() {
		if(getCargoType().equals("medical") || getCargoType().equals("animals")) {
			return true;
		}
		else {
			return false;
		}
	}//isUrgent
	
	//isStandard method returns true if CargoPlane type is "package"
	public boolean isStandard() {
		if(getCargoType().equals("package")) {
			return true;
		}
		else {
			return false;
		}
	}//isStandard
	
	//print method displays flight number, destination city, and cargo type
	public void print() {
		System.out.printf("flight:     %-10d %-10s      %-10s",
				getFlightNumber(), getDestinationCity(), getCargoType());
	}//print 
	
	@Override
	public int compareTo(CargoPlane plane) {
		if((this.getCargoType().compareTo(plane.getCargoType())) == -1) {
			return 1;
		}
		else if((this.getCargoType().compareTo(plane.getCargoType())) == 1) {
			return -1;
		}
		else {
			return 0;
		}
	}//end compareTo	
}//end class CargoPlane

//TaxiWays class: before moving planes to the runway, we move them
//to TaxiWays to organize between urgent and standard priority
class Taxiways{
	
	//Private Variables
	PriorityQueue<CargoPlane> urgentTaxiway;
	Queue<CargoPlane> standardTaxiway;
	
	//Constructor
	public Taxiways() {
		this.urgentTaxiway = new PriorityQueue<>();
		this.standardTaxiway = new LinkedList<>();
	}//end constructor
	
	//Public Methods
	
	//Following three methods for urgentTaxiway
	public boolean isUrgentTaxiwayEmpty() {								//check if urgentTaxiway is empty
		if(urgentTaxiway.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}//isUrgentTaxiwayEmpty
	
	public void addPlaneToUrgentTaxiway(CargoPlane plane) {				//adds plane to urgentTaxiway
		urgentTaxiway.offer(plane);
	}//addPlaneToUrgentTaxiway
	
	public CargoPlane removePlaneFromUrgentTaxiway() {					//removes Plane from urgentTaxiway
		return urgentTaxiway.poll();
	}//removePlaneFromUrgentTaxiway
	
	//Following three methods for standardTaxiway 
	public boolean isStandardTaxiwayEmpty() {							//check if standardTaxiway is empty
		if(standardTaxiway.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}//isStandardTaxiwayEmpty
	
	public void addPlaneToStandardTaxiway(CargoPlane plane) {			//adds plane to standardTaxiway
		standardTaxiway.offer(plane);
	}//addPlaneToStandardTaxiway
	
	public CargoPlane removePlaneFromStandardTaxiway() {				//removes Plane from standardTaxiway
		return standardTaxiway.poll();
	}//removePlaneFromStandardTaxiway	

}//end Taxiway Class

//Runway Class: Sorts planes from Taxiway in priority based order
//to simulate takeoff
class Runway{
	//Private variables
	Queue<CargoPlane> runway;
	
	//Constructor
	public Runway() {
		this.runway = new LinkedList<>();
	}
	
	public boolean isEmpty() {						//check to see if runway is empty
		if(runway.isEmpty() == true) {
			return true;
		}
		else {
			return false;
		}	
	}//isEmpty
		
	public void add(CargoPlane plane) {		//adds Planes from Taxiway to Runway
		
		runway.add(plane);
	
	}//add()
	
	
	public CargoPlane removeFromRunway() {		//remove plane from runway method
		return runway.poll();
	}
}//Runway Class

//AirTraffic Controller Object: moves planes from tarmac to taxiway,
//moves plane to runway from taxiway
class AirTrafficController{
	//No Private Variables
	//No Constructor
	
	//Public Variables
	
	//move each plane from tarmac to taxiway
	public void movePlanesToTaxiways(CargoTerminal terminal, Taxiways taxiways) {
		
		//Banner
		System.out.println("Control Tower: Moving planes from cargo terminal to taxiways:\n"
				+ "------------------------------------------------------------------------");
		
		//for-loop loops through terminal, adds plane to taxiway and removes from terminal
		for(int i = 0; i < terminal.getNumberOfStands(); i++) {
			CargoPlane currentPlane = terminal.removeCargoPlane(i);
			if(currentPlane != null){
				if(currentPlane.isUrgent()) {
					
					//add index currentPlane to taxiway
					taxiways.addPlaneToUrgentTaxiway(currentPlane);
					
					System.out.print("Moved to taxiway Urgent    ");				//print confirmation
					currentPlane.print();
					System.out.println();
				}
				else {
					
					//add index currentPlane to taxiway
					taxiways.addPlaneToStandardTaxiway(currentPlane);
					
					System.out.print("Moved to taxiway Standard  ");				//print confirmation
					currentPlane.print();
					System.out.println();
				}
			}//main if
			else {
				
			}
			
		}//for
	}//movePlanesToTaxiways method
	
	//move each plane in taxiway to runway according to priorities
	public void movePlanesToRunway(Taxiways taxiways, Runway runway) {
		
		//Banner
		System.out.println("Control Tower: Moving cargo planes from taxiways to runway\n" 
				+ "--------------------------------------------------------------------");
		
		//first move planes from urgent taxiway, then standard taxiway
		while((!taxiways.isStandardTaxiwayEmpty()) || (!taxiways.isUrgentTaxiwayEmpty())) {
			if(!taxiways.isUrgentTaxiwayEmpty()) {
				
				//remove plane from urgentTaxiway and add to runway
				CargoPlane currentPlane = taxiways.removePlaneFromUrgentTaxiway();
				runway.add(currentPlane);
				
				//Print confirmation
				System.out.print("Moved to runway ");
				currentPlane.print();
				System.out.println();
				
			}//if
			
			else{
				
				//remove plane from standardTaxiway and add to runway tail
				CargoPlane currentPlane = taxiways.removePlaneFromStandardTaxiway();
				runway.add(currentPlane);
				
				//Print confirmation
				System.out.print("Moved to runway ");
				currentPlane.print();
				System.out.println();
				
			}//else
		}//while
		System.out.println("\n\n");
	}//movePlanesToRunway
	
	//clear planes for takeoff from runway
	public void clearedForTakeoff(Runway runway) {
		
		//Banner
		System.out.println("Control Tower: Ready for takeoff!\n"
				+ "------------------------------------------");
		
		//remove all planes from runway starting at head
		while(!runway.isEmpty()) {
			
			CargoPlane currentPlane = runway.removeFromRunway();
			
			//print confirmation
			System.out.print("Cleared for takeoff    ");
			currentPlane.print();
			System.out.println();
			
		}//while
	}//clearedForTakeOff method
	
}//end AirTrafficController Class


