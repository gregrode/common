package com.gregrode.common.util.queue;

import java.util.Map.Entry;

/**
 * 
 * Entries consist of key and value A total order is defined on the keys.<br/>
 * Operations
 * <ul>
 * <li>Identify or removed enry whose key is lowest (but no other entry)
 * <li>Any key maybe inserted at any time
 * </ul>
 * Commonly used as "event queues" simulations.<br/>
 * key is the time event takes place <br/>
 * value is description of the event.<br/>
 * 
 * 
 * Binary heap : An implement of Priority Queue<br/>
 * A binary heap is a complete binary tree, which is a tree where every row
 * (level) is full, except bottom row, which is filled from left to right <br/>
 * 
 * <pre>
 *                2
 *              /   \
 *            5       3            0 1 2 3 4 5 6  7 8  9  10                                   
 *          /   \    / \           x 2 5 3 9 6 11 4 17 10 8                            
 *         9     6  11  4 
 *       /  \   /
 *      17  10 8
 * </pre>
 * 
 * Entries satisfy the heap-order property: no child has a key less than its
 * parent's key<br/>
 * 
 * Every subtree of a binary heap is a binary heap.<br/>
 * 
 * Often stored as arrays of entries by level-order traversal<br/>
 * 
 * Mapping of nodes to indices : level numbering Node i's children are 2i and
 * 2i+1 parent is i/2 rounding down.
 * 
 * Each tree node has 2 referencse (key, value) OR references an Entry object
 * <br/>
 * 
 * @author Gregroy Dennis<br/>
 * 
 */
public interface QueueInterface<K, V> {
	/**
	 * Let x be new entry(K,V). Place x in bottom level of tree, at first free spot
	 * from left. ie first free location in array. Entry bubbles up tree until
	 * heap-order property is satisfied.
	 * 
	 * <pre>
	 * Repeat- compare x's key with it
	 * 				parent's key, if x's is less replace keys.
	 * </pre>
	 * 
	 * @param key
	 * @param value
	 */
	void insert(K key, V value);

	/**
	 * Remove entry at root; save for return value. Fill hole with last entry in
	 * tree "x".
	 * 
	 * <pre>
	 * 	If x greater than one or both of its children, then swap x with its minimum child.
	 * </pre>
	 * 
	 * @return {@link Entry}
	 */
	Entry<K, V> removeMin();

	/**
	 * @return {@link Entry}
	 */
	Entry<K, V> min();

}
