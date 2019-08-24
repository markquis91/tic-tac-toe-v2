package com.markquis.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Player {

	/**
	 * This class is responsible for the functionality of the players(user and computer).
	 * It allows the controller to construct a Player with a token.
	 */
	
	private String token;
	private static final String TOKEN_X = "X";
	private static final String TOKEN_O = "O";
	
	public Player(String playerToken) {
		setPlayer(playerToken);
	}
	
	public static String getTokenX() {
		return TOKEN_X;
	}
		
	public static String getTokenO() {
		return TOKEN_O;
	}

	public String getToken() {
		return token;
	}

	public static List<String> getPlayerTokens() {
		List<String> tokens = new ArrayList<String>();
		tokens.add(TOKEN_X);
		tokens.add(TOKEN_O);
		return tokens;
	}

	private void setPlayer(String playerToken) {
		if (playerToken.equals(TOKEN_X)) {
			this.token = playerToken;
		} else if (playerToken.equals(TOKEN_O)) {
			this.token = playerToken;			
		}
	}
	
}