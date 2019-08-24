package com.markquis.tictactoe;


import java.util.ArrayList;

/**
 * The adjacent player class uses the IComputerPlayer interface to implement the strategy of advanced adjacent player.
 * The advanced adjacent player chooses winning moves to try and win the game. 
 * @author Markquis Simmons
 */

public class AdvancedAdjacentPlayer extends SimplePositionalPlayer implements IComputerPlayer {

	@Override
	public Position getNextPosition(Board board, Player computerPlayer, Player userPlayer) {
		
		ArrayList<Position> computerPositions = board.getPositionsForPlayer(computerPlayer);
		
		// If computer hasn't moved yet, get a random position
		if (computerPositions.size() == 0) {
			return board.getRandomEmptyPosition();
		}
		
		ArrayList<ArrayList<Position>> winningLines = getWinningLines(computerPositions, computerPlayer, board);
		
		//OR get the next best position in a winning sequence
		if (winningLines.size() != 0) {
			ArrayList<Position> shortestLine = getShortestLine(winningLines);
			Position winningPosition = getWinningPosition(shortestLine, board);
			return winningPosition;
		}
		
		//If there is no position available in a winning sequence get an empty position
		ArrayList<Position> emptyPositions = board.getAllEmptyPositions();
		return emptyPositions.get(0);
	}		
}

