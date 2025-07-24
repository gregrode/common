package com.gregrode.common.field.constraint.impl;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.field.Field;
import com.gregrode.common.field.constraint.ConstraintResult;

/**
 * The <code>FileConstraint</code> class allows its caller to validate file based values.
 * 
 * 
 * @author Gregroy Dennis
 * 
 */
public class FileConstraint extends FieldConstraint {

	public FileConstraint(final Field<Object> field) {
		super(field);
	}
	
	@Override
	public ConstraintResult validate() {
		final  Field<Object> field = getField();
		final String displayName = field.name();
		
		// Check if the value is allowed to be null or empty.
		if (field.value() == null || field.getValueAsString().isEmpty()) {
			if (field.required() == false) return new ConstraintResult(true, SUCCESS);
			return new ConstraintResult(false, getErrorMessage(ErrorStatus.CONSTRAINT_REQUIRED, displayName));
		}
		return new ConstraintResult(true, SUCCESS);
	}
}
