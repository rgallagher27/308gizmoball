package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.IOverlord;
import model.Overlord;

import controller.PlayController;

public class AbsorberFrame extends JFrame{
	
	private AbsCanvas abs;
	private PlayController cont;
	private JPanel jp;
	
	AbsorberFrame(){
		jp = new JPanel();
		jp.setMaximumSize(new Dimension(800, 800));
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		setSize(800, 800);
		abs = new AbsCanvas();
		jp.add(abs);
		add(abs);
		pack();
		setVisible(true);
		
	}
	
	public AbsCanvas getCanvas(){
		return abs;
	}
	
	public void init(){
		
	}

	
	
	
	
	
	public static void main(String[] args){
		AbsorberFrame fr = new AbsorberFrame();
		Overlord ov = new Overlord(20,20);
		PlayController pc = new PlayController(fr.getCanvas(), ov);
		fr.getCanvas().addController(pc);
		ov.addObserver(fr.getCanvas());
		
		ov.setFriction(0.025F, 0.025F);
		ov.setGravity(5);
		ov.addAbsorber("A1", 0, 19, 20, 20);
		ov.addBall("B1", "A1", 7.25F, 19.4F, 0.0, 0.0);
		//ov.addBall("B2", "", 5.34F, 3.45F, -10.0, 0.0);
		System.out.println("X: " + ov.getBall("B1").getLocation().getX() + " Y : " + ov.getBall("B1").getLocation().getY());
		
		fr.getCanvas().start();
	}
}
