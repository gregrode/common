package com.gregrode.common.util.math;

import java.util.Random;

public final class NumberGenerator
{
	private static final Random RANDOM = new Random();

	/**
	 * private constructor
	 */
	private NumberGenerator()
	{
	}

	/**
	 * @return get random positive number between 1 and 10
	 */
	public static int getRandomInteger()
	{
		final int rand = RANDOM.nextInt(10);
		return (rand == 0) ? 1 : rand;
	}

	/**
	 * @return get a random number (possible negative) between 1 and 10
	 */
	public static int getRandomNegativeInteger()
	{

		return (RANDOM.nextBoolean()) ? getRandomInteger() : getNegativeInteger();
	}

	/**
	 * @return get a random number negative between 1 and 10
	 */
	public static int getNegativeInteger()
	{
		final int rand = getRandomInteger();
		return ~(rand) + 1;
	}

}
