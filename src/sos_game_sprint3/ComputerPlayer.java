package sos_game_sprint3;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import sos_game_sprint3.Board.Cell;
import sos_game_sprint3.Board.Move;
import sos_game_sprint3.Board.Turn;

public class ComputerPlayer implements Runnable{

	private Board board;
	private GUI gui;
	private String color;
	private char nextMove_SorO;
	public static AtomicBoolean stop = new AtomicBoolean(false);
	@Override
	public void run() {
		try {
			while(!stop.get()) {
				
				if(color == "red") {
					Thread.sleep(500);
					if(board.getTurn() == Turn.R) {
						Thread.sleep(1000);
						System.out.print("thread "+color+" made a move\n");
						makeAutoMove();
					}
				}else {
					Thread.sleep(500);
					if(board.getTurn() == Turn.B) {
						Thread.sleep(1000);
						System.out.print("thread "+color+" made a move\n");
						makeAutoMove();
					}
				}
				gui.getBlueScore().setText(String.valueOf(board.blueWinLines.size()));
				gui.getRedScore().setText(String.valueOf(board.redWinLines.size()));
				gui.showMessage(board.gameStatus, false);
				gui.repaint();
				if(board.getTurn() == Turn.B)
					gui.getTurnLabel().setBackground(Color.blue);
				else
					gui.getTurnLabel().setBackground(Color.red);
			}
			System.out.print("thread "+color+" is stop\n");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ComputerPlayer(Board board, GUI gui, String color) {
		this.board = board;
		this.gui = gui;
		this.color = color;
	}
	
	private void makeAutoMove() {
		System.out.print("makeAutoMove called\n");
		if(!stop.get()) {
			if (!makeWinningMove()) {
				makeRandomMove();
			}
		}
		
	}
	
	private boolean makeWinningMove() {
		System.out.print("makeWinningMove called\n");
		Move nextWinMove = finWinMove();
		if(nextWinMove.getCol() == -1)
			return false;
		else
		{
			if(color == "red") {
				if(nextWinMove.getSOrO() == 's') 
					gui.getS1().setSelected(true);
				else
					gui.getO1().setSelected(true);
			}
			else {
				if(nextWinMove.getSOrO() == 's') 
					gui.getS2().setSelected(true);
				else
					gui.getO2().setSelected(true);
			}
			if(!stop.get())
			board.makeMove(nextWinMove.getRow(), nextWinMove.getCol());
			return true;
		}
	}
	
	private void makeRandomMove() {
		System.out.print("makeRandomMove called\n");
		Move nextWinMove = finWinMove();
		boolean success = false;
		Random random = new Random();
		boolean S_move = random.nextBoolean();
		if(color == "red") {
			if(S_move) 
				gui.getS1().setSelected(true);
			else
				gui.getO1().setSelected(true);
		}
		else {
			if(S_move) 
				gui.getS2().setSelected(true);
			else
				gui.getO2().setSelected(true);
		}
		
		int nextMove,nextMove_row,nextMove_column;
		do {
			nextMove = random.nextInt(board.getBoardSize()*board.getBoardSize());
			nextMove_row =  nextMove/board.getBoardSize();
			nextMove_column = nextMove%board.getBoardSize();
			if(!stop.get()) {
				success = board.makeMove(nextMove_row, nextMove_column);
			}
		}while (!success);
	}
	
	public boolean S_SCheck(int row1, int col1, int row2, int col2) {
		try {
			if(board.getCell(row1, col1) == Cell.S && board.getCell(row2, col2) == Cell.S ) {
				nextMove_SorO = 'o';
				return true;
			}
			else return false;
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public boolean SO_Check(int row1, int col1, int row2, int col2) {
		try {
			if(board.getCell(row1, col1) == Cell.S && board.getCell(row2, col2) == Cell.O ) {
				nextMove_SorO = 's';
				return true;
			}
			else return false;
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public Move finWinMove() {
		for (int row = 0; row < board.getBoardSize(); ++row) {
			for (int col = 0; col < board.getBoardSize(); ++col) {
				if(board.getCell(row, col) == Cell.E) {
					// S-O-(_) cases
					if(SO_Check(row,col-2,row,col-1))
						return board.new Move(row,col,nextMove_SorO);
					if(SO_Check(row-2,col-2,row-1,col-1))
						return board.new Move(row,col,nextMove_SorO);
					if(SO_Check(row-2,col,row-1,col))
						return board.new Move(row,col,nextMove_SorO);
					if(SO_Check(row-2,col+2,row-1,col+1))
						return board.new Move(row,col,nextMove_SorO);
					if(SO_Check(row,col+2,row,col+1))
						return board.new Move(row,col,nextMove_SorO);
					if(SO_Check(row+2,col+2,row+1,col+1))
						return board.new Move(row,col,nextMove_SorO);	
					if(SO_Check(row+2,col,row+1,col))
						return board.new Move(row,col,nextMove_SorO);
					if(SO_Check(row+2,col-2,row+1,col-1))
						return board.new Move(row,col,nextMove_SorO);
					
					// S-(_)-S cases
					if(S_SCheck(row-1,col-1,row+1,col+1))
						return board.new Move(row,col,nextMove_SorO);
					if(S_SCheck(row+1,col,row-1,col))
						return board.new Move(row,col,nextMove_SorO);
					if(S_SCheck(row-1,col+1,row+1,col-1))
						return board.new Move(row,col,nextMove_SorO);
					if(S_SCheck(row,col+1,row,col-1))
						return board.new Move(row,col,nextMove_SorO);
				}
			}
		}
		
		// in case of can't find any win move
		return board.new Move(-1,-1,'s');
	}
}
