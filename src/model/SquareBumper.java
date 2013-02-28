package model;

import java.awt.Point;
import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;

public class SquareBumper extends Gizmo implements iGizmo {

	public SquareBumper(String identifier, Point p, double rowWidth, double columnHeight, double cellWidth, double cellHeight) {
		super();
		super.point 		= p;
		super.rowWidth 		= rowWidth;
		super.columnHeight  = columnHeight;
		super.cellWidth		= cellWidth;
		super.cellHeight 	= cellHeight;
		super.identifier 	= identifier;
		
		super.lineSegments = new ArrayList<LineSegment>();
		super.circles = new ArrayList<Circle>();
		
		this.fillLineSegments();
		
	}

	private void fillLineSegments()
	{
		double topLX = (this.point.x * this.cellWidth)  - ((this.rowWidth * this.cellWidth) / 2);
		double topLY = (this.point.y * this.cellHeight) - ((this.columnHeight * this.cellHeight) / 2);
		
		double topRX = topLX + (this.rowWidth * this.cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = topLY + (this.columnHeight * this.cellHeight);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		this.lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		this.lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		this.lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		this.lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		this.circles.add(new Circle(topLX, topLY, 0));
		
		this.circles.add(new Circle(bottomLX, bottomLY, 0));
		
		this.circles.add(new Circle(topRX, topRY, 0));
		
		this.circles.add(new Circle(bottomRX, bottomRY, 0));
		
		
	}

}
