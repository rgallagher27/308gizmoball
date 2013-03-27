package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.BuildController;
import controller.GraphicsController;
import controller.IController;

public class CompleteViewContainer extends JFrame {
	
	private boolean mode;
	
	private IController control;
	private ViewCanvas view;
	private BuildController buildCont;
	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenu buildGizmoMenu;
	private JMenu buildBallMenu;
	private JMenu moveMenu;
	private JMenu triggerMenu;
	private JMenu menu;
	
	private JFileChooser chooser = new JFileChooser();
	private LinkedList<JRadioButton> buttons;
	
	private JPanel buildButtonsPanel;
	private JPanel playButtonsPanel;
	private JPanel buttonsPanel;
	private JPanel ballInfo;
	private JPanel absorberInfo;
	private JPanel keyInfo;
	
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
		buttons = new LinkedList<JRadioButton>();
		buildInitial();
		switchPlay();
		
	}
	
	public ViewCanvas getPlayView() {
		return view;
	}
	
	public void pause(){
		control.pause();
	}
	
	private void playButtons(){
		playButtonsPanel = new JPanel();
		play = new JButton("Pause");
		play.addActionListener(buildCont);
		play.setActionCommand("Pause");
		playButtonsPanel.setLayout(new BoxLayout(playButtonsPanel, BoxLayout.X_AXIS));
		playButtonsPanel.add(play);
	}
	
	private void buildButtons(){
		buildButtonsPanel = new JPanel();
		
		keyInfo = new JPanel();
		keyInfo.setLayout(new BoxLayout(keyInfo, BoxLayout.X_AXIS));
		keyInfo.add(new JLabel("Key Pressed:"));
		keyPressed = new JLabel();
		keyInfo.add(keyPressed);
		keyInfo.setVisible(false);
		
		ballInfo = new JPanel();
		ballInfo.setLayout(new BoxLayout(ballInfo, BoxLayout.X_AXIS));
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
		absorberInfo.setLayout(new BoxLayout(absorberInfo, BoxLayout.X_AXIS));
		absorberInfo.add(new JLabel("Absorber Width:"));
		absWidth = new TextField();
		absWidth.setMaximumSize(new Dimension(100,30));
		absorberInfo.add(absWidth);
		absorberInfo.add(new JLabel("Absorber Height:"));
		absHeight = new TextField();
		absHeight.setMaximumSize(new Dimension(100,30));
		absorberInfo.add(absHeight);
		absorberInfo.setVisible(false);
		
		buildButtonsPanel.setLayout(new BoxLayout(buildButtonsPanel, BoxLayout.X_AXIS));
		buildButtonsPanel.add(absorberInfo);
		buildButtonsPanel.add(keyInfo);
		buildButtonsPanel.add(ballInfo);
	}
	
	private void buildInitial() 
	{
		mapMenu = new JMenu("Map Control");
		
		JMenuItem loadMenu = new JMenuItem("Load Game Map");
				  loadMenu.addActionListener(buildCont);
				  loadMenu.setActionCommand("Load");
					
		JMenuItem saveMenu = new JMenuItem("Save Game Map");
				  saveMenu.addActionListener(buildCont);
				  saveMenu.setActionCommand("Save");
				  
		mapMenu.add(loadMenu);
		mapMenu.add(saveMenu);
		
		buildGizmoMenu = new JMenu("Build Gizmos");
			  
		JMenuItem AbMenu = new JMenuItem("Add Absorber");
				  AbMenu.addActionListener(buildCont);
				  AbMenu.setActionCommand("Absorber");
				  
		JMenuItem SqMenu = new JMenuItem("Add Square");
				  SqMenu.addActionListener(buildCont);
				  SqMenu.setActionCommand("Square");
				  
		JMenuItem TrMenu = new JMenuItem("Add Triangle");
				  TrMenu.addActionListener(buildCont);
		          TrMenu.setActionCommand("Triangle");
				  
		JMenuItem CiMenu = new JMenuItem("Add Circle");
				  CiMenu.addActionListener(buildCont);
				  CiMenu.setActionCommand("Circle");
				  
		JMenuItem LfMenu = new JMenuItem("Add Left Flipper");
				  LfMenu.addActionListener(buildCont);
				  LfMenu.setActionCommand("LeftFlipper");
				  
		JMenuItem RfMenu = new JMenuItem("Add Right Flipper");
				  RfMenu.addActionListener(buildCont);
				  RfMenu.setActionCommand("LeftFlipper");
				  
		JMenuItem PoMenu = new JMenuItem("Add Portal");
				  PoMenu.addActionListener(buildCont);
				  PoMenu.setActionCommand("Portal");
				  
		buildGizmoMenu.add(AbMenu);
		buildGizmoMenu.add(SqMenu);
		buildGizmoMenu.add(TrMenu);
		buildGizmoMenu.add(CiMenu);
		buildGizmoMenu.add(LfMenu);
		buildGizmoMenu.add(RfMenu);
		buildGizmoMenu.add(PoMenu);
		
		buildBallMenu = new JMenu("Build Balls");

		JMenuItem BaMenu = new JMenuItem("Add Ball");
				  BaMenu.addActionListener(buildCont);
				  BaMenu.setActionCommand("AddBall");

		JMenuItem BrMenu = new JMenuItem("Remove Ball");
				  BrMenu.addActionListener(buildCont);
				  BrMenu.setActionCommand("DeleteBall");
				  
		buildBallMenu.add(BaMenu);
		buildBallMenu.add(BrMenu);
		
		moveMenu = new JMenu("Move");
		
		JMenuItem MoMenu = new JMenuItem("Move Gizmo");
				  MoMenu.addActionListener(buildCont);
				  MoMenu.setActionCommand("Move");
		
		JMenuItem RmMenu = new JMenuItem("Remove Gizmo");
				  RmMenu.addActionListener(buildCont);
				  RmMenu.setActionCommand("Remove");
		
		JMenuItem RoMenu = new JMenuItem("Rotate Gizmo");
				  RoMenu.addActionListener(buildCont);
				  RoMenu.setActionCommand("Rotate");

		JMenuItem BmMenu = new JMenuItem("Move Ball");
				  BmMenu.addActionListener(buildCont);
				  BmMenu.setActionCommand("MoveBall");
				  
		moveMenu.add(MoMenu);
		moveMenu.add(BmMenu);
		moveMenu.add(RmMenu);
		moveMenu.add(RoMenu);
		
		triggerMenu = new JMenu("Triggers");
		
		JMenuItem AGTMenu = new JMenuItem("Add Gizmo Trigger");
				  AGTMenu.addActionListener(buildCont);
				  AGTMenu.setActionCommand("AddTrigger");
					
		JMenuItem AKTMenu = new JMenuItem("Add Key Trigger");
				  AKTMenu.addActionListener(buildCont);
				  AKTMenu.setActionCommand("AddKeyTrigger");
					
		JMenuItem RGTMenu = new JMenuItem("Remove Gizmo Trigger");
				  RGTMenu.addActionListener(buildCont);
				  RGTMenu.setActionCommand("RemoveTrigger");
					
		JMenuItem RKTMenu = new JMenuItem("Remove Key Trigger");
				  RKTMenu.addActionListener(buildCont);
				  RKTMenu.setActionCommand("RemoveKeyTrigger");
				  
				  
		triggerMenu.add(AGTMenu);
		triggerMenu.add(AKTMenu);
		triggerMenu.add(RGTMenu);
		triggerMenu.add(RKTMenu);
		
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
		
		/* menu bar setup */
		menuBar = new JMenuBar();
		
		menuBar.add(menu);
		menuBar.add(mapMenu);
		menuBar.add(buildGizmoMenu);
		menuBar.add(buildBallMenu);
		menuBar.add(moveMenu);
		menuBar.add(triggerMenu);
		
		setJMenuBar(menuBar);
		
		/* menu bar setup */
		playButtons();
		buildButtons();
		
		buttonsPanel 	= new JPanel();
		
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.add(playButtonsPanel);
		buttonsPanel.add(buildButtonsPanel);
		
		/* setup content area */

		getContentPane().add(view, BorderLayout.WEST);
		getContentPane().add(buttonsPanel, BorderLayout.NORTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		pack();
	}
	
	public File askForMapFile() {
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			return chooser.getSelectedFile();
		} else{
			return null;
		}
	}
	public File askForMapFileSave() {
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
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
		mapMenu.setVisible(true);
		buildGizmoMenu.setVisible(true);
		buildBallMenu.setVisible(true);
		moveMenu.setVisible(true);
		triggerMenu.setVisible(true);
		pack();
		repaint();
	}

	public void switchPlay() {
		buildButtonsPanel.setVisible(false);
		playButtonsPanel.setVisible(true);
		mode = true;
		view.setMode(true);
		mapMenu.setVisible(false);
		buildGizmoMenu.setVisible(false);
		buildBallMenu.setVisible(false);
		moveMenu.setVisible(false);
		triggerMenu.setVisible(false);
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
			error("The absorber width must be atleast 1L wide");
			in = Integer.MIN_VALUE;
		}
		if(in > 0) return in;
		error("The absorber width must be atleast 1L wide");
		return Integer.MIN_VALUE;
	}
	
	public int getAbsorberHeight(){
		int in;
		try{
			in = Integer.parseInt(absHeight.getText());
		}catch(NumberFormatException nfe){
			error("The absorber height must be atleast 1L high");
			in = Integer.MIN_VALUE;
		}
		if(in > 0) return in;
		error("The absorber height must be atleast 1L high");
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
		for(JRadioButton j : buttons){
			j.setSelected(false);
		}
		showBallInfo(false);
		showAbsInfo(false);
		showKeyInfo(false);
	}
	
	public void select(String t){
		for(JRadioButton j : buttons){
			if(j.getActionCommand().equals(t))
				j.setSelected(true);
		}
	}
	
	public void focusView(){
		view.requestFocus();
	}
	
	public boolean getMode(){
		return mode;
	}

	private static final long serialVersionUID = 1L;
}
