package com.gregrode.common.util.list;

/**
 * This class is a variation of the {@link DoubleLinkedList} class
 * 
 * @author Gregroy Dennis<br/>
 * 
 * 
 * @param <T>
 */
abstract class AbstractLinkedList<T> implements LinkedListInterface<T> {
	protected int size;

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

}
