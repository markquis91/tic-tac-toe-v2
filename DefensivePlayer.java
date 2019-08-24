package com.markquis.tictactoe;


import java.util.ArrayList;

/**
 * The adjacent player class uses the IComputerPlayer interface to implement the strategy of adjacent player.
 * The defensive player evaluates the userPlayer positions on the board and blocks any winning sequences for the userPlayer.
 */

public class DefensivePlayer extends SimplePositionalPlayer implements IComputerPlayer {

	// Todo: Figure out what is UNIQUE to Defensive player, and that code should be here.
	// But common things (for all positional players) should be in SimplePositionalPlayer.
	// Then refactor accordingly.
	// Including helper functions like getEmptyPositionsInRow, testForWinningSequence, etc.
	
	@Override
	public Position getNextPosition(Board board, Player computerPlayer, Player userPlayer) {

		ArrayList<Position> computerPositions = board.getPositionsForPlayer(computerPlayer);
		ArrayList<Position> userPositions = board.getPositionsForPlayer(userPlayer);
		
		// If computer hasn't moved yet, get random position
		if (computerPositions.size() == 0) {
			return board.getRandomEmptyPosition();
		}
		
		// OR find winning sequence for user. If winning move is found, block them by adding computer token
		ArrayList<ArrayList<Position>> winningLines = getWinningLines(userPositions, userPlayer, board);
		
		if (winningLines.size() != 0) {
			ArrayList<Position> shortestLine = getShortestLine(winningLines);
			Position winningPosition = getWinningPosition(shortestLine, board);
			return winningPosition;
		}
		
		// Minor: Defensive player isn't very smart when there are no blocking moves. At least random would be better.
		ArrayList<Position> emptyPositions = board.getAllEmptyPositions();
		return emptyPositions.get(0);
	}	
}
