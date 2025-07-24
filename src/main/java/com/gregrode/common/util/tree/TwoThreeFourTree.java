package com.gregrode.common.util.tree;

import java.util.Map.Entry;

/**
 * 2-3-4 Trees<br/>
 * Perfectly balanced tree<br/>
 * find, insert, remove worst-case O(log n) time. Every node have 2, 3, or 4
 * children, except leaves, which are all at he bottom level Each node stores 1,
 * 2, or 3 entries. number of children is
 * 
 * <pre>
 *                         {20,        40,        50}
 *                         /         /     \        \
 *                        14        32      43      {70,             79}
 *                       /  \      /  \    /  \      /          |      \
 *                      10  18   25   33  42  47   {57, 62, 66} 74     81
 * 
 * 
 * 
 * </pre>
 * 
 * <b>Running times<b/><br/>
 * A 2-3-4 tree with depth d has betweem 2^d and 4^d leaves<br/>
 * 
 * Total numbers of node is n >= 2^(d+1) - 1<br/>
 * d <= O(log n)<br/>
 * Time spent visition node O(1).<br/>
 * 
 * Find(), insert(), remove() worst-case times O(d) = O(log n)<br/>
 * compared with Binary search tree, whose worst O(n)<br/>
 * 
 * Another approach to duplicate keys: Collect all entries that shave a common
 * key in one node. Each node's entry is a list of entries. <br/>
 * 
 * @author greg
 * 
 */
public interface TwoThreeFourTree<K, V> {

	public Entry<K, V> find(K key);

	/**
	 * Walk down tree in search of K If i finds K, it proceeds to k's "left child"
	 * and continues.<br/>
	 * Whenever insert() encounters a 3 key node, middle key is placed in the parent
	 * node. (parent has at most 2 keys; thus has room for the third)<br/>
	 * Why we split 3-key nodes;
	 * <ul>
	 * <li>to make sure there's room for new key in leaf.
	 * <li>to make room for any key that's kicked up stairs. Sometimes insertion
	 * increases depth of tree by create new root node
	 * </ul>
	 * 
	 * @param key
	 *            the key to insert
	 * @param value
	 *            the value to insert
	 */
	public void insert(K key, V value);

	/**
	 * Find key k.
	 * <ul>
	 * <li>if its in leaf, remove it.
	 * <li>if in internal node, replace it with entry with next higher key.
	 * </ul>
	 * 
	 * Eliminates 1-key nodes (except the root) so key can be removed from a lead
	 * without emptying it. Rules:
	 * <ol>
	 * <li>Remove() rencounters 1 Key node (except root): tries to steal key from an
	 * adjacent sibling
	 * 
	 * <pre>
	 *                           {20,      40}
	 *                           /     |      \
	 *                          10     30      {50, 51, 52}
	 *                                /  \    /   /   \    \
	 *                                       |s|
	 * </pre>
	 * 
	 * eg: remove(30) rotation operation:
	 * 
	 * <pre>
	 *                           {20,      50}
	 *                           /     |      \
	 *                          10  {30 ,40}     {51, 52}
	 *                              /  |  \      /   |   \
	 *                                    |s|
	 * </pre>
	 * 
	 * <li>If no adjacent sibling has > 1 key, steal a key from parent.<bt/> Parent
	 * (unless its root) has more than 2 keys <br/>
	 * 
	 * <pre>
	 *                           {20,      40}         
	 *                           /     |      \
	 *                          10     30     50
	 * </pre>
	 * 
	 * eg: remove(10) fusion operation: <br/>
	 * 
	 * <pre>
	 *                              40         
	 *                             /  \
	 *                  {10, 20, 30}   50
	 * </pre>
	 * 
	 * <li>if parent is root and contains only one key and sibling has one key<br>
	 * Fuse into 3 key node --> the new root Depth of the tree decreases by one.
	 * 
	 * </ol>
	 * 
	 * @param key
	 *            the key to removed
	 */
	public void remove(K key);

}
