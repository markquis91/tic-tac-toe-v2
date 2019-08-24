package com.markquis.tictactoe;


/**
 * The random player class uses the IComputerPlayer interface to implement a game strategy 
 * of random token placement on the board.
 * 
 * @author Markquis Simmons
 *
 */

public class RandomPlayer implements IComputerPlayer {

	@Override
	public Position getNextPosition(Board board, Player computerPlayer, Player userPlayer) {
		return board.getRandomEmptyPosition();
	}

}
