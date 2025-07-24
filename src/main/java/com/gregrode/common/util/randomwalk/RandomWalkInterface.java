package com.gregrode.common.util.randomwalk;

public interface RandomWalkInterface
{

	static final int MOVE_NORTH = 0;
	static final int MOVE_EAST = 1;
	static final int MOVE_SOUTH = 2;
	static final int MOVE_WEST = 3;

	/**
	 * Move the coordinates to based on the given direction.
	 * 
	 * @param direction
	 *            the direction represented by the following numbers:
	 *            <ul>
	 *            <li>NORTH -> 1
	 *            <li>EAST -> 2
	 *            <li>SOUTH -> 3
	 *            <li>WEST -> 4
	 *            </ul>
	 * @return int[]
	 */
	int[] move(int direction);

	/**
	 * Reset the coordinates to the originals.
	 */
	void resetCoords();

	/**
	 * Get the current coordinates
	 * 
	 * @return int[]
	 */
	int[] getCurrentCoords();

	/**
	 * @return get the total number of steps traveled by the drunk.
	 */
	double getTotalSteps();
}
