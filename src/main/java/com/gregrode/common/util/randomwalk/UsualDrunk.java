package com.gregrode.common.util.randomwalk;


/**
 * @author gdennis
 *
 */
public class UsualDrunk extends Drunk
{

	
	/**
	 * 
	 */
	public UsualDrunk()
	{
		this(10, 10);
	}
	
	/**
	 * @param xInterval
	 * @param yInterval
	 */
	public UsualDrunk(int xInterval, int yInterval)
	{
		super(xInterval, yInterval);	
	}
	
	
	/* (non-Javadoc)
	 * @see com.gregrode.common.util.randomwalk.Drunk#move(int)
	 */
	@Override
	public int[] move(int direction)
	{
		int x = coords[0];
		int y = coords[1];
			switch(direction)
			{
				case MOVE_EAST:
					x += xInterval;
					break;
				case MOVE_WEST:
					x -= xInterval;
					break;
				case MOVE_NORTH:
					y += yInterval;
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
