package com.gregrode.common.util.list.node;

/**
 * @author Greg Dennis
 *
 * @param <T>
 */
public interface ListNodeInterface<T> {

	/**
	 * @param item
	 */
	void insertBefore(T item);

	/**
	 * @param item
	 */
	void insertAfter(T item);

	/**
	 * @param position
	 * @return {@link ListNodeInterface}
	 */
	ListNodeInterface<T> nth(final int position);

	/**
	 * @param position
	 * @return {@link ListNodeInterface}
	 */
	ListNodeInterface<T> nthFromTail(final int position);

	/**
	 * @return T
	 */
	T getItem();

	/**
	 * @param item
	 */
	void setItem(T item);

	/**
	 * @return {@link ListNodeInterface}
	 */
	ListNodeInterface<T> getNext();

	/**
	 * @param next
	 */
	void setNext(ListNodeInterface<T> next);

	boolean remove();

	/**
	 * @return {@link ListNodeInterface}
	 */
	ListNodeInterface<T> getPrevious();

	/**
	 * @param prev
	 */
	void setPrevious(ListNodeInterface<T> prev);

}
