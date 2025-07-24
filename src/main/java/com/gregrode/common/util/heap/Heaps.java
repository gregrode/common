package com.gregrode.common.util.heap;

import java.util.ArrayList;
import java.util.List;

import com.gregrode.common.util.tree.TreeNode;

public class Heaps {
	/**
	 * @param index
	 * @return int
	 */
	public static int getRightIndex(int index) {
		return (2 * index) + 2;
	}

	/**
	 * @param index
	 * @return int
	 */
	public static int getLeftIndex(int index) {
		return (2 * index) + 1;
	}

	/**
	 * @return {@link List}
	 */
	public static <E> List<E> convertTreetoArray(TreeNode<E> tree) {
		TreeNode<E> node = tree.getRoot();
		TreeNode<E> left = node.getLeftNode();
		TreeNode<E> right = node.getRightNode();

		final List<E> list = new ArrayList<E>(node.size());
		int index = 0;
		list.add(node.getEntry());
		while ((left != null) || (right != null)) {
			if (left != null) {
				list.add(getLeftIndex(index), left.getEntry());
			}

			if (right != null) {
				list.add(getRightIndex(index), right.getEntry());
			}
			index++;
			node = ((index % 2) == 0) ? node.getLeftNode() : node.getRightNode();
			left = node.getLeftNode();
			right = node.getRightNode();
		}
		return list;
	}
}
