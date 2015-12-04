package model;

/**
 * This is a Basic code object type for a single Karel instruction
 * <p>
 * The basic code object is a type for one of Kodename Karel's
 * five basic instructions; move, turn left, turn right, pick up beeper, put down beeper.
 * It contains a string which represents one of the basic Karel instructions.
 * </p>
 * @author Kodename team
 *
 */
public class BasicCode extends Code {

	String instruction;
	
	/**
	 * This is a constructor for the BasicCode object
	 * @param instruction a string that represents a single basic Karel instruction.
	 */
	public BasicCode(String instruction) {
		super();
		this.instruction = instruction;
	}
	
	/**
	 * This method is used to retrieve the instruction string stored in this BasicCode.
	 * @return a string that represents a single basic Karel instruction.
	 */
	public String getInstruction(){
		return this.instruction;
	}
}
