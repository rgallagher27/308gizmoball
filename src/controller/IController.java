package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

import view.framework.G2DAbstractCanvas;
import view.framework.G2DObject;


public interface IController extends KeyListener, ActionListener, MouseListener {
	
	public int getGizX(String name);
	public int getGizY(String name);
	public int getGizWidth(String name);
	
	public int getGizHeight(String name);
	
	public float getBallX(String name);

	public float getBallY(String name);
	
	public double getBallRadius(String name);
	
	public List<String> getGizmos();
	public G2DObject getGraphicsBall(String ball);
	public G2DObject getGraphicsGizmo(String gizmo);
	public List<String> getBalls();
	public void factoryDraw(G2DAbstractCanvas canvas, int rows, int columns, double rowWidth, double columnHeight);
	
}
