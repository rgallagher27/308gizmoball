package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;


public interface IController extends KeyListener, ActionListener {
	
	public List<String> getGizmos();
	public List<String> getBalls();
	public void start();
	public void stop();
	public void pause();
	
	
}
