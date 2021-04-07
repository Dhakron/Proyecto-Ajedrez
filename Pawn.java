package Main;
import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Pawn extends Piece {
	
	
	// constructor
	public Pawn( int color){
		m_color = color;
		
		if (color==1) m_type = Utils.wPawn;
		else m_type = Utils.bPawn;
		
	}
	
	
	// this method must be completed with all the possible pieces
	@Override
	public ArrayList<Action> getPossibleActions(State state){

		ArrayList<Action> list = null;
		list = getVerticalMoves(state);	
				
		return list;
	}
	
	public ArrayList<Action> getVerticalMoves(State state) {
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		 //  
		int c0, r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		int r;
		Boolean firstMove=null;
		if(agentColor==0) {
			r=1;
			if (r0 == 0) {
				firstMove=true;
			}else {
				firstMove=false;
			}
			
		}else {
			r=-1;
			if (r0 == (state.m_boardSize-1)) {
				firstMove=true;
			}else {
				firstMove=false;
			}
		}
		
		
		if (r0+r < state.m_boardSize && r0+r >=0){
			if (state.m_board[r0+r][c0] == Utils.empty){//standard pawn move
				list.add( new Action(state.m_agentPos, new Position(r0+r,c0)) );
			}
			
			if ((c0-1>=0)&&(state.m_board[r0+r][c0-1] != Utils.empty)
					&& (Utils.getColorPiece(state.m_board[r0+r][c0-1]) != agentColor)){//capture
				list.add( new Action(state.m_agentPos, new Position(r0+r,c0-1)) );
			}
			
			if ((c0+1 < state.m_boardSize)&&(state.m_board[r0+r][c0+1] != Utils.empty)
					&& (Utils.getColorPiece(state.m_board[r0+r][c0+1]) != agentColor)){//capture
				list.add( new Action(state.m_agentPos, new Position(r0+r,c0+1)) );
			}			
		}
        if ((state.m_board[r0+r][c0] == Utils.empty) && firstMove){//starting pawn move
        	if((state.m_board[r0+r+r][c0] == Utils.empty)) {
        		list.add( new Action(state.m_agentPos, new Position(r0+r+r,c0)));
        	}
		}

		return list;
	}
	
	
}
