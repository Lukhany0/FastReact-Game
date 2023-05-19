
import java.util.Timer;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.*;

public class Game implements ActionListener{
	
	/*int random;
	 JLabel random_lbl;
	
	*/
	
	//game over screen
	JPanel overPanel;	//panel for after game screen
	
	JLabel result_lbl;
	JLabel res_time_lbl;
	JLabel ask_replay_lbl;
	String resScore;
	String resGame;
	
	
	
	//game one
	JPanel playPanel;	//game components panel
	JFrame frame;		//panels parent: game container
	boolean isOver;		//game status:
	boolean isWin;		//win status
	
	JPanel detPanel = new JPanel();		//game details panel: score panel
	JProgressBar progressbar = new JProgressBar(JProgressBar.VERTICAL, 0, 49);	//count down timer bar
	HashMap<Integer, Integer> bounds = new HashMap<>(); //store coordinates(x,y) of buttons
	
	//width and height for buttons
	int width = 50;
	int height = 50;
	int timeLeft;	//time remaining
	//buttons
	JLabel label_score;	//label for displaying score in det panel
	
	//declare: game play buttons
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
	
	//array: store buttons
	JButton [] buttons = {
			btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20
	};  //buttons array
	
	
	List<Integer> correct_order;	//list: store clicked buttons
	int len = buttons.length;	//total number of buttons
	private int steps; // steps remaining
	private int score;	// variable: game score
	
	
	Game(){
		
		initializeGame();
		
		
		//commented are game over screen components
		/*random_lbl = new JLabel();
		random_lbl.setBounds(500,500,300,100);
		random_lbl.setFont(new Font("MV boli", Font.BOLD, 40));
		
		
		
		int mx=100;
		int m=1;
		int temp = (int)Math.floor(Math.random()*(mx - m) + 1) + m;
		random_lbl.setText(temp + "");
		
		
		
		this.p = p;
		this.removeAll();
		this.frame = frame;
		System.out.println("Game / panel2");
		*/
		
		
		
		
		
	}
	void initializeGame() {
		frame = new JFrame("game");
		startGame(); //call function that sets up playPanel screen and components
		
		
		frame.setSize(700,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}
	//start play screen
	 void startGame(){
		 
		//game variables: attributes
	 	score = 0; //initialize score
	 	steps = len;	//initialize steps remaining: steps = 20(number of buttons to be clicked)
	 	correct_order = new ArrayList<>(); //empty list for storing clicked buttons
	 	
	 	//initialize game components
	 	playPanel = new JPanel();
		playPanel.setSize(700,700);
		playPanel.setLayout(null);
		
		//game on
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
		
		detPanel.setBounds(0,550, 550,150);
		detPanel.setBackground(Color.cyan);
		
		label_score = new JLabel();
		label_score.setBounds(10,600,50,50);
		label_score.setFont(new Font("MV boli", Font.BOLD, 25));
		label_score.setText("Score: " + getScore());
		
		for(Map.Entry<Integer, Integer>  entry: bounds.entrySet()){
			int key = entry.getKey();
			int value = entry.getValue();
		}
		int i=0;
		int [] indexing = buttonsSetBounds();
		while(i<bounds.size()) {
			for(Map.Entry<Integer, Integer>  entry: bounds.entrySet()){
				int key = entry.getKey();
				int value = entry.getValue();
		    	buttons[indexing[i]].setBounds(key, value, width, height);
		    	i+=1;
			}
		}
		for(int j=0; j<len; j++) {
			buttons[j].setFocusable(false);
			buttons[j].addActionListener(this);
			playPanel.add(buttons[j]);
		}
		
		
		
		
		
		detPanel.add(label_score);
		playPanel.add(progressbar);
		playPanel.add(detPanel);
		playPanel.setSize(700,700);
		playPanel.setLayout(null);
		playPanel.setVisible(true);
		fill();
		
				
	}
	 
	void gameOverScreen(){
		
		//setting up components
		result_lbl = new JLabel();
		result_lbl.setBounds(200,100,300,100);
		result_lbl.setFont(new Font("MV boli", Font.BOLD, 40));
		result_lbl.setText("status: " + getResGame());
		
		res_time_lbl = new JLabel();
		res_time_lbl.setBounds(200,200,200,50);
		res_time_lbl.setFont(new Font("MV boli", Font.BOLD, 25));
		res_time_lbl.setText("Score: " + getResScore());
		
		ask_replay_lbl = new JLabel();
		ask_replay_lbl.setText("Do you want to play again?");
		ask_replay_lbl.setBounds(200,300,400,50);
		ask_replay_lbl.setFont(new Font("MV boli", Font.BOLD, 25));
		ask_replay_lbl.setFocusable(false);
		
		//overPanel.add(random_lbl);
		overPanel.add(result_lbl);
		overPanel.add(res_time_lbl);
		overPanel.add(ask_replay_lbl);
	}
	
	public void fill() {
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int counter = 49;
			@Override
			public void run() {
				
				if(isOver == true) {
					timer.cancel();
					timeLeft = counter;
				}
				if(isWin == true) {
					timer.cancel();
					timeLeft = counter;
				}
				progressbar.setValue(counter);
				progressbar.setString(""+counter);
				if(counter < 10) {
					
					progressbar.setForeground(Color.red);
				}
				
				if(counter >= 0) {
					System.out.println(counter);
					counter--;
				}
				else {
					timer.cancel();
					gameEnd(-1); //timeout!!!
				}
				
			}
		};
		
		timer.scheduleAtFixedRate(task, 100, 1000);
	}
	
