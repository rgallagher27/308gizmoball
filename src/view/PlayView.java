package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayView extends JFrame implements WindowListener{
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
		//Build the components.
		canvas = new GizmoCanvas();
		panel = new JPanel();
		load = new JButton("Load Map");
		load.addActionListener(new ButtonListener(ButtonListener.LOAD));
		start = new JButton("Start Game");
		start.addActionListener(new ButtonListener(ButtonListener.PLAY));
		build = new JButton("Build Mode");
		build.addActionListener(new ButtonListener(ButtonListener.BUILD));
		
		//Build the window.
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
	
	@Override
	public void windowClosed(WindowEvent arg0) {
		System.exit(0);
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);	
	}
	@Override
	public void windowActivated(WindowEvent e) {		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
	}
	@Override
	public void windowIconified(WindowEvent e) {
	}
	@Override
	public void windowOpened(WindowEvent e) {
	}
}