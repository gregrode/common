package com.gregrode.common.field;

import java.sql.Types;

public interface Field<T> {

	/**
	 * @return the value.
	 */
	T value();

	/**
	 * @return the value as string.
	 */
	default String getValueAsString() {
		final Object value = value();
		return (value == null) ? "" : String.valueOf(value);
	}

	/**
	 * @return the value as a boolean.
	 */
	default boolean getValueAsBoolean() {
		final Object value = value();
		return (value == null) ? false : Boolean.valueOf(value.toString());
	}

	/**
	 * @return the value as an integer.
	 */
	default Integer getValueAsInteger() {
		try {
			final Object value = value();
			return (value == null) ? 0 : Integer.valueOf(value.toString());
		} catch (Throwable t) {
			return 0;
		}
	}

	/**
	 * @return the previous value
	 */
	T previousValue();

	/**
	 * @return the value as string.
	 */
	default String getPreviousValueAsString() {
		final Object value = previousValue();
		return (value == null) ? "" : String.valueOf(value);
	}

	/**
	 * @return the value as a boolean.
	 */
	default boolean getPreviousValueAsBoolean() {
		final Object value = previousValue();
		return (value == null) ? false : Boolean.valueOf(value.toString());
	}

	/**
	 * @return the value as an integer.
	 */
	default Integer getPreviousValueAsInteger() {
		try {
			final Object value = previousValue();
			return (value == null) ? 0 : Integer.valueOf(value.toString());
		} catch (Throwable t) {
			return 0;
		}
	}

	/**
	 * @return the defaultValue
	 */
	Object defaultValue();
	
	/**
	 * @return the field type
	 */
	int type();

	/**
	 * @return the field name
	 */
	String name();

	/**
	 * @return the readOnly
	 */
	boolean readOnly();

	/**
	 * @return the required
	 */
	boolean required();

	/**
	 * @return the max
	 */
	Number max();

	
	/**
	 * @return the min
	 */
	 Number min();
	/**
	 *
	 * @return a boolean that indicates if the current value have changed.
	 */
	default boolean isDirty() {
		final T value = value();
		final T prev = previousValue();
		return value == null ? prev != null : !value.equals(prev);
	}

	/**
	 * @return a boolean that indicates if the data type is numeric.
	 */
	default boolean isNumeric() {
		return isNumeric(type());
	}

	/**
	 * @return a boolean that indicates if the data type is time based.
	 */
	default boolean isTimeBased() {
		switch (type()) {
			case Types.TIME:
			case Types.TIME_WITH_TIMEZONE:
			case Types.TIMESTAMP:
			case Types.TIMESTAMP_WITH_TIMEZONE:
			case Types.DATE:return true;
			default: return false;
		}
	}

	/**
	 * @return a boolean that indicates if the data type is numeric.
	 */
	static boolean isNumeric(final int type) {
		switch (type) {
			case Types.BIGINT:
			case Types.SMALLINT:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.INTEGER:
			case Types.NUMERIC:
			case Types.TINYINT:return true;
			default: return false;
		}
	}
}
