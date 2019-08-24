package com.markquis.tictactoe;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

	/**
	 * 
	 * This class is responsible for managing the control of the game flow. The
	 * controller accepts user input, manages the game moves, evaluates the
	 * gameboard for a winner.
	 */

	private String getUserInput() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String inputLine = br.readLine();
				if (inputLine != null && !inputLine.isEmpty()) {
					inputLine.trim();
					return inputLine;
				} else {
					blankOrNull();
				}
			}
		} catch (IOException e) {
			inputError();
		}
		return null;
	}

	public void startGame() {
		Board board = new Board();
		Evaluator evaluator = new Evaluator();
		IComputerPlayer computer = new DefensivePlayer();

		displayStartMenu();
		String userInput = getUserInput();
		if (userInput.toLowerCase().equals("start")) {

			// Assign a game token to a player
			userPlayerSelection();
			String unassignedPlayerSelection = getUserInput();

			Player userPlayer = assignTokenToUser(unassignedPlayerSelection);

			Player computerPlayer = addTokenToComputerPlayer(userPlayer);

			
			//firstMoveSelection()
			//String firstMove = getUserInupt();
			//if (firstMove.equals("user").caseInsensitive()) {
				//doUserMove()
				//boolean userMoveComplete = true;
				
				//} else {
					//doComputerMove()
					//computerMoveComplete = true;
			//}
			
			
			
			// Main loop for game execution
			boolean gameRunning = true;
			while (gameRunning) {

				board.display();

				userMovePrompt();
				
				doUserGameMove(userInput, board, userPlayer);

				String winner = checkForWinner(board, evaluator);

				board.display();

				if (winner != null) {
					playerHasWon(winner);
					board.display();
					;
					break;
				}

				boolean fullBoard = board.isBoardFull(board);

				if (fullBoard) {
					boardIsFull();
					break;
				}

				computerIsMoving();
				
				//boolean computerMoveComplete;
				boolean computerMoveComplete = false;
				while (!computerMoveComplete) {
					computerMoveComplete = doComputerGameMove(computer, board, computerPlayer, userPlayer);
				}
				
				winner = checkForWinner(board, evaluator);

				if (winner != null) {
					playerHasWon(winner);
					board.display();
					break;
				}
				
				fullBoard = board.isBoardFull(board);
				if (fullBoard) {
					boardIsFull();
					break;
				}
			}
		}
	}

	
	//get the next position and add the token to the board
	private boolean doComputerGameMove(IComputerPlayer computer, Board board, Player computerPlayer, Player userPlayer) {
		Position nextPosition = computer.getNextPosition(board, computerPlayer, userPlayer);
		if (nextPosition != null && board.isPositionEmpty(nextPosition)) {
			boolean computerMoveComplete = addTokenToBoard(board, nextPosition, computerPlayer);
			return computerMoveComplete;
		}
		return false;
	}

	private String checkForWinner(Board board, Evaluator evaluator) {
		String winner = evaluator.gameEvaluator(board);
		return winner;
	}

	//get the users input and add it to the board
	private void doUserGameMove(String rawUserInput, Board board, Player userPlayer) {
		//get the users input, which is an integer representing a position on the board
		rawUserInput = getUserInput();
		Position userPosition = null;
		
		//if userInput is a decimal
		//you must enter a whole number
		
		boolean tokenAddedToBoard = false;
		while (!tokenAddedToBoard) {
			
			
			//use the integer from the userinput to calculate and return a board position
			String userInput = rawUserInput.trim();
			if (userInput.matches("[^0-9]")) {
				Integer boardSquareId = Integer.parseInt(userInput);
				userPosition = board.calculateRowAndColumn(boardSquareId);
				
				//add token to the position
				if (userPosition != null) {
					tokenAddedToBoard = addTokenToBoard(board, userPosition, userPlayer);
				}
							
				if (!tokenAddedToBoard) {
					chooseNewSquare();
					userInput = getUserInput();
				}
			} else {
				specialCharacterError();
				rawUserInput = getUserInput();	
			}
		}
	}

	private Player assignTokenToUser(String unassignedPlayerSelection) {
		boolean userSelected = false;
		Player userPlayer = null;
		
		while (!userSelected) {

			//based on user input, add a token to a player object
			if (unassignedPlayerSelection != null && unassignedPlayerSelection.equals("1")) {
				String playerToken = Player.getTokenX();
				userPlayer = new Player(playerToken);
				userSelected = true;

			} else if (unassignedPlayerSelection != null && unassignedPlayerSelection.equals("2")) {
				String playerToken = Player.getTokenO();
				userPlayer = new Player(playerToken);
				userSelected = true;

			} else {
				incorrectPlayerSelection();
				unassignedPlayerSelection = getUserInput();
				continue;
			}
		}
		return userPlayer;
	}

	
	
	private boolean addTokenToBoard(Board board, Position position, Player player) {
		String token = player.getToken();
		if (board.isPositionEmpty(position)) {
			board.setToken(position, token);
			return true;
		}
		return false;
	}
	
	//uses user token to create the computer token
	private Player addTokenToComputerPlayer(Player player) {
		Player computerPlayer;
		if (player.getToken() == Player.getTokenX()) {
			computerPlayer = new Player(Player.getTokenO());
			return computerPlayer;
		} else {
			computerPlayer = new Player(Player.getTokenX());
			return computerPlayer;
		}
	}
	
	

	// Controller messages

	private void blankOrNull() {
		System.out.println("Your input cannot be blank.");
	}

	private void computerIsMoving() {
		System.out.println("Computer is moving, please wait...");
	}

	private void boardIsFull() {
		System.out.println("The board is full, Game Over!");
	}

	private void playerHasWon(String winningPlayer) {
		System.out.println("Player " + winningPlayer + " Wins!");
	}

	private void userPlayerSelection() {
		System.out.println(
				"Please select your desired player. Player 1 will be X. To select player 1 type 1 and press enter."
						+ " " + "Player 2 will be O. To be player 2 type 2 and press enter.");
	}

	private void userMovePrompt() {
		System.out.println("Please enter the placeholder value of the square you would like to move into.");

	}

	private void displayStartMenu() {
		System.out.println("Type Start and press Enter to begin a game.");
	}

	private void chooseNewSquare() {
		System.out.println("You cannot move into that square. Please choose an new square.");
	}

	private void incorrectPlayerSelection() {
		System.out.println("Your player selection was incorrect, please select 1 or 2.");
	}

	private void inputError() {
		System.out.println("There is an error with the input stream, please try again.");
	}
	
	private void specialCharacterError() {
		System.out.println("Special character error. Please try again.");
	}
}
	