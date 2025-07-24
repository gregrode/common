package com.gregrode.common.util.tree;

public class BinarySearchTree<T extends TreeNode<?>> implements BinaryTree<T> {
	private T node;

	public BinarySearchTree(T node) {
		this.node = node;
	}

	@Override
	public T find(Object key) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T first() {
		if (node == null)
			return null;

		T temp = node;
		while (temp.getLeftNode() != null) {
			temp = (T) temp.getLeftNode();
		}
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T last() {
		if (node == null)
			return null;

		T temp = node;
		while (temp.getRightNode() != null) {
			temp = (T) temp.getRightNode();
		}
		return temp;
	}

	@Override
	public T insert(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

}
