package sos_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
	public static final int SYMBOL_STROKE_WIDTH = 8; 

	public Font default_font;
//	private int CANVAS_WIDTH;
//	private int CANVAS_HEIGHT;

	private GameBoardCanvas gameBoardCanvas; 

	private Board board;
	
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	
	JTextField boardSizeField;
	JButton newGameButton;
	JLabel announcementLabel;
	
	public GUI(Board board) {
		this.board = board;
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("GAME TIME!");
		setVisible(true);  
	}
	
	public Board getBoard(){
		return board;
	}

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
		
		JRadioButton simpleGameRadio = new JRadioButton("Simple Game",true);
		JRadioButton generalGameRadio = new JRadioButton("General Game");
		ButtonGroup gameTypeGroup = new ButtonGroup();
		gameTypeGroup.add(simpleGameRadio);
		gameTypeGroup.add(generalGameRadio);
		
		boardSizeField = new JTextField(7);
		boardSizeField.setBorder(BorderFactory.createTitledBorder("Board Size"));
		boardSizeField.setHorizontalAlignment(JTextField.CENTER);
		
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
							showMessage("Let's play!",false);
							repaint();
							pack();
							System.out.println(getWidth());
							System.out.print(getHeight());
						}
					}
					catch(Exception e1) {
						//JOptionPane.showMessageDialog(null, "Please make sure to type in a number!", "Error",JOptionPane.ERROR_MESSAGE );
						showMessage("Please make sure to type in a number!", true);
					}
				}
			}
			
		});
		
		announcementLabel = new JLabel();
		announcementLabel.setPreferredSize(new Dimension(300,20));
		announcementLabel.setHorizontalAlignment(JLabel.CENTER);
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
		JRadioButton humanRadio1 = new JRadioButton("Human",true);
		humanRadio1.setFont(new Font(redPlayer.getFont().getFamily(), Font.BOLD,20));
		JRadioButton computerRadio1 = new JRadioButton("Computer");
		computerRadio1.setFont(new Font(redPlayer.getFont().getFamily(), Font.BOLD,20));
		ButtonGroup humanComputerGroup1 = new ButtonGroup();
		humanComputerGroup1.add(humanRadio1);
		humanComputerGroup1.add(computerRadio1);
		
		JRadioButton S1 = new JRadioButton("S"); 
		S1.setHorizontalAlignment(JRadioButton.CENTER);
		JRadioButton O1 = new JRadioButton("O");
		O1.setHorizontalAlignment(JRadioButton.CENTER);
		ButtonGroup SOGroup1 = new ButtonGroup();
		SOGroup1.add(S1);
		SOGroup1.add(O1);
		
		JLabel redScore = new JLabel("0");
		redScore.setPreferredSize(new Dimension(120,100));
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
		JRadioButton humanRadio2 = new JRadioButton("Human",true);
		humanRadio2.setFont(new Font(bluePlayer.getFont().getFamily(), Font.BOLD,20));
		JRadioButton computerRadio2 = new JRadioButton("Computer");
		computerRadio2.setFont(new Font(bluePlayer.getFont().getFamily(), Font.BOLD,20));
		ButtonGroup humanComputerGroup2 = new ButtonGroup();
		humanComputerGroup2.add(humanRadio2);
		humanComputerGroup2.add(computerRadio2);
		
		JRadioButton S2 = new JRadioButton("S"); 
		S2.setHorizontalAlignment(JRadioButton.CENTER);
		JRadioButton O2 = new JRadioButton("O");
		O2.setHorizontalAlignment(JRadioButton.CENTER);
		ButtonGroup SOGroup2 = new ButtonGroup();
		SOGroup2.add(S2);
		SOGroup2.add(O2);
		
		JLabel blueScore = new JLabel("0");
		blueScore.setPreferredSize(new Dimension(120,100));
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
		JCheckBox recordCheckBox = new JCheckBox("Record game");
		JLabel turnLabel = new JLabel("current turn");
		turnLabel.setPreferredSize(new Dimension(100,40));
		turnLabel.setForeground(Color.white);
		turnLabel.setBackground(Color.blue);
		turnLabel.setOpaque(true);
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		//turnLabel.setBorder(BorderFactory.createTitledBorder("Current turn:"));
		JButton replayButton = new JButton("Replay");
		southPanel.add(recordCheckBox);
		southPanel.add(turnLabel);
		southPanel.add(replayButton);
	}

	class GameBoardCanvas extends JPanel {
		
		private int size;
		
		GameBoardCanvas(int size){
			this.size = size;
			this.setBorder(BorderFactory.createLineBorder(Color.black));
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
}
