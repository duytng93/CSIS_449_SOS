package sos_game_sprint2;

public class Board {
	public enum Cell{E,S,O}
	public enum Turn{B,R}
	public enum GameType{Simple, General}
	public char blueCurrentSelection;
	public char redCurrentSelection;
	public GameType gameType;
	public boolean isPlaying;
	
	private Cell[][] grid;
	private int size;
	private final int DEFAULT_SIZE = 5;
	private Turn turn;
	
	
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
		isPlaying = false;
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
	
	public void makeMove(int row, int column) {
		isPlaying = true;
		if (row >= 0 && row < size && column >= 0 && column < size && grid[row][column] == Cell.E) {
			if(turn == Turn.B)
				grid[row][column] = (blueCurrentSelection == 's') ? Cell.S : Cell.O;
			else if(turn == Turn.R)
				grid[row][column] = (redCurrentSelection == 's') ? Cell.S : Cell.O;
			if(!scoredPoint())
				turn = (turn == Turn.B) ? Turn.R : Turn.B;
		}
	}
	
	public boolean scoredPoint() {
		// check 
		return false;
	}
	
	public GameType getGameType() {
		return gameType;
	}
}
