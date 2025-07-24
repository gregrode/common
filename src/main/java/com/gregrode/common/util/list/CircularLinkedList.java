package com.gregrode.common.util.list;

import com.gregrode.common.util.list.node.DoubleListNode;
import com.gregrode.common.util.list.node.ListNodeInterface;
import com.gregrode.common.util.Util;

/**
 * This class is a variation of the {@link DoubleLinkedList} class
 * 
 * @author Gregroy Dennis<br/>
 * 
 * 
 * @param <T>
 */
public class CircularLinkedList<T> extends AbstractLinkedList<T> {
	private ListNodeInterface<T> sentinel;

	/**
	 * Default constructor
	 */
	public CircularLinkedList() {
		this((T[]) null);
	}

	/**
	 * @param items
	 */
	@SafeVarargs
	public CircularLinkedList(final T... items) {
		sentinel = new DoubleListNode<T>((T) null);
		sentinel.setNext(sentinel);
		sentinel.setPrevious(sentinel);
		if (Util.isEmpty(items)) {
			return;
		}
		ListNodeInterface<T> node = null;

		for (final T item : items) {
			if (node == null) {
				node = new DoubleListNode<T>(item, sentinel, sentinel);
				sentinel.setNext(node);
				continue;
			}
			node.insertAfter(item);
			node = node.getNext();
		}
		sentinel.setPrevious(node);
		size = items.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#insertFront(java.lang
	 * .Object)
	 */
	@Override
	public void insertFront(final T item) {
		final ListNodeInterface<T> node = new DoubleListNode<T>(item, sentinel.getNext(), sentinel);
		sentinel.getNext().setPrevious(node);
		sentinel.setNext(node);
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#insertEnd(java.lang
	 * .Object)
	 */
	@Override
	public void insertEnd(final T item) {

		final ListNodeInterface<T> node = new DoubleListNode<T>(item, sentinel, sentinel.getPrevious());
		sentinel.setNext(node);
		sentinel.setPrevious(node);
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#insertAt(java.lang.
	 * Object, int)
	 */
	@Override
	public void insertAt(final T item, final int position) {
		final ListNodeInterface<T> nth = nth(position);
		nth.insertAfter(item);
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#removeFront()
	 */
	@Override
	public boolean removeFront() {
		if ((sentinel.getNext() != null) && !isEmpty()) {
			final ListNodeInterface<T> head = sentinel.getNext();
			final ListNodeInterface<T> newHead = head.getNext();
			newHead.setPrevious(sentinel);
			sentinel.setNext(newHead);
			head.setNext(null);
			head.setPrevious(null);
			size--;
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#removeTail()
	 */
	@Override
	public boolean removeTail() {
		if ((sentinel != null) && !isEmpty()) {
			final ListNodeInterface<T> tail = sentinel.getPrevious();
			final ListNodeInterface<T> newTail = tail.getPrevious();
			newTail.setNext(sentinel);
			sentinel.setPrevious(newTail);
			tail.setNext(null);
			tail.setPrevious(null);
			size--;
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#getItem(int)
	 */
	@Override
	public T getItem(final int position) {
		final ListNodeInterface<T> nth = nth(position);
		return nth.getItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#getFirst()
	 */
	@Override
	public T getFirst() {
		return sentinel.getNext().getItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#getLast()
	 */
	@Override
	public T getLast() {
		return sentinel.getPrevious().getItem();
	}

	/**
	 * Get the nth object by traverse from either the tail or head, which ever is
	 * shorter.
	 * 
	 * @param position
	 * @return ListNodeInterface<T>
	 */
	private ListNodeInterface<T> nth(final int position) {
		if (isEmpty()) {
			return sentinel;
		}
		ListNodeInterface<T> nth = null;
		final int half = (size / 2);
		if (position > half) {
			final int newPosition = size - (position);
			nth = sentinel.getPrevious().nthFromTail(newPosition);
		} else {
			nth = sentinel.getNext().nth(position);
		}
		return nth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#remove(int)
	 */
	@Override
	public boolean remove(final int position) {
		if (isEmpty()) {
			return false;
		}
		final ListNodeInterface<T> node = nth(position);
		if ((node == null) || (node == sentinel)) {
			return false;
		}
		node.remove();
		size--;
		return true;
	}

}
