package model;

import java.util.ArrayList;

/**
 * This is a LoopCode object type for the loop mechanism.
 * <p>
 * This allows users to repeat a set of actions multiple times as long
 * as a test condition holds without writing replicated code.
 * </p>
 * @author Kodename team
 * @see java.util.ArrayList
 *
 */
public class LoopCode extends Code {

	String condition;
	ArrayList<Code> body;
	int counter;
	
	/* No setter for this field. It is managed internally. */
	boolean evalCondition;
	
	/**
	 * Constructor for an object representing a while loop in Karel.
	 * @param condition the loop condition
	 * @param body the loop body
	 */
	public LoopCode(String condition, ArrayList<Code> body) {

		if(condition==null)
			throw new IllegalArgumentException("Condition for loop cannot be empty");

		if(body==null)
			throw new IllegalArgumentException("Body for loop cannot be empty");
		if(body.isEmpty())
			throw new IllegalArgumentException("Body for loop cannot be empty");

		this.evalCondition = true;
		this.condition = condition;
		this.body = body;
		this.counter = -1;
	}
	
	/**
	 * Constructs an object representing a loop that runs n times.
	 * @param n the number of times the loop body will be executed
	 * @param body the loop body
	 */
	public LoopCode(int n, ArrayList<Code> body) {
		if(n < 1)
			throw new IllegalArgumentException("The number of times to execute the loop body cannot be less than one");
		if(body==null)
			throw new IllegalArgumentException("Body for loop cannot be empty");
		if(body.isEmpty())
			throw new IllegalArgumentException("Body for loop cannot be empty");
		this.evalCondition = false;
		this.counter = n;
		this.body = body;
		this.condition = null;
	}
	
	/**
	 * Get the loop condition.
	 * @return the loop condition if it exists, else null
	 */
	public String getCondition() {
		return condition;
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
	 * Set the loop condition.
	 * @param c loop condition
	 */
	public void setCondition(String c) {
		if(c==null)
			throw new IllegalArgumentException("Condition for loop cannot be empty");
		evalCondition = true;
		condition = c;
	}
	
	/**
	 * Set the loop body.
	 * @param b loop body
	 */
	public void setBody(ArrayList<Code> b) {
		if(b==null)
			throw new IllegalArgumentException("Body for loop cannot be empty");
		if(b.isEmpty())
			throw new IllegalArgumentException("Body for loop cannot be empty");
		body = b;
	}
	
	/**
	 * Set the number of times to execute the loop body.
	 * @param c number of times to execute loop body
	 */
	public void setCounter(int c) {
		if(c < 1)
			throw new IllegalArgumentException("The number of times to execute the loop body cannot be less than one");
		evalCondition = false;
		counter = c;
	}
}
