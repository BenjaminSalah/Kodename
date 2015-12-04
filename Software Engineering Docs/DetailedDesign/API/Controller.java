package control;

import java.io.Serializable;
import java.util.ArrayList;

import model.Code;
import model.Karel;

/**
 * Controller acts as a communication medium between the code that the user has created for Karel and Karel itself.
 * The primary function of Controller is to perform all manipulations necessary in order to the user's code to be 
 * understood by Karel. Controller will take a list of code, processes it, and will actually tell Karel to execute
 * a specific instruction. Therefore, Controller is, in a sense, controlling Karel in that it explicitly tells Karel
 * which function to call that corresponds to what the user has instructed. 
 * @author Kodename team
 * @see java.util.ArrayList
 *
 */
public class Controller implements Serializable {
	
	ArrayList<Code> codeList;
	Karel karel;
	
	/**
	 * Constructor for an object representing a Controller.
	 * @param codeList the CodeBlocks generated from the user
	 * @param karel the karel object
	 */
	public Controller(ArrayList<Code> codeList, Karel karel){
		this.codeList = codeList;
		this.karel = karel;
	}
	
	/**
	 * Obtains the next executable instruction from the list of Code.
	 * @param list codeBlocks generated from the user
	 * @return the next executable instruction if it exists, else null
	 */
	private String processCode(ArrayList<Code> list){
		return null;
	}
	
	/**
	 * Calls Karel to act on the given instruction
	 * @param s the executable instruction
	 */
	private void callKarel(String s){
	}
	
	/**
	 * Iterates through the codeList
	 * @return false if reached the end of the list
	 */
	public boolean execute(){
		return false;
	}
	
	public boolean executeOne() {
		throw new RuntimeException("Not implemented.");
	}

}
