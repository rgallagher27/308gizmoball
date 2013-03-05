package view;

import java.awt.Color;

import javax.swing.*;

import controller.GUIListener;
import controller.IController;

public class CompleteViewContainer extends JFrame {
	
	private IController control;
	private PrototypeView playView;
	private boolean mode;
	private JMenuBar menuBar;
	private JMenu menu;
	private IController guiListener;
	
	
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
		
		/* setup content area */
		getContentPane().add(playView);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	
	private static final long serialVersionUID = 1L;
	

}
