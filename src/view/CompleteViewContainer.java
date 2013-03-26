package view;

import java.awt.*;

import java.io.File;

import javax.swing.*;

import controller.BuildController;
// import controller.GUIController;
import controller.GUIListener;
import controller.GraphicsController;
import controller.IController;

public class CompleteViewContainer extends JFrame {
	
	private IController control;
	private ViewCanvas view;
	private BuildController buildCont;
	private boolean mode;
	private JMenuBar menuBar;
	private JMenu menu;
	
	private JRadioButton square;
	private JRadioButton triangle;
	private JRadioButton leftFlipper;
	private JRadioButton rightFlipper;
	private JRadioButton absorber;
	private JRadioButton remove;
	private JRadioButton rotate;
	private JRadioButton addTrigger;
	private JRadioButton removeTrigger;
	private JRadioButton addBall;
	private JRadioButton circle;
	private JRadioButton addKeyTrigger;
	private JRadioButton removeKeyTrigger;
	
	
	private JPanel buildButtonsPanel;
	private JPanel playButtonsPanel;
	private JPanel buttonsPanel;
	private JPanel content;
	private JPanel ballInfo;
	private JPanel absorberInfo;
	private JPanel keyInfo;
	
	private JButton load;
	private JButton save;
	private JButton play;
	
	private TextField tfVX;
	private TextField tfVY;
	private TextField absHeight;
	private TextField absWidth;
	private JLabel keyPressed;
	
	public CompleteViewContainer(){
		view = new ViewCanvas();
		mode = true; //true = play, false = build
	}
	
	public void addController(IController ic, GraphicsController gc, BuildController bc){
		control = ic;
		buildCont = bc;
		view.addController(ic, gc, bc);
		buildInitial();
		switchPlay();
		
	}
	
	public ViewCanvas getPlayView() {
		return view;
	}
	
	private void playButtons(){
		playButtonsPanel = new JPanel();
		load = new JButton("Load");
		load.setActionCommand("Load");
		load.addActionListener(buildCont);
		save = new JButton("Save");
		save.addActionListener(buildCont);
		save.setActionCommand("Save");
		play = new JButton("Play");
		play.addActionListener(buildCont);
		play.setActionCommand("Play");
		playButtonsPanel.setLayout(new BoxLayout(playButtonsPanel, BoxLayout.Y_AXIS));
		playButtonsPanel.add(load);
		playButtonsPanel.add(save);
		playButtonsPanel.add(play);
		
	}
	
	private void buildButtons(){
		buildButtonsPanel = new JPanel();
		absorber = new JRadioButton("Add Absorber");
		absorber.addActionListener(buildCont);
		absorber.setActionCommand("Absorber");
		
		square = new JRadioButton("Add Square");
		square.addActionListener(buildCont);
		square.setActionCommand("Square");
		
		triangle = new JRadioButton("Add Triangle");
		triangle.addActionListener(buildCont);
		triangle.setActionCommand("Triangle");
		
		circle = new JRadioButton("Add Circle");
		circle.addActionListener(buildCont);
		circle.setActionCommand("Circle");
		
		
		leftFlipper = new JRadioButton("Add Left Flipper");
		leftFlipper.addActionListener(buildCont);
		leftFlipper.setActionCommand("LeftFlipper");
		
		rightFlipper = new JRadioButton("Add Right Flipper");
		rightFlipper.addActionListener(buildCont);
		rightFlipper.setActionCommand("RightFlipper");
		
		remove = new JRadioButton("Remove Gizmo");
		remove.addActionListener(buildCont);
		remove.setActionCommand("Remove");
		
		rotate = new JRadioButton("Rotate Gizmo");
		rotate.addActionListener(buildCont);
		rotate.setActionCommand("Rotate");
		
		addTrigger = new JRadioButton("Add Trigger");
		addTrigger.addActionListener(buildCont);
		addTrigger.setActionCommand("AddTrigger");
		
		removeTrigger = new JRadioButton("Remove Trigger");
		removeTrigger.addActionListener(buildCont);
		removeTrigger.setActionCommand("RemoveTrigger");
		
		addKeyTrigger = new JRadioButton("Add Key Trigger");
		addKeyTrigger.addActionListener(buildCont);
		addKeyTrigger.setActionCommand("AddKeyTrigger");
		
		keyInfo = new JPanel();
		keyInfo.setLayout(new BoxLayout(keyInfo, BoxLayout.Y_AXIS));
		keyInfo.add(new JLabel("Key Pressed:"));
		keyPressed = new JLabel();
		keyInfo.add(keyPressed);
		keyInfo.setVisible(false);
		
		removeKeyTrigger = new JRadioButton("Remove Key Trigger");
		removeKeyTrigger.addActionListener(buildCont);
		removeKeyTrigger.setActionCommand("RemoveKeyTrigger");
		
		addBall = new JRadioButton("Add Ball");
		addBall.addActionListener(buildCont);
		addBall.setActionCommand("AddBall");
		
		ballInfo = new JPanel();
		ballInfo.setLayout(new BoxLayout(ballInfo, BoxLayout.Y_AXIS));
		ballInfo.add(new JLabel("Ball X Velocity:"));
		tfVX = new TextField();
		tfVX.setMaximumSize(new Dimension(100,30));
		ballInfo.add(tfVX);
		ballInfo.add(new JLabel("Ball Y Velocity:"));
		tfVY = new TextField();
		tfVY.setMaximumSize(new Dimension(100,30));
		ballInfo.add(tfVY);
		ballInfo.setVisible(false);
		
		
		absorberInfo = new JPanel();
		absorberInfo.setLayout(new BoxLayout(absorberInfo, BoxLayout.Y_AXIS));
		absorberInfo.add(new JLabel("Absorber Width:"));
		absWidth = new TextField();
		absWidth.setMaximumSize(new Dimension(100,30));
		absorberInfo.add(absWidth);
		absorberInfo.add(new JLabel("Absorber Height:"));
		absHeight = new TextField();
		absHeight.setMaximumSize(new Dimension(100,30));
		absorberInfo.add(absHeight);
		absorberInfo.setVisible(false);
		
		
		buildButtonsPanel.setLayout(new BoxLayout(buildButtonsPanel, BoxLayout.Y_AXIS));
		buildButtonsPanel.add(absorber);
		buildButtonsPanel.add(absorberInfo);
		buildButtonsPanel.add(square);
		buildButtonsPanel.add(triangle);
		buildButtonsPanel.add(circle);
		buildButtonsPanel.add(leftFlipper);
		buildButtonsPanel.add(rightFlipper);
		buildButtonsPanel.add(remove);
		buildButtonsPanel.add(rotate);
		buildButtonsPanel.add(addTrigger);
		buildButtonsPanel.add(removeTrigger);
		buildButtonsPanel.add(addKeyTrigger);
		buildButtonsPanel.add(keyInfo);
		buildButtonsPanel.add(removeKeyTrigger);
		buildButtonsPanel.add(addBall);
		buildButtonsPanel.add(ballInfo);
		
		
	}
	private void buildInitial(){
		
		/* menu bar setup */
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		JMenuItem item = new JMenuItem("Build Mode");
		item.addActionListener(buildCont);
		item.setActionCommand("Build");
		menu.add(item);
		item = new JMenuItem("Play Mode");
		item.addActionListener(buildCont);
		item.setActionCommand("Play");
		menu.add(item);
		item = new JMenuItem("Exit");
		item.addActionListener(buildCont);
		item.setActionCommand("Exit");
		menu.add(item);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		/* menu bar setup */

		playButtons();
		buildButtons();
		buttonsPanel = new JPanel();
		content = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.add(playButtonsPanel);
		buttonsPanel.add(buildButtonsPanel);
		setContentPane(content);
		content.setLayout(new BorderLayout());
		
		/* setup content area */


		getContentPane().add(view, BorderLayout.WEST);
		getContentPane().add(buttonsPanel, BorderLayout.EAST);
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public File askForMapFile() {
		JFileChooser chooser = new JFileChooser();
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			return chooser.getSelectedFile();
		} else{
			return null;
		}
	}
	
