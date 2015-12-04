/**
 * @mainAuthor Stephen Chung
 */

package control;

import java.io.Serializable;

import model.Code.Proposition;

/**
 * This is a branch instruction to handle conditional statements. At run-time,
 * evaluate the proposition. If true, do not branch. If false, then move to
 * instruction x + offset.
 */
public class BranchOnFalse extends Executable implements Serializable {

	final Proposition prop;
	final int offset;
	
	BranchOnFalse(int lineNumber, Proposition prop, int offset) {
		super(lineNumber);
		this.prop = prop;
		this.offset = offset;
	}
	
}
