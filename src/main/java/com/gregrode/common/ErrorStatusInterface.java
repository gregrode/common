package com.gregrode.common;

/**
 * @author Greg Dennis
 *
 */
public interface ErrorStatusInterface {
	/**
	 * @return the errorCode
	 */
	long getCode();

	/**
	 * @return the errorMessage
	 */
	String getMessage();

}
