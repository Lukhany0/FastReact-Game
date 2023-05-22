import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.html.HTMLEditorKit;

public class Game implements ActionListener {
	
	//panels
	JPanel mainPanel;	
	JPanel gameEnd_pnl;	
	JPanel play_pnl;	
	JFrame frame;
	
	//gameEnd_pnl: labels
	JLabel result_lbl;
	JLabel res_time_lbl;
	JLabel ask_replay_lbl;
	
	//JPanel panel = new JPanel();
	JPanel detPanel = new JPanel();
	JProgressBar progressbar = new JProgressBar(JProgressBar.VERTICAL, 0, 30);
	HashMap<Integer, Integer> bounds = new HashMap<>(); //coordinates of (x,y) bounds for buttons
	//width and height of buttons
	int width = 50;
	int height = 50;
	int timeTaken = 0;
	//buttons
	JLabel label_score;
	JButton btn1 = new JButton("1");
	JButton btn2 = new JButton("2");
	JButton btn3 = new JButton("3");
	JButton btn4 = new JButton("4");
	JButton btn5 = new JButton("5");
	JButton btn6 = new JButton("6");
	JButton btn7 = new JButton("7");
	JButton btn8 = new JButton("8");
	JButton btn9 = new JButton("9");
	JButton btn10 = new JButton("10");
	JButton btn11 = new JButton("11");
	JButton btn12 = new JButton("12");
	JButton btn13 = new JButton("13");
	JButton btn14 = new JButton("14");
	JButton btn15 = new JButton("15");
	JButton btn16 = new JButton("16");
	JButton btn17 = new JButton("17");
	JButton btn18 = new JButton("18");
	JButton btn19 = new JButton("19");
	JButton btn20 = new JButton("20");
	JButton [] buttons = {
			btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20
	};  //buttons array
	
	List<Integer> correct_order = new ArrayList<>();
	int len = buttons.length;
	private int steps = len; 
	private int score = 0;
	boolean isOver;
	boolean isWin;
	String resScore;
	String resGame;

	
	//constructor
	public Game() {
		
		//set containers
		frame = new JFrame();
		gameEnd_pnl = new JPanel();
		play_pnl = new JPanel();
		mainPanel = new JPanel();
				
		gameEndScreen(); //set elements to gameEnd_pnl
		gameOpenScreen();	//set elements to play_pnl
		
		mainPanel.setVisible(true); //start game
		
		frame.add(mainPanel);
		frame.add(play_pnl);
		
		frame.setTitle("FastReact game");
		frame.setSize(700,700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	//welcome screen
	void gameOpenScreen() {
		//game instructions
		JLabel heading_lbl = new JLabel();
		heading_lbl.setText("<html><body style='font-size: 15px; color: black; font-family: \"MV boli\" '>welcome to <span style='color:white; font-size:17px'>Fast React Game</span></body></html>");
		heading_lbl.setBounds(150,10, 350,90);
		String paragraph = "<html><body style='margin: 0; font-family: \"MV boli\" ; background-color: rgb(20,120,150)'>" 
				+ "<h4 style='padding-left: 20px; font-size: 13px;' >How to play:</h2>"
				+ "<ul style='font-size: 13px;'"
				+	"<li>The play screen shows play buttons, score, and a timer</li>"
				+ 	"<li>You have to click all the buttons in ascending order</li>"
				+ 	"<li>Finish before time ends</li>"
				+	"<li>otherwise you lose</li>"
				
				+ "</ul>"
				+ "</body></html>";
		//styling field with CSS
		JEditorPane text_field = new JEditorPane();
		text_field.setEditable(false);
		text_field.setBackground(new Color(20,120,150));	
		text_field.setEditorKit(new HTMLEditorKit());
		text_field.setBounds(0,100,700,180);
		text_field.setText(paragraph); //use HTML to style and format text
		
		//mainPanel.setVisible(false);
		setScreen(mainPanel); //set up panel attributes
		
		JButton play_btn = new JButton("Play");;
		setTaskButton(play_btn, 300, 300);
		play_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				//hide mainPanel from screen and display play_pnl
				mainPanel.setVisible(false);
				playGame();
				play_pnl.setVisible(true);
				frame.add(gameEnd_pnl);
				SwingUtilities.updateComponentTreeUI(frame); //update frame
			}
		});
		
