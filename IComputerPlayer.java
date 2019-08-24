package com.markquis.tictactoe;

/**
 * The IComputerPlayer interface is used for implementing different levels of the computer class.
 * @author Markquis Simmons
 *
 */

public interface IComputerPlayer {
	
	Position getNextPosition(Board board, Player computerPlayer, Player userPlayer);
}
