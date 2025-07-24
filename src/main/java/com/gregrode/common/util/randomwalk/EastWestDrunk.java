package com.gregrode.common.util.randomwalk;

/**
 * @author gdennis
 * 
 */
public class EastWestDrunk extends Drunk
{

	/**
	 * Default constructor
	 */
	public EastWestDrunk()
	{
		this(10, 10);
	}

	/**
	 * Full Constructor
	 * 
	 * @param xInterval
	 *            the number of steps taken for each move on the x-axis
	 * @param yInterval
	 *            the number of steps taken for each move on the y-axis
	 */
	public EastWestDrunk(int xInterval, int yInterval)
	{
		super(xInterval, yInterval);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gregrode.common.util.randomwalk.Drunk#move(int)
	 */
	@Override
	public int[] move(int direction)
	{
		int x = coords[0];
		int y = coords[1];
		switch (direction)
		{
			case MOVE_NORTH:
			case MOVE_EAST:
				x += xInterval;
				break;
			case MOVE_SOUTH:
			case MOVE_WEST:
				x -= xInterval;
				break;
			default:
				break;
		}
		coords[0] = x;
		coords[1] = y;
		return coords;
	}
}
