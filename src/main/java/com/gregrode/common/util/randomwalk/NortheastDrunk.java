package com.gregrode.common.util.randomwalk;

/**
 * @author gdennis
 * 
 */
public class NortheastDrunk extends Drunk
{

	/**
	 * Default constructor
	 */
	public NortheastDrunk()
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
	public NortheastDrunk(int xInterval, int yInterval)
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
			case MOVE_EAST:
				x += (xInterval * 2);
				break;
			case MOVE_WEST:
				x -= xInterval;
				break;
			case MOVE_NORTH:
				y += (yInterval * 2);
				break;
			case MOVE_SOUTH:
				y -= yInterval;
				break;
			default:
				break;
		}
		coords[0] = x;
		coords[1] = y;
		return coords;
	}
}
