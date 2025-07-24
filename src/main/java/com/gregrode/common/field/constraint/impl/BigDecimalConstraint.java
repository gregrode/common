package com.gregrode.common.field.constraint.impl;

import java.math.BigDecimal;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.field.Field;
import com.gregrode.common.field.constraint.ConstraintResult;

/**
 * The <code>BigDecimalConstraint</code> class allows its caller to validate numeric value.
 * 
 * 
 * @author Gregroy Dennis
 * 
 */
public class BigDecimalConstraint extends FieldConstraint {

	public BigDecimalConstraint(final  Field<Object> field) {
		super(field);
	}
	
	@Override
	public ConstraintResult validate() {
		final Field<Object> field = getField();
		final String displayName = field.name();
		
		// Check if the value is allowed to be null or empty.
		if (field.value() == null || String.valueOf(field.value()).isEmpty()) {
			if (field.required() == false) return new ConstraintResult(true, SUCCESS);
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
		}
		
		final BigDecimal value = new BigDecimal(field.getValueAsString());
		// Check if the value is less than the minimum value allowed.
		if ((field.min() != null) && (value.doubleValue() < field.min().doubleValue())) {
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_VALUE_TOO_SMALL, displayName));
		}

		// Check if the value is greater than the maximum value allowed.
		if ((field.max() != null) && (value.doubleValue() > field.max().doubleValue())) {
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_VALUE_TOO_LARGE, displayName));
		}
		return new ConstraintResult(true, SUCCESS);
	}
}
