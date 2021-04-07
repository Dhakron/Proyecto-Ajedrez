package Main;

import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Queen extends Piece {

	// constructor
	public Queen(int color) {
		m_color = color;

		if (color == 0)
			m_type = Utils.wQueen;
		else
			m_type = Utils.bQueen;

	}

	// this method must be completed with all the possible pieces
	@Override
	public ArrayList<Action> getPossibleActions(State state) {
		ArrayList<Action> list = null;

		list = this.getDiagonalUpLeftMoves(state);
		list.addAll(this.getDiagonalUpRightMoves(state));
		list.addAll(this.getDiagonalDownLefttMoves(state));
		list.addAll(this.getDiagonalDownRightMoves(state));
		list.addAll(this.getHorizontalLeftMoves(state));
		list.addAll(this.getHorizontalRightMoves(state));
		list.addAll(this.getVerticalDownMoves(state));
		list.addAll(this.getVerticalUpMoves(state));
		return list;
	}

}