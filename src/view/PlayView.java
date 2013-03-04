package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GizmoListener;

public class PlayView extends JFrame implements IFrame{
	private GizmoCanvas canvas;
	private JPanel panel;
	private JButton load;
	private JButton start;
	private JButton build; 

	private static final long serialVersionUID = 1L;
	
	public PlayView(){
		this.setup();
	}

	private void setup(){
		//Build the components.
				//TODO Only used to test canvas.  Remove for proper game.
		Collection<Polygon> c = new ArrayList<Polygon>();
		Polygon poly;
		for(int i = 0; i < 200; i+=10){
			poly = new Polygon();
			poly.addPoint(i, 190);
			poly.addPoint(i+10, 190);
			poly.addPoint(i+10, 200);
			poly.addPoint(i, 200);
			c.add(poly);
		}
		for(int i = 0; i < 150; i+=10){
			poly = new Polygon();
			poly.addPoint(170, 180-i);
			poly.addPoint(180, 180-i);
			poly.addPoint(180, 190-i);
			poly.addPoint(170, 190-i);
			c.add(poly);	
		}
		for(int i = 0; i < 60; i+=10){
			poly = new Polygon();
			poly.addPoint(i, 30);
			poly.addPoint(i+10, 30);
			poly.addPoint(i+10, 40);
			poly.addPoint(i, 40);
			c.add(poly);	
		}
		for(int i = 110; i < 170; i+=10){
			poly = new Polygon();
			poly.addPoint(i, 30);
			poly.addPoint(i+10, 30);
			poly.addPoint(i+10, 40);
			poly.addPoint(i, 40);
			c.add(poly);	
		}
		for(int i = 40, j = 40; i <= 60; i+=10, j+=10){
			poly = new Polygon();
			poly.addPoint(i, j);
			poly.addPoint(i+10, j);
			poly.addPoint(i, j+10);
			c.add(poly);	
		}
		for(int i = 120, j = 40; i >= 100; i-=10, j+=10){
			poly = new Polygon();
			poly.addPoint(i, j);
			poly.addPoint(i+10, j);
			poly.addPoint(i, j+10);
			c.add(poly);	
		}
		poly = new Polygon();
		poly.addPoint(190, 0);
		poly.addPoint(200, 0);
		poly.addPoint(200, 10);
		c.add(poly);
				//TODO Remove everything above here.
		canvas = new GizmoCanvas(c);
		canvas.addKeyListener(new GizmoListener(GizmoListener.KEYLISTENER));
		panel = new JPanel();
		load = new JButton("Load");
		load.addActionListener(new GizmoListener(GizmoListener.LOAD));
		start = new JButton("Start Game");
		start.addActionListener(new GizmoListener(GizmoListener.START));
		build = new JButton("Build Mode");
		build.addActionListener(new GizmoListener(GizmoListener.BUILD_MODE, this));
		
		//Build the window.
		panel.add(start);
		panel.add(load);
		panel.add(build);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.setSize(this.getWidth(), 100);
		this.add(panel, BorderLayout.SOUTH);
		this.add(canvas);
		this.setTitle("Gizmoball Play Mode");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		canvas.repaint();
	}
}