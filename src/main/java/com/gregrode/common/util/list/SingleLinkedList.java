package com.gregrode.common.util.list;

import com.gregrode.common.util.list.node.ListNode;
import com.gregrode.common.util.list.node.ListNodeInterface;

public class SingleLinkedList<T> extends AbstractLinkedList<T>
{
	private ListNodeInterface<T> head;

	/**
	 * Default constructor
	 */
	public SingleLinkedList()
	{
		this((T[]) null);
	}

	/**
	 * @param items
	 *            the array of items to insert into the linkedlist.
	 */
	@SafeVarargs
	public SingleLinkedList(final T... items)
	{
		if (items == null)
		{
			return;
		}
		ListNodeInterface<T> node = null;
		for (final T item : items)
		{
			if (head == null)
			{
				head = new ListNode<T>(item);
				node = head;
				continue;
			}
			node.insertAfter(item);
			node = node.getNext();
		}
		size = items.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gregrode.common.util.list.LinkedListInterface#insertFront(java.lang
	 * .Object)
	 */
	@Override
	public void insertFront(final T item)
	{
		head = new ListNode<T>(item, head);
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gregrode.common.util.list.LinkedListInterface#insertAt(java.lang.
	 * Object, int)
	 */
	@Override
	public void insertAt(final T item, final int position)
	{
		final ListNodeInterface<T> nth = head.nth(position - 1);
		nth.insertAfter(item);
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#removeFront()
	 */
	@Override
	public boolean removeFront()
	{
		if (head != null)
		{
			head = head.getNext();
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
	public T getItem(final int position)
	{
		return head.nth(position).getItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gregrode.common.util.list.LinkedListInterface#insertEnd(java.lang
	 * .Object)
	 */
	@Override
	public void insertEnd(final T item)
	{
		final ListNodeInterface<T> node = head.nth(size);
		node.setNext(new ListNode<T>(item));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#removeTail()
	 */
	@Override
	public boolean removeTail()
	{
		if (size > 0)
		{
			final ListNodeInterface<T> node = head.nth(size - 1);
			node.setNext(null);
			size--;
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#getFirst()
	 */
	@Override
	public T getFirst()
	{
		return head.getItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#getLast()
	 */
	@Override
	public T getLast()
	{
		return head.nth(size).getItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.LinkedListInterface#remove(int)
	 */
	@Override
	public boolean remove(final int position)
	{
		if (isEmpty())
		{
			return false;
		}
		final ListNodeInterface<T> node = head.nth(position - 1);
		if (node == null)
		{
			return false;
		}

		node.setNext(node.getNext().getNext());
		size--;
		return true;
	}

}
