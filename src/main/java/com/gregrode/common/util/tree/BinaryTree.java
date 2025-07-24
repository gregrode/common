package com.gregrode.common.util.tree;

/**
 * <b>Binary Search trees</b><br/>
 * 
 * <b>Ordered Dictionary</b> : dictionary in which keys have a total order, like
 * a heap<br/>
 * 
 * <br>
 * Insert, find, remove entries ( but not as fast) Quickly find entry with
 * minimum or maximum ( not both fast) key or entry nearest another entry<br/>
 * 
 * <br>
 * Note, good for inexact matches.<br/>
 * <b> Binary search tree invariant:</b><br/>
 * for any node x, every key in left subtree of x is less than or equal to x's
 * key;. every key in right subtree of x is greater than or equal to x's key.
 * 
 * <pre>
 *                       18
 *                     /    \
 *                   12      25
 *                  /  \    /  \
 *                 4   15  25  30
 *                /   /  \    /
 *               1   13  17  28
 *                \   \        \
 *                 3   14       29
 * </pre>
 * 
 * Inorder traversal of a binary search tree visits nodes in sorter order.<br/>
 * 
 * How to find smaller key >= k or largest key <= k?<br/>
 * When searching down tree for a key k that is not in tree, we encounter
 * both:<br/>
 * <ul>
 * <li>node containing smallest key > k, and
 * <li>node containing largest key < K
 * </ul>
 * 
 * Running Time:
 * <ul>
 * <li>Perfectly balanced binary tree with depth d, number of nodes = 2^(d+1) -
 * 1<br/>
 * No node have depth > log2 n.<br/>
 * Running times of insert, find, remove proportional to depth of deepest node
 * visited.
 * <li>If data is already sorted, then all operations on binary search trees
 * have O(n), worst-case running time.
 * </ul>
 * 
 * @author Gregroy Dennis<br/>
 * 
 */
public interface BinaryTree<T extends TreeNode<?>> {

	T find(Object key);

	/**
	 * If the tree is empty, return null. Otherwise, start at root. <br/>
	 * Repeatedly go to the left Child until you reach a node with no left
	 * child.<br/>
	 * That node have the minimum key.
	 * 
	 * @return T
	 */
	T first();

	T last();

	/**
	 * 
	 * Follow same path through tree as find().<br/>
	 * When you reach null reference, replace null with new node with
	 * Entry(K,V).<br/>
	 * Duplicate keys allowed. Put new entry in the left subtree of old one<br/>
	 * 
	 * @param key
	 * @param value
	 * @return Entry<K,V>
	 */
	T insert(Object value);

	/**
	 * Find a node n with key k.<br/>
	 * Return null if k not in tree<br/>
	 * Scenarios:
	 * <ol>
	 * <li>if n has no children, detach it from parent
	 * <li>if n has one child, move n's child up to take n's place<br/>
	 * Eg: remove(30) from tree above:
	 * 
	 * <pre>
	 *                       18
	 *                     /    \
	 *                   12      25
	 *                  /  \    /  \
	 *                 4   15  25  28
	 *                /   /  \       \
	 *               1   13  17      29
	 *                \   \        
	 *                 3   14
	 * </pre>
	 * 
	 * <li>if n has 2 children, let x be node in n's right subtree with the smallest
	 * key.<br/>
	 * remove x -- x has no left child and is easily removed.<br/>
	 * Replace n's key with x's key.<br/>
	 * Eg: remove(12)
	 * 
	 * <pre>
	 *                       18
	 *                     /    \
	 *                   13      25
	 *                  /  \    /  \
	 *                 4   15  25  28
	 *                /   /  \       \
	 *               1   14  17      29
	 *                \           
	 *                 3
	 * </pre>
	 * 
	 * </ol>
	 * 
	 * @param key
	 *            the key to remove
	 * @return the removed Entry
	 */
	T remove(Object key);

}
