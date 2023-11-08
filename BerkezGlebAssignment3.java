/* Gleb Berkez
 * CS 1450 - 003
 * Assignment #3 
 * Due Date: February 13
 * Purpose: Expand upon the concept
 * of inheritance and polymorphism
 * using interfaces, classes, and subclasses
 * and also manipulating a polymorphic array.
 */

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class BerkezGlebAssignment3 {

	public static void main(String[] args) throws IOException {
		// read athletes.txt file by instantiating scanner class
		Scanner scanner = new Scanner(new File("athletes.txt"));
		
		final int NUM_ATHLETES = scanner.nextInt();				//number of athletes in athletes.txt
		
		//create polymorphic array to store Athlete objects
		Athlete[] athletesArray = new Athlete[NUM_ATHLETES];	//athletesArray is name of polymorphic array
		
		//Read each line in text file and place athlete objects in athletes array
		for (int i = 0; i < NUM_ATHLETES; i++) {
			
			//assign speeds to rightful disciplines
			String typeOfAthlete = scanner.next();				//type of athlete
			double runningSpeed = scanner.nextDouble();				//running speed
			double swimmingSpeed = scanner.nextDouble();				//swimming speed
			double bikerSpeed = scanner.nextDouble();					//biker speed
			String nameOfAthlete = scanner.next() + " " + scanner.next();		//first and last name
			
			//create and place proper athlete objects
			switch(typeOfAthlete) {
				case "t": 
					Triathlete object = new Triathlete(nameOfAthlete, runningSpeed, swimmingSpeed, bikerSpeed);
					athletesArray[i] = object;
					break;
				case "d":
					athletesArray[i] = new Duathlete(nameOfAthlete, runningSpeed, bikerSpeed);
					break;
				case "a":
					athletesArray[i] = new Aquathlete(nameOfAthlete, swimmingSpeed, runningSpeed);
					break;
				case "m":
					athletesArray[i] = new Marathoner(nameOfAthlete, runningSpeed);
					break;
			}//switch
		}//for
		
		ArrayList<Athlete> bikers = findBikers(athletesArray);						//assign findBikers method
		ArrayList<Athlete> nonSwimmers = findDoNotSwim(athletesArray);				//assign findDoNotSwim method
		
		//Print to console parameters of bikers

		System.out.println("ATHLETES THAT BIKE!\n-------------------------------");	    //Banner
		
		for (int i = 0; i < bikers.size(); i ++) {
			System.out.println( bikers.get(i).getName() + " is a " + bikers.get(i).getType() 
					+ "and is an athlete that bikes at " + ((Biker)bikers.get(i)).bikerSpeed() + "\n"
					+ bikers.get(i).disciplines() + "\n");
		}
		
		//Print to console parameters of nonSwimmers
		
		System.out.println("\nATHLETES THAT DO NOT SWIM!\n---------------------------------");		//Banner
		
		for (int i = 0; i < nonSwimmers.size(); i++) {
			System.out.println(nonSwimmers.get(i).getName() + " is a " +nonSwimmers.get(i).getType() 
					+ " and does not swim\n" + nonSwimmers.get(i).disciplines() + "\n");
		}
		
		//Print to console slowest runner using slowestRunner method
		
		System.out.println("\nSLOWEST RUNNER!\n----------------------------------");			//Banner
		
		Athlete slowestRunner = athletesArray[findSlowestRunner(athletesArray)];
		System.out.println("Slowest runner is" + slowestRunner.getName() + " who is a " 
				+ slowestRunner.getType() + " with a running speed of " 
				+ ((Runner)slowestRunner).runningSpeed() + "mph");
			
	}//end main



	//Find which athletes are bikers method
	public static ArrayList<Athlete> findBikers(Athlete[] athletes){
		
		//create ArrayList to return
		ArrayList<Athlete> bikers = new ArrayList<>();
		
		//add proper athletes to ArrayList
		for (int i = 0; i < athletes.length; i++) {
			if(athletes[i] instanceof Biker) {
				bikers.add(athletes[i]);
			}
		}//for
		return bikers;
	}//findBikers method
	
	//Find athletes that do not swim method
	public static ArrayList<Athlete> findDoNotSwim(Athlete[] athletes){
		
		//create ArrayList to return
		ArrayList<Athlete> nonSwimmers = new ArrayList<>();
		
		//add the proper athletes to ArrayList
		for (int i = 0; i < athletes.length; i++) {
			if(!(athletes[i] instanceof Swimmer)) {
				nonSwimmers.add(athletes[i]);
			}
		}//for
		return nonSwimmers;
	}//findDoNotSwim method
	
	//findSlowestRunner method - return index
	public static int findSlowestRunner(Athlete[] athletes) {
		
		int slowestSpeed = (int)((Runner)athletes[0]).runningSpeed();		//initialize slowestSpeed as first element in athletes
		int index = 0;
		
		//for-loop to loop through athletes array
		for (int i = 1; i < athletes.length-1; i++) {
			if (((Runner)athletes[i]).runningSpeed() < slowestSpeed){
				slowestSpeed = (int)((Runner)athletes[i]).runningSpeed();
				index = i;
			}
		}//for
		
		return index;
		
	}//findSlowestRunner method
}//end Berkez Class

