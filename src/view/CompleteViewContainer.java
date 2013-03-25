package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.io.File;

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
import javax.swing.LayoutStyle;

import controller.BuildController;
import controller.GraphicsController;
import controller.IController;
// import controller.GUIController;

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
	
	private JPanel buildButtonsPanel;
	private JPanel playButtonsPanel;
	private JPanel buttonsPanel;
	private JPanel content;
	private JPanel ballInfo;
	
	private JButton load;
	private JButton save;
	private JButton play;
	
	private TextField tfVX;
	private TextField tfVY;
	
	
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
		
		buildButtonsPanel.setLayout(new BoxLayout(buildButtonsPanel, BoxLayout.Y_AXIS));
		buildButtonsPanel.add(absorber);
		buildButtonsPanel.add(square);
		buildButtonsPanel.add(triangle);
		buildButtonsPanel.add(leftFlipper);
		buildButtonsPanel.add(rightFlipper);
		buildButtonsPanel.add(remove);
		buildButtonsPanel.add(rotate);
		buildButtonsPanel.add(addTrigger);
		buildButtonsPanel.add(removeTrigger);
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
		revalidate();
		repaint();
	}

	public void switchPlay() {
		buildButtonsPanel.setVisible(false);
		playButtonsPanel.setVisible(true);
		mode = false;
		view.setMode(true);
		pack();
		revalidate();
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
	
	public void showBallInfo(boolean b){
		if(b == false){
			tfVX.setText("");
			tfVY.setText("");
		}
		ballInfo.setVisible(b);
		pack();
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
		addBall.setSelected(false);
		showBallInfo(false);
	}

	private static final long serialVersionUID = 1L;
	


}
