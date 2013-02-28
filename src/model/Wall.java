package model;

import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;

public class Wall extends Gizmo implements iGizmo {

	public Wall(double width, double height) {
		super.identifier    = "WALL";
		super.cellWidth		= width;
		super.cellHeight 	= height;
		
		super.lineSegments  = new ArrayList<LineSegment>();
		super.circles	    = new ArrayList<Circle>();
		
		this.fillLineSegments();
		
	}
	
	private void fillLineSegments()
	{
		double topLX = 0 - (this.cellWidth / 2);
		double topLY = 0 - (this.cellHeight / 2);
		
		double topRX = (20 * this.cellWidth) - (this.cellWidth / 2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (20 * this.cellHeight) - (this.cellHeight / 2);
		
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
