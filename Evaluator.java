package com.markquis.tictactoe;

import java.util.List;

/**
 *The evaluator class is used to review the game board for a winning player. This class provides
 *the functions and stratagy for reviewing a game board. 
 * 
 * @author Markquis Simmons
 *
 */

public class Evaluator {
	
	public String gameEvaluator(Board board) {
		return evaluateForWinner(board);
	}

	private String evaluateForWinner(Board board) {

		String playerWinner;

		// Check column positions
		for (int column = 0; column < board.getLength(); column++) {
			int row = 0;
			Position startPoint = new Position(row, column);
			playerWinner = getTokensForColumns(startPoint, board);
			if (playerWinner != null) {
				return playerWinner;
			}
		}

		// Check row positions
		for (int row = 0; row < board.getLength(); row++) {
			int column = 0;
			Position startPoint = new Position(row, column);
			playerWinner = getTokensForRows(startPoint, board);
			if (playerWinner != null) {
				return playerWinner;
			}
		}

		//check left diagonal positions
		playerWinner = getTokensForLeftDiagonalColumnSquares(board);
		if (playerWinner != null) {
			return playerWinner;
		}

		//check right diagonal positons
		playerWinner = getTokensForRightDiagonalColumnSquares(board);
		if (playerWinner != null) {
			return playerWinner;
		}
		return null;
	}


	//--------for each token, check to see if there is a winner in the columns, rows and diagonals---------//
	
	private String getTokensForColumns(Position startPoint, Board board) {
		List<String> tokens = Player.getPlayerTokens();
		for (String token : tokens) {
			String playerToken = token;
			boolean isWinner = checkColumnsForWinner(playerToken, startPoint, board);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}

	private String getTokensForRows(Position startPoint, Board board) {
		List<String> tokens = Player.getPlayerTokens();
		for (String token : tokens) {
			String playerToken = token;
			boolean isWinner = checkRowsForWinner(playerToken, startPoint, board);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}

	private String getTokensForLeftDiagonalColumnSquares(Board board) {
		List<String> tokens = Player.getPlayerTokens();
		for (String token : tokens) {
			String playerToken = token;
			boolean isWinner = checkLeftDiagonalColumnsForWinner(playerToken, board);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}

	private String getTokensForRightDiagonalColumnSquares(Board board) {
		List<String> tokens = Player.getPlayerTokens();
		for (String token : tokens) {
			String playerToken = token;
			boolean isWinner = checkRightDiagonalColumnsForWinner(playerToken, board);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}

	//-----use a position to look through the columns, rows and diagonals for a winner---------//

	private boolean checkColumnsForWinner(String playerToken, Position startPoint, Board board) {
		int row = startPoint.getRow();
		int column = startPoint.getColumn();
		while (row < board.getLength()) {
			Position tokenPosition = new Position(row, column);
			if (!board.getToken(tokenPosition).equals(playerToken)) {
				return false;
			} else {
				++row;
			}
		}
		return true;
	}

	private boolean checkRowsForWinner(String playerToken, Position startPoint, Board board) {
		int column = startPoint.getColumn();
		int row = startPoint.getRow();
		while (column < board.getLength()) {
			Position tokenPosition = new Position(row, column);
			if (!board.getToken(tokenPosition).equals(playerToken)) {
				return false;
			} else {
				++column;
			}
		}
		return true;
	}

	private boolean checkLeftDiagonalColumnsForWinner(String playerToken, Board board) {
		Position startPoint = board.getTopLeftPosition();		
		int row = startPoint.getRow();
		int column = startPoint.getColumn();
		while (row < board.getLength()) {
			Position tokenPosition = new Position(row, column);
			if (!board.getToken(tokenPosition).equals(playerToken)) {
				return false;
			} else {
				++column;
				++row;
				tokenPosition.setRow(row);
				tokenPosition.setColumn(column);
			}
		}
		return true;
	}

	private boolean checkRightDiagonalColumnsForWinner(String playerToken, Board board) {
		Position startPoint = board.getTopRightPosition();
		int row = startPoint.getRow();
		int column = startPoint.getColumn();
		while (row < board.getLength()) {
			Position tokenPosition = new Position(row, column);
			if (!board.getToken(tokenPosition).equals(playerToken)) {
				return false;
			} else {
				--column;
				++row;
			}
		}
		return true;
	}

}
