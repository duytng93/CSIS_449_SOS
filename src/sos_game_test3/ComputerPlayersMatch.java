package sos_game_test3;

import static org.junit.Assert.*;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sos_game_sprint3.Board;
import sos_game_sprint3.GUI;
import sos_game_sprint3.Board.Move;

public class ComputerPlayersMatch {
	
	private Board board;
	private GUI mainFrame;
	private JLabel blueScore, redScore, announcementLabel;
	private JRadioButton C1, C2;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		mainFrame = new GUI(board);
		C1 = mainFrame.getCopmuterRadio1();
		C2 = mainFrame.getCopmuterRadio2();
		blueScore = mainFrame.getBlueScore();
		redScore = mainFrame.getRedScore();
		announcementLabel = mainFrame.getAccouncementLabel();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		try {
			Thread.sleep(1000);
			C2.setSelected(true);
			Thread.sleep(1000);
			C1.setSelected(true);
			
			while(board.gameStatus.contains("playing") || board.gameStatus.contains("Let's play")) {
				blueScore.setText(String.valueOf(board.blueWinLines.size()));
				redScore.setText(String.valueOf(board.redWinLines.size()));
				announcementLabel.setText(board.gameStatus);
				mainFrame.repaint();
			}
			
			if(!board.gameStatus.contains("winner") && !board.gameStatus.contains("draw") )
				fail("game end but with no valid result");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
