package sos_game_sprint3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import sos_game_sprint3.Board.Cell;
import sos_game_sprint3.Board.Turn;
import sos_game_sprint3.Board.GameType;
import sos_game_sprint3.Board.Move;
import sos_game_sprint3.Board.WinLinesData;
/* 
 * The GUI code was originally written by 
 * Prof. Chua Hock Chuan, Nanyang Technological University 
 */

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public static final int CELL_SIZE = 50; 
	public static final int GRID_WIDTH = 8;
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
	public static final int SYMBOL_STROKE_WIDTH = 5; 

	public Font default_font;
//	private int CANVAS_WIDTH;
//	private int CANVAS_HEIGHT;

	private GameBoardCanvas gameBoardCanvas; 
	private Board board;
	private ArrayList<Move> recordedMoves;
	ImageIcon noRecordIcon;
	ImageIcon recordIcon;
	
	//northPanel components
	private JPanel northPanel;
	JTextField boardSizeField;
	JButton newGameButton;
	JLabel announcementLabel;
	JRadioButton simpleGameRadio;
	JRadioButton generalGameRadio;
	
	//westPanel components
	private JPanel westPanel;
	JRadioButton humanRadio2;
	JRadioButton computerRadio2;
	JRadioButton S2;
	JRadioButton O2;
	JLabel blueScore;
	
	//eastPanel components
	private JPanel eastPanel;
	JRadioButton humanRadio1;
	JRadioButton computerRadio1;
	JRadioButton S1;
	JRadioButton O1;
	JLabel redScore;
	
	//southPanel components
	private JPanel southPanel;
	JButton recordButton;
	JButton replayButton;
	JLabel turnLabel;
	
	public GUI(Board board) {
		this.board = board;
		setResizable(false);
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		this.setLocationRelativeTo(null);
		setTitle("GAME TIME!");
		setVisible(true); 
//		System.out.println(northPanel.getSize());
//		System.out.println(westPanel.getSize());
	}
	
