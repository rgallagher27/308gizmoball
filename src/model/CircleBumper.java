package model;

import java.awt.Point;
import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;
import model.physics.Vect;

public class CircleBumper extends Gizmo implements iGizmo {
	
	public CircleBumper(String identifier, GizPoint p, double row, double column, double width, double height) {
		point 		= p;
		rowWidth 		= row;
		columnHeight	= column;
		this.identifier 	= identifier;
		cellWidth		= width;
		cellHeight	= height;
		
		lineSegments 	= new ArrayList<LineSegment>();
		circles = new ArrayList<Circle>();
		
		circles.add(
				new Circle(new Vect(point.getX() * cellWidth, point.getY() * cellHeight), cellWidth / 2)
				);
	}

}
