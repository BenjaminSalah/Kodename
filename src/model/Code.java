/**
 * @mainAuthor Stephen Chung
 */

package model;

import java.io.Serializable;
/**
 * This is a Code object type that will allow for proper hierarchy of the code
 * types. It also ensures that Loop, IfElse, Basic, and Custom code objects are
 * all equal to each other to prevent ambiguity.
 * @author Kodename team
 * @see java.util.ArrayList
 *
 */
public abstract class Code implements Serializable {
	
	public enum Action { MOVE, TURN_LEFT, TURN_RIGHT, PICK_UP, PUT_DOWN }
	public enum Proposition { IS_FRONT_CLEAR, IS_LEFT_CLEAR, IS_RIGHT_CLEAR, IS_FACING_NORTH, IS_FACING_SOUTH, IS_FACING_EAST, IS_FACING_WEST, NEXT_TO_BEEPER }
	
}
