package it.altran.springmvc.myApp.locator;

public class ServiceLocatorException  extends Exception {
	/**
	 * 
	 */
	public ServiceLocatorException() {
	
		super();
	}

	/**
	 * @param message
	 */
	public ServiceLocatorException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceLocatorException(Throwable cause) {
	
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceLocatorException(String message, Throwable cause) {
	
		super(message, cause);
	}

}
