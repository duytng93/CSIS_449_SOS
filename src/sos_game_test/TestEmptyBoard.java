package sos_game_test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sos_game.Board;
public class TestEmptyBoard {

	private Board board = new Board();
	private int size = board.getBoardSize();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// acceptance criterion 1.1
	@Test
	public void testNewBoard() {
		for (int row = 0; row<size; row++) {
			for (int column = 0; column<size; column++) {
				assertEquals("", board.getCell(row, column), 0); 
			}
		}
		assertEquals("", board.getTurn(), 'B'); 
	}
	
	// acceptance criterion 1.2
	@Test
	public void testInvalidRow() {
		assertEquals("", -1,board.getCell(size, 0)); 
	}

	// acceptance criterion 1.3
	@Test
	public void testInvalidColumn() {
		assertEquals("", -1,board.getCell(0, size)); 
	}
}
