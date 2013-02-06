package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayView extends JFrame{
	private GizmoCanvas canvas;
	private JPanel panel;
	private JButton load;
	private JButton start;
	private JButton build;

	private static final long serialVersionUID = 1L;
	
	public PlayView(){
		setup();
	}

	private void setup(){
		canvas = new GizmoCanvas();
		panel = new JPanel();
		load = new JButton("Load Map");
		start = new JButton("Start Game");
		build = new JButton("Build Mode");		
		
		this.setTitle("Gizmoball Play Mode");
		this.setSize(640, 480);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		canvas.setSize(this.getWidth(), this.getHeight()-100);
		this.add(canvas, BorderLayout.CENTER);
		panel.add(start);
		panel.add(load);
		panel.add(build);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(panel, BorderLayout.SOUTH);
		canvas.repaint();
	}
	
	public static void main(String[] args){
		new PlayView();
	}
}
