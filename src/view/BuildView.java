package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.GizmoListener;

public class BuildView extends JFrame implements IBuildView{

	private static final long serialVersionUID = 1L;
	
	private BuildCanvas canvas;
	private JPanel rightPanel;
	private List<AbstractButton> buttons;
	private JRadioButton square;
	private JRadioButton triangle;
	private JRadioButton leftFlipper;
	private JRadioButton rightFlipper;
	private JRadioButton absorber;
	private JRadioButton remove;
	private JRadioButton rotate;
	private JRadioButton addTrigger;
	private JRadioButton removeTrigger;
	private JButton addBall;
	private JButton setGravity;
	private JPanel bottomPanel;
	private JButton load;
	private JButton save;
	private JButton play;
	
	public BuildView(){
		setup();
	}
	
	private void setup(){
		//Build the components.
		canvas = new BuildCanvas();
		canvas.addMouseListener(new GizmoListener(GizmoListener.MOUSELISTENER, canvas));
		rightPanel = new JPanel();
		buttons = new LinkedList<AbstractButton>();
		square = new JRadioButton("Add Square");
		square.addActionListener(new GizmoListener(GizmoListener.BUILD_SQUARE, this));
		buttons.add(square);
		triangle = new JRadioButton("Add Triangle");
		triangle.addActionListener(new GizmoListener(GizmoListener.BUILD_TRIANGLE, this));
		buttons.add(triangle);
		leftFlipper = new JRadioButton("Add L Flipper");
		leftFlipper.addActionListener(new GizmoListener(GizmoListener.BUILD_LEFT_FLIPPER, this));
		buttons.add(leftFlipper);
		rightFlipper = new JRadioButton("Add R Flipper");
		rightFlipper.addActionListener(new GizmoListener(GizmoListener.BUILD_RIGHT_FLIPPER, this));
		buttons.add(rightFlipper);
		absorber = new JRadioButton("Add Absorber");
		absorber.addActionListener(new GizmoListener(GizmoListener.BUILD_ABSORBER, this));
		buttons.add(absorber);
		remove = new JRadioButton("Remove Gizmo");
		remove.addActionListener(new GizmoListener(GizmoListener.BUILD_REMOVE, this));
		buttons.add(remove);
		rotate = new JRadioButton("Rotate Gizmo");
		rotate.addActionListener(new GizmoListener(GizmoListener.BUILD_ROTATE, this));
		buttons.add(rotate);
		addTrigger = new JRadioButton("Add Trigger");
		addTrigger.addActionListener(new GizmoListener(GizmoListener.BUILD_ADD_TRIGGER, this));
		buttons.add(addTrigger);
		removeTrigger = new JRadioButton("Remove Trigger");
		removeTrigger.addActionListener(new GizmoListener(GizmoListener.BUILD_REMOVE_TRIGGER, this));
		buttons.add(removeTrigger);
		addBall = new JButton("Add Ball");
		addBall.addActionListener(new GizmoListener(GizmoListener.ADD_BALL, this));
		buttons.add(addBall);
		setGravity = new JButton("Set Gravity");
		setGravity.addActionListener(new GizmoListener(GizmoListener.SET_GRAVITY, this));
		buttons.add(setGravity);
		bottomPanel = new JPanel();
		load = new JButton("Load Map");
		load.addActionListener(new GizmoListener(GizmoListener.LOAD, this));
		save = new JButton("Save Map");
		save.addActionListener(new GizmoListener(GizmoListener.SAVE, this));
		play = new JButton("Play Mode");
		play.addActionListener(new GizmoListener(GizmoListener.PLAY_MODE, this));
		
		//Build the window.
		rightPanel.setLayout(new GridLayout(11,1));
		for(AbstractButton b : buttons){
			rightPanel.add(b);
		}
		this.add(rightPanel, BorderLayout.EAST);
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(load);
		bottomPanel.add(save);
		bottomPanel.add(play);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(canvas);
		this.addSquares();
		this.setTitle("Gizmoball Build Mode");
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.repaint();
	}
	
	private void deselectAllButtons(){
		for(AbstractButton b : buttons){
			b.setSelected(false);
		}
	}
	
	@Override
	public void addSquares(){
		deselectAllButtons();
		square.setSelected(true);
	}
	
	@Override
	public void addTriangles(){
		deselectAllButtons();
		triangle.setSelected(true);
	}
	
	@Override
	public void addLeftFlipper(){
		deselectAllButtons();
		leftFlipper.setSelected(true);
	}
	
	@Override
	public void addRightFlipper(){
		deselectAllButtons();
		rightFlipper.setSelected(true);
	}
	
	@Override
	public void addAbsorber(){
		deselectAllButtons();
		absorber.setSelected(true);
	}
	
	@Override
	public void remove(){
		deselectAllButtons();
		remove.setSelected(true);
	}
	
	@Override
	public void rotate(){
		deselectAllButtons();
		rotate.setSelected(true);
	}
	
	@Override
	public void addTrigger(){
		deselectAllButtons();
		addTrigger.setSelected(true);
	}
	
	@Override
	public void removeTrigger(){
		deselectAllButtons();
		removeTrigger.setSelected(true);
	}
	
	@Override
	public File askForMapFile() {
		JFileChooser chooser = new JFileChooser();
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			return chooser.getSelectedFile();
		} else{
			return null;
		}
	}
	
	@Override
	public File askForSaveFile() {
		JFileChooser chooser = new JFileChooser();
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			return chooser.getSelectedFile();
		} else{
			return null;
		}
	}
	
	@Override
	public void information(String message){
		JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void error(String message){
		JOptionPane.showMessageDialog(this, message, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public String ask(String question){
		return JOptionPane.showInputDialog(this, question);
	}
	
	@Override
	public int confirmYesNo(String question){
		return JOptionPane.showConfirmDialog(this, question, "Please confirm!", JOptionPane.YES_NO_OPTION);
	}
}