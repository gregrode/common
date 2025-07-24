package com.gregrode.common.util;

import org.apache.log4j.Logger;
import org.jasypt.util.text.StrongTextEncryptor;

/**
 * @author Greg Dennis
 *
 */
public class Encryptor {

	private static final Logger log = Logger.getLogger(Encryptor.class);
	private final static StrongTextEncryptor textEncryptor = Util.build(new StrongTextEncryptor(), encrypt -> {
		try {
			encrypt.setPassword(Util.getMACAddress());
		} catch (final Exception ex) {
			encrypt.setPassword("D@t@b@$3________________F1nd3r");
			log.debug(ex);
		}
	});

	private Encryptor() {
	}

	public static String encrypt(final String message) {
		if (isEncrypted(message)) {
			return message;
		}
		return textEncryptor.encrypt(message);
	}

	public static String decrypt(final String message) {
		return textEncryptor.decrypt(message);
	}

	public static boolean isEncrypted(final String message) {
		try {
			decrypt(message);
			// If we are able to decrypt the message, then the message is already
			// encrypted. so just return true.
			return true;
		} catch (final Exception e) {
			log.debug(e);
			return false;
		}
	}
}
