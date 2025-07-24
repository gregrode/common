package com.gregrode.common.util.list;

import com.gregrode.common.util.list.node.DoubleListNode;
import com.gregrode.common.util.list.node.ListNodeInterface;

public class DoubleLinkedList<T> extends AbstractLinkedList<T> {
	private ListNodeInterface<T> head;
	private ListNodeInterface<T> tail;

	public DoubleLinkedList() {
		this((T[]) null);
	}

	@SafeVarargs
	public DoubleLinkedList(final T... items) {
		if (items == null) {
			return;
		}
		ListNodeInterface<T> node = null;
		for (final T item : items) {
			if (head == null) {
				head = new DoubleListNode<T>(item);
				node = head;
				continue;
			}
			node.insertAfter(item);
			node = node.getNext();
		}
		tail = node;
		size = items.length;
	}

	@Override
	public void insertFront(final T item) {
		head = new DoubleListNode<T>(item, head);
		head.getNext().setPrevious(head);
		size++;
	}

	@Override
	public void insertEnd(final T item) {
		tail = new DoubleListNode<T>(item, null, tail);
		tail.getPrevious().setNext(tail);
		size++;
	}

	@Override
	public void insertAt(final T item, final int position) {

		final ListNodeInterface<T> nth = nth(position - 1);
		nth.insertAfter(item);
		size++;
	}

	@Override
	public boolean removeFront() {
		if ((head != null) && (size > 0)) {
			final ListNodeInterface<T> temp = head.getNext();
			temp.setPrevious(null);
			head = temp;
			size--;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeTail() {
		if ((tail != null) && (size > 0)) {

			tail.getPrevious().setNext(null);
			tail = tail.getPrevious();
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
		final ListNodeInterface<T> node = nth(position);
		return node.getItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#getFirst()
	 */
	@Override
	public T getFirst() {
		return head.getItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#getLast()
	 */
	@Override
	public T getLast() {
		return tail.getItem();
	}

	/**
	 * Get the nth object by traverse from either the tail or head, which ever is
	 * shorter.
	 * 
	 * @param position
	 * @return ListNodeInterface<T>
	 */
	private ListNodeInterface<T> nth(final int position) {
		ListNodeInterface<T> nth = null;
		final int half = (size / 2);
		if (position > half) {
			final int newPosition = size - (position);
			nth = tail.nthFromTail(newPosition);
		} else {
			nth = head.nth(position);
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
		final ListNodeInterface<T> node = nth(position);
		if (node == null) {
			return false;
		}
		return node.remove();
	}

}
