package com.markquis.tictactoe;

public class Position {
	
	/**
	 * This position class is for an object that holds the row and column of a board space.
	 */
	
	private int row;
	private int column;
	
	public Position(int row, int column) {
		setRow(row);
		setColumn(column);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	
}