package model;

import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;
import model.physics.Vect;

public class CircleBumper extends Gizmo implements iGizmo {
	
	public static final String _TYPE = "C";
	
	public CircleBumper(String identifier, GizPoint p, double row, double column, double width, double height) {
		point 		= p;
		rowWidth 		= row;
		columnHeight	= column;
		this.identifier 	= identifier;
		cellWidth		= width;
		cellHeight	= height;
		height = 1;
		width = 1;
		lineSegments 	= new ArrayList<LineSegment>();
		circles = new ArrayList<Circle>();
		
		double radius = cellWidth / 2;
		circles.add(
				new Circle(new Vect((point.getX() * cellWidth) + radius, (point.getY() * cellHeight) + radius), cellWidth / 2)
				);
		
		/*
		circles.add(
				new Circle(new Vect(point.getX() * cellWidth, point.getY() * cellHeight), cellWidth / 2)
				);
		*/		
		
	}
	
	@Override
	public String getGizType() 
	{
		return CircleBumper._TYPE;
	}

	public void setLocation(GizPoint p) {
		point = p;
		fillLineSegments();
	}
	
	@Override
	public String toString() {
		return ("Circle " + identifier + " " + point.getX() + " " + point.getY());
	}
	
	private void fillLineSegments(){
		circles.clear();
		double radius = cellWidth / 2;
		circles.add(
				new Circle(new Vect((point.getX() * cellWidth) + radius, (point.getY() * cellHeight) + radius), cellWidth / 2)
				);
	}

}
