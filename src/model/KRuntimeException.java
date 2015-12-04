/**
 * @mainAuthor Stephen Chung
 */

package model;

/**
 * Consists of a set of exceptions which are thrown to handle errors related to the logic of the user's program.
 * These include runtime errors such as if Karel were instructed to Move even though it is facing a wall. *
 */
public class KRuntimeException extends RuntimeException {

	public KRuntimeException(String s) {
		super(s);
	}
}