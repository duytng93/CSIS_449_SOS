package sos_game_sprint3;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Board {
	public enum Cell{E,S,O}
	public enum Turn{B,R}
	public enum GameType{Simple, General}
	public char blueCurrentSelection;
	public char redCurrentSelection;
	public GameType gameType;
	public boolean isPlaying;
	public ArrayList<WinLinesData> blueWinLines;
	public ArrayList<WinLinesData> redWinLines;
	public String gameStatus;
	public ArrayList<Move> recordedMoves;
	
	private Cell[][] grid;
	private int size;
	private final int DEFAULT_SIZE = 5;
	private Turn turn, lastTurn;
	private int availableCells;
	private boolean recording;
	
	//private char turn = 'B';

	public Board() {
		this.size = DEFAULT_SIZE;
		grid = new Cell[size][size];
		gameType = GameType.General;
		initBoard();
	}
	
	public void initBoard() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				grid[row][column] = Cell.E;
			}
		}
		turn = Turn.B;
		blueCurrentSelection = 's';
		redCurrentSelection = 's';
		availableCells=size*size;
		recording = false;
		recordedMoves = new ArrayList<Move>();
		isPlaying = true;
		blueWinLines = new ArrayList<WinLinesData>();
		redWinLines = new ArrayList<WinLinesData>();
		//gameStatus="Let's play";
	}
	
	public Cell getCell(int row, int column) {
		if (row >= 0 && row < size && column >= 0 && column < size)
			return grid[row][column];
		else 
			return null;
	}

	
	public int getBoardSize() {
		return size;
	}
	
	public void setSize(int newSize) {
		this.size = newSize;
		grid = new Cell[size][size];
	}
	
	public Turn getTurn() {
		return turn;
	}
	
	public Turn getLastTurn() {
		return lastTurn;
	}
	
	public int getAvailableCells() {
		return availableCells;
	}
	
	public void setRecording() {
		recording = true;
	}
	
	public boolean isRecording() {
		return recording;
	}
	
	public boolean makeMove(int row, int column) {
		gameStatus = "playing...";
		if (row >= 0 && row < size && column >= 0 && column < size && grid[row][column] == Cell.E) {
			if(recording) {
				if(turn == Turn.B)
					recordedMoves.add(new Move(row,column,blueCurrentSelection));
				else recordedMoves.add(new Move(row,column,redCurrentSelection));
			}
			lastTurn = turn;
			availableCells--;
			if(turn == Turn.B)
				grid[row][column] = (blueCurrentSelection == 's') ? Cell.S : Cell.O;
			else if(turn == Turn.R)
				grid[row][column] = (redCurrentSelection == 's') ? Cell.S : Cell.O;
			if(!hasScored(row,column,turn))
				turn = (turn == Turn.B) ? Turn.R : Turn.B;
			else {
				if(gameType==GameType.Simple) {
					gameStatus = (turn==Turn.B)? "Blue is the winner!!!" : "Red is the winner!!!";
					isPlaying = false;
				}
			}
			if(availableCells==0) {
				if(gameType==GameType.Simple&&!gameStatus.contains("winner"))
					gameStatus = "This is a draw!";
				else if(gameType==GameType.General) {
					if(blueWinLines.size()==redWinLines.size())
						gameStatus = "This is a draw!";
					else if(blueWinLines.size()>redWinLines.size())
						gameStatus = "Blue is the winner!!!";
					else if(blueWinLines.size()<redWinLines.size())
						gameStatus = "Red is the winner!!!";
				}
				isPlaying = false;
			}
			return true;	
		}else return false;
		//System.out.println(row+" "+column);
	}
	
	public boolean hasScored(int row, int col, Turn turn) {
		boolean isScore = false;
		if(grid[row][col] == Cell.S) {
//			if(sosCheck(row,col,row-1,col-1,row-2,col-2,turn)||sosCheck(row,col,row-1,col,row-2,col,turn))
//				return true;
//			else if(sosCheck(row,col,row+1,col+1,row+2,col+2,turn)||sosCheck(row,col,row,col+1,row,col+2,turn))
//				return true;
//			else if(sosCheck(row,col,row+1,col+1,row+2,col+2,turn)||sosCheck(row,col,row+1,col,row+2,col,turn))
//				return true;
//			else if(sosCheck(row,col,row+1,col-1,row+2,col-2,turn)||sosCheck(row,col,row,col-1,row,col-2,turn))
//				return true;
			if(sosCheck(row,col,row-1,col-1,row-2,col-2,turn))
				isScore = true;
			if(sosCheck(row,col,row-1,col,row-2,col,turn))
				isScore = true;
			if(sosCheck(row,col,row-1,col+1,row-2,col+2,turn))
				isScore = true;
			if(sosCheck(row,col,row,col+1,row,col+2,turn))
				isScore = true;
			if(sosCheck(row,col,row+1,col+1,row+2,col+2,turn))
				isScore = true;
			if(sosCheck(row,col,row+1,col,row+2,col,turn))
				isScore = true;
			if(sosCheck(row,col,row+1,col-1,row+2,col-2,turn))
				isScore = true;
			if(sosCheck(row,col,row,col-1,row,col-2,turn))
				isScore = true;
		}else {
			if(sosCheck(row,col-1,row,col,row,col+1,turn))
				isScore=true;
			if(sosCheck(row-1,col-1,row,col,row+1,col+1,turn))
				isScore=true;
			if(sosCheck(row-1,col,row,col,row+1,col,turn))
				isScore=true;
			if(sosCheck(row-1,col+1,row,col,row+1,col-1,turn))
				isScore=true;
		}
		// check 
		return isScore;
	}
	
	public boolean sosCheck(int row1, int col1, int row2, int col2, int row3, int col3, Turn turn) {
		try {
			if(grid[row1][col1] == Cell.S && grid[row2][col2] == Cell.O && grid[row3][col3] == Cell.S) {
				if(turn == Turn.B) {
					//System.out.println(row1 + " "+ col1 + " "+ row3 + " "+ col3);
					blueWinLines.add(new WinLinesData(row1,col1,row3,col3));
				}
				else redWinLines.add(new WinLinesData(row1,col1,row3,col3));
				return true;
			}
			else return false;
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	public class WinLinesData {
		private int row1,col1,row2,col2;
		WinLinesData(int row1, int col1, int row2, int col2){
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
		}
		public int getRow1() {
			return row1;
		}
		public int getCol1() {
			return col1;
		}
		public int getRow2() {
			return row2;
		}
		public int getCol2() {
			return col2;
		}
	}
	
	public class Move{
		private int row,col;
		private char sOrO;
		public Move(int row, int col, char sOrO){
			this.row = row;
			this.col = col;
			this.sOrO = sOrO;
		}
		public int getRow() {
			return row;
		}
		public int getCol() {
			return col;
		}
		public char getSOrO() {
			return sOrO;
		}
	}
	
	
//	public boolean SosCheck(int row1, int col1, int row2, int col2, int row3, int col3) {
//		
//		return false;
//	}
}
