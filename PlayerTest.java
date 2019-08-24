package com.markquis.tictactoe;
import junit.framework.*;

public class PlayerTest extends TestCase {

	
	public void testPlayerConstructor() {
		String token = "X";
		Player player = new Player(token);
		assertEquals("X", player.getToken());
	}
	
	
	Player player = new Player("X");
	public void testGetToken() {
		String token = player.getToken();
		assertEquals("X", token);
	}
	
}
