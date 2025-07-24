package com.gregrode.common.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The <code>StringUtil</code> class provides some functionality to manipulate
 * strings.
 *
 * @author Gregroy Dennis<br>
 *         Last updated: December 11, 2010<br>
 *         &copy; Gregroy Dennis 2010
 *
 */
public final class StringUtil {

	public static final String ASCII_SINGLE_QUOTE = "&#39;";
	public static final String ASCII_DOUBLE_QUOTE = "&#34;";
	public static final String ASCII_COMMA = "&#44;";
	public static final String NULL_VALUE = "NULL";
	public static final String EMPTY = "";
	public static final String SINGLE_QUOTE = "'";
	public static final String ESCAPED_SINGLE_QUOTE = "\'";
	public static final String ESCAPED_DOUBLE_QUOTE = "\"";
	public static final String[] TRUE_LIST = { "1", "yes", "y", "on", "t", "true" };

	private StringUtil() {
	}

	/**
	 * check if any of given values are null or empty .
	 *
	 * @param values
	 *            the strings to check for
	 * @return a boolean that indicates that one of the strings is null or empty.
	 */
	public static boolean isEmpty(final String... values) {
		final boolean empty = true;
		if (values == null) return empty;
		final Optional<String> optional = Stream.of(values).filter(StringUtil::isEmpty).findFirst();
		return optional == null ? false : optional.isPresent();
	}

	/**
	 * check if a value is not null or return empty string.
	 *
	 * @param value
	 *            the string the check for
	 * @return a boolean that indicates the string is not null or empty
	 */
	public static boolean isNotEmpty(final String value) {
		return !isEmpty(value);
	}

	/**
	 * Check if the given string is null or is equals to the string "null" (ignoring
	 * the case)
	 *
	 * @param value
	 *            the value
	 * @return boolean
	 */
	public static boolean isNull(final String value) {
		if (value == null) return true;
		return equalsIgnoreCase(value, NULL_VALUE);
	}
	
	/**
	 * Trim the given string
	 * @param value
	 *            the value
	 * @return String
	 */
	public static String trim(final String value) {
		if (value == null) return null;
		return value.trim();
	}
	

	/**
	 * check if a value is null and return empty string. Otherwise the same value is
	 * returned.
	 *
	 * @param value
	 *            the string the check for
	 * @return a non null String.
	 */
	public static boolean isEmpty(final CharSequence value) {
		return (value == null) || (value.toString().trim().length() == 0);
	}

	/**
	 * check if a value is null and return empty string. Otherwise the same value is
	 * returned.
	 *
	 * @param value
	 *            the string the check for
	 * @return a non null String.
	 */
	public static String setAsEmpty(final String value) {
		if (isEmpty(value)) return EMPTY;
		return value;
	}

	/**
	 * Convert a string value to a boolean
	 *
	 * @param value
	 *            the string to convert
	 * @return the boolean equivalent to the given string.
	 */
	public static boolean isBoolean(final String value) {
		if (isEmpty(value)) return false;
		return isAnyIgnoreCase(value, TRUE_LIST);
	}

	/**
	 * Surround the given string with double quotes. Note, if the given string
	 * already contains double quote(s), they will be replaced by their ASCII
	 * representation.
	 *
	 * @param value
	 *            The supplied value
	 * @return String
	 */
	public static String surroundWithDoubleQuotes(final String value) {
		return surroundWith(value, ESCAPED_DOUBLE_QUOTE, ASCII_DOUBLE_QUOTE);
	}

	/**
	 * Surround the given string with single quotes. Note, if the given string
	 * already contain single quote(s), they will be replaced by their ASCII
	 * representation.
	 *
	 * @param value
	 *            The supplied value
	 * @return String
	 */
	public static String surroundWithSingleQuotes(final String value) {
		return surroundWith(value, SINGLE_QUOTE, ESCAPED_SINGLE_QUOTE);
	}

	/**
	 * Surround a string with the given value.
	 *
	 * @param str
	 *            the string
	 * @param val
	 *            the value to surround the string with
	 * @param escape
	 *            the escape value in case the string already contained the given
	 *            value
	 * @return String
	 */
	public static String surroundWith(String str, final String val, final String escape) {
		if (isEmpty(str)) str = EMPTY;
		final StringBuilder sBuilder = new StringBuilder(val);
		if (str.contains(val)) {
			sBuilder.append(str.replaceAll(val, escape));
		} else {
			sBuilder.append(str);
		}
		return sBuilder.append(val).toString();
	}

	/**
	 * Convert a boolean value to a string
	 *
	 * @param isTrue
	 *            the boolean value to convert
	 * @return if true, return '1'. Otherwise return '0'
	 */
	public static String convertBooleanToString(final boolean isTrue) {
		return isTrue ? "1" : "0";
	}

	/**
	 * Convert a string to a boolean that is more PostgreSQL friendly.
	 *
	 * @param value
	 *            the string to convert
	 * @return String
	 */
	public static String convertStringToDataBaseBoolean(final String value) {
		return convertBooleanToString(isBoolean(value));
	}

