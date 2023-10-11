package sos_game_test2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sos_game_sprint2.Board;
import sos_game_sprint2.Board.GameType;;
public class TestDefaultSetting {
	
	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(5, board.getBoardSize());
		assertEquals(GameType.General, board.getGameType());
	}

}
