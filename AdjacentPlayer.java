package com.markquis.tictactoe;


import java.util.ArrayList;

/***
 * The adjacent player class uses the IComputerPlayer interface to implement the strategy of adjacent player.
 * The adjacent player uses a token and position to add a token to any adjacent square.
 * @author Markquis Simmons
 *
 */

public class AdjacentPlayer extends SimplePositionalPlayer implements IComputerPlayer {

	@Override
	public Position getNextPosition(Board board, Player computerPlayer, Player userPlayer) {

		// If computer hasn't moved yet, get a random position
		ArrayList<Position> playerPositions = board.getPositionsForPlayer(computerPlayer);
		if (playerPositions.size() == 0) {
			return board.getRandomEmptyPosition();
		}
		
		//OR get a random position
		Position position = getPositionForPlayer(computerPlayer, board);
		return position;
	}
		
}
