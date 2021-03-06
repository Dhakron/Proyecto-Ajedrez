package Main;
import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Rook extends Piece {
	
	
	// constructor
	public Rook( int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wRook;
		else m_type = Utils.bRook;
		
	}
	
	
	// this method must be completed with all the possible pieces
	
	public ArrayList<Action> getPossibleActions(State state){
		ArrayList<Action> list = null;
		
		list = this.getHorizontalLeftMoves(state);
		list.addAll(this.getHorizontalRightMoves(state));
		list.addAll(this.getVerticalDownMoves(state));
		list.addAll(this.getVerticalUpMoves(state));
		
		return list;
	}
	
	
	
	
}
