package com.gregrode.common.util.tree;

public class BestMove {
	private int score;
	private int[] move;

	public BestMove() {
	}

	public BestMove(int score, int[] move) {
		this.score = score;
		setMove(move);
	}

	public BestMove(int score, int x, int y) {
		this.score = score;
		setMove(x, y);
	}

	/**
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return move
	 */
	public int[] getMove() {
		return move;
	}

	public void setMove(int[] move) {
		if (move == null || move.length < 2) {
			throw new IllegalArgumentException("Array cannot be null and should have a length of two");
		}
		this.move = move;
	}

	public void setMove(int x, int y) {
		if (move == null) {
			move = new int[2];
		}
		move[0] = x;
		move[1] = y;
	}

}
