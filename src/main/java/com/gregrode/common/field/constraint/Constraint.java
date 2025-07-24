package com.gregrode.common.field.constraint;

import java.util.Objects;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.field.Field;

/**
 * The <code>Constraint</code> interface encapsulate different type of
 * constraints.
 * 
 * @author Gregroy Dennis
 * 
 */
public interface Constraint {

	/**
	 * Validates the internal @{link Field} object based on the attributes.
	 * 
	 * @return boolean that indicates if the @{link Field} object
	 */
	default ConstraintResult validate() {
		final Field<Object> field = getField();
		final String displayName = field.name();
		// Check if the value is allow to be null.
		if (field.value() == null) {
			if (field.required() == false) return new ConstraintResult(true, "success");
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
		}
		return new ConstraintResult(true, "success");
	}

	/**
	 * @return the field
	 */
	Field<Object> getField();

	/**
	 * @param errorStatus
	 *            The ErrorStatus object that contains the error.
	 * @param args
	 * @return String
	 */
	String getErrorMessage(final ErrorStatus errorStatus, final String... args);

	static boolean isNull(Object value) {
		return (value == null) || Objects.equals("null", String.valueOf(value));
	}
}