	public int [] buttonsSetBounds() {
		
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
	
	
	void setScore(int score) {
		this.score = score + 1;
	}
	int getScore() {
		return score;
	}
	
	public boolean isCorrect(List<Integer> order, int value) {
		if(!order.isEmpty() && order.get(0) != 0) {
			return false;
		}
		
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
	
	//game over screen 
	public String getResGame() {
		return resGame;
	}
	public String getResScore() {
		return resScore;
	}
	public void setResGame(String gr) {
		this.resGame = gr;
	}
	public void setResScore(String rs) {
		this.resScore = rs;
	}
	
	
	
	//game over screen
	void displayResults(int res) {
		
		//res == 1: game win
		//res == -1 game lose
		int mx=100;
		int m=1;
		int temp = (int)Math.floor(Math.random()*(mx - m) + 1) + m;
		//random_lbl.setText(temp + "");
		if(res == -1) { //lose
			
			setResGame("lose");
			System.out.println(getResGame());
			System.out.println(resGame);
			result_lbl.setText("status: " + getResGame());
			res_time_lbl.setText("Score: " + getResScore());
		}
		else if(res == 1) { //win
			
			setResGame("win");
			System.out.println(getResGame());
			System.out.println(resGame);
			result_lbl.setText("status: " + getResGame());
			res_time_lbl.setText("Score: " + getResScore());
		}
		else{
			System.out.println("Error!!! game result: result must be 1 or zero");
			
		}
		
	}
	
	
	//check if game is lost
	boolean isLose(int val){
		
		if(!isCorrect(correct_order, val)) {
			return true;
		}
		return false;
	}
	void clearText() {
		result_lbl.setText("");
		res_time_lbl.setText("");
		
		
	}
	
	
	
	//game play screen: start game over screen
	void gameEnd(int res){ //result = 1 if win otherwise -1
		//frame.remove(playPanel);
		
		//frame.remove(p);
		//frame.add(p);
		///SwingUtilities.updateComponentTreeUI(frame);
		//displayResults(res);
		//game end panel
		//frame.remove(this);
		//frame.remove(p);
		//frame.add(p);
		
		if(res == 1) {
			System.out.println("win");
		}
		else if(res == -1) {
			System.out.println("lose");
		}
		else{
			System.out.println("fatal error!!!");
		}
	}
	
	boolean isWin(int steps) {
		if(steps==0) {
			return true;

		}
		return false;
	}
	
	/*void gameWin() {
		System.out.println("You won");
		System.out.println("Your score is " + getScore());
		//System.exit(0);
		//gameReset(0);
	}*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//for GameOnScreen
		
		for(int i=0; i<buttons.length;i++) {
			if(e.getSource() == buttons[i]) {
				steps--;
				
				correct_order.add(i);
						
				isOver = isLose(i);
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
	
	
}
