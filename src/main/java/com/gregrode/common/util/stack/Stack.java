package com.gregrode.common.util.stack;

import com.gregrode.common.util.list.node.ListNode;

public class Stack<T> implements StackInterface<T>
{

	ListNode<T> head;
	
	
	public Stack (T item)
	{
		this.head = new ListNode<T>(item);
	}
	
	@Override
	public void push(T item)
	{
		head.insertAfter(item);

	}

	@Override
	public T pop()
	{
		T item = head.getItem();
		return item;
	}

	@Override
	public T top()
	{
		T item = head.getItem();
		return item;
	}

}
