package com.gregrode.common.util.tree;

/**
 * Assume both opponents are infinitely intelligent. Each grid is assigned an
 * numerical score that indicates how optimistic we are about winning. <br/>
 * <ul>
 * <li>1 : computer is guaranteed to win
 * <li>-1 : opponent is guaranteed to win
 * <li>0 : if perfect, players will draw
 * </ul>
 * 
 * <b>Minimax algorithm</b><br/>
 * Consider each possible move. determine each child grid<br/>
 * Score each child by calling minimax recursively<br/>
 * <b>Note</b>, each grid is game tree represents one invocation of the choose
 * chooseMove(). <br/>
 * <br/>
 * Score parent grid <br/>
 * Computer turn : choose move that yields the maximum score <br/>
 * Opponent turn : choose move that yields the minimum score <br/>
 * <br/>
 * <br/>
 * <b>Simple pruning algorithm</b><br/>
 * Note, if you find a winning move, then there is no need to continue look for
 * a better move<br/>
 * <br/>
 * <br/>
 * <b>Alpha-beta pruning</b><br/>
 * Alpha : a score the computer knows with certainty it can achieve<br/>
 * Beta : the opponent can achieve a score of beta or better<br/>
 * eg. alpha = 0 : the computer knows it can force a draw<br/>
 * 
 * If beta becomes less than or equal to alpha, further investigation of current
 * grid is useless.<br/>
 * 
 * Simply: if opponent2 has a better move that opponent1, then opponent2 will
 * take that move. Therefore, there is no need to investigation the leaf nodes
 * of that possibly thus saving some computation time.<br/>
 * <br/>
 * <br/>
 * 
 * Minimax algorithm check if grid is in the hash table
 * <ul>
 * <li>Yes - return score
 * <li>No - evaluale score and store value in hash table
 * </ul>
 * 
 * @author Gregroy Dennis
 * 
 */
public class GameTree {

	public static final boolean COMPUTER = true;
	public static final boolean OPPONENT = false;

	public BestMove chooseMove(boolean side, int alpha, int beta) {
		BestMove best = new BestMove();
		int defaultScore = alpha;
		if (side == OPPONENT) {
			defaultScore = beta;
		}
		best.setScore(defaultScore);

		return best;
	}

}
