/**
 * @mainAuthor Stephen Chung
 */

package control;

import java.io.Serializable;

class Executable implements Serializable {

	int lineNumber;
	
	Executable(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
}
