package com.gregrode.common.util.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Greg Dennis
 *
 * @param <T>
 */
public class MaxHeap<T extends Comparable<T>> implements Heap<T> {

	private List<T> heap;

	public MaxHeap(final T[] heap) {
		this(new ArrayList<>(List.of(heap)));
	}

	public MaxHeap(final List<T> heap) {
		this.heap = heap;
		buildHeap();
	}

	@Override
	public List<T> getHeap() {
		return heap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.heap.Heap#heapify(int)
	 */
	@Override
	public void heapify(final int index) {

		if ((index >= size()) || (index < 0)) {
			return;
		}
		final List<T> heap = getHeap();
		T node = heap.get(index);
		int largest = index;
		final int leftIndex = Heaps.getLeftIndex(index);
		final int rightIndex = Heaps.getRightIndex(index);

		final T left = getLeftNode(index);
		if ((left != null) && (left.compareTo(node) > 0)) {
			largest = leftIndex;
			node = heap.get(largest);
		}

		final T right = getRightNode(index);
		if ((right != null) && (right.compareTo(node) > 0)) {
			largest = rightIndex;
		}

		if (largest != index) {
			swap(index, largest);
			heapify(largest);
		}
		heapify(getParentIndex(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.heap.Heap#insert(java.lang.Object)
	 */
	@Override
	public void insert(final T node) {
		insert(node);
		heapify(size() - 1);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.heap.Heap#delete(int)
	 */
	@Override
	public T delete(final int index) {
		final T node = delete(index);
		heapify(index);
		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.heap.Heap#buildHeap()
	 */
	@Override
	public void buildHeap() {
		int index = size() - 1;
		while (index >= 0) {
			heapify(index);
			index--;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.heap.Heap#sort()
	 */
	@Override
	public void sort() {
		int last = size() - 1;
		while (last > 0) {
			swap(0, last);
			last--;
			heapify(last);
		}
	}

	@Override
	public void visit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void preOrder(final int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inOrder(final int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postOrder(final int index) {
		// TODO Auto-generated method stub

	}

}
