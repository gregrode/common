package com.gregrode.common.field.constraint.impl;

import java.sql.Date;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.field.Field;
import com.gregrode.common.field.constraint.ConstraintResult;

/**
 * The <code>DateConstraint</code> class is used to validate a date string based
 * on the given constraint.
 * 
 * <p>
 * To validate a value, the setValue(String) and setConstraint(String) must both
 * be set before calling the validate() method.
 * </p>
 * 
 * @author Gregroy Dennis<br>
 *         Last update: April 21, 2010<br>
 *         &copy; Gregroy Dennis 2010
 * 
 */
public class DateConstraint extends FieldConstraint {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

	public DateConstraint(Field<Object> field) {
		super(field);
	}

	@Override
	public ConstraintResult validate() {
		final Field<Object> field = getField();
		final String displayName = field.name();
		try {

			// Check if the value is allowed to be null or empty.
			if (field.value() == null) {
				if (field.required() == false)
					return new ConstraintResult(true, SUCCESS);
				return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
			}

			final Date value = Date.valueOf(field.getValueAsString());
			// Check if the given date has is before the specified start date.
			if (field.min() != null) {
				Date startTime = new Date(field.min().longValue());
				if (value.before(startTime)) {
					return new ConstraintResult(false,
							getErrorMessage(ErrorStatus.DATE_CONSTRAINT_BEFORE_START_DATE, displayName));
				}
			}

			// Check if the given date has is after the specified end date.
			if ((field.max() != null)) {
				Date endTime = new Date(field.max().longValue());
				if (value.after(endTime)) {
					return new ConstraintResult(false,
							getErrorMessage(ErrorStatus.DATE_CONSTRAINT_AFTER_END_DATE, displayName));
				}
			}
			return new ConstraintResult(true, SUCCESS);
		} catch (Exception e) {
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
		}
	}
}
