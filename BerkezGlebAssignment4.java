import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

public class BerkezGlebAssignment4 {

	public static void main(String[] args) throws IOException{
		
		//import Scanners for planes and semitrucks files
		Scanner planeScanner = new Scanner(new File("FedExPlanes.txt"));
		Scanner semiTruckScanner = new Scanner(new File("FedExTrucks.txt"));
		
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
			CargoPlane cargoPlane = new CargoPlane(planeScanner.nextInt(),planeScanner.next(),planeScanner.nextDouble(),planeScanner.next());
			terminal.addCargoPlane(standNumber, cargoPlane);
			
		}//while	
		
		/***Display trucks and planes in terminal***/
		terminal.displayTruckAndPlanes();
		
		/***Print Status Report for Tarmac***/
		printTarmacStatus(terminal);
	
		planeScanner.close();
		semiTruckScanner.close();
		
		
	}//end main
	
	//public method printTarmacStatus: prints details of each plane
	public static void printTarmacStatus(CargoTerminal terminal) {
		
		ArrayList<PlaneReport> planeReportArrayList = new ArrayList<>();			//ArrayList of PlaneReports initialized
		
		//Banner
		System.out.println("\n\n*************************************************\n"
				+ "\t\tTarmac Status\n"
				+ "*************************************************\n");
		System.out.printf("%-10s%-10s%-17s%-15s%s\n",
				"Flight", "Stand", "Departing To", "Type", "Capacity(lbs)");
		
		//Create a PlaneReport for each cargo plane
		for (int i = 1; i < terminal.getNumberOfStands(); i++) {
			
			if(terminal.getCargoPlane1(i) == null) {}			//skip if null
			
			else {
				int stand = i;
				int flightNumber = terminal.getCargoPlane(i).getFlightNumber();					
				String type = terminal.getCargoPlane(i).getType1();
				double capacity = terminal.getCargoPlane(i).getCapacity();
				String destinationCity = terminal.getCargoPlane(i).getDestinationCity();
				
				PlaneReport planeReport = new PlaneReport(stand, flightNumber, type, capacity, destinationCity);
				
				//Place all PlaneReports into ArrayList
				planeReportArrayList.add(planeReport);
			}
		}//for
		
		//Sort using Collections.sort()
		Collections.sort(planeReportArrayList);
		
		//Print plane report to console
		for(int i = 0; i<planeReportArrayList.size(); i++) {
			if(planeReportArrayList.get(i) == null) {}
			System.out.println(planeReportArrayList.get(i).print());
		}
		
	}//end printTarmacStatus method
}//end Assignment Class

/****Interface and Classes*****/

//Printable Interface
interface Printable{
	
	public abstract String print();
	
}//end Printable

//CargoTerminal Class
class CargoTerminal1{
	//Private variables
	private int numberOfDocks;
	private int numberOfStands;
	private SemiTruck[] loadingDock;
	private CargoPlane[] tarmac;
	
	//Constructor
	public CargoTerminal1(int numberOfDocks, int numberOfStands) {
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
	public void displayTruckAndPlanes() {
		
		//Banner
		System.out.println("Loading semi-trucks and planes into cargo terminal...");
		
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
		
	}//end displayTruckAndPlanes()
}//end CargoTerminal class

//SemiTruck class
class SemiTruck1{
	//private variables
	private String truckNumber;
	private String destinationCity;
	
	//constructor
	public SemiTruck1(String truckNumber, String destinationCity) {
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

//class CargoPlane
class CargoPlane1{
	//private variables
	private int flightNumber;
	private String type, destinationCity;
	private double capacity;
	
	//constructor
	public CargoPlane1(int flightNumber, String type, double capacity, String destinationCity) {
		this.flightNumber = flightNumber;
		this.type = type;
		this.capacity = capacity;
		this.destinationCity = destinationCity;
	}
	//getters
	public int getFlightNumber1() {
		return flightNumber;
	}
	public double getCapacity1() {
		return capacity;
	}
	public String getType1() {
		return type;
	}
	public String getDestinationCity1() {
		return destinationCity;
	}
	
	
}//end class CargoPlane

//class PlaneReport: printable report for 1 cargo plane
class PlaneReport implements Printable, Comparable<PlaneReport>{
	//private variables
	private int stand, flightNumber;
	private String type, destinationCity;
	private double capacity;
	
	
	//constructor
	public PlaneReport (int stand, int flightNumber, String type, double capacity, String destinationCity) {
		this.stand = stand;
		this.flightNumber = flightNumber;
		this.type = type;
		this.destinationCity = destinationCity;
		this.capacity = capacity;
	}
	
	//implementing interfaces
	@Override
	public String print() {
		return String.format("%-10d%-10d%-17s%-15s%.2f", 
				flightNumber,
				stand,
				destinationCity,
				type,
				capacity);
	}
	
	//Collections interface override
	@Override
	public int compareTo(PlaneReport report) {
		return this.capacity > report.capacity ? 1 : this.capacity < report.capacity ? -1 : 0;
		
        
	}
}//end PlaneReport class