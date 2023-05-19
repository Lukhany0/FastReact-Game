
import java.util.Timer;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.*;

public class Game implements ActionListener{
	
	
	/*int random;
	JPanel p;
	String resScore;
	String resGame;*/
	
	JLabel random_lbl;
	JLabel result_lbl;
	JLabel res_time_lbl;
	JLabel ask_replay_lbl;
	
	
	//game one
	JPanel playPanel;
	JFrame frame;
	boolean isOver;
	boolean isWin;
	//JPanel panel = new JPanel();
	JPanel detPanel = new JPanel();
	JProgressBar progressbar = new JProgressBar(JProgressBar.VERTICAL, 0, 49);
	HashMap<Integer, Integer> bounds = new HashMap<>(); //coordinates of (x,y) bounds for buttons
	//width and height of buttons
	int width = 50;
	int height = 50;
	int timeLeft;
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
	int len = buttons.length;	//total number of buttons
	private int steps = len; // winner if completed steps
	private int score = 0;
	
	
	Game(){
		frame = new JFrame("game");
		playPanel = new JPanel();
		playPanel.setSize(700,700);
		playPanel.setLayout(null);
		//commented are game over screen components
		/*random_lbl = new JLabel();
		random_lbl.setBounds(500,500,300,100);
		random_lbl.setFont(new Font("MV boli", Font.BOLD, 40));
		
		
		int mx=100;
		int m=1;
		int temp = (int)Math.floor(Math.random()*(mx - m) + 1) + m;
		random_lbl.setText(temp + "");
		
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
		
		this.p = p;
		this.removeAll();
		this.frame = frame;
		System.out.println("Game / panel2");
		*/
		
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
		
		
		
		
		/*p.add(random_lbl);
		p.add(random_lbl);
		p.add(result_lbl);
		p.add(res_time_lbl);
		p.add(ask_replay_lbl);*/
		detPanel.add(label_score);
		playPanel.add(progressbar);
		playPanel.add(detPanel);
		playPanel.setSize(700,700);
		playPanel.setLayout(null);
		playPanel.setVisible(true);
		//frame.add(this);
		
		
		
		frame.add(playPanel);
		
		
		frame.setSize(700,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		fill();
		
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
	
	/*
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
	*/
	
	
	/*
	void displayResults(int res) {
		
		//res == 1: game win
		//res == -1 game lose
		int mx=100;
		int m=1;
		int temp = (int)Math.floor(Math.random()*(mx - m) + 1) + m;
		random_lbl.setText(temp + "");
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
	*/
	
	
	boolean isGameOver(int val){
		
		if(!isCorrect(correct_order, val)) {
			return true;
		}
		return false;
	}
	void clearText() {
		result_lbl.setText("");
		res_time_lbl.setText("");
		
		
	}
	
	
	
	//game over
	void gameEnd(int res){ //result = 1 if win otherwise -1
		frame.remove(playPanel);
		
		//frame.remove(p);
		//frame.add(p);
		///SwingUtilities.updateComponentTreeUI(frame);
		//displayResults(res);
		//game end panel
		//frame.remove(this);
		//frame.remove(p);
		//frame.add(p);
		
	}
	
	boolean isWin(int steps) {
		if(steps==0) {
			return true;

		}
		return false;
	}
	
	void gameWin() {
		System.out.println("You won");
		System.out.println("Your score is " + getScore());
		//System.exit(0);
		//gameReset(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	//for GameOnScreen
		
		for(int i=0; i<buttons.length;i++) {
			if(e.getSource() == buttons[i]) {
				steps--;
				System.out.println("steps left: " +steps);
				
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
	
	/*boolean isOver; //game status
	
	JFrame frame = new JFrame();
	JPanel playPanel = new JPanel(); //game play section
	
	JPanel detPanel = new JPanel(); //score section
	JProgressBar progressbar = new JProgressBar(JProgressBar.VERTICAL, 0, 49); //game timer
	HashMap<Integer, Integer> bounds = new HashMap<>(); //coordinates(x,y) for buttons
	
	//width and height of buttons
	int width = 50;
	int height = 50;
	
	//buttons: 20 buttons
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
	
	List<Integer> correct_order = new ArrayList<>(); //clicked buttons list
	int len = buttons.length;	//total number of buttons
	private int steps = len; //steps left variables => clicks left = total number of buttons to be clicked
	private int score = 0; //score variable
	
	
	//Constructor
	Game(){
		
		System.out.println("Game starting");
		
		progressbar.setBounds(550,0,150,700);
		progressbar.setStringPainted(true);
		progressbar.setForeground(Color.green);
		progressbar.setBorderPainted(false);
		
		//store button coordinates in HashMap
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
		
		detPanel.setBounds(0,550, 550,150); //detPanel location on the frame
		detPanel.setBackground(Color.cyan);
		
		//score label
		label_score = new JLabel();
		label_score.setBounds(10,600,50,50);
		label_score.setFont(new Font("MV boli", Font.BOLD, 25));
		label_score.setText("Score: " + getScore()); //store score from score counter function: getScore()
		
		
		int i=0; //first index in buttons arrays
		int [] indexing = buttonsSetBounds(); //stores buttons bounds to array indexing
		while(i<bounds.size()) { //iterate through coordinates data structure: HashMap to set buttons' bounds (x,y,width,height)
			for(Map.Entry<Integer, Integer>  entry: bounds.entrySet()){
				int key = entry.getKey();	//get x from bounds
				int value = entry.getValue(); //get y from bounds
		    	buttons[indexing[i]].setBounds(key, value, width, height); //set button bounds from buttons array with coordinates from bounds array; width and height is fixed for all buttons
		    	i+=1; //move to next index in buttons array
			}
		}
		//set buttons attributes
		for(int j=0; j<len; j++) {
			buttons[j].setFocusable(false);
			buttons[j].addActionListener(this);
			playPanel.add(buttons[j]);
		}
		
		detPanel.add(label_score); //add score label to details panel
		playPanel.add(progressbar);	//add progress bar to game panel
		playPanel.add(detPanel);	//add details panel to game panel
		playPanel.setSize(700,700);
		playPanel.setLayout(null);
		playPanel.setVisible(true);
		
		frame.add(playPanel);
		frame.setTitle("Fast React Game");
		frame.setSize(700,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		fill(); //start timer function
		
	}
	//timer function
	public void fill() {
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int counter = 49; //start from
			@Override
			public void run() {
				//if game is over stop timer
				if(isOver == true) {
					timer.cancel();
					gameOver(); //game over check function
				}
				progressbar.setValue(counter); //set progress bar value
				progressbar.setString(""+counter); //display value
				
				if(counter < 10) { //change color
					progressbar.setForeground(Color.red);
				}
				//count down if there is still time
				if(counter >= 0) {
					System.out.println(counter);
					counter--;
				}
				else { //no time left; end game; stop timer
					timer.cancel();
					gameOver();
				}
				
			}
		};
		timer.scheduleAtFixedRate(task, 100, 1000); //delay 100ms, count in seconds=1000ms
	}
	//set buttons bounds
	int [] buttonsSetBounds() {
		
		//create orders array of 20 items equal to buttons total
		int [] v = new int[20]; //buttons total
		for(int i=0; i<v.length; i++) {
			v[i] = i;
		}
		//randomize the order of arrays items: create randon index valid in array v => swap; original item with random generated index
		for (int i = 0; i < v.length; i++) {
			int index = (int) (Math.random() * v.length);
			int temp = v[i];
			v[i] = v[index];
			v[index] = temp;
		 }
		 return v;
	}
	void displayArray(int arr []) { //visualize array
		System.out.print("[");
		for(int s: arr)
			System.out.print(s + ", ");
		System.out.print("]");
	}
	//set score
	void setScore(int score) {
		this.score = score + 1; //add 1 to current score
	}
	//get score
	int getScore() {
		return score; //return score
	}
	//check if button clicked is the correct buttons: buttons are clicked in ascending order
	public boolean isCorrect(List<Integer> order, int value) {
		//for order list size = 1
		if(!order.isEmpty() && order.get(0) != 0) { //if first button in the the list is not 0 then oder is not correct
			return false;
		}
		
		//for order list size greater than 1: check if list items are arranged in range from 0 with a step of 1
		if(order.size() > 1) { 
			for(int i=0; i< order.size(); i++) { 
				for(int j=1; j<order.size(); j++) {
					if(order.get(j) != order.get(j-1) + 1) { //if difference is not 1 between items them order is is no correct
						return false;
					}
				}
			}
		}
		return true; //return list is in correct order, thus button clicked is correct
	}	
	//check if game is over
	boolean isGameOver(int val){
		
		if(!isCorrect(correct_order, val)) {
			return true; //game over
		}

		return false; //game is on
	}
	boolean isGameOver() { //game over status status
		
		return false;
	}
	//when game is over
	void gameOver(){
		System.out.println("GameOver");
		System.out.println("Your score: " + getScore());
		
		//panel.setVisible(false);
		//GameOverScreen();
		//add(replayPanel);
		
		//System.exit(0);
	}
	//check if game is won
	boolean isWin(int steps) {
		if(steps==0) { //if no steps left; game win
			return true;
		}
		return false; //else game is not yet won
	}
	
	//after game is won
	void gameWin() {
		System.out.println("You won");
		System.out.println("Your score is " + getScore());
		System.exit(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//game play on
		for(int i=0; i<buttons.length;i++) {
			if(e.getSource() == buttons[i]) { 
				steps--;	//decrease steps by 1
				System.out.println("steps: " +steps);
				correct_order.add(i); //add button to correct order list
						
				isOver = isGameOver(i);	//check is button clicked is correct
				if(isOver) { //end game
					
					gameOver();
				}
				setScore(score); //increase score by 1
				label_score.setText("Score: " + getScore()); //update score to the screen
			}
		}
		if(isWin(steps)) { //check steps; if no steps left: game won
			gameWin();
		}
	}*/

}
