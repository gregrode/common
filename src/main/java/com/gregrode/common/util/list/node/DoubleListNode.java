package com.gregrode.common.util.list.node;

public class DoubleListNode<T> extends AbstractListNode<T>
{

	/**
	 * @param next
	 *            the next object in the list.
	 */
	public DoubleListNode(ListNodeInterface<T> next)
	{
		this(null, next, null);
	}

	/**
	 * @param item
	 *            the item to add to the list
	 */
	public DoubleListNode(T item)
	{
		this(item, null, null);
	}

	/**
	 * @param item
	 *            the item to add to the list
	 * @param next
	 *            the next object in the list
	 */
	public DoubleListNode(T item, ListNodeInterface<T> next)
	{
		this(item, next, null);
	}

	/**
	 * @param item
	 *            the item to add to the list
	 * @param next
	 *            the next item in the list
	 * @param prev
	 *            the previous item in the list
	 */
	public DoubleListNode(T item, ListNodeInterface<T> next, ListNodeInterface<T> prev)
	{
		super(item);
		this.item = item;
		this.next = next;
		this.prev = prev;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#insertAfter(java.lang .Object)
	 */
	@Override
	public void insertAfter(T item)
	{
		next = new DoubleListNode<T>(item, next, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#insertBefore(java.lang .Object)
	 */
	@Override
	public void insertBefore(T item)
	{
		prev = new DoubleListNode<T>(item, this, prev);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#nthFromTail(int)
	 */
	@Override
	public DoubleListNode<T> nthFromTail(final int position)
	{
		if (position == 0)
		{
			return this;
		}

		if ((position < 0) || (prev == null))
		{
			return null;
		}
		return ((DoubleListNode<T>) prev).nthFromTail(position - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.node.ListNodeInterface#remove()
	 */
	@Override
	public boolean remove()
	{
		prev.setNext(next);
		((DoubleListNode<T>) next).setPrevious(prev);
		return true;
	}

}
