package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import model.iGizmo;

public interface IController extends KeyListener, ActionListener, MouseListener {
	
	public int getGizX(String name);
	public int getGizY(String name);
	public int getGizWidth(String name);
	
	public int getGizHeight(String name);
	
	public float getBallX(String name);

	public float getBallY(String name);
	
	public double getBallRadius(String name);
	
	public List<String> getGizmos();
}
