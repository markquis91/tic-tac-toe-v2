package com.markquis.tictactoe;


public class Main {

	/**
	 * This class is responsible for starting the tic tac toe program using the controller class.
	 * @param args
	 * 
	 * @author Markquis Simmons
	 */
	
	public static void main (String[]args) {
		Controller controller = new Controller();
		controller.startGame();
	}		
}