/****Interfaces****/
//Runner Interface
interface Runner{
	
	//Running speed method
	double runningSpeed();
}//end Runner interface

//Swimmer interface
interface Swimmer{
	
	//Swimming speed method
	public abstract double swimmingSpeed();
	
}//end Swimmer interface

//Biker interface
interface Biker{
	
	//Biker speed method
	public double bikerSpeed();
	
}//Biker interface

/***Superclass and subclasses***/
//abstract Class Athlete
abstract class Athlete{
	
	//private variables 
	String name;
	String type;
	
	/****public variables****/
	//getters
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	
	//setters
	public void setName(String c_name) {
		name = c_name;
	}
	public void setType(String c_type) {
		type = c_type;
	}
	
	//abstract disciplines method
	public abstract String disciplines();
}//end Athlete class

//subclass Triathlete
class Triathlete extends Athlete implements Runner, Swimmer, Biker{
	
	//private variables
	private double runningSpeed;
	private double swimmingSpeed;
	private double bikerSpeed;
	
	//constructor 
	public Triathlete(String name, double c_runningSpeed, double c_swimmingSpeed, double c_bikerSpeed) {
		
		setName(name);							//use setter
		setType("Triathlete");
		this.runningSpeed = c_runningSpeed;
		this.swimmingSpeed = c_swimmingSpeed;
		this.bikerSpeed = c_bikerSpeed;
	}
	
	//implement interfaces
	@Override
	public double runningSpeed(){
		return runningSpeed;
	}
	
	@Override
	public double bikerSpeed() {
		return bikerSpeed;
	}
	
	@Override
	public double swimmingSpeed() {
		return swimmingSpeed;
	}
	//abstract method 
	@Override									//inherit from abstract Athlete class
	public String disciplines(){
		return "During the Ironman triathlon, I swim 2.4 miles, bike 112 miles, then run 26.2 miles";
	}
}//end Triathlete

class Duathlete extends Athlete implements Runner, Biker{
	
	//private variables
	double runningSpeed;
	double bikerSpeed;
	
	//constructor
	public Duathlete(String name, double runningSpeed, double bikerSpeed) {
		setName(name);
		setType("Duathlete");
		this.runningSpeed = runningSpeed;
		this.bikerSpeed = bikerSpeed;
	}
	
	//implement interfaces
	@Override
	public double runningSpeed() {
		return runningSpeed;
	}
	@Override
	public double bikerSpeed() {
		return bikerSpeed;
	}
	
	//public methods
	@Override
	public String disciplines() {
		return "I run, bike, then sometimes run again. In a long-distance duathlon, I run 6.2\r\n"
				+ "miles, bike 93 miles, then run 18.6 miles.";
	}
}//end Duathlete

//subclass Aquathlete
class Aquathlete extends Athlete implements Runner, Swimmer{
	
	//private variables
	private double swimmingSpeed;
	private double runningSpeed;
	
	//constructor
	public Aquathlete(String name, double swimmingSpeed, double runningSpeed) {
		setName(name);
		setType("Aquathlete");
		this.swimmingSpeed = swimmingSpeed;
		this.runningSpeed = runningSpeed;
	}
	
	//implement interfaces
	@Override
	public double swimmingSpeed() {
		return swimmingSpeed;
	}
	@Override
	public double runningSpeed() {
		return runningSpeed;
	}
	
	//public methods
	@Override
	public String disciplines() {
		return "I run, swim, then run again. In the Swedish OTILLO Championship, the race\r\n"
				+ "takes place over 24 islands and requires 6 miles of swimming between the \r\n"
				+ "islands and 40 miles of trail running.";
	}
}//end Aquathlete

//subclass Marathoner
class Marathoner extends Athlete implements Runner{
	
	//private variables
	double runningSpeed;
	
	//constructor
	public Marathoner(String name, double runningSpeed) {
		setName(name);
		setType("Marathoner");
		this.runningSpeed = runningSpeed;
	}
	
	//implement interfaces
	@Override
	public double runningSpeed() {
		return runningSpeed;
	}
	
	//public methods
	@Override
	public String disciplines() {
		return "During a full marathon I run 26.2 miles!";
	}
}//end Marathoner