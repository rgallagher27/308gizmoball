package model;

import java.awt.Point;
import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;

public class TriangleBumper extends Gizmo implements iGizmo {

	public TriangleBumper( String identifier, Point p, double row, double column, double width, double height) {
		super();
		super.point 		= p;
		super.rowWidth 		= row;
		super.columnHeight 	= column;
		super.cellWidth		= width;
		super.cellHeight    = height;
		super.rotation		= height;
		super.identifier 	= identifier;
		super.rotation		= 0;
		
		this.fillLineSegments();
	}
	
	private void fillLineSegments()
	{
		super.lineSegments   	= null;
		super.circles  	= null;
		super.lineSegments		= new ArrayList<LineSegment>();
		super.circles 	= new ArrayList<Circle>();
		
		double topLX = (this.point.x * this.cellWidth)  - ((this.rowWidth * this.cellWidth) / 2);
		double topLY = (this.point.y * this.cellHeight) - ((this.columnHeight * this.cellHeight) / 2);
		
		double topRX = topLX + (this.rowWidth * this.cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = topLY + (this.columnHeight * this.cellHeight);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
	
		switch ((int)super.rotation) {
		case 0:
			lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
			lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
			lineSegments.add(new LineSegment(bottomLX, bottomLY, topRX, topRY));
			
			circles.add(new Circle(topLX, topLY, 0));
			circles.add(new Circle(topRX, topRY, 0));
			circles.add(new Circle(bottomLX, bottomLY, 0));
			
			break;
		case 90:
			lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
			lineSegments.add(new LineSegment(topRX, topRY,bottomRX, bottomRY));
			lineSegments.add(new LineSegment(topLX, topLY, bottomRX, bottomRY));
			
			circles.add(new Circle(topLX, topLY, 0));
			circles.add(new Circle(topRX, topRY, 0));
			circles.add(new Circle(bottomRX, bottomRY, 0));
			break;
		case 180:
			lineSegments.add(new LineSegment(bottomLX, bottomLY, topRX, topRY));
			lineSegments.add(new LineSegment(topRX, topRY,bottomRX, bottomRY));
			lineSegments.add(new LineSegment(bottomRX, bottomRY, bottomLX, bottomLY));
			
			circles.add(new Circle(topRX, topRY, 0));
			circles.add(new Circle(bottomLX, bottomLY, 0));
			circles.add(new Circle(bottomRX, bottomRY, 0));

		default:
			break;
		}
		
	}

}
