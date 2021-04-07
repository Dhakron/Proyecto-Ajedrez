package Main;

import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Knight extends Piece {

	// constructor
	public Knight(int color) {
		m_color = color;

		if (color == 0)
			m_type = Utils.wKnight;
		else
			m_type = Utils.bKnight;

	}

	// this method must be completed with all the possible pieces
	@Override
	public ArrayList<Action> getPossibleActions(State state) {
		ArrayList<Action> list = null;

		list = getUpLefttMoves(state);
		list.addAll(getUpRightMoves(state));
		list.addAll(getRighUpMoves(state));
		list.addAll(getRightDownMoves(state));
		list.addAll(getDownRightMoves(state));
		list.addAll(getDownLeftMoves(state));
		list.addAll(getLeftDownMoves(state));
		list.addAll(getLeftUpMoves(state));
		
		
		return list;
	}

	private ArrayList<Action> getUpRightMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 - 2;
		c = c0 + 1;
		Action action = null;
		if ((r >= 0) && (c < state.m_boardSize)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	
	private ArrayList<Action> getUpLefttMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 - 2;
		c = c0 - 1;
		Action action = null;
		if ((r >= 0) && (c >= 0)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	
	private ArrayList<Action> getRighUpMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 - 1;
		c = c0 + 2;
		Action action = null;
		if ((r >= 0) && (c < state.m_boardSize)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	
	private ArrayList<Action> getRightDownMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 + 1;
		c = c0 + 2;
		Action action = null;
		if ((r < state.m_boardSize) && (c < state.m_boardSize)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	
	private ArrayList<Action> getDownRightMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 + 2;
		c = c0 + 1;
		Action action = null;
		if ((r < state.m_boardSize) && (c < state.m_boardSize)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	private ArrayList<Action> getDownLeftMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 + 2;
		c = c0 - 1;
		Action action = null;
		if ((r < state.m_boardSize) && (c >= 0)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	
	private ArrayList<Action> getLeftDownMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 + 1;
		c = c0 - 2;
		Action action = null;
		if ((r < state.m_boardSize) && (c >= 0)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	
	private ArrayList<Action> getLeftUpMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;

		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r, c;
		//set movement
		r = r0 - 1;
		c = c0 - 2;
		Action action = null;
		if ((r >= 0) && (c >= 0)) {
			if (state.m_board[r][c] == Utils.empty) {// add action
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			} else if (agentColor != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
				action = new Action(state.m_agentPos, new Position(r, c));
				list.add(action);
			}
		}

		return list;
	}
	
}