package model;

import java.awt.Point;
import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;
import model.physics.Vect;

public class CircleBumper extends Gizmo implements iGizmo {
	
	public CircleBumper(String identifier, Point p, double row, double column, double width, double height) {
		super.point 		= p;
		super.rowWidth 		= row;
		super.columnHeight	= column;
		super.identifier 	= identifier;
		super.cellWidth		= width;
		super.cellHeight	= height;
		
		super.lineSegments 	= new ArrayList<LineSegment>();
		super.circles = new ArrayList<Circle>();
		
		super.circles.add(
				new Circle(new Vect(super.point.x * super.cellWidth, super.point.y * super.cellHeight), super.cellWidth / 2)
				);
	}

}
