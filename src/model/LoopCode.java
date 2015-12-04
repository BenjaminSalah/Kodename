/**
 * @mainAuthor Benjamin Salah
 * @helper Stephen Chung, Isaac Tyan
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a LoopCode object type for the loop mechanism.
 * This allows users to repeat a set of actions multiple times. Each loop runs
 * a fixed number of times. Checking whether a condition to continue a loop
 * exists is NOT supported.
 * 
 * @author Kodename team
 */
public class LoopCode extends Code implements Serializable {

	ArrayList<Code> body;
	int counter;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Loop: " + String.valueOf(counter) + "times.\n");
		for(Code code: body) {
			sb.append(code.toString() + "\n");
		}
		//return sb.toString();
		return "For-End For";
	}

	/**
	 * Constructs an object representing a loop that runs n times.
	 * @param n the number of times the loop body will be executed
	 * @param body the loop body
	 */
	public LoopCode(int n, ArrayList<Code> body) {
		this.counter = n;
		this.body = body;
	}

	/**
	 * Get the loop body.
	 * @return the loop body
	 */
	public ArrayList<Code> getBody() {
		return body;
	}
	
	/**
	 * Get the number of times the loop runs.
	 * @return the number of times the loop runs, or -1 if the loop
	 * condition is checked instead of there being a fixed number
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Set the loop body.
	 * @param b loop body
	 */
	public void setBody(ArrayList<Code> b) {
		body = b;
	}
	
	/**
	 * Set the number of times to execute the loop body.
	 * @param c number of times to execute loop body
	 */
	public void setCounter(int c) {
		counter = c;
	}
}
