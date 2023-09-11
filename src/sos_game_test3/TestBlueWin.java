package sos_game_test3;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.junit.After;
import org.junit.Before;

import sos_game_sprint3.Board;
import sos_game_sprint3.GUI;
import sos_game_sprint3.Board.Move;;
public class TestBlueWin {

	private Board board;
	private GUI mainFrame;
	private JRadioButton S1, S2, O1, O2;
	private ArrayList<Move> moves;
	private JLabel blueScore, redScore, announcementLabel;
	@Before
	public void setUp() throws Exception {
		board = new Board();
		mainFrame = new GUI(board);
		S1 = mainFrame.getS1();
		S2 = mainFrame.getS2();
		O1 = mainFrame.getO1();
		O2 = mainFrame.getO2();
		blueScore = mainFrame.getBlueScore();
		redScore = mainFrame.getRedScore();
		announcementLabel = mainFrame.getAccouncementLabel();
		moves = new ArrayList<Move>();
		moves.add(board.new Move(1,1,'s'));
		moves.add(board.new Move(2,2,'o'));
		moves.add(board.new Move(3,1,'s'));
		moves.add(board.new Move(3,2,'o'));
		moves.add(board.new Move(3,3,'s'));
		moves.add(board.new Move(1,3,'s'));
		moves.add(board.new Move(2,0,'s'));
		moves.add(board.new Move(1,2,'o'));
		moves.add(board.new Move(2,1,'o'));
		moves.add(board.new Move(2,3,'o'));
		moves.add(board.new Move(0,0,'s'));
		moves.add(board.new Move(1,0,'o'));
		moves.add(board.new Move(0,3,'s'));
		moves.add(board.new Move(3,0,'s'));
		moves.add(board.new Move(0,1,'s'));
		moves.add(board.new Move(0,2,'o'));
		moves.add(board.new Move(2,4,'o'));
		moves.add(board.new Move(1,4,'s'));
		moves.add(board.new Move(3,4,'s'));
		moves.add(board.new Move(4,4,'s'));
		moves.add(board.new Move(4,2,'s'));
		moves.add(board.new Move(4,0,'s'));
		moves.add(board.new Move(4,1,'o'));
		moves.add(board.new Move(4,3,'o'));
		moves.add(board.new Move(0,4,'s'));
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			for(Move move : moves) {
				if(move.getSOrO() == 's')
				{
					S1.setSelected(true);
					S2.setSelected(true);
				}else {
					O1.setSelected(true);
					O2.setSelected(true);
				}
				board.makeMove(move.getRow(), move.getCol());
				blueScore.setText(String.valueOf(board.blueWinLines.size()));
				redScore.setText(String.valueOf(board.redWinLines.size()));
				announcementLabel.setText(board.gameStatus);
				mainFrame.repaint();
				Thread.sleep(500);
			}
			if(!board.gameStatus.contains("Blue"))
				fail("blue should be the winner!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
