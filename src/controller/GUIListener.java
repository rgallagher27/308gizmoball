package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import javax.swing.JFrame;

// import view.IBuildView;
// import view.IPlayView;
import view.CompleteViewContainer;

public class GUIListener implements KeyListener, ActionListener, MouseListener {

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
	public static final int ADD_BALL = 15;

	private final int type;

	private CompleteViewContainer frame;

	public GUIListener(final int type) {
		this.type = type;
	}

	public GUIListener(final int type, CompleteViewContainer frame) {
		this.type = type;
		this.frame = frame;
	}
	
	// public GUIListener(final int type, IBuildView frame){
	// 	this.type = type;
	// 	this.buildView = frame;
	// 	this.playView = null;
	// }

	// public GUIListener(final int type, view.ICanvas canvas) {
	// 	this.type = type;
	// 	this.canvas = canvas;
	// }

	private void unimplemented() { //TODO Remove before final release.
		String message1 = "Unfortunately, I haven't implemented this button yet, hence why rather than do";
		String message2 = "what it appear that it's meant to, all I can do is display this message stating";
		String message3 = "that I can't do what I appear I'm meant to...  But please rest assured that my";
		String message4 = "awesome development team (namely James, Arran, Darren, Iain and Ross) are working";
		String message5 = "tirelessly to correct this inadequacy before I next have to be let out in public.";
		String message6 = "In the meantime, we hope that the few enabled features that my fellow prototypes and";
		String message7 = "I have just now give you a nice taster of how the finalised version of us will work.  :)";
		JOptionPane.showMessageDialog(null, message1 + '\n' + message2 + '\n'
				+ message3 + '\n' + message4 + '\n' + message5 + '\n'
				+ message6 + '\n' + message7);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch (type) {
		case LOAD:
			if(frame != null){
				java.io.File f = frame.askForMapFile();
				if(f != null && f.exists()) {
					frame.information(f.getPath()); //TODO Implement with model
				} else if (f != null){ 
					frame.error("This file doesn't exist.");
				}
			} else if (frame != null){
				java.io.File f = frame.askForMapFile();
				if(f != null && f.exists()) {
					frame.information(f.getPath()); //TODO Implement with model
				} else if(f != null) { 
					frame.error("This file doesn't exist.");
				}
			}
			break;
		case SAVE:
			java.io.File f = frame.askForMapFile();
			if(f != null && !f.exists()) {
				frame.information(f.getPath()); //TODO Implement with model
			} else if(f != null){ 
				frame.error("I can't overwrite an existing file.");
			}
			break;
			case BUILD_MODE:
			frame.switchBuild();
			break;
			case PLAY_MODE:
			frame.switchPlay();
			break;
		// case BUILD_MODE:
		// 	playView.dispose(); playView = null;
		// 	buildView = new view.BuildView();
		// 	break;
		// case PLAY_MODE:
		// 	buildView.dispose(); buildView = null;
		// 	playView = new view.PlayView();
		// 	break;
		case START: //TODO Implement with model
			frame.information("The game will start when you press this button.");
			break;
		case ADD_BALL:
			// int velocity = -1;
			// while(velocity < 0 || velocity > 200){
			// 	String input = buildView.ask("Please give the initial velocity (0 - 200).");
			// 	if(input == null) return;
			// 	velocity = Integer.parseInt(input);
			// }
			// buildView.information("Initial velocity = " + velocity); //TODO Implement with model
			break;
		case BUILD_SQUARE: //TODO Implement with model
			// buildView.addSquares();
			break;
		case BUILD_TRIANGLE: //TODO Implement with model
			// buildView.addTriangles();
			break;
		case BUILD_LEFT_FLIPPER: //TODO Implement with model
			// buildView.addLeftFlipper();
			break;
		case BUILD_RIGHT_FLIPPER: //TODO Implement with model
			// buildView.addRightFlipper();
			break;
		case BUILD_ABSORBER: //TODO Implement with model
			// buildView.addAbsorber();
			break;
		case BUILD_REMOVE: //TODO Implement with model
			// buildView.remove();
			break;
		case BUILD_ROTATE: //TODO Implement with model
			// buildView.rotate();
			break;
		case BUILD_ADD_TRIGGER: //TODO Implement with model
			// buildView.addTrigger();
			break;
		case BUILD_REMOVE_TRIGGER: //TODO Implement with model
			// buildView.removeTrigger();
			break;
		default:
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("key pressed");
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			JOptionPane.showMessageDialog(null, "Left flippers activated");
		} else if (arg0.getKeyCode() == KeyEvent.VK_P) {
			JOptionPane.showMessageDialog(null, "Left flippers released");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			JOptionPane.showMessageDialog(null, "Left flippers released");
			break;
		case KeyEvent.VK_RIGHT:
			JOptionPane.showMessageDialog(null, "Right flippers released");
			break;
		default:
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		canvas.addShape(arg0.getX(), arg0.getY()); //TODO Create a map builder and connect.
	}
	/*
	 * (non-Javadoc)
	 * None of the following are implemented here.
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}