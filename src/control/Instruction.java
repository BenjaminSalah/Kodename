/**
 * @mainAuthor Stephen Chung
 */

package control;

import java.io.Serializable;

import model.BasicCode;
import model.Code.Action;

class Instruction extends Executable implements Serializable {

	final Action action;
	
	Instruction(int line, Action action) {
		super(line);
		this.action = action;
	}
	
	Instruction(int line, BasicCode code) {
		super(line);
		this.action = code.getInstruction();
	}
}
