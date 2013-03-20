package view;

import java.awt.*;

import java.io.File;

import javax.swing.*;

// import controller.GUIController;
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
	private JPanel rightPanel;

	private JButton addBall;
	private JButton load;
	private JButton save;
	private JButton play;
	
	
	public CompleteViewContainer(){
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
		item.addActionListener(new GUIListener(3, this));
		menu.add(item);
		item = new JMenuItem("Play Mode");
		item.addActionListener(new GUIListener(4, this));
		menu.add(item);
		item = new JMenuItem("Exit");
		// item.addActionListener(guiListener);
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
		load.addActionListener(new GUIListener(1, this));
		save = new JButton("Save");
		save.addActionListener(new GUIListener(2, this));
		play = new JButton("Play");
		play.addActionListener(new GUIListener(5, this));
		
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

		rightPanel = playPanel;

		getContentPane().add(canvasPanel);
		getContentPane().add(rightPanel, BorderLayout.EAST);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
		playPanel.setVisible(false);
		buildPanel.setVisible(true);
		rightPanel = buildPanel;
		getContentPane().add(rightPanel, BorderLayout.EAST);
		pack();
		revalidate();
		repaint();
	}

	public void switchPlay() {
		buildPanel.setVisible(false);
		playPanel.setVisible(true);
		rightPanel = playPanel;
		getContentPane().add(rightPanel, BorderLayout.EAST);
		pack();
		revalidate();
		repaint();
	}

	private static final long serialVersionUID = 1L;
	


}