//	public Board getBoard(){
//		return board;
//	}

	private void setContentPane(){
		gameBoardCanvas = new GameBoardCanvas(board.getBoardSize());  
		int DEFAULT_CANVAS_SIDE = CELL_SIZE * board.getBoardSize();
		gameBoardCanvas.setPreferredSize(new Dimension(DEFAULT_CANVAS_SIDE, DEFAULT_CANVAS_SIDE));

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20, 0));
		northPanel.setPreferredSize(new Dimension(DEFAULT_CANVAS_SIDE+200,70));
		setNorthPanelContent();
		
		
		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER,75, 5));
		southPanel.setPreferredSize(new Dimension(DEFAULT_CANVAS_SIDE+200,50));
		setSouthPanelContent();
		
		westPanel = new JPanel();
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		westPanel.setPreferredSize(new Dimension(150,DEFAULT_CANVAS_SIDE));
		setWestPanelContent();
		
		
		eastPanel = new JPanel();
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		eastPanel.setPreferredSize(new Dimension(150,DEFAULT_CANVAS_SIDE));
		setEastPanelContent();
		
		contentPane.add(eastPanel,BorderLayout.EAST);
		contentPane.add(westPanel, BorderLayout.WEST);
		contentPane.add(northPanel,BorderLayout.NORTH);
		contentPane.add(southPanel,BorderLayout.SOUTH);
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		
	}
	
	public void setNorthPanelContent() {
		JLabel gameName = new JLabel("SOS");
		gameName.setFont(new Font(gameName.getFont().getFamily(), Font.ITALIC,20));
		
		simpleGameRadio = new JRadioButton("Simple Game");
		//simpleGameRadio.addActionListener((e)->{board.gameType = GameType.Simple;});
		simpleGameRadio.addItemListener((e)->{
			if(e.getStateChange()==ItemEvent.SELECTED) {
				board.gameType = GameType.Simple;
			}
		});
		generalGameRadio = new JRadioButton("General Game",true);
		//generalGameRadio.addActionListener((e)->{board.gameType = GameType.General;});
		generalGameRadio.addItemListener((e)->{
			if(e.getStateChange()==ItemEvent.SELECTED) {
				board.gameType = GameType.General;
			}
		});
		ButtonGroup gameTypeGroup = new ButtonGroup();
		gameTypeGroup.add(simpleGameRadio);
		gameTypeGroup.add(generalGameRadio);
		
		boardSizeField = new JTextField(7);
		boardSizeField.setText("5");
		boardSizeField.setBorder(BorderFactory.createTitledBorder("Board Size"));
		boardSizeField.setHorizontalAlignment(JTextField.CENTER);
		
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			
			public void changeSize() {
				if(!"".equals(boardSizeField.getText())) {
					try {
						int newSize = Integer.parseInt(boardSizeField.getText());
						if(newSize<5) {
							//JOptionPane.showMessageDialog(null, "Please choose a number greater or equal 5!", "Board size too small",JOptionPane.INFORMATION_MESSAGE );
							showMessage("Please enter a number greater than or equal 5!", true);
						}else {
							board.setSize(newSize);
							gameBoardCanvas.setSize(board.getBoardSize());
							northPanel.setPreferredSize(new Dimension(CELL_SIZE * board.getBoardSize()+200,70));
							southPanel.setPreferredSize(new Dimension(CELL_SIZE * board.getBoardSize()+200,50));
							int verticalGap = (CELL_SIZE * board.getBoardSize()-250)/4>50?50:(CELL_SIZE * board.getBoardSize()-250)/4;
							westPanel.setPreferredSize(new Dimension(150,CELL_SIZE * board.getBoardSize()));
							westPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,verticalGap));
							eastPanel.setPreferredSize(new Dimension(150,CELL_SIZE * board.getBoardSize()));
							eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,verticalGap));
							board.initBoard();
							humanRadio1.setSelected(true);
							humanRadio2.setSelected(true);
							S1.setSelected(true);
							S2.setSelected(true);
							turnLabel.setBackground(Color.blue);
							blueScore.setText("0");
							redScore.setText("0");
							recordButton.setText("Record");
							recordButton.setIcon(noRecordIcon);
							replayButton.setEnabled(true);
							recordButton.setEnabled(true);
							simpleGameRadio.setEnabled(true);
							generalGameRadio.setEnabled(true);
							//board.isPlaying = false;
							showMessage("Let's play!",false);
//							System.out.println(getWidth());
//							System.out.print(getHeight());
						}
					}
					catch(Exception e1) {
						//JOptionPane.showMessageDialog(null, "Please make sure to type in a number!", "Error",JOptionPane.ERROR_MESSAGE );
						showMessage("Please make sure to type in a number!", true);
					}
				}
			};

			@Override
			public void actionPerformed(ActionEvent e) {
				if(board.isPlaying && board.getAvailableCells()<board.getBoardSize()*board.getBoardSize()) {
					int ans = JOptionPane.showConfirmDialog(null,"Are you sure to start a new game?","",JOptionPane.YES_NO_OPTION);
					if(ans == 0) 
						changeSize();
				}else {
					changeSize();
				}
				repaint();
				pack();
				setLocationRelativeTo(null);

			}
		});
		
		announcementLabel = new JLabel();
		announcementLabel.setPreferredSize(new Dimension(500,25));
		announcementLabel.setHorizontalAlignment(JLabel.CENTER);
		announcementLabel.setFont(new Font(announcementLabel.getFont().getFamily(), Font.PLAIN,20));
		showMessage("Let's play!",false);
		
		northPanel.add(gameName);
		northPanel.add(simpleGameRadio);
		northPanel.add(generalGameRadio);
		northPanel.add(boardSizeField);
		northPanel.add(newGameButton);
		northPanel.add(announcementLabel);
	}
	
	public void setEastPanelContent() {
		JLabel redPlayer = new JLabel("Red player");
		redPlayer.setFont(new Font(redPlayer.getFont().getFamily(), Font.BOLD,20));
		redPlayer.setForeground(Color.red);
		
		JPanel humanRedPanel = new JPanel();
		humanRedPanel.setLayout(new GridLayout(3,1));
		humanRadio1 = new JRadioButton("Human",true);
		humanRadio1.setFont(new Font(redPlayer.getFont().getFamily(), Font.BOLD,20));
		computerRadio1 = new JRadioButton("Computer");
		computerRadio1.setFont(new Font(redPlayer.getFont().getFamily(), Font.BOLD,20));
		ButtonGroup humanComputerGroup1 = new ButtonGroup();
		humanComputerGroup1.add(humanRadio1);
		humanComputerGroup1.add(computerRadio1);
		
		S1 = new JRadioButton("S",true); 
		S1.setHorizontalAlignment(JRadioButton.CENTER);
		S1.addItemListener((e)->{if(e.getStateChange()==ItemEvent.SELECTED) board.redCurrentSelection = 's';}); // radio button setSelect won't work with addActionlistener
		O1 = new JRadioButton("O");
		O1.setHorizontalAlignment(JRadioButton.CENTER);
		O1.addItemListener((e)->{if(e.getStateChange()==ItemEvent.SELECTED) board.redCurrentSelection = 'o';});
		
		ButtonGroup SOGroup1 = new ButtonGroup();
		SOGroup1.add(S1);
		SOGroup1.add(O1);
		
		redScore = new JLabel("0");
		redScore.setPreferredSize(new Dimension(120,90));
		redScore.setHorizontalAlignment(JLabel.CENTER);
		redScore.setFont(new Font(redScore.getFont().getFamily(), Font.BOLD,60));
		redScore.setForeground(Color.red);
		redScore.setBorder(BorderFactory.createTitledBorder("SCORE"));
		
		humanRedPanel.add(humanRadio1);
		humanRedPanel.add(S1);
		humanRedPanel.add(O1);
		
		eastPanel.add(redPlayer);
		eastPanel.add(humanRedPanel);
		eastPanel.add(computerRadio1);
		eastPanel.add(redScore);
	}
	
	public void setWestPanelContent() {
		JLabel bluePlayer = new JLabel("Blue player");
		bluePlayer.setFont(new Font(bluePlayer.getFont().getFamily(), Font.BOLD,20));
		bluePlayer.setForeground(Color.blue);
		
		JPanel humanBluePanel = new JPanel();
		humanBluePanel.setLayout(new GridLayout(3,1));
		humanRadio2 = new JRadioButton("Human",true);
		humanRadio2.setFont(new Font(bluePlayer.getFont().getFamily(), Font.BOLD,20));
		computerRadio2 = new JRadioButton("Computer");
		computerRadio2.setFont(new Font(bluePlayer.getFont().getFamily(), Font.BOLD,20));
		ButtonGroup humanComputerGroup2 = new ButtonGroup();
		humanComputerGroup2.add(humanRadio2);
		humanComputerGroup2.add(computerRadio2);
		
		S2 = new JRadioButton("S",true); 
		S2.setHorizontalAlignment(JRadioButton.CENTER);
		S2.addItemListener((e)->{if(e.getStateChange()==ItemEvent.SELECTED) board.blueCurrentSelection = 's';});
		O2 = new JRadioButton("O");
		O2.setHorizontalAlignment(JRadioButton.CENTER);
		O2.addItemListener((e)->{if(e.getStateChange()==ItemEvent.SELECTED) board.blueCurrentSelection = 'o';});
		ButtonGroup SOGroup2 = new ButtonGroup();
		SOGroup2.add(S2);
		SOGroup2.add(O2);
		
		blueScore = new JLabel("0");
		blueScore.setPreferredSize(new Dimension(120,90));
		blueScore.setHorizontalAlignment(JLabel.CENTER);
		blueScore.setFont(new Font(blueScore.getFont().getFamily(), Font.BOLD,60));
		blueScore.setForeground(Color.blue);
		blueScore.setBorder(BorderFactory.createTitledBorder("SCORE"));
		
		humanBluePanel.add(humanRadio2);
		humanBluePanel.add(S2);
		humanBluePanel.add(O2);
		
		westPanel.add(bluePlayer);
		westPanel.add(humanBluePanel);
		westPanel.add(computerRadio2);
		westPanel.add(blueScore);
	}
	
	public void setSouthPanelContent() {
		noRecordIcon = new ImageIcon("noRecordIcon.png");
		recordIcon = new ImageIcon("recordIcon.png");
		recordButton = new JButton(noRecordIcon);
		recordButton.setText("Record");
		recordButton.setHorizontalTextPosition(JButton.TRAILING);
		recordButton.addActionListener((e)->{
			if(!board.isRecording()) {
				if(board.getAvailableCells()<board.getBoardSize()*board.getBoardSize())
					JOptionPane.showMessageDialog(null,"Please click record before playing.", "Can't start recording during game", JOptionPane.INFORMATION_MESSAGE);
				else {
					board.setRecording();
					recordButton.setText("Recording...");
					recordButton.setIcon(recordIcon);
				}
			}
		});
		turnLabel = new JLabel("current turn");
		turnLabel.setPreferredSize(new Dimension(100,40));
		turnLabel.setForeground(Color.white);
		turnLabel.setBackground(Color.blue);
		turnLabel.setOpaque(true);
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		replayButton = new JButton("Replay");
		replayButton.addActionListener((e)->{
			if(board.isPlaying)
				JOptionPane.showMessageDialog(null,"The game isn't over yet.", "Can't replay during game", JOptionPane.INFORMATION_MESSAGE);
			else {
				if(board.recordedMoves.size()>0) {
					recordedMoves = new ArrayList<Move>(board.recordedMoves);
					GameType lastGameType = board.gameType;
					newGameButton.doClick();
					if(lastGameType == GameType.Simple)
						simpleGameRadio.setSelected(true);
					else generalGameRadio.setSelected(true);
					replayButton.setEnabled(false);
					recordButton.setEnabled(false);
					Thread childThread = new Thread(new Runnable() {

						@Override
						public void run() {
							showMessage("Replaying...",false);
							for(Move move : recordedMoves) {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
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
								repaint();
								
							}
							showMessage(board.gameStatus + " End replay.",false);
						}
					});
					childThread.start();
					
				}else {JOptionPane.showMessageDialog(null,"Nothing to replay!", "Record did not start", JOptionPane.INFORMATION_MESSAGE);
				}
			}});
		southPanel.add(recordButton);
		southPanel.add(turnLabel);
		southPanel.add(replayButton);
	}

	public class GameBoardCanvas extends JPanel {
		
		private int size;
		private int rowSelected;
		private int colSelected;
		GameBoardCanvas(int size){
			this.size = size;
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
					if(board.isPlaying) {
						rowSelected = e.getY() / CELL_SIZE;
						colSelected = e.getX() / CELL_SIZE;
						boolean sucess = board.makeMove(rowSelected, colSelected);
						if(sucess) {
							if(simpleGameRadio.isEnabled() && generalGameRadio.isEnabled())
							{
								simpleGameRadio.setEnabled(false);
								generalGameRadio.setEnabled(false);
							}
							showMessage(board.gameStatus,false);
							if(board.getTurn() == Turn.B)
								turnLabel.setBackground(Color.blue);
							else
								turnLabel.setBackground(Color.red);
							blueScore.setText(String.valueOf(board.blueWinLines.size()));
							redScore.setText(String.valueOf(board.redWinLines.size()));
							repaint(); 
						}
							
					}	
				}
			});
		}
		
		public int getRowSelected() {
			return rowSelected;
		}
		
		public int getColSelected() {
			return colSelected;
		}
		
		
		public void setSize(int newSize) {
			this.size = newSize;
			this.setPreferredSize(new Dimension(CELL_SIZE*size,CELL_SIZE*size));
		}
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawMoves(g);
			drawWinLines(g);
		}
		
		private void drawGridLines(Graphics g){
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < size; row++) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
						CELL_SIZE*size-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < size; col++) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
						GRID_WIDTH, CELL_SIZE*size-1, GRID_WIDTH, GRID_WIDTH);
			}

		}
		
		private void drawMoves(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.setColor(Color.black);
			for (int row = 0; row < board.getBoardSize(); ++row) {
				for (int col = 0; col < board.getBoardSize(); ++col) {
					if(row == rowSelected && col == colSelected) {
						if(board.getLastTurn()==Turn.B)
							g2d.setColor(Color.blue);
						else g2d.setColor(Color.red);
					}else g2d.setColor(Color.black);
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if (board.getCell(row,col) == Cell.S) {
						g2d.drawArc(x1,y1,CELL_SIZE-2*CELL_PADDING, CELL_SIZE/2 - CELL_PADDING, 35,235 );
						g2d.drawArc(x1,y1+CELL_SIZE/2 - CELL_PADDING,CELL_SIZE-2*CELL_PADDING, CELL_SIZE/2 - CELL_PADDING, -155,245 );
					} else if (board.getCell(row,col) == Cell.O) {
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
			}
		}
		
		//test draw red win line
		private void drawWinLines(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.setColor(Color.red);
			if(board.redWinLines.size()>0) {
				for (WinLinesData data : board.redWinLines) {
					int x1 = data.getCol1() * CELL_SIZE + CELL_SIZE/2;
					int y1 = data.getRow1() * CELL_SIZE + CELL_SIZE/2;
					int x2 = data.getCol2() * CELL_SIZE + CELL_SIZE/2;
					int y2 = data.getRow2() * CELL_SIZE + CELL_SIZE/2;
					g2d.drawLine(x1, y1, x2, y2);
				}
			}
			g2d.setColor(Color.blue);
			if(board.blueWinLines.size()>0) {
				for (WinLinesData data : board.blueWinLines) {
					int x1 = data.getCol1() * CELL_SIZE + CELL_SIZE/2;
					int y1 = data.getRow1() * CELL_SIZE + CELL_SIZE/2;
					int x2 = data.getCol2() * CELL_SIZE + CELL_SIZE/2;
					int y2 = data.getRow2() * CELL_SIZE + CELL_SIZE/2;
					//System.out.print(data.getRow1() + " "+data.getCol1() + " "+data.getRow2() + " "+data.getCol2());
					g2d.drawLine(x1, y1, x2, y2);
				}
				
			}
			
		}

	}
	
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI(new Board()); 
			}
		});
	}
	
	public void showMessage(String message, boolean error) {
		if(error) 
			announcementLabel.setForeground(Color.red);
		else 
			announcementLabel.setForeground(new Color(0,179,0)); //light dark green
		announcementLabel.setText(message);
	}
	
	
	// For testing
	public JTextField getBoardSizeField() {
		return boardSizeField;
	}
	
	public JButton getNewGameButton() {
		return newGameButton;
	}
	
	public GameBoardCanvas getGameBoard() {
		return gameBoardCanvas;
	}
	
	public JRadioButton getO1() {
		return O1;
	}
	
	public JRadioButton getO2() {
		return O2;
	}
	
	public JRadioButton getS1() {
		return S1;
	}
	
	public JRadioButton getS2() {
		return S2;
	}
	
	public JLabel getBlueScore() {
		return blueScore;
	}
	public JLabel getRedScore() {
		return redScore;
	}
	public JLabel getAccouncementLabel() {
		return announcementLabel;
	}
}
