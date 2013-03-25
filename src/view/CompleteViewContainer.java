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

	private JPanel buildButtonsPanel;
	private JPanel playButtonsPanel;
	private JPanel buttonsPanel;
	private JPanel content;
	
	
	private JButton addTrigger;
	private JButton removeTrigger;
	private JButton addBall;
	private JButton load;
	private JButton save;
	private JButton play;
	
	
	public CompleteViewContainer(){
		view = new ViewCanvas();
		mode = true; //true = play, false = build
		buildInitial();
	}
	
	public void addController(IController ic, GraphicsController gc, BuildController bc){
		control = ic;
		buildCont = bc;
		view.addController(ic, gc, bc);
		switchPlay();
	}
	
	public ViewCanvas getPlayView() {
		return view;
	}
	
	private void playButtons(){
		playButtonsPanel = new JPanel();
		load = new JButton("Load");
		load.addActionListener(buildCont);
		load.setActionCommand("Load");
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
		
		addTrigger = new JButton("Add Trigger");
		addTrigger.addActionListener(buildCont);
		addTrigger.setActionCommand("AddTrigger");
		
		removeTrigger = new JButton("Remove Trigger");
		removeTrigger.addActionListener(buildCont);
		removeTrigger.setActionCommand("RemoveTrigger");
		
		addBall = new JButton("Add Ball");
		addBall.addActionListener(buildCont);
		addBall.setActionCommand("AddBall");
		
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

	private static final long serialVersionUID = 1L;
	


}
