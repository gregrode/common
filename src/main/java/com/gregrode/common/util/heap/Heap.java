package com.gregrode.common.util.heap;

import java.util.List;

/**
 * @author Greg Dennis
 *
 * @param <T>
 */
public interface Heap<T> {

	void heapify(int index);

	default void insert(T node) {
		getHeap().add(node);
	}

	default T delete(int index) {
		final int last = size() - 1;
		swap(index, last);
		return getHeap().get(last);
	}

	default int size() {
		return getHeap().size();
	}

	default T getRightNode(int index) {
		try {
			return getHeap().get(Heaps.getRightIndex(index));
		} catch (final Exception e) {
			return null;
		}
	}

	default T getLeftNode(int index) {
		try {
			return getHeap().get(Heaps.getLeftIndex(index));
		} catch (final Exception e) {
			return null;
		}
	}

	default T getParentNode(int index) {
		try {
			return getHeap().get(getParentIndex(index));
		} catch (final Exception e) {
			return null;
		}
	}

	default int getParentIndex(int index) {
		if (index == 0) {
			return -1;
		}
		return (index - 1) / 2;
	}

	default void swap(int first, int second) {
		// this is not in-place
		List<T> heap = getHeap();
		final T temp = heap.get(second);
		heap.add(second, heap.get(first));
		heap.add(first, temp);
	}

	public List<T> getHeap();

	void buildHeap();

	void sort();

	void visit();

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
	void preOrder(int index);

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
	void inOrder(int index);

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
	void postOrder(int index);
}
