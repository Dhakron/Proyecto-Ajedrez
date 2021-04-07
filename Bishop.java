package Main;

import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Bishop extends Piece {

	// constructor
	public Bishop(int color) {
		m_color = color;

		if (color == 0)
			m_type = Utils.wBishop;
		else
			m_type = Utils.bBishop;

	}

	// this method must be completed with all the possible pieces
	@Override
	public ArrayList<Action> getPossibleActions(State state) {
		ArrayList<Action> list = null;

		list = this.getDiagonalUpLeftMoves(state);
		list.addAll(this.getDiagonalUpRightMoves(state));
		list.addAll(this.getDiagonalDownLefttMoves(state));
		list.addAll(this.getDiagonalDownRightMoves(state));
		return list;
	}

}