		JButton quit_btn = new JButton("Quit");
		setTaskButton(quit_btn,300,400); // quit button setup
		quit_btn.setBackground(Color.red);
		quit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		
		mainPanel.add(heading_lbl);
		mainPanel.add(text_field);
		mainPanel.add(quit_btn);
		mainPanel.add(play_btn);
	}
	
	//play screen
	void playGame() {
		//count down timer
		progressbar.setBounds(550,0,150,700);
		progressbar.setStringPainted(true);
		progressbar.setForeground(Color.green);
		progressbar.setBorderPainted(false);
		
		//button bounds (x,y)
		bounds.put(50,50);
		bounds.put(10,150);
		bounds.put(203,10);
		bounds.put(100,250);
		bounds.put(51,350);
		bounds.put(101,400);
		bounds.put(151,100);
		bounds.put(202,400);
		bounds.put(201,300);
		bounds.put(300,350);
		bounds.put(250,150);
		bounds.put(200,200);
		bounds.put(450,100);
		bounds.put(400,200);
		bounds.put(350,150);
		bounds.put(305,250);
		bounds.put(401,300);
		bounds.put(451,400);
		bounds.put(304,50);
		bounds.put(351,450);
		
		//progress bar panel
		detPanel.setBounds(0,550, 550,150);
		detPanel.setBackground(Color.cyan);
		
		//score label
		label_score = new JLabel();
		label_score.setBounds(10,600,50,50);
		label_score.setFont(new Font("MV boli", Font.BOLD, 25));
		label_score.setText("Score: " + getScore());
		
		setButtonToScreen(); //add buttons to screen
		
		setPlayButtonAttributes(); //setup play buttons
		
		detPanel.add(label_score); //add score to detPanel
		//add widgets to play_pnl
		play_pnl.add(progressbar);
		play_pnl.add(detPanel);
		play_pnl.setBounds(0,0,700,700);
		play_pnl.setLayout(null);
		play_pnl.setVisible(true);
		fill();		//start count down timer
	}
	

	//restart game
	void restartGame(){
		
		//reset values
		score = 0;
		steps = len;
		correct_order.clear();
		isOver = false;
		isWin = false;
		timeTaken= 0;
	
		gameEnd_pnl.setVisible(false);	//hide gameEnd_pnl
		play_pnl.setVisible(true);		//display play_pnl
		
		setButtonToScreen(); 
		fill();	
		
		label_score.setText("score: " + getScore()); //display score
		SwingUtilities.updateComponentTreeUI(frame); //update the frame

	}
	//game over screen
	void gameEndScreen() {
	
		result_lbl = new JLabel();	//game results win/lose
		result_lbl.setBounds(200,100,300,100);
		result_lbl.setFont(new Font("MV boli", Font.BOLD, 40));
		result_lbl.setText("You " + getResGame() + "!");
		
		res_time_lbl = new JLabel();	//results data score/time taken
		res_time_lbl.setBounds(200,200,200,50);
		res_time_lbl.setFont(new Font("MV boli", Font.BOLD, 25));
		res_time_lbl.setText("Score: " + getResScore());
		
		ask_replay_lbl = new JLabel();
		ask_replay_lbl.setText("Do you want to play again?");
		ask_replay_lbl.setBounds(200,300,400,50);
		ask_replay_lbl.setFont(new Font("MV boli", Font.BOLD, 25));
		ask_replay_lbl.setFocusable(false);
		
		setScreen(gameEnd_pnl); //setup game over screen: gameEnd panel
		
		JButton replay_btn = new JButton("yes");	//replay button
		setTaskButton(replay_btn,200,350); 	// setup replay button
		replay_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				restartGame();	
			}
		});
		
		JButton exit_btn = new JButton("no"); //exit button
		setTaskButton(exit_btn,400,350); 	//setup exit button
		exit_btn.setBackground(Color.red);
		exit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);		//quit game
			}
		});
		
		//add widgets to gameEnd_pnl
		gameEnd_pnl.add(exit_btn);
		gameEnd_pnl.add(replay_btn);
		gameEnd_pnl.add(result_lbl);
		gameEnd_pnl.add(res_time_lbl);
		gameEnd_pnl.add(ask_replay_lbl);
	}
	
	//set play button styles
	void setPlayButtonAttributes(){
		for(int j=0; j<len; j++) {
			buttons[j].setFocusable(false);
			buttons[j].addActionListener(this);
			buttons[j].setFont(new Font("MV boli", Font.BOLD, 11));
			play_pnl.add(buttons[j]);
		}
	}
	//function to set bounds for buttons
	void setButtonToScreen(){
		int i=0;
		int [] indexing = rearrangeButtonsOrder();
		while(i<bounds.size()) {
			
			for(Map.Entry<Integer, Integer>  entry: bounds.entrySet()){
				int key = entry.getKey();	
				int value = entry.getValue();	
		    	buttons[indexing[i]].setBounds(key, value, width, height);
		    	i+=1;
			} 
		}
	}
	
	//count down timer function
	public void fill() {
		
		progressbar.setForeground(Color.green); 
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int counter = 30;
			@Override
			public void run() {
				
				if(isOver == true) { 
					timer.cancel();
				}
				if(isWin == true) {
					timer.cancel(); 

				}
				progressbar.setValue(counter);
				progressbar.setString(""+counter);

				if(counter < 10) {
					progressbar.setForeground(Color.red); 
				}
				
				if(counter >= 0) {
					counter--;	
					
					setTimeTaken(timeTaken);
				}
				else {
					timer.cancel();
					gameEnd(-1);
				}
				
			}
		};
		
		timer.scheduleAtFixedRate(task, 100, 1000);
	}
	//function to rearrange  elements in an array
	public int [] rearrangeButtonsOrder() {
		
		int [] v = new int[20];
		for(int i=0; i<v.length; i++) {
			v[i] = i;
		}
		for (int i = 0; i < v.length; i++) {
			int index = (int) (Math.random() * v.length);
			int temp = v[i];
			v[i] = v[index];
			v[index] = temp;
		 }
		 return v;
	}
	
	void setTimeTaken(int time) {
		this.timeTaken = timeTaken + 1;
	}
	
	int getTimeTaken() {
		return timeTaken;
	}
	
	void setScore(int score) {
		this.score = score + 1;
	}
	
	int getScore() {
		return score;
	}
	
	//function to check the correctness of clicked button
	public boolean isCorrect(List<Integer> order, int value) {
		
		if(!order.isEmpty() && order.get(0) != 0) {
			return false;
		}
		//for list > 1
		if(order.size() > 1) {
			for(int i=0; i< order.size(); i++) {
				for(int j=1; j<order.size(); j++) {
					if(order.get(j) != order.get(j-1) + 1) {
						return false;	
					}
				}
			}
		}
		return true;
	}
	
	//function to get game results
	public String getResGame() {
		return resGame;
	}
	//function to get game results score
	public String getResScore() {
		return resScore;
	}
	//function to set game results
	public void setResGame(String gr) {
		this.resGame = gr;
	}
	//function to set results score
	public void setResScore(int rs) {
		this.resScore = rs + "";
	}
	
	//function to set game results values
	void displayResults(int res) {
				
		if(res == -1) { 
			setResGame("Lose");	
			setResScore(getScore());	
			res_time_lbl.setText("Score: " + getResScore());	
			
		}
		else if(res == 1) { 
			setResGame("Win");	
			res_time_lbl.setText("time: " + getTimeTaken() + "s"); 
		
		}
		else{
			System.out.println("Error!!! game result: result must be 1 or zero");
			
		}
		result_lbl.setText("You " + getResGame()+ "!");
	} 
	
	//function to check is game is over
	boolean isGameOver(int val){

		if(!isCorrect(correct_order, val)) {
			return true;
		}
		return false; 
	}

	//function to display game over screen
	void gameEnd(int res){
		

		displayResults(res);

		play_pnl.setVisible(false);
		gameEnd_pnl.setVisible(true);
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	//function to check is user has won
	boolean isWin(int steps) {
		if(steps==0) {
			return true;

		}
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0; i<buttons.length;i++) {
			if(e.getSource() == buttons[i]) {
				steps--; 
				correct_order.add(i);		
				isOver = isGameOver(i);	
				
				if(isOver) {
					gameEnd(-1);
					break;
				}
				setScore(score);	
				label_score.setText("Score: " + getScore()); 
			}
		}
		isWin = isWin(steps);	
		if(isWin) {				
			gameEnd(1);
		}
	}
	//set screens
	void setScreen(JPanel p) {
		p.setBounds(0,0,700,700);
		p.setBackground(new Color(20,120,150));
		p.setLayout(null);
		p.setVisible(false);
	}
	//set buttons that are not in the play screen
	void setTaskButton(JButton b, int x, int y) {
		int height = 50;
		int width = 100;
		b.setBounds(x,y, width,height);
		b.setBackground(Color.cyan);
		b.setFocusable(false);
		b.setUI(new BasicButtonUI());
		b.setFont(new Font("MV boli", Font.PLAIN, 25));
	}
}