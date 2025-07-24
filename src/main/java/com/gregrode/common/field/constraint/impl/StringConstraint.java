package com.gregrode.common.field.constraint.impl;

import java.util.regex.Pattern;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.ErrorStatusException;
import com.gregrode.common.field.Field;
import com.gregrode.common.field.constraint.ConstraintResult;
import com.gregrode.common.util.StringUtil;

/**
 * The <code>StringConstraint</code> class contains method that construct and
 * validate a <string-constraint>
 * 
 * <p>
 * To validate a value, the setValue(String) and setConstraint(String) must both
 * be set before calling the validate() method.
 * </p>
 * 
 * @author Gregroy Dennis<br>
 *         Last updated: February 7, 2010<br>
 *         &copy; Gregroy Dennis 2010
 * 
 */
public class StringConstraint extends FieldConstraint {

	private Pattern pattern = null;

	/**
	 * Default Constructor
	 */
	public StringConstraint(final Field<Object> field) {
		super(field);
	}

	@Override
	public ConstraintResult validate() throws ErrorStatusException {
		final Field<Object> field = getField();
		final String displayName = field.name();

		try {
			// Check if the value is allowed to be null or empty.
			if (field.value() == null || field.getValueAsString().isEmpty()) {
				if (field.required() == false) return new ConstraintResult(true, SUCCESS);
				return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
			}

			final String value = field.getValueAsString();
			// Check if the value meets the minimum length requirement (if there
			// are any).
			if ((field.min() != null) && (value.length() < field.min().intValue())) {
				return new ConstraintResult(false, getErrorMessage(ErrorStatus.STRING_CONSTRAINT_VALUE_TOO_SMALL,
						displayName, String.valueOf(field.min().intValue())));
			}

			// Check if the value meets the maximum length requirement (if there
			// are any).
			if ((field.max() != null) && (value.length() > field.max().intValue())) {
				return new ConstraintResult(false, getErrorMessage(ErrorStatus.STRING_CONSTRAINT_VALUE_TOO_LARGE,
						displayName, String.valueOf(field.max().intValue())));
			}

			// Check if the given value matches the pattern. (if there are any).
			if ((pattern != null) && StringUtil.matchRegex(pattern, value) == false) {
				return new ConstraintResult(false, getErrorMessage(ErrorStatus.STRING_CONSTRAINT_INVALID, displayName));
			}
			return new ConstraintResult(true, SUCCESS);
		} catch (Exception e) {
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.STRING_CONSTRAINT_INVALID, displayName));
		}
	}
}
