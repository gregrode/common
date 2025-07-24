package com.gregrode.common.util.tree;

/**
 * Balanced binary search tree<br/>
 * All operations O(log n) time on average.<br/>
 * 
 * Single operation 0(n) worst-case time, where n is number of items in
 * tree<br/>
 * 
 * Any Sequence of k operations, starting from empty tree, never > n items in
 * tree, O(k log n) worst-case time<br/>
 * 
 * Easier to program that 2-3-4 trees<br/>
 * Fast access to entries accessed recently.<br/>
 * 
 * 
 * <b>Tree Rotations</b><br/>
 * Splay trees are kept balanced with rotations.<Br/>
 * 
 * <pre>
 *                   Y     <- rotate left       X
 *                  / \       rotate Right ->  / \
 *                 X   C                       A   Y
 *                / \                             / \
 *               A   B                           B   C
 * 
 * 
 * </pre>
 * 
 * Splay trees are not kept perfectly balanced.
 * 
 * Slpay tree Operations
 * 
 * @author Gregroy Dennis<br/>
 * 
 */
public interface SplayTree<K, V> {

	/**
	 * 
	 * Begin like an ordinary BST: walk down tree to entry with key k or
	 * deadend.<br/>
	 * Let X be node where search ended, whether it contains K or not. Splay X up
	 * the tree through a sequence of rotations, so X becomes root.<br/>
	 * 
	 * 3 Cases
	 * <ol>
	 * <li>X is left child of right child OR right child of a left child
	 * 
	 * <pre>
	 *      G            G             X
	 *     / \          / \          /   \
	 *    P   D  ->    X   D   ->   P     G
	 *   / \          / \          / \   / \
	 *  A   X        P   C        A   B C   D
	 *     / \      / \          
	 *    B   C    A   B          
	 *    
	 *    "Zig-Zag"
	 * </pre>
	 * 
	 * <li>X is left child of a left child OR right child of a right child
	 * 
	 * <pre>
	 *             G              P               X
	 *            / \           /   \            / \
	 *           P   D   ->    X     G     ->   A   P
	 *          / \           / \   / \            / \
	 *         X   C         A   B C   D          B   G
	 *        / \                                    / \
	 *       A   B                                  C   D
	 *       
	 *       "Zig-Zig"
	 * </pre>
	 * 
	 * <li>Repeat 1 and 2 until finish or X is child of the root.
	 * 
	 * <pre>
	 *         P           X
	 *        / \         / \
	 *       X   D   ->  A   P
	 *      / \             / \
	 *     A   B           B   D
	 *     
	 *     "Zig"
	 * 
	 * </pre>
	 * 
	 * </ol>
	 * 
	 * A node initially at depth of d an access from root to X moves to final depth
	 * <= 3 + d/2
	 * 
	 * @param key
	 *            The key to search for
	 * @return The Object with given key or the very last object searched
	 */
	public V find(K key);

	/**
	 * Find entry with min/max key. Splay it to root
	 * 
	 * @return V
	 */
	public V first();

	public V last();

	/**
	 * Insert new entry. Splay new node to the root
	 * 
	 * @param key
	 * @param value
	 */
	public void insert(K key, V value);

	/**
	 * An entry having key k is removed from tree, as with ordinary BST. Let X be
	 * the node removed from the tree. Splay X's parent to root. <br/>
	 * remove(2):
	 * 
	 * <pre>
	 *       2              4            5
	 *      / \            / \          / \
	 *     1   7     ->   1   7   ->   4   7
	 *        / \            / \      /     \
	 *       5   8          5   8    1       8
	 *      /
	 *     4
	 * </pre>
	 * 
	 * If key k is not in tree, splay the node where the search ended to the root.
	 * 
	 * @param key
	 * @return V
	 */
	public V remove(K key);

}
