package com.gregrode.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gregrode.common.ErrorStatusException;
import com.gregrode.common.ErrorStatusInterface;

/**
 * The <code>Util</code> class provides common functionalities
 *
 * @author Gregroy Dennis
 */
public final class Util {

	private static final Logger log = Logger.getLogger(Util.class);

	private static final String EMPTY = "";

	private Util() {
	}

	/**
	 * Check whether the given object is null or not. if null, an
	 * <code>ErrorStatusException</code> is thrown.
	 *
	 * @param obj
	 *            the object to check
	 * @param status
	 *            the status within the exception
	 * @return obj
	 */
	public static <T> T verify(final T obj, final ErrorStatusInterface status) {
		return verify(obj, new ErrorStatusException(status));
	}

	/**
	 * Check whether the given object is valid based on the given predicate. if the
	 * predicate fails, an <code>ErrorStatusException</code> is thrown.
	 *
	 * @param obj
	 *            the object to check
	 * @param status
	 *            the status within the exception
	 * @param predicate
	 *            the predicate to test
	 * @return obj
	 */
	public static <T> T verify(final T obj, final ErrorStatusInterface status, final Predicate<T> predicate) {
		return verify(obj, new ErrorStatusException(status), predicate);
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	@SafeVarargs
	public static <T> boolean isEmpty(final T... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param predicate
	 *            the {@link Predicate} object
	 * @param array
	 *            the array
	 * @return boolean
	 */
	@SafeVarargs
	public static <T> boolean isEmpty(final Predicate<T> predicate, final T... array) {
		return isEmpty(array) ? true : Arrays.stream(array).anyMatch(predicate);
	}

	/**
	 * @param array
	 *            the varargs
	 * @return boolean
	 */
	@SafeVarargs
	public static <T> boolean isNotEmpty(final T... array) {
		return !isEmpty(array);
	}

	/**
	 * @param predicate
	 *            the {@link Predicate} object
	 * @param array
	 *            the varargs
	 * @return boolean
	 */
	@SafeVarargs
	public static <T> boolean isNotEmpty(final Predicate<T> predicate, final T... array) {
		return (isEmpty(array)) ? false : Arrays.stream(array).allMatch(predicate);

	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final boolean... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final byte... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final short... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final int... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final double... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final float... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final long... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given array object is empty
	 *
	 * @param array
	 *            the array
	 * @return boolean
	 */
	public static boolean isEmpty(final char... array) {
		return ((array == null) || (array.length == 0));
	}

	/**
	 * Checks if the given collection object is empty
	 *
	 * @param collection
	 *            the collection object
	 * @return boolean
	 */
	public static boolean isEmpty(final Collection<?> collection) {
		return ((collection == null) || collection.isEmpty());
	}

	/**
	 * checks if the given map is empty
	 *
	 * @return boolean
	 */
	public static boolean isEmpty(final Map<?, ?> map) {
		return ((map == null) || map.isEmpty());
	}

	/**
	 * Check whether the given object is null or not. if null, an
	 * {@link NullPointerException} is thrown.
	 *
	 * @param t
	 *            the object to check
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T verify(final T t) {
		return Objects.requireNonNull(t);
	}

	/**
	 * Check whether the given object is null or not. if null, an
	 * {@link NullPointerException} is thrown.
	 *
	 * @param t
	 *            the object to check
	 * @param message
	 *            the message within the exception
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T verify(final T t, final String message) {
		return Objects.requireNonNull(t, message);
	}

	/**
	 * Check whether the given object is null or valid based on the given predicate.
	 * if the predicate fails, an {@link NullPointerException} is thrown.
	 *
	 * @param t
	 *            the object to check
	 * @param predicate
	 *            the {@link Predicate} object to test
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T verify(final T t, final Predicate<T> predicate) {
		return verify(t, new NullPointerException(), predicate);
	}

	/**
	 * Check whether the given object is null or valid based on the given predicate.
	 * if the predicate fails, a {@link NullPointerException} is thrown.
	 *
	 * @param t
	 *            the object to check
	 * @param message
	 *            the message within the exception
	 * @param predicate
	 *            the {@link Predicate} object to test
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T verify(final T t, final String message, final Predicate<T> predicate) {
		return verify(t, new NullPointerException(message), predicate);
	}

	/**
	 * Check whether the given object is null or not. if null, an
	 * {@link RuntimeException} is thrown.
	 *
	 * @param t
	 *            the object to check
	 * @param exception
	 *            the {@link RuntimeException} to throw
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T verify(final T t, final RuntimeException exception) {
		return verify(t, exception, o -> true);
	}

	/**
	 *
	 * This method will throw an instance of {@link RuntimeException} if one of the
	 * following cases occurs:
	 * <ol>
	 * <li>The object is {@code null}</li>
	 * <li>The exception is {@code null}</li>
	 * <li>The predicate is {@code null} or the result of the predicate is
	 * {@code false}.</li>
	 * <li>The object is an instance of a {@link Boolean} and the value is
	 * {@code false}</li>
	 * <li>The object is an instance of a {@link String} and the value is empty</li>
	 * <li>The object is an instance of a {@link Collection} and the value is
	 * empty</li>
	 * <li>The object is an instance of a {@link Map} the value is empty</li>
	 * </ol>
	 *
	 *
	 * @param t
	 *            the object to check
	 * @param exception
	 *            the {@link RuntimeException} to throw
	 * @param predicate
	 *            the {@link Predicate} object to test
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T verify(final T t, final RuntimeException exception, final Predicate<T> predicate) {
		Objects.requireNonNull(t, "Object was not specified.");
		Objects.requireNonNull(exception, "Exception was not specified.");
		Objects.requireNonNull(predicate, "Predicate was not specified.");

		if (((t instanceof Boolean) && !((Boolean) t).booleanValue())
		        || ((t instanceof String) && ((String) t).isEmpty())
		        || ((t instanceof Collection) && ((Collection<?>) t).isEmpty())
		        || ((t instanceof Map) && ((Map<?, ?>) t).isEmpty()) || ((t instanceof Object[]) && isEmpty(t))
		        || (!predicate.test(t))) {
			throw exception;
		}
		return t;
	}

	/**
	 * Check if the first parameter is not null, if so, {@code first} is returned.
	 * Otherwise, {@code def} is returned
	 *
	 * @param first
	 *            the object to test
	 * @param def
	 *            the default value to return if the first parameter is null
	 * @return T
	 */
	public static <T> T buildNonNull(final T first, final T def) {
		return first != null ? first : def;
	}

	/**
	 * Check if the given item is not null. If {@code t} is null, then return the
	 * result of the {@link Supplier} object.
	 *
	 *
	 * @param t
	 *            the object to test
	 * @param supplier
	 *            the {@link Supplier} object which contained the default value if
	 *            {@code t} is null.
	 * @return T
	 */
	public static <T> T buildNonNull(final T t, final Supplier<T> supplier) {
		return (t == null) ? verify(supplier.get(), "Supplier is null") : t;
	}

	/**
	 * Get the first non zero integer within the given varargs
	 *
	 * @param numbers
	 *            the list of integer
	 * @return first non zero integer
	 **/
	@SafeVarargs
	public static int nonZero(final int... numbers) {
		return Arrays.stream(numbers).filter(t -> t > 0).findFirst().getAsInt();
	}

	/**
	 * Create a {@link HashMap} from the given JSON object
	 *
	 * @param json
	 *            the JSON String
	 * @return {@link Map}
	 */
	public static Map<String, String> toMap(final String json) {
		return toMap(HashMap::new, json);
	}

	/**
	 * Create an implementation of the {@link Map} interface using the given
	 * {@link Supplier} object and populate the map with the given JSON
	 *
	 * @param json
	 *            the JSON String
	 * @param <K>
	 *            The type of object for the map key.
	 * @param <V>
	 *            The type of object for the map value
	 * @return {@link Map}
	 */
	@SuppressWarnings("unchecked")
	public static <K, V, M extends Map<K, V>> Map<K, V> toMap(final Supplier<M> mapSupplier, final String json) {
		verify(json, "Cannot transform null string in Map.");
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final JsonNode jsonNode = mapper.readTree(json.replaceAll("'", "\""));
			return mapper.treeToValue(jsonNode, verify(mapSupplier).get().getClass());
		} catch (final IOException e) {
			log.debug(e);
			return new HashMap<>();
		}
	}

	/**
	 * Convert the given object into a map
	 *
	 * @param t
	 *            the the object to convert a map
	 * @return {@link Map}
	 */
	public static <T> Map<String, String> toMap(final T t) {
		verify(t, "Object cannot be null");
		try {
			final String str = toJSON(t);
			return toMap(str);
		} catch (final Exception e) {
			log.debug(e);
			return new HashMap<>();
		}
	}

	/**
	 * Create a {@link Entry} object using the
	 * {@link java.util.AbstractMap.SimpleEntry} implementation.
	 *
	 * @param key
	 *            The key
	 * @param value
	 *            the value
	 *
	 * @param <K>
	 *            The type of object for the key.
	 * @param <V>
	 *            The type of object for the value.
	 * @return {@link Entry}
	 */
	public static <K, V> Entry<K, V> toEntry(final K key, final V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}

	/**
	 * Convert the given object in the a valid JSON object.
	 *
	 * @param obj
	 *            The object to transformed into a JSON object.
	 * @return String
	 */
	public static String toJSON(final Object obj) {
		verify(obj, "Cannot transform null object in JSON.");
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (final IOException e) {
			log.debug(e);
			return EMPTY;
		}
	}

	/**
	 * Pluck value out of the given {@link Collection} based on the given
	 * {@link Function} object and return them as a Collection
	 *
	 * @param items
	 *            the collection of itmes
	 * @param function
	 *            the functional interface that determine what value will be
	 *            plucked.
	 * @return {@link Collection}
	 */
	public static <T, R> Collection<R> pluck(final Collection<T> items, final Function<T, R> function) {
		verify(items, new IllegalArgumentException("Collection not specified"));
		verify(function, new IllegalArgumentException("function lambda not specified"));

		return items.stream().collect(Collectors.mapping(function, Collectors.toList()));
	}

	/**
	 * Build upon the given object.
	 *
	 * @param t
	 *            the object to build
	 * @param builder
	 *            the function interface used to built up the object
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T build(final T t, final Consumer<T> builder) {
		builder.accept(Objects.requireNonNull(t, "Cannot build object when it is null"));
		return t;
	}

	/**
	 * Get the first element in the given list
	 *
	 * @param list
	 *            the List
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T getFirst(final Collection<T> list) {
		return isEmpty(list) ? null : list.stream().findFirst().get();
	}

	/**
	 * Get the first element in the given array
	 *
	 * @param array
	 *            the array
	 * @param <T>
	 *            the type of object
	 * @return T
	 */
	public static <T> T getFirst(final T[] array) {
		return isEmpty(array) ? null : array[0];
	}

	/**
	 * @param callable
	 * @return Supplier
	 */
	public static <T> Supplier<T> uncheck(final Callable<T> callable) {
		return () -> {
			try {
				return callable.call();
			} catch (final RuntimeException e) {
				throw e;
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		};
	}


	public static <T> boolean equals(final T obj, final T obj2) {
		return obj == null ? obj2 == null : obj.equals(obj2);
	}

	/**
	 *
	 * Sway the given indexes
	 *
	 * @param array
	 *            the array
	 * @param start
	 *            the starting index
	 * @param end
	 *            the ending index
	 * @return array
	 */
	public static <T> T[] swap(final T[] array, final int start, final int end) {
		if (isEmpty(array)) return array;
		final T temp = array[start];
		array[start] = array[end];
		array[end] = temp;
		return Arrays.copyOf(array, array.length);
	}

	/**
	 * @return the MAC address of this host.
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static String getMACAddress() throws UnknownHostException, SocketException {
		final InetAddress address = InetAddress.getLocalHost();
		final NetworkInterface network = NetworkInterface.getByInetAddress(address);

		if (network == null) {
			log.error("Network Interface for the specified address is not found.");
			return StringUtil.EMPTY;
		}
		final byte[] mac = network.getHardwareAddress();
		if (mac == null) {
			log.error("Address doesn't exist or is not accessible.");
			return StringUtil.EMPTY;
		}
		return getMACAddressAsString(mac);
	}

	private static String getMACAddressAsString(final byte[] mac) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < (mac.length - 1)) ? "-" : ""));
		}
		return sb.toString();
	}

	/**
	 * Check if the incoming mac address is on any of the networks.
	 * 
	 * @param incomingMacAddress
	 * @return a boolean that indicates that the incoming mac address is on one of
	 *         the networks associated with this machine.
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static boolean hasMACAddress(final String incomingMacAddress) throws SocketException, UnknownHostException {
		if (StringUtil.isEmpty(incomingMacAddress)) return false;
		final Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();

		if (networks == null) {
			log.error("Network Interface for the specified address is not found.");
			return false;
		}
		for (final Iterator<NetworkInterface> iter = networks.asIterator(); iter.hasNext();) {
			final NetworkInterface network = iter.next();
			final byte[] macAddressAsBytes = network.getHardwareAddress();
			if (macAddressAsBytes == null) continue;
			final String macAddress = getMACAddressAsString(macAddressAsBytes);
			if (StringUtil.equals(incomingMacAddress, macAddress)) return true;
		}
		return false;
	}

	/**
	 * Check if the incoming mac address is on any of the networks.
	 * 
	 * @param incomingMacAddress
	 * @return a boolean that indicates that the incoming mac address is on one of
	 *         the networks associated with this machine.
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static boolean hasMacAddress(final byte[] incomingMacAddress) throws SocketException, UnknownHostException {
		final Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();

		if (networks == null) {
			log.error("Network Interface for the specified address is not found.");
			return false;
		}
		for (final Iterator<NetworkInterface> iter = networks.asIterator(); iter.hasNext();) {
			final NetworkInterface network = iter.next();
			final byte[] macAddress = network.getHardwareAddress();
			if (Arrays.equals(incomingMacAddress, macAddress)) return true;
		}
		return false;
	}

	/**
	 * @param template
	 * @param map
	 * @return String
	 */
	public static String format(final String template, final Map<String, ?> map) {
		String str = template;
		if (map == null) return str;
		for (final Entry<String, ?> entry : map.entrySet()) {
			final String regex = "${" + entry.getKey() + "}";
			final String replaceWith = String.valueOf(entry.getValue());
			if (str.contains(regex)) {
				str = str.replaceAll(Pattern.quote(regex), replaceWith);
			}
		}
		return str;
	}

	/**
	 * @param throwable
	 *            the {@link Throwable} object
	 * @return the string version of the print stack trace.
	 */
	public static String toString(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}

}
