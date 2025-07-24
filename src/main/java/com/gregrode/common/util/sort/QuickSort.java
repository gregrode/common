package com.gregrode.common.util.sort;

import java.util.Arrays;

public class QuickSort {

	public int[] sort(int[] array) {

		int len = array.length;
		if (len <= 1) {
			return array;
		}
		int pivotIndex = (len) / 2;
		int pivot = array[pivotIndex];
		int value, lessIndex = 0, greaterIndex = 0;

		int[] less = new int[len];
		int[] greater = new int[len];

		for (int index = 0; index < len; index++) {
			value = array[index];
			if (index == pivotIndex || value == 0) {
				continue;
			}

			if (value <= pivot) {
				less[lessIndex] = value;
				lessIndex++;
			}

			if (value > pivot) {
				greater[greaterIndex] = value;
				greaterIndex++;
			}
		}

		less = Arrays.copyOf(less, lessIndex);
		greater = Arrays.copyOf(greater, greaterIndex);
		return concatenate(sort(less), pivot, sort(greater));
	}

	public double[] sort(double[] array) {
		int len = array.length;
		if (len <= 1) {
			return array;
		}
		int pivotIndex = (len) / 2;
		double pivot = array[pivotIndex];
		double value;
		int lessIndex = 0, greaterIndex = 0;

		double[] less = new double[len];
		double[] greater = new double[len];

		for (int index = 0; index < len; index++) {
			value = array[index];
			if (index == pivotIndex || value == 0) {
				continue;
			}

			if (value <= pivot) {
				less[lessIndex] = value;
				lessIndex++;
			}

			if (value > pivot) {
				greater[greaterIndex] = value;
				greaterIndex++;
			}
		}

		less = Arrays.copyOf(less, lessIndex);
		greater = Arrays.copyOf(greater, greaterIndex);
		return concatenate(sort(less), pivot, sort(greater));
	}

	private static double[] concatenate(double[] less, double pivot, double[] greater) {
		int size = less.length + greater.length + 1, len = 0;
		double[] array = new double[size];
		System.arraycopy(less, 0, array, 0, less.length);
		if (less.length > 0) {
			len = less.length;
		}
		array[len] = pivot;
		System.arraycopy(greater, 0, array, len + 1, greater.length);
		return array;
	}

	private static int[] concatenate(int[] less, int pivot, int[] greater) {
		int size = less.length + greater.length + 1, len = 0;
		int[] array = new int[size];
		System.arraycopy(less, 0, array, 0, less.length);
		if (less.length > 0) {
			len = less.length;
		}
		array[len] = pivot;
		System.arraycopy(greater, 0, array, len + 1, greater.length);
		return array;
	}

}
