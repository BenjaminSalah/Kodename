package model;

import java.util.ArrayList;

import control.Controller;

/**
 * Project holds instances of the following: World, Karel, Controller, and an ArrayList of type CustomCode.
 * The purpose of Project is two-fold. The first is to allow for easy, top-down serialization of a user's project when they save.
 * This is because all the necessary information pertaining to any given project of the user is contained within Project.
 * The second purpose of Project is to have an object store references to all important data that the user will directly manipulate.
 * 
 * @author Kodename team
 *
 */
public class Project {
	
	World world;
	Karel karel;
	
	// Tracks custom code that the user has created
	ArrayList<CustomCode> customCode;
	Controller controller;
	
	/**
	 * Creates a new Project instance based on arguments that the user has either created or has manipulated
	 * @param world A World object that the user has selected for their project
	 * @param karel A Karel object that the user manipulates
	 * @param customCode A list of customCode objects which represent any custom code the user may have created on their own
	 * @param controller A Controller object which stores the user's existing program code that they've created to execute on Karel the robot
	 * @throws IllegalAgumentException if any parameter is null
	 */
	public Project(World world, Karel karel, ArrayList<CustomCode> customCode, Controller controller){
		//Enforcing pre-condition.
		if(world == null || karel == null || customCode.size()==0 || controller == null){
			throw new IllegalArgumentException(); 
		}
		this.world = world;
		this.karel = karel;
		this.customCode = customCode;
		this.controller = controller;
		
		//checking post-condition
		assert(check_arguments());
	}
	
	/**
	 * Serializes an instance of Project
	 * @param pathName The path definition that the serialized object should reside in 
	 */
	public void serialize(String pathName){
	}
	
	/**
	 * A static method that will de-serialize a Project based on the given path definition
	 * @param pathName The path definition where the serialized Project object is located 
	 * @return a de-serialized Project object
	 */
	public static Project deserialize(String pathName){
		return null;
	}
	
	/**
	 * Returns the World instance
	 * @return a World object
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Sets the world field to a World object
	 * @param world a World object
	 * @throws IllegalAgumentException if parameter is null
	 */
	public void setWorld(World world) {
		//Enforcing pre-condition
		if(world == null){
			throw new IllegalArgumentException();
		}
		this.world = world;
		
		//checking post-condition
		assert(this.world != null);
	}

	/**
	 * Returns the Karel instance
	 * @return a Karel object
	 */
	public Karel getKarel() {
		
		return karel;
	}

	/**
	 * Sets the karel field to a Karel object
	 * @param karel a Karel object
	 * @throws IllegalAgumentException if parameter is null
	 */
	public void setKarel(Karel karel) {
		
		//Enforcing pre-condition
		if(karel == null){
			throw new IllegalArgumentException();
		}
		
		this.karel = karel;
		
		//checking post-condition
		assert(this.karel != null);
	}

	/**
	 * Returns the list of CustomCode created by the user
	 * @return an ArrayList of type CustomCode
	 */
	public ArrayList<CustomCode> getCustomCode() {
		return customCode;
	}

	/**
	 * Sets the customCode field to a list of CustomCode
	 * @param customCode An ArrayList of type CustomCode
	 * @throws IllegalAgumentException if argument is null
	 */
	public void setCustomCode(ArrayList<CustomCode> customCode) {
		
		//Enforcing pre-condition
		if(customCode.size() == 0){
			throw new IllegalArgumentException();
		}
		
		this.customCode = customCode;
		
		//checking post-condition
		assert(this.customCode.size() != 0);
	}

	/**
	 * Returns the Controller instance
	 * @return a Controller object
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Sets the controller field to a Controller object
	 * @param controller a Controller object
	 * @throws IllegalAgumentException if argument is null
	 */
	public void setController(Controller controller) {
		
		//Enforcing pre-condition
		if(controller == null){
			throw new IllegalArgumentException();
		}
		
		this.controller = controller;
		
		//checking post-condition
		assert(this.controller != null);
	}
	
	/*
	 * This method is a class invariant to check that the "contents" of the project are not empty.
	 */
	private boolean check_arguments(){
		if(world == null && karel == null && customCode.size()==0 && controller == null){
			return false;
		}
		return true;
	}
}

