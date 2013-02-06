package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class BuildView extends JFrame {

	private static final long serialVersionUID = 1L;
	private GizmoCanvas canvas;
	private JPanel rightPanel;
	private JRadioButton gizmo1;
	private JRadioButton gizmo2;
	private JRadioButton gizmo3;
	private JRadioButton gizmo4;
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
		canvas = new GizmoCanvas();
		rightPanel = new JPanel();
		gizmo1 = new JRadioButton("Add Gizmo1");
		gizmo2 = new JRadioButton("Add Gizmo2");
		gizmo3 = new JRadioButton("Add Gizmo3");
		gizmo4 = new JRadioButton("Add Gizmo4");
		remove = new JRadioButton("Remove Gizmo");
		rotate = new JRadioButton("Rotate Gizmo");
		addTrigger = new JRadioButton("Add Trigger");
		removeTrigger = new JRadioButton("Remove Trigger");
		bottomPanel = new JPanel();
		load = new JButton("Load Map");
		save = new JButton("Save Map");
		play = new JButton("Play Mode");
		
		this.setTitle("Gizmoball Build View");
		this.setSize(640, 480);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		canvas.setSize(this.getWidth()-200, this.getHeight()-100);
		this.add(canvas, BorderLayout.CENTER);
		rightPanel.setLayout(new GridLayout(10,1));
		rightPanel.add(gizmo1);
		rightPanel.add(gizmo2);
		rightPanel.add(gizmo3);
		rightPanel.add(gizmo4);
		rightPanel.add(new JPanel());
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
		canvas.repaint();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BuildView();
	}

}
