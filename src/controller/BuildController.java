package controller;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ViewCanvas;

import model.iOverlord;

public class BuildController implements MouseListener, ActionListener {

	
	private iOverlord overlord;
	private ViewCanvas view;
	
	
	public BuildController(iOverlord ov, ViewCanvas v){
		view = v;
		overlord = ov;
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
