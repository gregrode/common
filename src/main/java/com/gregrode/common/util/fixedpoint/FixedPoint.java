package com.gregrode.common.util.fixedpoint;

/**
 * The following code is an example of successive approximation
 * 
 * @author Greg Dennis
 *
 */
public class FixedPoint {
	public static double findFixedPoint(final double number) {
		return tryGuess(1, number);
	}

	private static double tryGuess(double guess, final double number) {

		if (isGuessGoodEnough(guess, number)) {
			return guess;
		}
		final double newGuess = (guess + (number / guess)) / 2.0;
		return tryGuess(newGuess, number);
	}

	private static boolean isGuessGoodEnough(double guess, final double number) {
		final double valueSquared = guess * guess;
		final double diff = Math.abs(number - valueSquared);
		if (diff < .0001) {
			return true;
		}
		return false;
	}
}
