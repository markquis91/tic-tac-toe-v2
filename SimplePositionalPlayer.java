package com.markquis.tictactoe;

import java.util.ArrayList;
import java.util.Random;

public abstract class SimplePositionalPlayer implements IComputerPlayer {

	
	public Position getNextPosition() throws Exception {
		throw new Exception();
	}
	
	//uses a list of playerPositions and returns a random empty position
	// Minor: What happens if board is full and this gets called?
	public Position getPositionForPlayer(Player player, Board board) {
		Random random = new Random();
		ArrayList<Position> playerPositions = board.getPositionsForPlayer(player);
		Position position = playerPositions.get(random.nextInt(playerPositions.size()));		
		if (!board.isPositionEmpty(position) && board.getToken(position) == player.getToken()) {
			ArrayList<Position> allPositions = board.getEmptyAdjacentPositions(position);
			Position randomAdjacentPosition = allPositions.get(random.nextInt(allPositions.size()));
			return randomAdjacentPosition;
		}
		return null;
	}

	//takes a list of game winning lines and finds the smallest line and returns the list
	public ArrayList<Position> getShortestLine(ArrayList<ArrayList<Position>> winningLines) {			

		ArrayList<Position> shortestLine = null;
		for (int i = 0; i < winningLines.size(); i++) {
			ArrayList<Position> currentLine = winningLines.get(i);
			if (shortestLine == null || currentLine.size() < shortestLine.size()) {
				shortestLine = currentLine;
			}
		}
		return shortestLine;

	}
	
	//takes the winning positions, if the list is not empty, returns the first position
	public Position getWinningPosition(ArrayList<Position> winningPositions, Board board) {
		if (winningPositions.size() > 0 && winningPositions.get(0) != null) {
			return winningPositions.get(0);
		} else {
			Position position = board.getRandomEmptyPosition();
			return position;
		}
	}
	
	//takes a player and looks for a winning sequence, if winning sequence found boolean value is returned
	// minor: emptyRows is misleading, "takes a player" is not precise enough.
	public boolean testForWinningSequence(ArrayList<Position> emptyRows, Board board, Player player) {
		Board testBoard = new Board(board);
		for (int i = 0; i < emptyRows.size(); i++) {
			testBoard.setToken(emptyRows.get(i), player.getToken());
		}
		Evaluator evaluate = new Evaluator();
		String winner = evaluate.gameEvaluator(testBoard);
		if (winner != null) {
			return true;
		} else {
			return false;
		}
		
	}

	//uses a position and returns empty positions in a row
	public ArrayList<Position> getEmptyPositionsInRow(Position position, Board board) {
		ArrayList<Position> rowPositions = new ArrayList<Position>();
		int row = position.getRow();
		for (int column = 0; column < board.getLength(); column++) {
			Position newPosition = new Position(row, column);
			if (board.isWithinBoardBoundary(newPosition) && board.isPositionEmpty(newPosition)) {
				rowPositions.add(newPosition);
			}
		}	
		return rowPositions;
	}
	
	//uses a position and returns empty columns
	public ArrayList<Position> getEmptyPositionsInColumn(Position position, Board board) {
		ArrayList<Position> columnPositions = new ArrayList<Position>();
		int column = position.getColumn();
		for (int row = 0; row < board.getLength(); row++) {
			Position newPosition = new Position(row, column);
			if (board.isWithinBoardBoundary(newPosition) && board.isPositionEmpty(newPosition)) {
				columnPositions.add(newPosition);
			}
		}	
		return columnPositions;
	}
	
	//uses a position and returns empty left diagonals
	public ArrayList<Position> getEmptyPositionsInLeftDiagonal(Position position, Board board) {
		ArrayList<Position> leftDiagonalPositions = new ArrayList<Position>();
		for (int row = 0; row < board.getLength(); row++) {
			for (int column = row; column == row; column++) {
				Position newPosition = new Position(row, column);
				if (board.isWithinBoardBoundary(newPosition) && board.isPositionEmpty(newPosition)) {
					leftDiagonalPositions.add(newPosition);
				}
			}
		}
		return leftDiagonalPositions;
	}
	
	//uses a position and returns empty right diagonals
	public ArrayList<Position> getEmptyPositionsInRightDiagonal(Position position, Board board) {
		ArrayList<Position> rightDiagonalPositions = new ArrayList<Position>();		
		int column = 0;
		for (int row = board.getLength() - 1; row > 0; row--) {
				Position newPosition = new Position(row, column);
				if (board.isWithinBoardBoundary(newPosition) && board.isPositionEmpty(newPosition)) {
					rightDiagonalPositions.add(newPosition);
				}
				column++;
			}
		return rightDiagonalPositions;
	}
	
	//takes a users position and finds winning sequences of rows, columns and diagonals
	//if winning sequences are found they are added to a list and the list is returned
	public ArrayList<ArrayList<Position>> getWinningLines(ArrayList<Position> positions, Player player, Board board) {
		ArrayList<ArrayList<Position>> winningLines = new ArrayList<ArrayList<Position>>();
		boolean winner;
		for (int i = 0; i < positions.size(); i++) {

			ArrayList<Position> emptyRows = getEmptyPositionsInRow(positions.get(i), board);
			winner = testForWinningSequence(emptyRows, board, player);
			if (winner) {
				winningLines.add(emptyRows);
			}

			ArrayList<Position> emptyColumns = getEmptyPositionsInColumn(positions.get(i), board);
			winner = testForWinningSequence(emptyColumns, board, player);
			if (winner) {
				winningLines.add(emptyColumns);
			}

			ArrayList<Position> emptyLeftDiagonals = getEmptyPositionsInLeftDiagonal(positions.get(i), board);
			winner = testForWinningSequence(emptyLeftDiagonals, board, player);
			if (winner) {
				winningLines.add(emptyLeftDiagonals);
			}

			ArrayList<Position> emptyRightDiagonals = getEmptyPositionsInRightDiagonal(positions.get(i), board);
			winner = testForWinningSequence(emptyRightDiagonals, board, player);
			if (winner) {
				winningLines.add(emptyRightDiagonals);
			}
		}
		return winningLines;
	}
}
