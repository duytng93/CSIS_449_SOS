package sos_game_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sos_game.Board;
import sos_game.GUI;

public class TestGUI {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmptyBoard() {
		new GUI(board);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
