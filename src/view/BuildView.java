package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.GizmoListener;

public class BuildView extends JFrame implements IFrame, IBuildView{

	private static final long serialVersionUID = 1L;
	
	private BuildCanvas canvas;
	private JPanel rightPanel;
	private JRadioButton square;
	private JRadioButton triangle;
	private JRadioButton leftFlipper;
	private JRadioButton rightFlipper;
	private JRadioButton absorber;
	private JRadioButton remove;
	private JRadioButton rotate;
	private JRadioButton addTrigger;
	private JRadioButton removeTrigger;
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
		square = new JRadioButton("Add Square");
		square.addActionListener(new GizmoListener(GizmoListener.BUILD_SQUARE, this));
		triangle = new JRadioButton("Add Triangle");
		triangle.addActionListener(new GizmoListener(GizmoListener.BUILD_TRIANGLE, this));
		leftFlipper = new JRadioButton("Add L Flipper");
		leftFlipper.addActionListener(new GizmoListener(GizmoListener.BUILD_LEFT_FLIPPER, this));
		leftFlipper.setEnabled(false); //TODO Implement left flipper.
		rightFlipper = new JRadioButton("Add R Flipper");
		rightFlipper.addActionListener(new GizmoListener(GizmoListener.BUILD_RIGHT_FLIPPER, this));
		rightFlipper.setEnabled(false); //TODO Implement right flipper.
		absorber = new JRadioButton("Add Absorber");
		absorber.addActionListener(new GizmoListener(GizmoListener.BUILD_ABSORBER, this));
		absorber.setEnabled(false); //TODO Implement absorber.
		remove = new JRadioButton("Remove Gizmo");
		remove.addActionListener(new GizmoListener(GizmoListener.BUILD_REMOVE, this));
		rotate = new JRadioButton("Rotate Gizmo");
		rotate.addActionListener(new GizmoListener(GizmoListener.BUILD_ROTATE, this));
		rotate.setEnabled(false); //TODO Implement rotate
		addTrigger = new JRadioButton("Add Trigger");
		addTrigger.addActionListener(new GizmoListener(GizmoListener.BUILD_ADD_TRIGGER, this));
		addTrigger.setEnabled(false); //TODO Implement addTrigger.
		removeTrigger = new JRadioButton("Remove Trigger");
		removeTrigger.addActionListener(new GizmoListener(GizmoListener.BUILD_REMOVE_TRIGGER, this));
		removeTrigger.setEnabled(false); //TODO Implement removeTrigger.
		bottomPanel = new JPanel();
		load = new JButton("Load Map");
		load.addActionListener(new GizmoListener(GizmoListener.LOAD));
		save = new JButton("Save Map");
		save.addActionListener(new GizmoListener(GizmoListener.SAVE));
		play = new JButton("Play Mode");
		play.addActionListener(new GizmoListener(GizmoListener.PLAY_MODE, this));
		
		//Build the window.
		rightPanel.setLayout(new GridLayout(10,1));
		rightPanel.add(square);
		rightPanel.add(triangle);
		rightPanel.add(leftFlipper);
		rightPanel.add(rightFlipper);
		rightPanel.add(absorber);
//		rightPanel.add(new JPanel());
		rightPanel.add(remove);
		rightPanel.add(rotate);
		rightPanel.add(addTrigger);
		rightPanel.add(removeTrigger);
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
	
	public void addSquares(){
		square.setSelected(true);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		canvas.buildAction = "square";
	}
	
	public void addTriangles(){
		square.setSelected(false);
		triangle.setSelected(true);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);	
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		canvas.buildAction = "triangle";
	}
	
	public void addLeftFlipper(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(true);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		canvas.buildAction = "left flipper";
	}
	
	public void addRightFlipper(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(true);
		absorber.setSelected(false);
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		canvas.buildAction = "right flipper";
	}
	
	public void addAbsorber(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(true);
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		canvas.buildAction = "absorber";
	}
	
	public void remove(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);
		remove.setSelected(true);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		canvas.buildAction = "remove";
	}
	
	public void rotate(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);
		remove.setSelected(false);
		rotate.setSelected(true);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(false);
		canvas.buildAction = "rotate";
	}
	
	public void addTrigger(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(true);
		removeTrigger.setSelected(false);
		canvas.buildAction = "addTrigger";
	}
	
	public void removeTrigger(){
		square.setSelected(false);
		triangle.setSelected(false);
		leftFlipper.setSelected(false);
		rightFlipper.setSelected(false);
		absorber.setSelected(false);
		remove.setSelected(false);
		rotate.setSelected(false);
		addTrigger.setSelected(false);
		removeTrigger.setSelected(true);
		canvas.buildAction = "removeTrigger";
	}
	
	public static void main(String[] args) {
		new BuildView();
	}
}