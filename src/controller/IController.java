package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import view.ViewCanvas;
import view.framework.G2DAbstractCanvas;
import view.framework.G2DObject;


public interface IController extends KeyListener, ActionListener {
	
	public List<String> getGizmos();
	public List<String> getBalls();
	public void start();
	public void stop();
	
	
}
