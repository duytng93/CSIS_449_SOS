package sos_game_test2;


import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import sos_game_sprint2.Board;
import sos_game_sprint2.GUI;


public class TestBoardSizeChange {

	private GUI mainFrame;
	private String size;
	@Before
	public void setUp() throws Exception {
		mainFrame = new GUI(new Board());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {  // parameterized test step 4: Create your test case(s) using the instance variables as the source of the test data.
		try {
			mainFrame.getBoardSizeField().setText("7");
			Thread.sleep(500);
			mainFrame.getNewGameButton().doClick();
			Thread.sleep(500);
			mainFrame.getBoardSizeField().setText("10");
			Thread.sleep(500);
			mainFrame.getNewGameButton().doClick();
			Thread.sleep(500);
			mainFrame.getBoardSizeField().setText("a");
			Thread.sleep(500);
			mainFrame.getNewGameButton().doClick();
			Thread.sleep(500);
			mainFrame.getBoardSizeField().setText("-1");
			Thread.sleep(500);
			mainFrame.getNewGameButton().doClick();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
