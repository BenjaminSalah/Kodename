/**
 * @mainAuthor Benjamin Salah
 * @helper Stephen Chung, Isaac Tyan 
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.Code.Proposition;;

/**
 * This is a IfElse code object type for if statement blocks that include the condition,
 * a list of code to execute if the condition evaluates to true, and a list of code to
 * execute if the condition evaluates to false.
 * @author Kodename team
 * @see java.util.ArrayList
 *
 */
public class IfElseCode extends Code implements Serializable {

	Proposition prop;
	ArrayList<Code> body1;
	ArrayList<Code> body2;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("If ");
		switch(prop) {
		case IS_FACING_EAST:
			sb.append("Facing East");
			break;
		case IS_FACING_NORTH:
			sb.append("Facing North");
			break;
		case IS_FACING_SOUTH:
			sb.append("Facing South");
			break;
		case IS_FACING_WEST:
			sb.append("Facing West");
			break;
		case IS_FRONT_CLEAR:
			sb.append("Front Clear");
			break;
		case IS_LEFT_CLEAR:
			sb.append("Left Clear");
			break;
		case IS_RIGHT_CLEAR:
			sb.append("Right Clear");
			break;
		case NEXT_TO_BEEPER:
			sb.append("Next to Beeper");
			break;
		default:
			break;
		}
		sb.append("\nThen\n");
		for(Code code: body1) {
			sb.append("    " + code.toString() + "\n");
		}
		sb.append("Else\n");
		for(Code code: body2) {
			sb.append("    " + code.toString());
		}
		//return sb.toString();
		return "If-Else";
	}
	
	/**
	 * Constructor for an object representing an if else in Karel.
	 * @param prop the if condition
	 * @param body1 the if body
	 * @param body2 the else body
	 */
	public IfElseCode(Proposition prop, ArrayList<Code> body1, ArrayList<Code> body2) {
		if(body1 == null){
			body1 = new ArrayList<Code>();
		}
		if(body2 == null){
			body2 = new ArrayList<Code>();
		}
		this.prop = prop;
		this.body1 = body1;
		this.body2 = body2;
	}
	/**
	 * Get the if condition.
	 * @return the if condition if it exists, else null
	 */
	public Proposition getCondition() {
		return prop;
	}
	
	/**
	 * Get the if body.
	 * @return the if body
	 */
	public ArrayList<Code> getBody1() {
		return body1;
	}
	
	/**
	 * Get the else body.
	 * @return the else body
	 */
	public ArrayList<Code> getBody2() {
		return body2;
	}
	
	/**
	 * Set the if condition.
	 * @param c if condition
	 */
	public void setCondition(Proposition c) {
		prop = c;
	}
	
	/**
	 * Set the if body.
	 * @param b if body
	 */
	public void setBody1(ArrayList<Code> b) {
		body1 = b;
	}
	
	/**
	 * Set the else body.
	 * @param b else body
	 */
	public void setBody2(ArrayList<Code> b) {
		body2 = b;
	}
}

