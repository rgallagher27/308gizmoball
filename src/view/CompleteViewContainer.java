package view;

import java.awt.*;

import java.io.File;

import javax.swing.*;

// import controller.GUIController;
import controller.GUIListener;
import controller.GraphicsController;
import controller.IController;

public class CompleteViewContainer extends JFrame {
	
	private IController control;
	private ViewCanvas view;
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
	
	public void addController(IController ic, GraphicsController gc){
		control = ic;
		view.addController(ic, gc);
	}
	
	public ViewCanvas getPlayView() {
		return view;
	}
	
	private void playButtons(){
		playButtonsPanel = new JPanel();
		load = new JButton("Load");
		load.addActionListener(new GUIListener(1, this));
		save = new JButton("Save");
		save.addActionListener(new GUIListener(2, this));
		play = new JButton("Play");
		play.addActionListener(new GUIListener(5, this));
		playButtonsPanel.setLayout(new BoxLayout(playButtonsPanel, BoxLayout.Y_AXIS));
		playButtonsPanel.add(load);
		playButtonsPanel.add(save);
		playButtonsPanel.add(play);
		
	}
	
	private void buildButtons(){
		buildButtonsPanel = new JPanel();
		absorber = new JRadioButton("Add Absorber");
		square = new JRadioButton("Add Square");
		triangle = new JRadioButton("Add Triangle");
		leftFlipper = new JRadioButton("Add Left Flipper");
		rightFlipper = new JRadioButton("Add Right Flipper");
		remove = new JRadioButton("Remove Gizmo");
		rotate = new JRadioButton("Rotate Gizmo");
		addTrigger = new JButton("Add Trigger");
		removeTrigger = new JButton("Remove Trigger");
		addBall = new JButton("Add Ball");
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
		switchPlay();
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
		pack();
		revalidate();
		repaint();
	}

	public void switchPlay() {
		buildButtonsPanel.setVisible(false);
		playButtonsPanel.setVisible(true);
		mode = false;
		pack();
		revalidate();
		repaint();
	}

	private static final long serialVersionUID = 1L;
	


}
