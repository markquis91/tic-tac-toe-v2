package com.markquis.tictactoe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

	/**
	 * This class is responsible for the functionality and access to the game board.
	 *  
	 */
	
	private static final String EMPTY_SQUARE = "";
	private static final int BOARD_SIZE = 5;
	private String[][] board;

	public Board() {
		this.board = new String[BOARD_SIZE][BOARD_SIZE];
		
		//set null positions to EMPTY_SQUARE - a blank string ""
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {				
				Position position = new Position(row, column);
				setEmptyPosition(position);
			}
		}
	}

	// copy constructor
	public Board(Board source) {
		this.board = new String[source.getLength()][source.getLength()];

		//add board positions to the copy constructor board
		for (int row = 0; row < source.getLength(); row++) {
			for (int column = 0; column < source.getLength(); column++) {
				this.board[row][column] = source.board[row][column];
			}
		}
	}
	
	//returns all the empty positions on the board
	public ArrayList<Position> getAllEmptyPositions() {
		ArrayList<Position> allPositions = new ArrayList<Position>();
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				Position position = new Position(row, column);
				if (isPositionEmpty(position)) {
					allPositions.add(position);
				}
			}
		}
		return allPositions;
	}
	
	//takes a position and returns a list of EMPTY adjacent positions
	public ArrayList<Position> getEmptyAdjacentPositions(Position position) {
		ArrayList<Position> allPositions = new ArrayList<Position>();		
		for (int row = position.getRow() - 1; row <= position.getRow() + 1; row++) {
			for ( int column = position.getColumn() - 1; column < position.getColumn() + 1; column++) {
				Position adjacentPosition = new Position(row,column);
				if (!adjacentPosition.equals(position) && isWithinBoardBoundary(adjacentPosition) && isPositionEmpty(adjacentPosition)) {
					allPositions.add(adjacentPosition);
				}
			}
		}
		return allPositions;
	}

	public ArrayList<Position> getPositionsForPlayer(Player player) {
		ArrayList<Position> positionsList = new ArrayList<Position>();
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				Position position = new Position(row, column);
				if (getTokenAtPosition(position).equals(player.getToken())) {
					positionsList.add(position);
				}
			}
		}
		return positionsList;
	}
	
	
	public Position getTopLeftPosition() {
		return new Position(0, 0);		
	}
	
	public Position getTopRightPosition() {
		return new Position(0, BOARD_SIZE - 1);
	}
	
	public Position getBottomLeftPosition() {
		return new Position(BOARD_SIZE - 1, 0);
	}
	
	public Position getBottomRightPosition() {
		return new Position(BOARD_SIZE - 1, BOARD_SIZE - 1);
	}

	//return a list of positions that are not empty
	public ArrayList<Position> getFilledPositions() {
		ArrayList<Position> winningPositions = new ArrayList<Position>();
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				Position position = new Position(row, column);
				if (!isPositionEmpty(position)) {
					winningPositions.add(position);
				}
			}
		}
		return winningPositions;
	}
	
	//set a null position to empty. Empty is a blanks string ""
	public void setEmptyPosition(Position position) {
		if (board[position.getRow()][position.getColumn()] == null) {
			board[position.getRow()][position.getColumn()] = EMPTY_SQUARE;
		}
	}

	//display the board in any state, with tokens or without tokens
	public void display() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				Position position = new Position(row, column);
				boolean isEmpty = isPositionEmpty(position);
				if (isEmpty) {
					int positionId = getIdAtPosition(position);
					System.out.print("  |  " + " "+positionId+" " + "  |  ");
				} else {
					String token = getTokenAtPosition(position);
					System.out.print("  |  " + " "+token+" " + "  |  ");
				}
			}
			System.out.println("\n");
		}
	}

	public int getLength() {
		return BOARD_SIZE;
	}

	//returns a boolean value if the position is within the bounds/size of the board
	//if the positions is on the board it will return true
	public boolean isWithinBoardBoundary(Position position) {
		if (position.getRow() > BOARD_SIZE || position.getColumn() > BOARD_SIZE) {
			return false;
		} else if (position.getRow() < 0 || position.getColumn() < 0) {
			return false;
		}
		return true;
	}
	
	//return boolean value if the position an empty square
	public boolean isPositionEmpty(Position position) {
		try {
			return board[position.getRow()][position.getColumn()].equals(EMPTY_SQUARE);
		} catch (Exception e) {
			return false;
		}
	}

	//returns the integer value representing a board position
	public int getIdAtPosition(Position position) {
		return position.getRow() * BOARD_SIZE + position.getColumn() + 1;
	}
	
	public String getTokenAtPosition(Position position) {
		return board[position.getRow()][position.getColumn()];
	}
	
	public void setToken(Position position, String token) {
		try {
			if (isWithinBoardBoundary(position)) {
				board[position.getRow()][position.getColumn()] = token;
			}
		} catch (Exception e) {
			System.out.println("Row/column cannot exceed the size of the board.");
		}
	}

	//clears the board by setting board positions to empty
	public void clear() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				Position position = new Position(row, column);
				board[position.getRow()][position.getColumn()] = EMPTY_SQUARE;
			}
		}
	}

	public String getToken(Position position) {
		return board[position.getRow()][position.getColumn()];
	}

	//checks each position to find out if the board has empty positions
	public boolean emptyPositionsExist() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				Position position = new Position(row, column);
				boolean emptySquare = isPositionEmpty(position);
				if (emptySquare) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isBoardFull(Board board) {
		return !board.emptyPositionsExist();
	}

	public Position getRandomEmptyPosition() {
		boolean emptySpaceFound = false;
		while (!emptySpaceFound) {
			Position position = getRandomPosition();
			boolean isEmpty = isPositionEmpty(position);			
			if (isEmpty) {
				return position;
			} else {
				position = getRandomPosition();
			}
		}
		return null;
	}
	
	private Position getRandomPosition() {
		int row = ThreadLocalRandom.current().nextInt(0, board.length);
		int column = ThreadLocalRandom.current().nextInt(0, board.length);
		Position position = new Position(row, column);
		return position;
	}
	
	// takes a boardPositionId and calculates the row and column for a board position
	public Position calculateRowAndColumn(Integer boardSquareId) {
		int row = (boardSquareId - 1) / board.length;
		int column = (boardSquareId - 1) % board.length;
		Position position = new Position(row, column);
		if (isWithinBoardBoundary(position)) {
			return position;
		}
		return null;
	}
}
