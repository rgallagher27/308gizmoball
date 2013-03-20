package view;

import java.awt.*;

import javax.swing.*;

import controller.GUIController;
import controller.GUIListener;
import controller.IController;

public class CompleteViewContainer extends JFrame {
	
	private IController control;
	private PrototypeView playView;
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

	private JPanel canvasPanel;
	private JPanel playPanel;
	private JPanel buildPanel;

	private JButton addBall;
	private JButton load;
	private JButton save;
	private JButton play;

	private GUIController guiListener;
	
	
	public CompleteViewContainer(){
		guiListener = new GUIListener();
		playView = new PrototypeView();
		mode = true; //true = play, false = build
		buildInitial();
	}
	
	public void addController(IController ic){
		control = ic;
		playView.addController(ic);
	}
	
	public PrototypeView getPlayView(){
		return playView;
	}
	
	private void buildInitial(){
		
		/* menu bar setup */
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		JMenuItem item = new JMenuItem("Build Mode");
		item.addActionListener(guiListener);
		menu.add(item);
		item = new JMenuItem("Play Mode");
		item.addActionListener(guiListener);
		menu.add(item);
		item = new JMenuItem("Exit");
		item.addActionListener(guiListener);
		menu.add(item);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		/* menu bar setup */

		absorber = new JRadioButton("Add Absorber");
		square = new JRadioButton("Add Square");
		triangle = new JRadioButton("Add Triangle");
		leftFlipper = new JRadioButton("Add Left Flipper");
		rightFlipper = new JRadioButton("Add Right Flipper");
		remove = new JRadioButton("Remove Gizmo");
		rotate = new JRadioButton("Rotate Gizmo");
		addTrigger = new JRadioButton("Add Trigger");
		removeTrigger = new JRadioButton("Remove Trigger");

		addBall = new JButton("Add Button");
		load = new JButton("Load");
		save = new JButton("Save");
		play = new JButton("Play");
		
		canvasPanel = new JPanel();
		buildPanel = new JPanel();
		buildPanel.setLayout(new GridLayout(10,1));
		playPanel = new JPanel();
		playPanel.setLayout(new GridLayout(3,1));
		
		/* setup content area */
		canvasPanel.add(playView);
		buildPanel.add(absorber);
		buildPanel.add(square);
		buildPanel.add(triangle);
		buildPanel.add(leftFlipper);
		buildPanel.add(rightFlipper);
		buildPanel.add(remove);
		buildPanel.add(rotate);
		buildPanel.add(addTrigger);
		buildPanel.add(removeTrigger);
		buildPanel.add(addBall);

		playPanel.add(load);
		playPanel.add(save);
		playPanel.add(play);

		getContentPane().add(canvasPanel);
		getContentPane().add(playPanel, BorderLayout.EAST);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	
	private static final long serialVersionUID = 1L;
	

}
