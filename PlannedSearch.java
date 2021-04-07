package Main;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PlannedSearch {
	// member variables
	State m_initialState = null;
	ArrayList<Action> m_solution = null;
	Piece m_piece = null;
	State m_finalState = null;
	double m_cost = 0.0;
	double m_nodesExpanded=0.0;
	double m_nodesGenerated=0.0;
	double m_nodesExplored=0.0;
	State current = null;
	

	public static void main(String[] args) {
		if (args.length != 5) {
			System.out.println(msgWrongArgs);
			System.exit(0);
		}
		String searchMethod = args[0];
		int size = Integer.parseInt(args[1]);
		double density = Double.parseDouble(args[2]);
		int agent = Integer.parseInt(args[3]);
		int seed = Integer.parseInt(args[4]);

		if (size < 4) {
			System.out.println(msgWrongSize);
			size = 4;
		}

		if ((density < 0.1) || (density > 1)) {
			System.out.println(msgWrongDensity);
			density = 0.25;
		}

		if ((density * 32) > (size * size)) {
			System.out.println(msgWrongDensity2);
			density = 0.25;
		}

		if ((agent < 0) || (agent > 11)) {
			System.out.println(msgWrongAgent);
			agent = Utils.wRook;
		}

		// getting the initial state
		State state = Utils.getProblemInstance(size, density, seed, agent);
		Utils.printBoard(state);
		PlannedSearch pSearch = new PlannedSearch(state);
		pSearch.doSearch(searchMethod);
		pSearch.calculateSolution();
		System.out.println("Number of generated nodes:   " + pSearch.m_nodesGenerated);
		System.out.println("Number of explored nodes:   " + pSearch.m_nodesExplored);
		System.out.println("Number of expanded nodes:   " + pSearch.m_nodesExpanded);
		if (pSearch.m_finalState==null) {
			System.out.println("\nSorry, no solution found ....");
		}else {
			System.out.println("Solution length: " + pSearch.m_solution.size());
			System.out.println("Solution cost:   " + pSearch.m_cost);
			System.out.println("Solution:\n");
			int i=1;
			for(Action action:pSearch.m_solution) {
				System.out.println(i + ": " + action);
				i++;
			}
			Utils.printBoard(pSearch.m_finalState);
		}
	}

	// constructor
	public PlannedSearch(State s0) {
		m_initialState = s0;
		switch (s0.m_agent) {
		case Utils.wRook:
			m_piece = new Rook(0);
			break;
		case Utils.bRook:
			m_piece = new Rook(1);
			break;
		case Utils.wPawn:
			m_piece = new Pawn(0);
			break;
		case Utils.bPawn:
			m_piece = new Pawn(1);
			break;
		case Utils.wBishop:
			m_piece = new Bishop(0);
			break;
		case Utils.bBishop:
			m_piece = new Bishop(1);
			break;
		case Utils.wKing:
			m_piece = new King(0);
			break;
		case Utils.bKing:
			m_piece = new King(1);
			break;
		case Utils.wKnight:
			m_piece = new Knight(0);
			break;
		case Utils.bKnight:
			m_piece = new Knight(1);
			break;
		case Utils.wQueen:
			m_piece = new Queen(0);
			break;
		case Utils.bQueen:
			m_piece = new Queen(1);
			break;
		default:
			break; // define rest of pieces
		}
	}

	public void doSearch(String searchMethod) {
		switch (searchMethod) {
		case "breadth-first":
			breadthFirst();
			break;
		case "depth-first":
			depthFirst();
			break;
		case "uniform-cost":
			uniformCost();
			break;
		case "best-first":
			bestFirst();
			break;
		case "a-star":
			aStar();
			break;
		default: // define rest of pieces
			System.out.println(msgWrongSearchMethod);
			breadthFirst();
		}
	}
	
	
	
	public void calculateSolution() {
		if (m_finalState!=null) {
			m_solution=m_finalState.actionList;
			for(Action action : m_finalState.actionList) {
				m_cost+=action.getCost();
			}
		}
	}

	public State breadthFirst() {
		current = m_initialState;
		if (current.isFinal()) { // first we check if the state is final
			return m_finalState=current;
		} else {
			HashMap<String, State> closedList = new HashMap<String, State>(); //Create closed list (HasMap)
			Queue<State> openList = new LinkedList<State>(); //Created open list (FIFO)
			openList.add(current);
			m_nodesGenerated+=1;
			while (!openList.isEmpty()) { //Repeat until FIFO get empty
				m_nodesExplored+=1;
				final State state = openList.poll();
				final String idState = state.m_agentPos.row + "" + state.m_agentPos.col;
				if(state.isFinal()) { //Return if this is the objective state
					return m_finalState=state;
				}
				closedList.put(idState, state);			
				final ArrayList<Action> actionList = new ArrayList<Action>(m_piece.getPossibleActions(state));
				if(actionList.size()>0) {
					m_nodesExpanded+=1;
				}
				for (Action action : actionList) { // For each possible action
					final State newState = state.applyAction(action);
					final String idNewState = newState.m_agentPos.row + "" + newState.m_agentPos.col;
					newState.actionList.add(action);
					m_nodesGenerated+=1;
					if (!closedList.containsKey(idNewState)) {
						
						openList.add(newState);
					}
				}
				
			}
		}
		return null;
	}
	
	
	public State depthFirst() {
		current = m_initialState;
		if (current.isFinal()) { // first we check if the state is final
			return m_finalState=current;
		} else {
			HashMap<String, State> closedList = new HashMap<String, State>(); // Create closed list (HasMap)
			Deque<State> openList = new LinkedList<State>(); // Create open list (LIFO)
			openList.add(current);
			m_nodesGenerated+=1;
			while (!openList.isEmpty()) { //Repeat until LIFO get empty
				m_nodesExplored+=1;
				final State state = openList.pollLast();
				final String idState = state.m_agentPos.row + "" + state.m_agentPos.col;
				if(state.isFinal()) { //Return if this is the objective state
					return m_finalState=state;
				}
				closedList.put(idState, state);
				final ArrayList<Action> actionList = new ArrayList<Action>(m_piece.getPossibleActions(state));
				if(actionList.size()>0) {
					m_nodesExpanded+=1;
				}
				for (Action action : actionList) { // For each possible action
					m_nodesGenerated+=1;
					final State newState = state.applyAction(action);
					final String idNewState = newState.m_agentPos.row + "" + newState.m_agentPos.col;
					newState.actionList.add(action);
					if (!closedList.containsKey(idNewState)) {
						openList.add(newState);
					}
				}
			}
		}
		return null;
	}

	public State uniformCost() {
		current = m_initialState;
		if (current.isFinal()) { // first we check if the state is final
			return m_finalState=current;
		} else {
			HashMap<String, State> closedList = new HashMap<String, State>(); // Create closed list (HasMap)
			PriorityQueue<State> openList = new PriorityQueue<State>(Comparator.comparing(State::getCost)); // Create open list (Priority by cost of state)
			openList.add(current);
			m_nodesGenerated+=1;
			while (!openList.isEmpty()) { //Repeat until priority list get empty
				m_nodesExplored+=1;
				final State state = openList.poll(); //get state with lower cost of list
				final String idState = state.m_agentPos.row + "" + state.m_agentPos.col;
				if(state.isFinal()) { //Return if this is the objective state
					return m_finalState=state;					
				}
				closedList.put(idState, state); 
				final ArrayList<Action> actionList = new ArrayList<Action>(m_piece.getPossibleActions(state));
				if(actionList.size()>0) {
					m_nodesExpanded+=1;
				}
				for (Action action : actionList) { // For each possible action
					m_nodesGenerated+=1;
					final State newState = state.applyAction(action);
					final String idNewState = newState.m_agentPos.row + "" + newState.m_agentPos.col;
					newState.setCost(action.getCost()+state.getCost());
					newState.actionList.add(action);
					if (!closedList.containsKey(idNewState)) {
						openList.add(newState);
					}else {
						final State oldState = closedList.get(idNewState);
						if(newState.getCost()<oldState.getCost()) {
							openList.remove(oldState);
							openList.add(newState);
						}
					}
				}
			}
		}
		return null;
	}

	public State bestFirst() {
		current = m_initialState;
		if (current.isFinal()) { // first we check if the state is final
			 return m_finalState=current;
		} else {
			HashMap<String, State> closedList = new HashMap<String, State>(); // Create closed list (HasMap)
			PriorityQueue<State> openList = new PriorityQueue<State>(Comparator.comparing(State::getCost)); // Create open list (Priority by cost of state)
			openList.add(current);
			m_nodesGenerated+=1;
			while (!openList.isEmpty()) { //Repeat until priority list get empty
				m_nodesExplored+=1;
				final State state = openList.poll(); //get state with lower cost of list
				final String idState = state.m_agentPos.row + "" + state.m_agentPos.col;
				closedList.put(idState, state); 
				if(state.isFinal()) { //Return if this is the objective state
					return m_finalState=state;					
				}
				final ArrayList<Action> actionList = new ArrayList<Action>(m_piece.getPossibleActions(state));
				if(actionList.size()>0) {
					m_nodesExpanded+=1;
				}
				for (Action action : actionList) { // For each possible action
					m_nodesGenerated+=1;
					final State newState = state.applyAction(action);
					final String idNewState = newState.m_agentPos.row + "" + newState.m_agentPos.col;
					newState.setCost(getObjetiveLength(newState)); //Calculate cost with heuristic function
					newState.actionList.add(action);
					if (!closedList.containsKey(idNewState)) {
						openList.add(newState);
					}else {
						final State oldState = closedList.get(idNewState);
						if(newState.getCost()<oldState.getCost()) {
							openList.remove(oldState);
							openList.add(newState);
						}
					}
				}
			}
		}
		return null;
	}

	public State aStar() {
		current = m_initialState;
		if (current.isFinal()) { // first we check if the state is final
			 return m_finalState=current;
		} else {
			HashMap<String, State> closedList = new HashMap<String, State>(); // Create closed list (HasMap)
			PriorityQueue<State> openList = new PriorityQueue<State>(Comparator.comparing(State::getCost)); // Create open list (Priority by cost of state)
			openList.add(current);
			m_nodesGenerated+=1;
			while (!openList.isEmpty()) { //Repeat until priority list get empty
				m_nodesExplored+=1;
				final State state = openList.poll(); //get state with lower cost of list
				final String idState = state.m_agentPos.row + "" + state.m_agentPos.col;
				closedList.put(idState, state); 
				if(state.isFinal()) { //Return if this is the objective state
					return m_finalState=state;					
				}
				final ArrayList<Action> actionList = new ArrayList<Action>(m_piece.getPossibleActions(state));
				if(actionList.size()>0) {
					m_nodesExpanded+=1;
				}
				for (Action action : actionList) { // For each possible action
					m_nodesGenerated+=1;
					final State newState = state.applyAction(action);
					final String idNewState = newState.m_agentPos.row + "" + newState.m_agentPos.col;
					newState.setCost(action.getCost()+state.getCost()+getObjetiveLength(newState)); //Calculate total cost with heuristic function
					newState.actionList.add(action);
					if (!closedList.containsKey(idNewState)) {
						openList.add(newState);
					}else {
						final State oldState = closedList.get(idNewState);
						if(newState.getCost()<oldState.getCost()) {
							openList.remove(oldState);
							openList.add(newState);
						}
					}
				}
			}
		}
		return null;
	}
	
	// Heuristic function
	public Double getObjetiveLength(State state) {
		final double iRow= state.m_agentPos.row;
		final double fRow= state.m_boardSize-1;
		return fRow-iRow;
	}

	private static final String msgWrongArgs = "\n**Sorry, correct usage require 5 params:\n"
			+ "Search method: String.\n" + "Board size: int.\n"
			+ "Density: (0.1,1]. Probability for each piece to be included.\n"
			+ "Seed: int. To initialize the problem instance random number generator (for reproducibility)\n"
			+ "Agent: {0,1,2,3,4,5} standing for white pawn, rook, bishop, knight, queen or king.\n";

	private static final String msgWrongSize = "\nSorry: board to small, modified to 4";
	private static final String msgWrongDensity = "\nSorry: bad density value, modified to 0.25";
	private static final String msgWrongDensity2 = "\nSorry: too much pieces for the board size, modifying density to 0.25";
	private static final String msgWrongAgent = "\nSorry: bad selected agent, modified to 1 (white rook)";
	private static final String msgWrongSearchMethod = "\nSorry: bad selected method, modified to breadth-first";
}
