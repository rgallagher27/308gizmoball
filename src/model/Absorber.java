package model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import model.physics.Circle;
import model.physics.LineSegment;
import model.physics.Vect;

public class Absorber extends Gizmo implements iGizmo {

	protected List<iBall> capturedBalls;
	protected boolean active;
	
	public Absorber( String identifier, Point p, double row, double column, double width, double height) {
		super.point 		= p;
		super.rowWidth 		= row;
		super.columnHeight	= column;
		super.identifier 	= identifier;
		super.cellWidth		= width;
		super.cellHeight	= height;
		this.active 		= false;
		
		super.lineSegments 	= new ArrayList<LineSegment>();
		super.circles		= new ArrayList<Circle>();
		this.capturedBalls 	= new ArrayList<iBall>();
		
		this.fillLineSegments();
	}
	
	@Override
	public void performAction(boolean a)
	{
		this.active = a;
	}

	@Override
	public void move() {
		if(this.active){
			if((!this.capturedBalls.isEmpty())){
				iBall b = this.capturedBalls.get(0);
					  b.setVelocity(new Vect(0, -2));
					  b.setLocation(new Point2D.Double(19, 18));
					  b.setCaptured(false);
				this.capturedBalls.remove(0);
			}
		}
		this.active = false;
	}

	private void fillLineSegments()
	{
		double topLX = (this.point.x * this.cellWidth) - (this.cellWidth / 2);
		double topLY = (this.point.y * this.cellHeight) - (this.cellHeight / 2);
		
		double topRX = (this.point.x * this.cellWidth) + (this.rowWidth * this.cellWidth)- (this.cellWidth / 2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (this.point.y * this.cellHeight) + (this.columnHeight * this.cellHeight) - (this.cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		this.lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		this.lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		this.lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		this.lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		
	}
	
	public void captureBall(iBall ball)
	{
		this.capturedBalls.add(ball);
	}

}
