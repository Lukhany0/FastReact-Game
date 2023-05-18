
import java.util.Timer;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.*;

public class Game implements ActionListener{

	boolean isOver; //game status
	
	JFrame frame = new JFrame();
	JPanel Playpanel = new JPanel(); //game play section
	
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
		
		/*for(Map.Entry<Integer, Integer>  entry: bounds.entrySet()){ //
			int key = entry.getKey();
			int value = entry.getValue();
		}*/
		
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
			Playpanel.add(buttons[j]);
		}
		
		detPanel.add(label_score); //add score label to details panel
		Playpanel.add(progressbar);	//add progress bar to game panel
		Playpanel.add(detPanel);	//add details panel to game panel
		Playpanel.setSize(700,700);
		Playpanel.setLayout(null);
		Playpanel.setVisible(true);
		
		frame.add(Playpanel);
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
		//remove(panel);
		
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
	}

}
