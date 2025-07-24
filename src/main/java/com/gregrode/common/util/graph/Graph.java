package com.gregrode.common.util.graph;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Graph extends JFrame implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	protected int panelx = 301, panely = 301;
	private Map<String, JButton> buttons = new HashMap<String, JButton>();
	protected Graphics paper;
	protected Point defaultPoint = new Point();

	/**
	 * Create the GUI
	 */
	public void createGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container window = getContentPane();
		window.setLayout(new FlowLayout());
		setTitle("Graph");
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(panelx, panely));
		panel.setBackground(Color.white);
		window.add(panel);
	}

	/**
	 * Creates a JButton object and add it to the given Container and internal Map
	 * 
	 * @param buttonName
	 *            the name of the JButton
	 */
	public void addJButton(String buttonName) {
		JButton button = new JButton(buttonName);
		Container window = getContentPane();
		window.add(button);
		button.addActionListener(this);
		buttons.put(buttonName, button);
	}

	/**
	 * Clear the screen.
	 */
	public void clear() {
		if (paper == null) {
			paper = panel.getGraphics();
		}
		paper.setColor(Color.white);
		paper.fillRect(0, 0, panelx, panely);
	}

	/**
	 * 
	 * @param buttonName
	 *            the name of the button the get from the Map
	 * @return JButton
	 */
	public JButton getButton(String buttonName) {
		return buttons.get(buttonName);
	}

	/**
	 * 
	 */
	public void makeGrid() {
		paper.setColor(Color.gray);
		int move = 0;
		for (int index = 0; index < panelx; index++) {
			paper.drawLine(0, move, panelx, move);
			paper.drawLine(move, 0, move, panely);
			move += 10;
		}
	}

	/**
	 * 
	 */
	public void makeGraph() {
		makeGraph(defaultPoint.x, defaultPoint.y);
	}

	/**
	 * 
	 */
	public void makeGraph(int xAxis, int yAxis) {
		paper.setColor(Color.gray);
		paper.drawLine(0, yAxis, panelx, yAxis);
		paper.drawLine(xAxis, 0, xAxis, panely);
	}

	public void resetLocation() {
		setOriginPoint(0, 0);
	}

	/**
	 * @param coords
	 *            the coordinates to set, where the first index (0) represents the
	 *            x-axis and the second index (1) represents the y-axis
	 * 
	 */
	public void setCoordinates(String... coords) {
		if (coords.length < 2) {
			return;
		}
		try {
			int x = Integer.parseInt(coords[0].trim());
			int y = Integer.parseInt(coords[1].trim());
			setCoordinates(x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param coords
	 *            the coordinates to set, where the first index (0) represents the
	 *            x-axis and the second index (1) represents the y-axis
	 */
	public void setCoordinates(int... coords) {
		if (coords.length < 2) {
			return;
		}
		Point point = modifyCoordinates(coords);
		paper.setColor(Color.red);
		paper.fillOval(point.x, point.y, 2, 2);
	}

	/**
	 * Set the origin point the to given x and y axis.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public void setOriginPoint(int x, int y) {
		defaultPoint.setLocation(x, y);
	}

	/**
	 * @param coords
	 * @return
	 */
	private Point modifyCoordinates(int... coords) {
		Point point = new Point();

		int x = coords[0] + defaultPoint.x;
		int y = coords[1] + defaultPoint.y;
		point.setLocation(x, y);
		return point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public abstract void itemStateChanged(ItemEvent event);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public abstract void actionPerformed(ActionEvent event);

}
