/**
 * @mainAuthor Benjamin Salah
 * @helper Stephen Chung, Isaac Tyan
 */

package model;

import java.io.Serializable;


/**
 * This is a Basic code object type for a single Karel instruction
 * 
 * The basic code object is a type for one of Kodename Karel's
 * five basic instructions; move, turn left, turn right, pick up beeper, put down beeper
 * it contains a string which represents one of the basic Karel instructions.
 */
public class BasicCode extends Code implements Serializable {

	final Action instruction;
	
	/**
	 * This is a constructor for the BasicCode object
	 * @param instruction a string that represents a single basic Karel instruction.
	 */
	public BasicCode(Action instruction) {
		super();
		this.instruction = instruction;
	}
	
	/**
	 * This method is used to retrieve the instruction string stored in this BasicCode.
	 * @return a string that represents a single basic Karel instruction.
	 */
	public Action getInstruction(){
		return this.instruction;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append("BasicCode: " );
		switch (instruction) {
		case MOVE:
			sb.append("Move");
			break;
		case PICK_UP:
			sb.append("Pick Up");
			break;
		case PUT_DOWN:
			sb.append("Put Down");
			break;
		case TURN_LEFT:
			sb.append("Turn Left");
			break;
		case TURN_RIGHT:
			sb.append("Turn Right");
			break;
		default:
			break;
		
		}
		return sb.toString();
	}
}
