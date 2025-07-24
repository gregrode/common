package com.gregrode.common.util.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdennis
 *
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> extends MaxHeap<T> {

	public MinHeap(final T[] heap) {
		this(new ArrayList<>(List.of(heap)));
	}

	public MinHeap(final List<T> heap) {
		super(heap);
		buildHeap();
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
		int smallest = index;
		final int leftIndex = Heaps.getLeftIndex(index);
		final int rightIndex = Heaps.getRightIndex(index);

		final T left = getLeftNode(index);
		if ((left != null) && (left.compareTo(node) < 0)) {
			smallest = leftIndex;
			node = heap.get(smallest);
		}

		final T right = getRightNode(index);
		if ((right != null) && (right.compareTo(node) < 0)) {
			smallest = rightIndex;
		}

		if (smallest != index) {
			swap(index, smallest);
			heapify(smallest);
		}
		heapify(getParentIndex(index));
	}

}
