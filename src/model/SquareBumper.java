package model;

import java.awt.Point;
import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;

public class SquareBumper extends Gizmo implements iGizmo {

	public SquareBumper(String identifier, GizPoint p, double rowWidth, double columnHeight, double cellWidth, double cellHeight) {
		super();
		point 		= p;
		this.rowWidth 		= rowWidth;
		this.columnHeight  = columnHeight;
		this.cellWidth		= cellWidth;
		this.cellHeight 	= cellHeight;
		this.identifier 	= identifier;
		
		lineSegments = new ArrayList<LineSegment>();
		circles = new ArrayList<Circle>();
		
		fillLineSegments();
		
	}

	private void fillLineSegments()
	{
		double topLX = (point.getX() * cellWidth)  - ((rowWidth * cellWidth) / 2);
		double topLY = (point.getY() * cellHeight) - ((columnHeight * cellHeight) / 2);
		
		double topRX = topLX + (rowWidth * cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = topLY + (columnHeight * cellHeight);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		circles.add(new Circle(topLX, topLY, 0));
		
		circles.add(new Circle(bottomLX, bottomLY, 0));
		
		circles.add(new Circle(topRX, topRY, 0));
		
		circles.add(new Circle(bottomRX, bottomRY, 0));
		
		
	}

}
