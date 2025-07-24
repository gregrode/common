package com.gregrode.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Greg Dennis
 *
 */
public class ClassUtils {

	private static final Logger log = Logger.getLogger(ClassUtils.class);

	private ClassUtils() {
	}

	/**
	 * Convert a map to a POJO (plain old java object). Note, the object must have a
	 * default constructor with no parameter.
	 * 
	 * @param map
	 *            the map to convert
	 * @param clazz
	 *            the class to convert the map to
	 * @return an instance of the given class
	 * @throws Exception
	 */
	public static <T> T toPojo(final Map<String, Object> map, final Class<T> clazz) throws Exception {
		final Constructor<?> defConstructor = getDefaultConstructor(clazz);
		final T obj = clazz.cast(defConstructor.newInstance());
		final Map<String, Method> methods = getMethods(clazz);
		for (final Entry<String, Object> entry : map.entrySet()) {
			try {
				final String name = entry.getKey();
				callSetter(obj, methods, name, entry.getValue());
			} catch (final Exception e) {
				log.debug(e);
				continue;
			}
		}
		return obj;
	}

	/**
	 * Convert the given {@link Element} object to pojo object which is represented
	 * by the given {@link Class} object.
	 * 
	 * @param element
	 *            the {@link Element} object
	 * @param clazz
	 *            the class to convert the map to
	 * @return an instance of the given class
	 * @throws Exception
	 */
	public static <T> T toPojo(final Element element, final Class<T> clazz) throws Exception {
		final Constructor<?> defConstructor = getDefaultConstructor(clazz);
		final T obj = clazz.cast(defConstructor.newInstance());
		final Map<String, Method> methods = getMethods(clazz);
		final Field[] fields = clazz.getDeclaredFields();
		for (final Field field : fields) {
			final String name = field.getName();
			final String value = getTagValue(element.getElementsByTagName(name));
			callSetter(obj, methods, name, value);
		}
		return obj;
	}

	/**
	 * @param nodeList
	 *            the {@link NodeList}
	 * @return the value of the first node in the list
	 */
	private static String getTagValue(final NodeList nodeList) {
		if ((nodeList == null) || (nodeList.getLength() == 0)) {
			return null;
		}

		final Element element = (Element) nodeList.item(0);
		if ((element == null) || (element.getFirstChild() == null)) {
			return null;
		}
		return element.getFirstChild().getNodeValue();
	}

	/**
	 * Call the setter of for the given field
	 * 
	 * @param obj
	 *            the new instance
	 * @param methods
	 *            the Map of {@link Method}
	 * @param name
	 *            the name of the field
	 * @param value
	 *            the value of the field
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static <T> void callSetter(final T obj, final Map<String, Method> methods, final String name,
	        final Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final String setter = "set" + StringUtil.capitalizeFirstLetter(name);
		final Method method = methods.get(setter);
		if ((method != null) && (value != null)) {
			method.invoke(obj, value);
		}
	}

	/**
	 * @param clazz
	 *            the {@link Class} object
	 * @return the default {@link Constructor} object
	 */
	public static Constructor<?> getDefaultConstructor(final Class<?> clazz) {
		return Stream.of(clazz.getConstructors()).filter(con -> con.getParameterTypes().length == 0).findFirst().get();
	}

	/**
	 * @param clazz
	 *            the {@link Class} object
	 * @return the Map of {@link Method} associated with the given class
	 */
	public static Map<String, Method> getMethods(final Class<?> clazz) {
		return Stream.of(clazz.getMethods())
		        .collect(Collectors.toMap(Method::getName, method -> method, (m1, m2) -> m1, HashMap::new));
	}

}
