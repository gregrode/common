package com.gregrode.common.util.tree;

/**
/ * Binary tree Node
 * 
 * @author Greg Dennis
 *
 * @param <T>
 */
public interface TreeNode<T> {

	/**
	 * Traverse the tree from self, left then right.
	 * 
	 * <pre>
	 *                     F
	 *                  /    \
	 *                B       G
	 *               / \       \
	 *             A    D       I
	 *                 /  \    /
	 *             	  C    E  H
	 * </pre>
	 * 
	 * The output is following: F, B, A, D, C, E, G, I, H
	 */
	void preOrder();

	/**
	 * Traverse the tree from left, self then right.
	 * 
	 * <pre>
	 *                     F
	 *                  /    \
	 *                B       G
	 *               / \       \
	 *             A    D       I
	 *                 /  \    /
	 *             	  C    E  H
	 * </pre>
	 * 
	 * The output is the following: A, B, C, D, E, F, G, H, I
	 */
	void inOrder();

	/**
	 * Traverse the tree from left, right, then self.
	 * 
	 * <pre>
	 *                     F
	 *                  /    \
	 *                B       G
	 *               / \       \
	 *             A    D       I
	 *                 /  \    /
	 *             	  C    E  H
	 * </pre>
	 * 
	 * The output is the following: A, C, E, D, B, H, I, G, F
	 */
	void postOrder();

	/**
	 * Performs the action on the visited node and set a boolean indicating that
	 * this node was visited
	 */
	void visit();

	/**
	 * @return a boolean indicating that this node was visited
	 */
	boolean isVisited();

	/**
	 * @param value
	 *            set the value of the left node
	 */
	void setLeftNode(T value);

	/**
	 * @param left
	 *            the left node
	 */
	void setLeftNode(TreeNode<T> left);

	/**
	 * @param value
	 *            set the value of the right node
	 */
	void setRightNode(T value);

	/**
	 * @param right
	 *            the right node
	 */
	void setRightNode(TreeNode<T> right);

	/**
	 * @return the value of the node
	 */
	T getEntry();

	/**
	 * @param entry
	 *            set the value of the node
	 */
	void setEntry(T entry);

	/**
	 * @return the parent of the node
	 */
	TreeNode<T> getParent();

	/**
	 * @param parent
	 *            set the parent of the node
	 */
	void setParent(TreeNode<T> parent);

	/**
	 * @return the right node
	 */
	TreeNode<T> getRightNode();

	/**
	 * @return the left node
	 */
	TreeNode<T> getLeftNode();

	/**
	 * @return a boolean that indicates whether this node is the root node
	 */
	boolean isRootNode();

	/**
	 * set the boolean to false that indicate that the node was visited.
	 */
	void leave();

	/**
	 * @return a boolean that indicates that the node has both it's left and right
	 *         children. This must be true for all it's children.
	 */
	boolean isBalanced();

	/**
	 * @param leaf
	 *            a boolean that indicated that the node is a leaf
	 */
	void setLeaf(boolean leaf);

	/**
	 * @return a boolean that indicates that the node is a leaf
	 */
	boolean isLeaf();

	/**
	 * @return the size of the tree
	 */
	int size();

	/**
	 * @return the height of node in tree. Height is maximum path length to
	 *         descendant.
	 */
	int height();

	/**
	 * @return Compute the depth of a node. The depth is the path length from node
	 *         to root.
	 */
	int depth();

	/**
	 * @return the root node of the supplied tree
	 */
	TreeNode<T> getRoot();

}
