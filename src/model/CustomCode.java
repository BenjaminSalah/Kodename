/**
 * @mainAuthor Benjamin Salah
 * @helper Stephen Chung, Isaac Tyan
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a Custom code object type that will contain one or more Karel instructions
 * 
 * A custom code is small Karel program, it can contain any of Karel's basic instructions 
 * and conditional statements. 
 * This object contains a name which is the title of this "mini" program and an arraylist which 
 * contains all the code/objects that make up the body of the "mini" program.

 */
public class CustomCode extends Code implements Serializable {
	
	String name;
	ArrayList<Code> body;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		return sb.toString();
	}
	/**
	 * This is a constructor to initialize all fields of a Custom code object.
	 * @param name a string to represent the name of the custom code
	 * @param body an arraylist of code objects, containing the body of the custom code
	 */
	public CustomCode(String name, ArrayList<Code> body){
		super();
		if (body == null) {
			throw new IllegalArgumentException("Code body cannot be null.");
		}
		for(Code code: body) {
			if(code == null) {
				throw new IllegalArgumentException("Code object cannot be null.");
			}
		}
		this.name = name;
		this.body = body;		
	}
	
	/**
	 * A method to retrieve the name of the custom code
	 * @return a string which represents the name of the custom code
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * A method to retrieve the body of the custom code
	 * @return an arraylist of code objects that represent the body of this custom code
	 */
	public ArrayList<Code> getCodeBody(){
		return this.body;
	}
	
	/**
	 * A method to change the name of the users custom code
	 * @param name the new name the user wishes to change the custom code name to
	 * @assume the new name is different from the old name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * A method to change the body of the cutom code
	 * <p>
	 * After the user edits a custom code, a new list is created and this list is used
	 * to replace the old body arraylist
	 * </p>
	 * @param body an arraylist of code objects representing a new body for this custom code.
	 * @assume changes have been made to the body list currently contained in the custom code
	 */
	public void setCodeBody(ArrayList<Code> body){
		this.body = body;
	}

}
