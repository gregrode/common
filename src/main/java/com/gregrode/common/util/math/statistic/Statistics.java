package com.gregrode.common.util.math.statistic;

/**
 * 
 * @author Gregroy Dennis<br/>
 *         Last updated : November 18, 2011<br/>
 *         Copyright &copy 2011
 *
 */
public class Statistics {

	/**
	 * Find the number of possible combinations of r objects from the a set of n
	 * objects (written nCr).
	 * 
	 * @param n
	 *            the set of object
	 * @param r
	 *            possible combinations
	 * @return double
	 */
	public static double findCombination(final int n, final int r) {
		return (findFactorial(n) / findFactorial(r)) * findFactorial(n - r);
	}

	/**
	 * Find the number of possible permutation of k objects from a set of n objects
	 * (written nPk).
	 * 
	 * @param set
	 *            the set of object
	 * @param combinations
	 *            possible combinations
	 * @return double
	 */
	public static double findPermutation(final int set, final int combinations) {
		return findFactorial(set) / findFactorial(set - combinations);
	}

	/**
	 * find the factorial of the given number.
	 * 
	 * @param num
	 *            the number whose factorial to find
	 * @return long
	 */
	public static long findFactorial(final int num) {
		if (num <= 1) {
			return 1;
		}
		return num * findFactorial(num - 1);
	}

	/**
	 * Find Z score of the given sample
	 * 
	 * @param predictionInterval
	 * @param sample
	 * @return double
	 */
	public static double findZScoreWithUnknownVariance(final double predictionInterval, final int[] sample) {
		final int sampleSize = sample.length;
		final double mean = findMean(sample);
		final double stdDeviation = findStandardDeviation(sample, mean);
		return (predictionInterval - mean) / (stdDeviation / Math.sqrt(sampleSize));
	}

	/**
	 * Find the z score of the given sample using the prediction interval.
	 * 
	 * @param predictionInterval
	 *            the estimate of an internal in which future observations will
	 *            fall, with a certain probability, given what has already been
	 *            observed.
	 * @param sample
	 *            the data sample
	 * @return double
	 */
	public static double findZScore(final double predictionInterval, final int[] sample) {
		final double mean = findMean(sample);
		final double stdDeviation = findStandardDeviation(sample, mean);
		return (predictionInterval - mean) / stdDeviation;
	}

	/**
	 * Find the standard deviation of the given sample
	 * 
	 * @param sample
	 *            an list (array) of data.
	 * 
	 * @return double
	 */
	public static double findStandardDeviation(final int[] sample) {
		final int size = sample.length;
		final double mean = findMean(sample);
		double total = 0;
		for (int index = 0; index < size; index++) {
			final int num = sample[index];
			total += Math.pow((num - mean), 2);
		}
		return Math.sqrt(total / (size - 1));
	}

	/**
	 * Find the standard deviation of the given sample
	 * 
	 * @param sample
	 *            an list (array) of data.
	 * @param mean
	 *            the mean of the given sample.
	 * 
	 * @return double
	 */
	public static double findStandardDeviation(final int[] sample, double mean) {
		final int size = sample.length;
		double total = 0;
		for (int index = 0; index < size; index++) {
			final int num = sample[index];
			total += Math.pow((num - mean), 2);
		}
		final double stdDeviation = Math.sqrt(total / (size - 1));
		return stdDeviation;
	}

	/**
	 * Find the mean of the given sample.
	 * 
	 * @param sample
	 *            an list (array) of data.
	 * 
	 * @return double
	 */
	public static double findMean(int[] sample) {
		final int size = sample.length;
		double total = 0, mean = 0;
		for (int index = 0; index < size; index++) {
			total += sample[index];
		}
		mean = total / size;
		return mean;
	}
}
