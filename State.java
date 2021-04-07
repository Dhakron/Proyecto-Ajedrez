package Main;
// This class contains the information needed to represent a state 
// and some useful methods

import java.util.ArrayList;
public class State {
	int[][] m_board = null;
	Position m_agentPos = null;
	int m_agent = -1; // the type of piece
	int m_color = 0; // 0 for white, 1 for black
	int m_boardSize = -1;
	//used for help comparation of state
	double m_cost=0.0;
	ArrayList<Action> actionList = new ArrayList<Action>();
	
	public double getCost() {
		return m_cost;
	}

	public void setCost(double m_cost) {
		this.m_cost = m_cost;
	}

	// constructor
	State(int[][] board, Position pos, int a){
		m_board = board;
		m_agentPos = pos;
		m_agent = a;
		
		if (m_agent >11){
			System.out.println("\n*** Invalid piece ***\n");
			System.exit(0);
		}
		else if (m_agent >5) m_color = 1; // black
		
		m_boardSize = board[0].length;
	}

	// compares if the current state is final, i.e. the agent is in the last row
	public boolean isFinal(){
		if (this.m_agentPos.row == this.m_boardSize-1) return true;
		else return false;
	}
	
	// hard copy of an State
	public State copy(){
		int[][] cBoard = new int[this.m_boardSize][this.m_boardSize];
		
		for(int r=0;r<this.m_boardSize;r++)
			for(int c=0; c<this.m_boardSize;c++)
				cBoard[r][c] = this.m_board[r][c];
		State newState=new State(cBoard, this.m_agentPos.copy(),m_agent);
		newState.setCost(this.getCost());
		newState.actionList.addAll(this.actionList);
		return newState;
	}
	
	// apply a given action over the current state -which remains unmodified. Return a new state
	
	public State applyAction(Action action) {
		State newState = this.copy();
		newState.m_board[action.m_initPos.row][action.m_initPos.col] = Utils.empty;
		newState.m_board[action.m_finalPos.row][action.m_finalPos.col] = newState.m_agent;
		newState.m_agentPos = action.m_finalPos;
		
		return newState;
	}
	
}
