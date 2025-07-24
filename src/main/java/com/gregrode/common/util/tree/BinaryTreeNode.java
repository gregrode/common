package com.gregrode.common.util.tree;

public class BinaryTreeNode<T> implements TreeNode<T> {
	private T entry;
	private TreeNode<T> parent;
	private TreeNode<T> left;
	private TreeNode<T> right;
	private boolean visited;
	private boolean leaf;

	/**
	 * 
	 */
	public BinaryTreeNode() {
	}

	/**
	 * 
	 */
	public BinaryTreeNode(T entry) {
		setEntry(entry);
	}

	/**
	 * 
	 */
	public BinaryTreeNode(T entry, TreeNode<T> parent) {
		this.entry = entry;
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#preOrder()
	 */
	@Override
	public void preOrder() {
		visit();
		if (left != null) {
			left.preOrder();
		}

		if (right != null) {
			right.preOrder();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#inOrder()
	 */
	@Override
	public void inOrder() {
		if (left != null) {
			left.inOrder();
		}
		visit();

		if (right != null) {
			right.inOrder();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#postOrder()
	 */
	@Override
	public void postOrder() {

		if (left != null) {
			left.postOrder();
		}

		if (right != null) {
			right.postOrder();
		}
		visit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#visit()
	 */
	@Override
	public void visit() {
		visited = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#isVisited()
	 */
	@Override
	public boolean isVisited() {
		return visited;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#setLeftNode(java
	 * .lang.Object)
	 */
	@Override
	public void setLeftNode(T value) {
		setLeftNode(new BinaryTreeNode<>(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#setLeftNode(com
	 * .gregrode.common.util.tree.BinaryTreeNodeInterface)
	 */
	@Override
	public void setLeftNode(TreeNode<T> left) {
		if (!isLeaf()) {
			this.left = left;
			this.left.setParent(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#setRightNode(java
	 * .lang.Object)
	 */
	@Override
	public void setRightNode(T value) {
		setRightNode(new BinaryTreeNode<>(value));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#setRightNode(com
	 * .gregrode.common.util.tree.BinaryTreeNodeInterface)
	 */
	@Override
	public void setRightNode(TreeNode<T> right) {
		if (!isLeaf()) {
			this.right = right;
			this.right.setParent(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#getEntry()
	 */
	@Override
	public T getEntry() {
		return entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#setEntry(java.lang
	 * .Object)
	 */
	@Override
	public void setEntry(T entry) {
		this.entry = entry;
		this.left = null;
		this.right = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#getParent()
	 */
	@Override
	public TreeNode<T> getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#setParent(com.
	 * gregrode .common.util.tree.BinaryTreeNodeInterface)
	 */
	@Override
	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#getRightNode()
	 */
	@Override
	public TreeNode<T> getRightNode() {
		return right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#getLeftNode()
	 */
	@Override
	public TreeNode<T> getLeftNode() {
		return left;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#isRootNode()
	 */
	@Override
	public boolean isRootNode() {
		return (parent == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#leave()
	 */
	@Override
	public void leave() {
		if (left != null) {
			left.leave();
		}
		visited = false;

		if (right != null) {
			right.leave();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#isBalanced()
	 */
	@Override
	public boolean isBalanced() {
		if (leaf) {
			return true;
		}

		if ((left != null && left.isBalanced()) && (right != null && right.isBalanced())) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#setLeaf(boolean)
	 */
	@Override
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
		if (this.leaf) {
			left = null;
			right = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return leaf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#size()
	 */
	@Override
	public int size() {
		int size = 1;
		if (left != null) {
			size += left.size();
		}

		if (right != null) {
			size += right.size();
		}
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#height()
	 */
	@Override
	public int height() {
		int leftHeight = 0, rightHeight = 0;
		if (left != null) {
			leftHeight = left.height();
		}

		if (right != null) {
			rightHeight = right.height();
		}

		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		}
		return rightHeight + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinaryTreeNodeInterface#depth()
	 */
	@Override
	public int depth() {
		int depth = 1;
		TreeNode<T> parent = getParent();
		while (parent != null) {
			depth++;
			parent = parent.getParent();
		}
		return depth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.tree.BinarySearchTree#getRoot()
	 */
	@Override
	public TreeNode<T> getRoot() {
		TreeNode<T> node = this;
		while (!node.isRootNode()) {
			node = node.getParent();
		}
		return node;
	}

}
