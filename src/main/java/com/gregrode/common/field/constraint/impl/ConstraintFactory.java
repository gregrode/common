package com.gregrode.common.field.constraint.impl;

import java.sql.Types;
import java.util.Collection;

import com.gregrode.common.field.Field;
import com.gregrode.common.field.constraint.Constraint;
import com.gregrode.common.field.constraint.ConstraintResult;

public final class ConstraintFactory {

	private ConstraintFactory() {
	}

	public static Constraint getInstance(final Field<Object> field) {
		switch (field.type()) {
			case Types.DATE: return new DateConstraint(field);
			case Types.TIME:
			case Types.TIMESTAMP:
			case Types.TIMESTAMP_WITH_TIMEZONE: return new TimestampConstraint(field);
			case Types.BLOB:
			case Types.CLOB: 
			case 10002: return new FileConstraint(field);
			case Types.INTEGER:
			case Types.SMALLINT:
			case Types.BIGINT:
			case Types.LONGNVARCHAR:
			case Types.LONGVARBINARY:
			case Types.LONGVARCHAR:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.NUMERIC: return new BigDecimalConstraint(field);
			default: return new StringConstraint(field);
		}
	}

	public static ConstraintResult validate(final Field<Object> field) {
		Constraint constraint = getInstance(field);
		return constraint.validate();
	}



	/**
	 * validate the value contained in the each field
	 *
	 * @param fields
	 *            the list of fields
	 * @return boolean
	 */
	public static boolean validate(final Collection<? extends Field<Object>> fields) {
		for (final Field<Object> field : fields) {
			if (!validate(field).success()) return false;
		}
		return true;
	}
}
