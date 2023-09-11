package sos_game_test;


import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import sos_game.Board;
import sos_game.GUI;

@RunWith(Parameterized.class)       // parameterized test step 1: Annotate test class with @RunWith(Parameterized.class).
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
	
	@Parameterized.Parameters  // parameterized test step 2: Create a public static method annotated with @Parameters that returns a Collection of Objects (as Array) as test data set.
	public static Collection sizeEntry() {
		return Arrays.asList(new Object[] {
				"7","10","a","3"
		});
	}
	
	public TestBoardSizeChange(String newSizeEntry) {  // parameterized test step 3: Create a public constructor that takes in what is equivalent to one "row" of test data.
		size = newSizeEntry;
	}
	
	@Test
	public void test() {  // parameterized test step 4: Create your test case(s) using the instance variables as the source of the test data.
		try {
			mainFrame.getBoardSizeField().setText(size);
			Thread.sleep(500);
			mainFrame.getNewGameButton().doClick();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
