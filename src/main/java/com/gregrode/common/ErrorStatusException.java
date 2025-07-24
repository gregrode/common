package com.gregrode.common;

/**
 * This exception class should be used throughout your project. It provides the
 * means to log and display error messages. For now, this is a checked
 * exception, however, I may change it to unchecked (RuntimeException), so
 * handling of this exception does not need to happen whenever it is thrown.
 * 
 * @see <a href="http://c2.com/cgi/wiki?TheProblemWithCheckedExceptions">Checked
 *      Exception Are Evil</a>
 * 
 * @author Gregroy Dennis
 * 
 */
public class ErrorStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorStatusInterface errorStatus;

	/**
	 * Default constructor
	 */
	public ErrorStatusException() {
		super(ErrorStatus.STATUS_FAILURE.getMessage());
		this.errorStatus = ErrorStatus.STATUS_FAILURE;
	}

	/**
	 * @param message
	 *            to the message to set
	 */
	public ErrorStatusException(final String message) {
		super(message);
		this.errorStatus = ErrorStatus.STATUS_FAILURE;
	}

	/**
	 * @param errorStatus
	 *            the errorStatus to set
	 */
	public ErrorStatusException(final ErrorStatusInterface errorStatus) {
		super(errorStatus.getMessage());
		this.errorStatus = errorStatus;
	}

	/**
	 * @param throwable
	 *            the throwable to set
	 */
	public ErrorStatusException(final Throwable throwable) {
		super(throwable);
		if (throwable instanceof ErrorStatusException) {
			this.errorStatus = ((ErrorStatusException) throwable).getErrorStatus();
		} else {
			this.errorStatus = ErrorStatus.STATUS_FAILURE;
		}
	}

	/**
	 * @param message
	 *            the message to set
	 * @param throwable
	 *            the throwable to set
	 */
	public ErrorStatusException(final String message, final Throwable throwable) {
		super(message, throwable);
		if (throwable instanceof ErrorStatusException) {
			this.errorStatus = ((ErrorStatusException) throwable).getErrorStatus();
		} else {
			this.errorStatus = ErrorStatus.STATUS_FAILURE;
		}
	}

	/**
	 * @param message
	 * @param errorStatus
	 */
	public ErrorStatusException(final String message, final ErrorStatusInterface errorStatus) {
		super(message);
		this.errorStatus = errorStatus;
	}

	/**
	 * @param errorStatus
	 *            the errorStatus to set
	 * @param throwable
	 *            the throwable to set
	 */
	public ErrorStatusException(final ErrorStatusInterface errorStatus, final Throwable throwable) {
		super(errorStatus.getMessage(), throwable);
		this.errorStatus = errorStatus;
	}

	/**
	 * @param errorStatus
	 *            the errorStatus to set
	 * @param message
	 *            the message to set
	 * @param throwable
	 *            the throwable to set
	 */
	public ErrorStatusException(final String message, final ErrorStatusInterface errorStatus,
	        final Throwable throwable) {
		super(message, throwable);
		this.errorStatus = errorStatus;
	}

	/**
	 * @return the errorStatus
	 */
	public ErrorStatusInterface getErrorStatus() {
		return this.errorStatus;
	}
}
