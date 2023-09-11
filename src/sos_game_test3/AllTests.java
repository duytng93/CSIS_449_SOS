package sos_game_test3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBoardSizeChange.class, TestClickOnGameBoard.class, TestEmptyBoard.class, TestGUI.class, TestBlueWin.class, TestDraw.class, TestRedWin.class })
public class AllTests {

}
