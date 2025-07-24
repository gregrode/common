package com.gregrode.common.util.randomwalk;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import com.gregrode.common.util.graph.Graph;

public class RandomWalkGraph extends Graph
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final String MOVE_HISTOGRAM = "Move Histogram";
	private static final String MOVE_GRAPH = "Move Graph";
	private static final String PLOT_POINT = "Plot Point";

	private static final String CLEAR = "Clear";
	private RandomWalkInterface drunk = new UsualDrunk();

	private final Map<String, JRadioButton> radios = new HashMap<String, JRadioButton>();

	private ButtonGroup buttonGroup;

	@Override
	public void createGUI()
	{
		super.createGUI();
		setTitle("Random Walk Graph");

		addRadioButton(UsualDrunk.class.getSimpleName(), true);
		addRadioButton(NortheastDrunk.class.getSimpleName(), false);
		addRadioButton(EastWestDrunk.class.getSimpleName(), false);

		addJButton(MOVE_HISTOGRAM);
		addJButton(MOVE_GRAPH);
		addJButton(PLOT_POINT);
		addJButton(CLEAR);

	}

	private void addRadioButton(String buttonName, boolean isSelected)
	{
		if (buttonGroup == null)
		{
			buttonGroup = new ButtonGroup();
		}
		final Container window = getContentPane();
		final JRadioButton button = new JRadioButton(buttonName, isSelected);
		buttonGroup.add(button);
		window.add(button);
		button.addItemListener(this);
		radios.put(buttonName, button);

	}

	@Override
	public void itemStateChanged(ItemEvent event)
	{
	}

	private RandomWalkInterface getDrunk(int... args)
	{
		Drunk drunk = null;
		try
		{
			for (final String key : radios.keySet())
			{
				final JRadioButton radio = radios.get(key);
				if (!radio.isSelected())
				{
					continue;
				}
				final Class<?> clazz = Class.forName(this.getClass().getPackage().getName() + "." + key);

				if (args.length < 2)
				{
					final Constructor<?> constructor = clazz.getConstructor();
					drunk = (Drunk) constructor.newInstance();
					break;
				}
				final int xStep = args[0];
				final int yStep = args[1];
				final Constructor<?> constructor = clazz.getConstructor(int.class, int.class);
				drunk = (Drunk) constructor.newInstance(xStep, yStep);

			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return drunk;
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		try
		{
			clear();
			final JButton source = (JButton) event.getSource();
			if (MOVE_HISTOGRAM.equals(source.getText()))
			{
				drunk = getDrunk();
				setOriginPoint(151, 151);
				getHistogram();
			}
			if (MOVE_GRAPH.equals(source.getText()))
			{
				drunk = getDrunk(1, 1);
				setOriginPoint(0, 299);
				getLineGraph();
			}
			if (PLOT_POINT.equals(source.getText()))
			{
				resetLocation();
				makeGrid();
				final String coords = JOptionPane.showInputDialog("Set coordinates:");
				if (coords == null)
				{
					return;
				}
				setCoordinates(coords.split(","));
			}
		}
		catch (final Throwable th)
		{
			th.printStackTrace();
		}

	}

	public void getHistogram()
	{
		makeGraph();
		final int time = Integer.parseInt(JOptionPane.showInputDialog("Enter number of moves:"));
		final Random random = new Random();
		for (int index = 0; index < time; index++)
		{
			final int direction = random.nextInt(4);
			final int[] coord = drunk.move(direction);
			setCoordinates(coord);
		}
		final double totalSteps = drunk.getTotalSteps();
		printTotalMoveMessage(time, totalSteps);
	}

	public void getLineGraph()
	{
		makeGrid();
		final int time = Integer.parseInt(JOptionPane.showInputDialog("Enter number of moves:"));
		final Random random = new Random();
		for (int index = 0; index < time; index++)
		{
			final int direction = random.nextInt(4);
			drunk.move(direction);
			final int totalSteps = (int) Math.ceil(drunk.getTotalSteps());
			final int[] coord = { index, totalSteps * -1 };
			setCoordinates(coord);
		}
		final double totalSteps = drunk.getTotalSteps();
		printTotalMoveMessage(time, totalSteps);
	}

	private String printTotalMoveMessage(int totalMoves, double totalSteps)
	{
		final StringBuilder str = new StringBuilder("Moving ").append(totalMoves).append(" times, ")
			.append(drunk.getClass().getSimpleName()).append(" traveled ").append(totalSteps).append(" steps from origin.");
		System.out.println(str.toString());
		return str.toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gregrode.common.util.graph.Graph#clear()
	 */
	@Override
	public void clear()
	{
		super.clear();
		drunk.resetCoords();
	}
}