	public void information(String message){
		JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void error(String message){
		JOptionPane.showMessageDialog(this, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void switchBuild() {
		buildButtonsPanel.setVisible(true);
		playButtonsPanel.setVisible(false);
		mode = false;
		view.setMode(false);
		pack();
		repaint();
	}

	public void switchPlay() {
		buildButtonsPanel.setVisible(false);
		playButtonsPanel.setVisible(true);
		mode = true;
		view.setMode(true);
		pack();
		repaint();
	}
	
	public double getBallVX(){
		double in;
		try{
			in = Double.parseDouble(tfVX.getText());
		}catch(NumberFormatException nfe){
			error("The ball X velocity must be between -200 and 200L");
			in = Double.MIN_VALUE;
		}
		if(in >= -200 && in <= 200) return in;
	
		
		return Double.MIN_VALUE;
	}
	
	public double getBallVY(){
		double in;
		try{
			in = Double.parseDouble(tfVY.getText());
		}catch(NumberFormatException nfe){
			error("The ball Y velocity must be between -200 and 200L");
			in = Double.MIN_VALUE;
		}
		if(in >= -200 && in <= 200) return in;
	
		return Double.MIN_VALUE;
	}
	
	public int getAbsorberWidth(){
		int in;
		try{
			in = Integer.parseInt(absWidth.getText());
		}catch(NumberFormatException nfe){
			error("The absorber width must be atleast 1 wide");
			in = Integer.MIN_VALUE;
		}
		if(in > 0) return in;
		
		return Integer.MIN_VALUE;
	}
	
	public int getAbsorberHeight(){
		int in;
		try{
			in = Integer.parseInt(absHeight.getText());
		}catch(NumberFormatException nfe){
			error("The absorber height must be atleast 1 high");
			in = Integer.MIN_VALUE;
		}
		if(in > 0) return in;
		
		return Integer.MIN_VALUE;
	}
	
	public void showBallInfo(boolean b){
		if(b == false){
			tfVX.setText("");
			tfVY.setText("");
		}
		ballInfo.setVisible(b);
		pack();
	}
	
	public void showKeyInfo(boolean b){
		if(b == false){
			keyPressed.setText("");
		}
		keyInfo.setVisible(b);
		pack();
	}
	
	public void showAbsInfo(boolean b){
		if(b == false){
			absWidth.setText("");
			absHeight.setText("");
		}
		absorberInfo.setVisible(b);
		pack();
	}
	
	public void setKey(int key){
		keyPressed.setText(""+key);
	}
	
	
	public void unselectAll(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		circle.setSelected(false);
		addBall.setSelected(false);
		removeKeyTrigger.setSelected(false);
		addKeyTrigger.setSelected(false);
		showBallInfo(false);
		showAbsInfo(false);
		showKeyInfo(false);
	}
	
	public void focusView(){
		view.requestFocus();
	}
	
	public boolean getMode(){
		return mode;
	}

	private static final long serialVersionUID = 1L;
	


}
