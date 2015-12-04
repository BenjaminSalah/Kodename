/**
 * @mainAuthor Stephen Chung
 */

package control;

import java.io.Serializable;

class Jump extends Executable implements Serializable {

	/**
	 * If this jump instruction is instruction n, the next instruction
	 * to execute is instruction n + offset.
	 */
	final int offset;
	
	Jump(int line, int offset) {
		super(line);
		this.offset = offset;
	}

}
