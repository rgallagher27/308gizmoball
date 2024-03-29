package model;

import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;
import model.physics.Vect;

public class Portal extends Gizmo implements iGizmo {
	
	public static final String _TYPE = "P";
	
	private GizPoint secondPoint;
	
	public Portal(String identifier, GizPoint p, GizPoint p2, double row, double column, double width, double height) {
		point 				= p;
		secondPoint			= p2;
		rowWidth 			= row;
		columnHeight		= column;
		cellWidth			= width;
		cellHeight			= height;
		height 				= 1;
		width 				= 1;
		lineSegments 		= new ArrayList<LineSegment>();
		circles 			= new ArrayList<Circle>();
		double radius 		= cellWidth / 2;
		
		this.identifier 	= identifier;
		
		circles.add(
				new Circle(new Vect((point.getX() * cellWidth) + radius, (point.getY() * cellHeight) + radius), cellWidth / 2)
				);	
	}
	
	@Override
	public String getGizType() 
	{
		return Portal._TYPE;
	}

	@Override
	public void setLocation(GizPoint p) {
		point = p;
		fillLineSegments();
	}
	
	public void setSecondLocation(GizPoint p)
	{
		secondPoint = p;
	}
	
	public GizPoint getSecondLocation()
	{
		return this.secondPoint;
	}
	
	@Override
	public String toString() {
		return ("Portal " + identifier + " " + point.getX() + " " + point.getY() + " " + secondPoint.getX() + " " + secondPoint.getY());
	}
	
	private void fillLineSegments(){
		circles.clear();
		double radius = cellWidth / 2;
		circles.add(
				new Circle(new Vect((point.getX() * cellWidth) + radius, (point.getY() * cellHeight) + radius), cellWidth / 2)
				);
	}

}
