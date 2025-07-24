package com.gregrode.common.field.constraint.impl;

import static com.gregrode.common.util.Util.isEmpty;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.gregrode.common.ErrorStatus;
import com.gregrode.common.field.Field;
import com.gregrode.common.field.constraint.Constraint;

/**
 * This class should be used to validate the field based on the given constraint
 * type in conjunction with the {@link Constraint} interface.
 * 
 * @author Gregroy Dennis
 * 
 * @see Constraint
 */
public abstract class FieldConstraint implements Constraint {

	private static final Logger log = Logger.getLogger(FieldConstraint.class);
	private static final String DEFAULT_BUNDLE_NAME = "com.gregrode.common.resources.errormessages";
	private static final String CUSTOM_BUNDLE_NAME = "messages";
	protected static final String SUCCESS = "success";

	private static ResourceBundle bundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME);
	private ResourceBundle customBundle =null;
	private final Field<Object> field;

	public FieldConstraint(final Field<Object> field) {
		setCustomBundle(CUSTOM_BUNDLE_NAME);
		this.field = field;
	}
	
	/**
	 * @param customBundleName
	 */
	public void setCustomBundle(final String customBundleName) {
		try {
			// Note, the ability to change the name of the bundle in not fully
			// exposed, so if you want to change some of the messages, create a
			// "message.properties" file and override the messages.
			customBundle = ResourceBundle.getBundle(customBundleName);
		} catch (final MissingResourceException mre) {
			log.trace("User declined to create custom message bundle...");
		}
	}

	@Override
	public String getErrorMessage(final ErrorStatus errorStatus, final String... args) {

		String errorMessage = bundle.getString(errorStatus.toString());
		if (customBundle != null) {
			try {
				errorMessage = customBundle.getString(errorStatus.toString());
			} catch (final MissingResourceException mre) {
				log.debug(mre);
			}
		}

		if (!isEmpty(args)) {
			for (int index = 0; index < args.length; index++) {
				final String pattern = "{" + index + "}";
				errorMessage = errorMessage.replaceAll(Pattern.quote(pattern), args[index]);
			}
		}
		log.debug(errorMessage);
		return errorMessage;
	}
	
	@Override
	public Field<Object> getField(){
		return field;
	}
}
