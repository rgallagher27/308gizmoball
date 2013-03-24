package controller;

import model.iOverlord;
import view.GizmoFactory;
import view.framework.G2DAbstractCanvas;
import view.framework.G2DObject;

public class GraphicsController {
	
	private iOverlord overlord;
	private GizmoFactory gizFactory;
	
	public GraphicsController(iOverlord ov){
		overlord = ov;
		gizFactory = new GizmoFactory(this);
		
	}
	public int getGizX(String name){
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
	
	public void factoryDraw(G2DAbstractCanvas canvas, int rows, int columns,
			double rowWidth, double columnHeight) {
		gizFactory.drawGrid(canvas, rows, columns, rowWidth, columnHeight);
		
	}
}
