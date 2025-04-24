package edu.tus.winemanager.exception;
/**
 * Exception used to indicate some error occurred when processing a 
 * request in a Movie Store.
 */
public class WineException extends Exception {

	/**
	 * Create a new exception with an error message.
	 * @param message a String explaining the error which occurred.
	 */
	public WineException(String message) {
		super(message);
	}

}
