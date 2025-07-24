package com.gregrode.common;

import java.util.stream.Stream;

/**
 * The <code>ErrorStatus</code> ENUM is used to display errors to the end-user
 * as well as for logging purposes. Note, when adding a new status, you should
 * also enter the corresponding value in the "errormessages.properties" file,
 * which is used for internationalization.
 *
 *
 */
@ClassPreamble(author = "Gregroy Dennis", lastModified = "May 12, 2022",  copyright = "2022")
public enum ErrorStatus implements ErrorStatusInterface {

	STATUS_SUCCESS(0, "Success"),
	STATUS_FAILURE(1, "Failure."),

	STATUS_INVALID_ARGS(50, "Invalid Argument."),
	STATUS_STALE_DATA(51, "The data was recently changed."),

	STATUS_DATABASE_READ_ERROR(100, "Unable to read from the database."),
	STATUS_DATABASE_CONNECTION_NOT_CLOSED(116, "Unable to close the connection to the database."),
	STATUS_DATABASE_ROLL_BACK_FAILURE(117, "Unable to rollback connection"),

	STATUS_NO_CACHE(400, "Object not found in cache."),

	XML_PARSING_FAILURE(500, "Unable to parse the given xml string"),
	XML_ATTRIBUTE_MISSING(510, "A Required XML attribute is missing."),
	XML_ATTRIBUTE_MALFORMED(520, "A XML attribute is malformed."),

	FILE_NOT_FOUND(600, "Unable to find the specified file"),

	CONSTRAINT_REQUIRED(1010, "The value is required"),
	CONSTRAINT_VALUE_TOO_SMALL(1020, "The value is too small"),
	CONSTRAINT_VALUE_TOO_LARGE(1030, "The value is too large"),

	INTEGER_CONSTRAINT_NON_NUMERIC(1200, "The value must be numeric"),

	STRING_CONSTRAINT_INVALID(1300, "The value is invalid"),
	STRING_CONSTRAINT_VALUE_TOO_SMALL(1305, "The value is too small"),
	STRING_CONSTRAINT_VALUE_TOO_LARGE(1310, "The value is too large"),

	DATE_CONSTRAINT_INVALID(1400, "The date is invalid"),
	DATE_CONSTRAINT_BEFORE_START_DATE(1405, "The supplied date is before the start date."),
	DATE_CONSTRAINT_AFTER_END_DATE(1410, "The supplied date is beyond the given end date."),

	STATUS_CONNECTION_SUCCESSFULLY_CLOSED(3000, "The Connection was successfully closed."),
	MATH_ERROR_CANNOT_DIVIDE_BY_ZERO(4000, "Cannot divide by zero");

	private long code;
	private String message;

	/**
	 * @param code
	 *            The code associated with the ErrorStatus.
	 * @param message
	 *            The message should be used for logging purposes only.
	 */
	ErrorStatus(final long code, final String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Find an ErrorStatus by the specified error code.
	 *
	 * @param errorCode
	 *            The errorCode to search for.
	 * @return ErrorStatus
	 */
	public static final ErrorStatus findErrorStatusByCode(final long errorCode) {
		return Stream.of(ErrorStatus.values()).filter(e -> errorCode == e.code).findFirst().get();
	}

	/**
	 * @return the code
	 */
	@Override
	public long getCode() {
		return code;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
