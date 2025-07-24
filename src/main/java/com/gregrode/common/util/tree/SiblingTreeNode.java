package com.gregrode.common.util.tree;

public class SiblingTreeNode<T> {
	private T item;
	private SiblingTreeNode<T> parent;
	private SiblingTreeNode<T> firstChild;
	private SiblingTreeNode<T> nextSibling;

	public SiblingTreeNode() {
	}

	/**
	 * @return the item
	 */
	public T getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(T item) {
		this.item = item;
	}

	/**
	 * @return the parent
	 */
	public SiblingTreeNode<T> getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(SiblingTreeNode<T> parent) {
		this.parent = parent;
	}

	/**
	 * @return the firstChild
	 */
	public SiblingTreeNode<T> getFirstChild() {
		return firstChild;
	}

	/**
	 * @param firstChild
	 *            the firstChild to set
	 */
	public void setFirstChild(SiblingTreeNode<T> firstChild) {
		this.firstChild = firstChild;
	}

	/**
	 * @return the nextSibling
	 */
	public SiblingTreeNode<T> getNextSibling() {
		return nextSibling;
	}

	/**
	 * @param nextSibling
	 *            the nextSibling to set
	 */
	public void setNextSibling(SiblingTreeNode<T> nextSibling) {
		this.nextSibling = nextSibling;
	}

	public void preorder() {

	}

	public void postOrder() {

	}

	public void visit() {
		System.out.println("the value of item is " + item.toString());
	}
}
