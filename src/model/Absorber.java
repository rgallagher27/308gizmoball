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
	
	public Absorber( String identifier, GizPoint p, double row, double column, double width, double height) {
		point 		= p;
		rowWidth 		= row;
		columnHeight	= column;
		this.identifier 	= identifier;
		cellWidth		= width;
		cellHeight	= height;
		active 		= false;
		
		lineSegments 	= new ArrayList<LineSegment>();
		circles		= new ArrayList<Circle>();
		capturedBalls 	= new ArrayList<iBall>();
		
		fillLineSegments();
	}
	
	@Override
	public void performAction(boolean a)
	{
		active = a;
	}

	@Override
	public void move() {
		if(active){
			if((!capturedBalls.isEmpty())){
				iBall b = capturedBalls.get(0);
					  b.setVelocity(new Vect(0, -2));
					  b.setLocation(new BallPoint(19, 18));
					  b.setCaptured(false);
				capturedBalls.remove(0);
			}
		}
		this.active = false;
	}

	private void fillLineSegments()
	{
		double topLX = (point.getX() * cellWidth) - (cellWidth / 2);
		double topLY = (point.getY() * cellHeight) - (cellHeight / 2);
		
		double topRX = (point.getX() * cellWidth) + (rowWidth * cellWidth)- (cellWidth / 2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (point.getY() * cellHeight) + (columnHeight * cellHeight) - (cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		
	}
	
	public void captureBall(iBall ball)
	{
		capturedBalls.add(ball);
	}

}