	/**
	 * Check if the given value matches the regular expression.
	 *
	 * @param pattern
	 *            the pattern
	 * @param value
	 *            the value to check
	 * @return A boolean indicating if the value matches the pattern.
	 */
	public static boolean matchRegex(final String pattern, final String value) {
		if (isEmpty(pattern)) return false;
		return matchRegex(Pattern.compile(pattern), value);
	}

	/**
	 * Check if the given value matches the regular expression.
	 *
	 * @param pattern
	 *            the Pattern object
	 * @param value
	 *            the value to check
	 * @return A boolean indicating if the value matches the pattern.
	 */
	public static boolean matchRegex(final Pattern pattern, final String value) {
		if ((pattern == null) || isEmpty(value)) return false;
		return pattern.matcher(value).matches();
	}


	/**
	 * Capitalized the first letter of the given String
	 *
	 * @param value
	 * @return String
	 */
	public static String capitalizeFirstLetter(final String value) {
		if (isEmpty(value)) return value;
		String first = value.substring(0,1).toUpperCase();
		return first + value.substring(1);
	}

	/**
	 *
	 * @param str
	 * @param prefix
	 *            the string to remove the str
	 * @return String
	 */

	public static String removePrefix(final String str, final String prefix) {
		if (isEmpty(str) || isEmpty(prefix)) return str;
		return str.replaceFirst(prefix, EMPTY);
	}

	/**
	 * Check if two strings are equal
	 *
	 * @param str
	 *            the first string
	 * @param str2
	 *            the second string
	 * @return a boolean indicating if the strings are equal.
	 */
	public static boolean equals(final String str, final String str2) {
		return Util.equals(str, str2);
	}

	/**
	 * Check if two strings are equals. Note, null and empty string ("") will be
	 * considered to be equal. 
	 * @param str the first string
	 *
	 * @param str2
	 *            the second string
	 * @return a boolean indicating if the strings are equal.
	 */
	public static boolean equalsEmptyOrNull(final String str, final String str2) {
		return equals(setAsEmpty(str), setAsEmpty(str2));
	}

	/**
	 * Check if two strings are equal ignoring the case
	 *
	 * @param str
	 *            the first string
	 * @param str2
	 *            the second string
	 * @return a boolean indicating if the strings are equal.
	 */
	public static boolean equalsIgnoreCase(final String str, final String str2) {
		return str == null ? str2 == null : str.equalsIgnoreCase(str2);
	}

	/**
	 * Check if the given string is equals to any of the entry in the list
	 *
	 * @param str
	 *            the string to check
	 * @param args
	 *            the list of possible values
	 * @return boolean
	 */
	public static boolean isAny(final String str, final String... args) {
		if (isEmpty(str) || Util.isEmpty(args)) return false;
		return Arrays.stream(args).filter(str::equals).findAny().isPresent();
	}

	/**
	 * Check if the given string is contained in the list of possible values.
	 *
	 * @param str
	 *            the string to check for
	 * @param args
	 *            the list of possible values
	 * @return boolean
	 */
	public static boolean isAnyIgnoreCase(final String str, final String... args) {
		if (isEmpty(str) || Util.isEmpty(args)) return false;
		return Arrays.stream(args).filter(str::equalsIgnoreCase).findAny().isPresent();
	}

	/**
	 * Checks if the str2 is contained in str1
	 *
	 * @param str
	 *            the container string
	 * @param str1
	 *            the string to check for
	 * @return boolean
	 */
	public static boolean contains(final String str, final String str1) {
		if (isEmpty(str) || isEmpty(str1)) return false;
		return str.contains(str1);
	}

	/**
	 * Checks if the str2 is contained in str1 regardless of the case
	 *
	 * @param str
	 *            the container string
	 * @param str1
	 *            the string to check for
	 * @return boolean
	 */
	public static boolean containsIgnoreCase(final String str, final String str1) {
		if (isEmpty(str) || isEmpty(str1)) return false;
		return str.toLowerCase().contains(str1.toLowerCase());
	}

	/**
	 * Replace single quote with two single quotes
	 *
	 * @param str
	 * @return String
	 */
	public static String escapeSQLQuotes(final String str) {
		if (!contains(str, SINGLE_QUOTE)) return str;
		String subStr = str;
		final String delimiter = "<to-be-replaced>";
		while (contains(subStr, SINGLE_QUOTE)) {
			subStr = subStr.replaceFirst(SINGLE_QUOTE, delimiter);
		}

		return subStr.replaceAll(delimiter, "''");
	}

	/**
	 * @param str
	 *            reverse the given string
	 * @return the reverse string
	 */
	public static String reverse(final String str) {
		if (isEmpty(str)) return str;
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 *
	 * @param array
	 * @param start
	 * @param end
	 * @return Array of T
	 */
	public static <T> T[] swap(final T[] array, final int start, final int end) {
		final T temp = array[start];
		array[start] = array[end];
		array[end] = temp;
		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Append the list of given string into one string.
	 *
	 * @param strings
	 *            the strings to concatenate
	 * @return String
	 */
	public static String append(final String... strings) {
		return (strings == null) ? EMPTY : Stream.of(strings).collect(Collectors.joining());
	}

}
