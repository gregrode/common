package com.gregrode.common.util.list.node;

/**
 * ListNode is an example of a single linked list.
 * 
 * @author Gregroy Dennis
 * 
 * @param <T>
 */
public class ListNode<T> extends AbstractListNode<T>
{

	/**
	 * @param item
	 */
	public ListNode(T item)
	{
		this(item, null);
	}

	/**
	 * @param next
	 */
	public ListNode(ListNodeInterface<T> next)
	{
		this(null, next);
	}

	/**
	 * @param item
	 * @param next
	 */
	public ListNode(T item, ListNodeInterface<T> next)
	{
		super(item);
		this.item = item;
		this.next = next;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.node.ListNodeInterface#insertBefore(java .lang.Object)
	 */
	@Override
	public void insertBefore(T item)
	{
		next = new ListNode<T>(item, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.node.ListNodeInterface#insertAfter(java .lang.Object)
	 */
	@Override
	public void insertAfter(T item)
	{
		next = new ListNode<T>(item, next);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.node.ListNodeInterface#nthFromTail(int)
	 */
	@Override
	public ListNodeInterface<T> nthFromTail(final int position)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.node.ListNodeInterface#remove()
	 */
	@Override
	public boolean remove()
	{
		return true;
	}

}
