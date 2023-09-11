package sos_game_test3;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sos_game_sprint3.Board;
import sos_game_sprint3.GUI;
import sos_game_sprint3.Board.Cell;
public class TestClickOnGameBoard {
	private Board board;
	private GUI mainFrame;
	@Before
	public void setUp() throws Exception {
		board = new Board();
		mainFrame = new GUI(board);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMouseClick() {
		try {
			Robot robot = new Robot();  
			mainFrame.getO2().setSelected(true);
			Thread.sleep(500);
			robot.mouseMove(mainFrame.getX()+276,mainFrame.getY() + 184); // cell  1,2
			Thread.sleep(500);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500);
			assertEquals(1,mainFrame.getGameBoard().getRowSelected());
            assertEquals(2,mainFrame.getGameBoard().getColSelected());
            assertEquals(Cell.O, board.getCell(mainFrame.getGameBoard().getRowSelected(), mainFrame.getGameBoard().getColSelected()));
			
			robot.mouseMove(mainFrame.getX()+347, mainFrame.getY()+256); // cell  3,3
			Thread.sleep(500);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500);
			assertEquals(3,mainFrame.getGameBoard().getRowSelected());
            assertEquals(3,mainFrame.getGameBoard().getColSelected());
            assertEquals(Cell.S, board.getCell(mainFrame.getGameBoard().getRowSelected(), mainFrame.getGameBoard().getColSelected()));
            
            robot.mouseMove(mainFrame.getX()+400, mainFrame.getY()+256); // cell  3,4
            mainFrame.getS2().setSelected(true);
			Thread.sleep(500);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500);
            assertEquals(3,mainFrame.getGameBoard().getRowSelected());
            assertEquals(4,mainFrame.getGameBoard().getColSelected());
            assertEquals(Cell.S, board.getCell(mainFrame.getGameBoard().getRowSelected(), mainFrame.getGameBoard().getColSelected()));
		} catch (AWTException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
