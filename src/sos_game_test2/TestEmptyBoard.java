package sos_game_test2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sos_game_sprint2.Board;
import sos_game_sprint2.Board.Cell;
import sos_game_sprint2.Board.Turn;;
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
				assertEquals("", board.getCell(row, column), Cell.E); 
			}
		}
		assertEquals("", board.getTurn(), Turn.B); 
	}
	
	// acceptance criterion 1.2
	@Test
	public void testInvalidRow() {
		assertEquals("", null,board.getCell(size, 0)); 
	}

	// acceptance criterion 1.3
	@Test
	public void testInvalidColumn() {
		assertEquals("", null,board.getCell(0, size)); 
	}

}
