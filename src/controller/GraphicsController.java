package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.iGizmo;
import model.iOverlord;
import model.physics.Circle;
import model.physics.LineSegment;
import view.GizmoFactory;
import view.framework.G2DAbstractCanvas;
import view.framework.G2DObject;

public class GraphicsController {
	
	private iOverlord overlord;
	private GizmoFactory gizFactory;
	
	public GraphicsController(iOverlord ov){
		overlord 	= ov;
		gizFactory 	= new GizmoFactory(this);
		
	}
	
	public ArrayList<String> getGizTriggers(){
		return overlord.getConnects();
	}
	public ArrayList<String> getGizTriggers(String name){
		ArrayList<String> tmp = new ArrayList<String>();
		for(iGizmo giz : overlord.getGizmo(name).getTriggers()){
			tmp.add(giz.getIdentifier());
		}
		return tmp;
	}
	
	public String getGizType(String name)
	{
		return overlord.getGizmo(name).getGizType();
	}
	
	public int getGizX(String name)
	{
		return overlord.getGizmo(name).getLocation().getX();
	}

	public int getGizY(String name){
		return overlord.getGizmo(name).getLocation().getY();
	}
	
	public double getGizRotation(String name){
		return overlord.getGizmo(name).getRotation();
	}
	
	public double getGizWidth(String name){
		return overlord.getGizmo(name).getCellWidth();
	}
	
	public double getGizHeight(String name){
		return overlord.getGizmo(name).getCellHeight();
	}
	
	public Color getGizColour(String name){
		return overlord.getGizmo(name).getColour();
	}
	
	public float getBallX(String name){
		return overlord.getBall(name).getLocation().getX();
	}

	public float getBallY(String name){
		return overlord.getBall(name).getLocation().getY();
	}
	
	public double getBallRadius(String name){
		return overlord.getBall(name).getRadius();
	}
	
	public double getBallCellWidth(String name){
		return overlord.getBall(name).getCellWidth();
	}
	
	public double getGizRowWidth(String name){
		return overlord.getGizmo(name).getRowWidth();
	}
	
	public double getGizColumnHeight(String name){
		return overlord.getGizmo(name).getColumnHeight();
	}
	
	public List<LineSegment> getLines(String name)
	{
		return overlord.getGizmo(name).getSegments();
	}
	
	public List<Circle> getCircles(String name)
	{
		return overlord.getGizmo(name).getCircles();
	}
	
	public double getBallCellHeight(String name){
		return overlord.getBall(name).getCellHeight();
	}
	
	public G2DObject getGraphicsGizmo(String gizmo){
		if(overlord.getGizmo(gizmo) != null){
			return gizFactory.draw(gizmo);
		}
		return null;
	}
	
	public G2DObject getGraphicsBall(String ball){
		if(overlord.getBall(ball) != null){
			return gizFactory.drawBall(ball);
		} 
		return null;
	}
	
	public G2DObject getGraphicsLine(String connect, String to){
		if(overlord.getGizmo(connect) != null && overlord.getGizmo(to) != null){
			return gizFactory.drawLine(connect, to);
		}
		return null;
	}
	
	public void factoryDraw(G2DAbstractCanvas canvas, int rows, int columns, double rowWidth, double columnHeight) {
		gizFactory.drawGrid(canvas, rows, columns, rowWidth, columnHeight);
		
	}
}
