package com.gregrode.common.field.constraint.impl;

import java.sql.Timestamp;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.field.Field;
import com.gregrode.common.field.constraint.ConstraintResult;

/**
 * 
 * The <code>TimestampConstraint</code> class validates if the given
 * {@link Field} object is a valid {@link Timestamp}.
 * 
 * @author Gregroy Dennis
 * 
 */
public class TimestampConstraint extends FieldConstraint {

	public TimestampConstraint(Field<Object> field) {
		super(field);
	}

	@Override
	public ConstraintResult validate() {
		final Field<Object> field = getField();
		final String displayName = field.name();
		try {
			// Check if the value is allow to be null.
			if (field.value() == null || field.getValueAsString().isEmpty()) {
				if (field.required() == false)
					return new ConstraintResult(true, SUCCESS);
				return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
			}
			return new ConstraintResult(true, SUCCESS);
		} catch (Exception e) {
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
		}
	}

	/**
	 * @return Check if the {@link Field#value()} is after {@link Field#max()}.
	 */
	public ConstraintResult isAfter() {
		final Field<Object> field = getField();
		final String displayName = field.name();
		try {
			ConstraintResult result = validate();
			if (!result.success()) {
				return result;
			}

			final Timestamp value = Timestamp.valueOf(field.getValueAsString());

			// Check if the given date has is before the specified start date.
			if (field.min() != null) {
				Timestamp startTime = new Timestamp(field.min().longValue());
				if (value.before(startTime)) {
					return new ConstraintResult(false,
							getErrorMessage(ErrorStatus.DATE_CONSTRAINT_BEFORE_START_DATE, displayName));
				}
			}
			return result;
		} catch (Exception e) {
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
		}
	}

	/**
	 * @return Check if the {@link Field#value()} is before {@link Field#min()}
	 */
	public ConstraintResult isBefore() {
		final Field<Object> field = getField();
		final String displayName = field.name();
		try {
			ConstraintResult result = validate();
			if (!result.success()) {
				return result;
			}
			final Timestamp value = Timestamp.valueOf(field.getValueAsString());

			// Check if the given date has is after the specified end date.
			if ((field.max() != null)) {
				Timestamp endTime = new Timestamp(field.max().longValue());
				if (value.after(endTime)) {
					return new ConstraintResult(false,
							getErrorMessage(ErrorStatus.DATE_CONSTRAINT_AFTER_END_DATE, displayName));
				}
			}
			return result;
		} catch (Exception e) {
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
		}
	}
}
