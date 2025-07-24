package com.gregrode.common.util.randomwalk;


public class RandomWalk
{

	public static void main(String...args)
	{
		RandomWalkGraph  field = new RandomWalkGraph();
		field.setSize(350,450);
		field.createGUI();	
		field.setVisible(true);
	}
}
