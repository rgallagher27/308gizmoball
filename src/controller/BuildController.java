package controller;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.CompleteViewContainer;
import view.ViewCanvas;

import model.iOverlord;

public class BuildController implements MouseListener, ActionListener {

	private iOverlord overlord;
	private ViewCanvas view;
	private CompleteViewContainer frame;
	private int currentSelectedMode;
	
	private final int BUILD_SQUARE = 2;
	private final int BUILD_TRIANGLE = 3;
	private final int BUILD_LEFT_FLIPPER = 4;
	private final int BUILD_RIGHT_FLIPPER = 5;
	private final int BUILD_ABSORBER = 6;
	private final int BUILD_REMOVE = 7;
	private final int BUILD_ROTATE = 8;
	private final int BUILD_ADD_TRIGGER = 9;
	private final int BUILD_REMOVE_TRIGGER = 10;
	private final int ADD_BALL = 1;
	private final int BUILD_CIRCLE = 11;

	public BuildController(iOverlord ov, ViewCanvas v,
			CompleteViewContainer frame) {
		view = v;
		this.frame = frame;
		overlord = ov;
		currentSelectedMode = 0;
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(frame.getMode()){
			//ignore mouse clicks during play mode.
		}else{
		int x = view.mouseX(e.getX());
		int y = view.mouseY(e.getY());
		
		String gizName = overlord.getGizName(x, y);
		String ballName = overlord.getBallName(x, y);
		System.out.println("gizName: " + gizName);
		String type = "";
		boolean success = false;

		if (x < 20 && y < 20) {
			switch (currentSelectedMode) {
			case ADD_BALL:
				type = "ball";
				if(gizName.contains("A")){
					success = overlord.addBall(overlord.getNextName("B"), gizName, x, y, 0.0, 0.0);
					frame.showBallInfo(false);
					
				}else{
					if(frame.getBallVX() != Double.MIN_VALUE && frame.getBallVY() != Double.MIN_VALUE){
					success = overlord.addBall(overlord.getNextName("B"), "", x, y, frame.getBallVX(), frame.getBallVY());
					frame.showBallInfo(false);
					
					}
				}
				break;
			case BUILD_ABSORBER:
				type = "absorber";
				if(frame.getAbsorberHeight() != Integer.MIN_VALUE && frame.getAbsorberWidth() != Integer.MIN_VALUE){
					success = overlord.addAbsorber(overlord.getNextName("A"), x, y, (x +frame.getAbsorberWidth()), (y + frame.getAbsorberHeight()));
					frame.showAbsInfo(false);
				}
				break;
			case BUILD_SQUARE:
				type = "square";
				success = overlord.addSquare(overlord.getNextName("S"), x, y);
				break;
			case BUILD_TRIANGLE:
				type = "triangle";
				success = overlord.addTriangle(overlord.getNextName("T"), x, y);
				break;
			case BUILD_CIRCLE:
				type = "circle";
				success = overlord.addCircle(overlord.getNextName("C"), x, y);
				break; 	
			}
			frame.unselectAll();
			if(!success && currentSelectedMode != 0){
				frame.error("The " + type + " gizmo could not be added at that location!");
			}
			currentSelectedMode = 0;
			success = false;
			type = "";

		}
		}

		System.out.println(view.mouseX(e.getX()) + " : "
				+ view.mouseY(e.getY()));

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
		switch (e.getActionCommand()) {
		
		case "Load":
			if (frame != null) {
				java.io.File f = frame.askForMapFile();
				if (f != null && f.exists()) {
					frame.information(f.getPath()); // TODO Implement with model
				} else if (f != null) {
					frame.error("This file doesn't exist.");
				}
			} else if (frame != null) {
				java.io.File f = frame.askForMapFile();
				if (f != null && f.exists()) {
					frame.information(f.getPath()); // TODO Implement with model
				} else if (f != null) {
					frame.error("This file doesn't exist.");
				}
			}
			break;
		case "Save":
			java.io.File f = frame.askForMapFile();
			if (f != null && !f.exists()) {
				frame.information(f.getPath()); // TODO Implement with model
			} else if (f != null) {
				frame.error("I can't overwrite an existing file.");
			}
			break;
		case "Build":
			frame.switchBuild();
			break;
		case "Play":
			frame.switchPlay();
			break;
		// case BUILD_MODE:
		// playView.dispose(); playView = null;
		// buildView = new view.BuildView();
		// break;
		// case PLAY_MODE:
		// buildView.dispose(); buildView = null;
		// playView = new view.PlayView();
		// break;
		case "AddBall":
			currentSelectedMode = ADD_BALL;
			frame.showBallInfo(true);
			// int velocity = -1;
			// while(velocity < 0 || velocity > 200){
			// String input =
			// buildView.ask("Please give the initial velocity (0 - 200).");
			// if(input == null) return;
			// velocity = Integer.parseInt(input);
			// }
			// buildView.information("Initial velocity = " + velocity); //TODO
			// Implement with model
			break;
		case "Square": // TODO Implement with model
			currentSelectedMode = BUILD_SQUARE;
			// buildView.addSquares();
			break;
		case "Triangle": // TODO Implement with model
			currentSelectedMode = BUILD_TRIANGLE;
			// buildView.addTriangles();
			break;
		case "LeftFlipper": // TODO Implement with model
			currentSelectedMode = BUILD_LEFT_FLIPPER;
			// buildView.addLeftFlipper();
			break;
		case "Circle":
			currentSelectedMode = BUILD_CIRCLE;
			break;
		case "RightFlipper": // TODO Implement with model
			currentSelectedMode = BUILD_RIGHT_FLIPPER;
			// buildView.addRightFlipper();
			break;
		case "Absorber": // TODO Implement with model
			currentSelectedMode = BUILD_ABSORBER;
			frame.showAbsInfo(true);
			// buildView.addAbsorber();
			break;
		case "Remove": // TODO Implement with model
			currentSelectedMode = BUILD_REMOVE;
			// buildView.remove();
			break;
		case "Rotate": // TODO Implement with model
			currentSelectedMode = BUILD_ROTATE;
			// buildView.rotate();
			break;
		case "AddTrigger": // TODO Implement with model
			currentSelectedMode = BUILD_ADD_TRIGGER;
			// buildView.addTrigger();
			break;
		case "RemoveTrigger": // TODO Implement with model
			currentSelectedMode = BUILD_REMOVE_TRIGGER;
			// buildView.removeTrigger();
			break;
		default:
		}

	}

}
