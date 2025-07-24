package com.gregrode.common.util.list.node;

abstract class AbstractListNode<T> implements ListNodeInterface<T>
{
	T item;
	ListNodeInterface<T> next;
	ListNodeInterface<T> prev;

	public AbstractListNode(T item)
	{
		this.item = item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#getItem()
	 */
	@Override
	public T getItem()
	{
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#setItem(java.lang.Object)
	 */
	@Override
	public void setItem(T item)
	{
		this.item = item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#getNext()
	 */
	@Override
	public ListNodeInterface<T> getNext()
	{
		return next;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#setNext(com.gregrode. common.util.list.ListNodeInterface)
	 */
	@Override
	public void setNext(ListNodeInterface<T> next)
	{
		this.next = next;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.ListNodeInterface#nth(int)
	 */
	@Override
	public ListNodeInterface<T> nth(final int position)
	{
		if (position == 1)
		{
			return this;
		}

		if ((position < 1) || (next == null))
		{
			return null;
		}
		return next.nth(position - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.node.ListNodeInterface#getPrevious()
	 */
	@Override
	public ListNodeInterface<T> getPrevious()
	{
		return prev;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.list.node.ListNodeInterface#setPrevious(com.gregrode.common.util.list.node.ListNodeInterface)
	 */
	@Override
	public void setPrevious(ListNodeInterface<T> prev)
	{
		this.prev = prev;
	}
}
