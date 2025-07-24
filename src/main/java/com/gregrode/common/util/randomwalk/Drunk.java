package com.gregrode.common.util.randomwalk;


/**
 * @author gdennis
 *
 */
public abstract class Drunk implements RandomWalkInterface
{
	protected int[] coords;
	protected int xInterval;
	protected int yInterval;
	
	/**
	 * Default constructor
	 */
	public Drunk()
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
	public Drunk(int xInterval, int yInterval)
	{

		coords =  new int[2];
		this.xInterval = xInterval;
		this.yInterval = yInterval;	
	}
	
	/* (non-Javadoc)
	 * @see com.gregrode.common.util.randomwalk.Drunk#move(int)
	 */
	@Override
	public abstract int[] move(int direction);
	
	
	/* (non-Javadoc)
	 * @see com.gregrode.common.util.randomwalk.Drunk#getCurrentCoords()
	 */
	@Override
	public int[] getCurrentCoords()
	{
		return coords;
	}
	
	/* (non-Javadoc)
	 * @see com.gregrode.common.util.randomwalk.Drunk#resetCoords()
	 */
	@Override
	public void resetCoords()
	{
		coords = new int[2];
	}

	/* (non-Javadoc)
	 * @see com.gregrode.common.util.randomwalk.RandomWalkInterface#getTotalSteps()
	 */
	@Override
	public double getTotalSteps()
	{
		int a = Math.abs(coords[0]) / Math.abs(xInterval);
		int b = Math.abs(coords[1]) / Math.abs(yInterval);
		int aSquared =  a * a;
		int bSquared = b * b;
		
		// a^2 + b^2 = c^2 then c = sqrt( a^2 + b^2)
		return Math.sqrt(aSquared + bSquared);
	}

}
