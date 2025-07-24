package com.gregrode.common.util.stack;

/**
 * 
 * Stack is a crippled list that does the following:
 * <ol>
 * <li>"Push" a new item onto the top of the stack
 * <li>"pop" an item off the stack
 * <li>examine "top" item of stack
 * </ol>
 * 
 * It is easily implemented using a singly-linked list. All operates take O(1)
 * time.
 * 
 * Note the limitation of stack tell how your algorithm works
 * 
 * @author Gregroy Dennis
 * 
 */
public interface StackInterface<T>
{
	/**
	 * @param item
	 *            put an item on the stack.
	 */
	void push(T item);

	/**
	 * @return get and remove the top item from the stack.
	 */
	T pop();

	/**
	 * Exxamine the top of the stack
	 * 
	 * @return top item
	 */
	T top();

}
