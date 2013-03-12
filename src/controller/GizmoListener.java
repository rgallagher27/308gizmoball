package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import view.IBuildView;
import view.IFrame;

public class GizmoListener implements ActionListener, KeyListener, MouseListener {

	public static final int KEYLISTENER = 0;
	public static final int MOUSELISTENER = 0;
	public static final int LOAD = 1;
	public static final int SAVE = 2;
	public static final int BUILD_MODE = 3;
	public static final int PLAY_MODE = 4;
	public static final int START = 5;
	public static final int BUILD_SQUARE = 6;
	public static final int BUILD_TRIANGLE = 7;
	public static final int BUILD_LEFT_FLIPPER = 8;
	public static final int BUILD_RIGHT_FLIPPER = 9;
	public static final int BUILD_ABSORBER = 10;
	public static final int BUILD_REMOVE = 11;
	public static final int BUILD_ROTATE = 12;
	public static final int BUILD_ADD_TRIGGER = 13;
	public static final int BUILD_REMOVE_TRIGGER = 14;
	
	private final int type; 
	private view.IBuildView buildView;
	private view.ICanvas canvas;
	private view.IFrame frame;
	
	public GizmoListener(final int type){
		this.type = type;
	}
	
	public GizmoListener(final int type, IFrame frame){
		this.type = type;
		this.frame = frame;
		try{
			this.buildView = (IBuildView) frame;
		} catch(ClassCastException e){
		}
	}
	
	public GizmoListener(final int type, view.ICanvas canvas){
		this.type = type;
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String message1 = "Unfortunately, I haven't implemented this button yet, hence why rather than do";
		String message2 = "what it appear that it's meant to, all I can do is display this message stating";
		String message3 = "that I can't do what I appear I'm meant to...  But please rest assured that my";
		String message4	= "awesome development team (namely James, Arran, Darren, Iain and Ross) are working";
		String message5 = "tirelessly to correct this inadequacy before I next have to be let out in public.";
		String message6 = "In the meantime, we hope that the few enabled features that my fellow prototypes and";
		String message7 = "I have just now give you a nice taster of how the finalised version of us will work.  :)";
		switch(type){
			case LOAD: JOptionPane.showMessageDialog(null, message1+'\n'+message2+'\n'+message3+'\n'+message4+'\n'+message5+'\n'+message6+'\n'+message7); break;
			case SAVE: JOptionPane.showMessageDialog(null, message1+'\n'+message2+'\n'+message3+'\n'+message4+'\n'+message5+'\n'+message6+'\n'+message7); break;
			case BUILD_MODE: frame.dispose(); frame = new view.BuildView(); break;
			case PLAY_MODE: frame.dispose(); frame = new view.PlayView(); break;
			case START: JOptionPane.showMessageDialog(null, message1+'\n'+message2+'\n'+message3+'\n'+message4+'\n'+message5+'\n'+message6+'\n'+message7); break;
			case BUILD_SQUARE: buildView.addSquares(); break;
			case BUILD_TRIANGLE: buildView.addTriangles(); break;
			case BUILD_LEFT_FLIPPER: buildView.addLeftFlipper(); break;
			case BUILD_RIGHT_FLIPPER: buildView.addRightFlipper(); break;
			case BUILD_ABSORBER: buildView.addAbsorber(); break;
			case BUILD_REMOVE: buildView.remove(); break;
			case BUILD_ROTATE: buildView.rotate(); break;
			case BUILD_ADD_TRIGGER: buildView.addTrigger(); break;
			case BUILD_REMOVE_TRIGGER: buildView.removeTrigger(); break;
			default: 
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("key pressed");
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			JOptionPane.showMessageDialog(null, "Left flippers activated");
		} else if(arg0.getKeyCode() == KeyEvent.VK_P){
			JOptionPane.showMessageDialog(null, "Left flippers released");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
			case KeyEvent.VK_LEFT: JOptionPane.showMessageDialog(null, "Left flippers released"); break;
			case KeyEvent.VK_RIGHT: JOptionPane.showMessageDialog(null, "Right flippers released"); break;
			default:
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		canvas.addShape(arg0.getX(), arg0.getY());
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


}