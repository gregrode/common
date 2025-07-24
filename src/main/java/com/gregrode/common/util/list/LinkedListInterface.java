package com.gregrode.common.util.list;

/**
 * The interface that all my implementation of the linkedlist will use.
 * 
 * @author Gregroy Dennis<br/>
 * 
 * 
 * @param <T>
 */
public interface LinkedListInterface<T>
{

	/**
	 * inserts item in the front of the list
	 * 
	 * @param item
	 *            the item to insert
	 */
	void insertFront(T item);

	/**
	 * Inserts the item at the end of the list
	 * 
	 * @param item
	 *            the item to insert
	 */
	void insertEnd(T item);

	/**
	 * Insert the item at the given position in the list
	 * 
	 * @param item
	 *            the item to insert
	 * @param position
	 *            the position where the item is being inserted.
	 */
	void insertAt(T item, int position);

	/**
	 * remove the item at the front of the list.
	 * 
	 * @return a boolean that indicates if the item was successfully removed.
	 */
	boolean removeFront();

	/**
	 * remove the item at the end of the list.
	 * 
	 * @return a boolean that indicates if the item was successfully removed.
	 */
	boolean removeTail();

	/**
	 * remove the item at the given position of the list.
	 * 
	 * @return a boolean that indicates if the item was successfully removed.
	 */
	boolean remove(int position);

	/**
	 * Get the item at the given position
	 * 
	 * @param position
	 *            the position of the item
	 * @return the item at the given position
	 */
	T getItem(int position);

	/**
	 * @return the first item in the list
	 */
	T getFirst();

	/**
	 * @return the last item in the list.
	 */
	T getLast();

	/**
	 * @return the size of the list
	 */
	int getSize();

	boolean isEmpty();
}
