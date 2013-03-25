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
	
	
	
	public BuildController(iOverlord ov, ViewCanvas v,  CompleteViewContainer frame){
		view = v;
		this.frame = frame;
		overlord = ov;
		currentSelectedMode = 0;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(view.mouseX(e.getX()) + " : " + view.mouseY(e.getY()));
		
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
		
		switch (e.getActionCommand()) {
		case "Load":
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
		case "Save":
			java.io.File f = frame.askForMapFile();
			if(f != null && !f.exists()) {
				frame.information(f.getPath()); //TODO Implement with model
			} else if(f != null){ 
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
		// 	playView.dispose(); playView = null;
		// 	buildView = new view.BuildView();
		// 	break;
		// case PLAY_MODE:
		// 	buildView.dispose(); buildView = null;
		// 	playView = new view.PlayView();
		// 	break;
		case "AddBall":
			currentSelectedMode = 1;
			// int velocity = -1;
			// while(velocity < 0 || velocity > 200){
			// 	String input = buildView.ask("Please give the initial velocity (0 - 200).");
			// 	if(input == null) return;
			// 	velocity = Integer.parseInt(input);
			// }
			// buildView.information("Initial velocity = " + velocity); //TODO Implement with model
			break;
		case "Square": //TODO Implement with model
			currentSelectedMode = 2;
			// buildView.addSquares();
			break;
		case "Triangle": //TODO Implement with model
			currentSelectedMode = 3;
			// buildView.addTriangles();
			break;
		case "LeftFlipper": //TODO Implement with model
			currentSelectedMode = 4;
			// buildView.addLeftFlipper();
			break;
		case "RightFlipper": //TODO Implement with model
			currentSelectedMode = 5;
			// buildView.addRightFlipper();
			break;
		case "Absorber": //TODO Implement with model
			currentSelectedMode = 6;
			// buildView.addAbsorber();
			break;
		case "Remove": //TODO Implement with model
			currentSelectedMode = 7;
			// buildView.remove();
			break;
		case "Rotate": //TODO Implement with model
			currentSelectedMode = 8;
			// buildView.rotate();
			break;
		case "AddTrigger": //TODO Implement with model
			currentSelectedMode = 9;
			// buildView.addTrigger();
			break;
		case "RemoveTrigger": //TODO Implement with model
			currentSelectedMode = 10;
			// buildView.removeTrigger();
			break;
		default:
		}
		
	}

}
