package sos_game;

public class Board {

	private int[][] grid;
	private int size;
	private final int DEFAULT_SIZE = 5;
	private char turn = 'B';

	public Board() {
		this.size = DEFAULT_SIZE;
		grid = new int[size][size];
	}
	
	public int getCell(int row, int column) {
		if (row >= 0 && row < size && column >= 0 && column < size)
			return grid[row][column];
		else 
			return -1;
	}

	
	public int getBoardSize() {
		return size;
	}
	
	public void setSize(int newSize) {
		this.size = newSize;
		grid = new int[size][size];
	}
	
	public char getTurn() {
		return turn;
	}
}
