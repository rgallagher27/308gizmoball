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
		this.width = (int) row;
		this.height = (int) column;
		fillLineSegments();
	}
	
	@Override
	public void performAction(boolean a)
	{
		active = a;
	}
	
	public void setLocation(GizPoint p) {
		point = p;
		fillLineSegments();
	}

	@Override
	public void move() {
		if(active){
			if((!capturedBalls.isEmpty())){
				iBall b = capturedBalls.get(0);
					  b.setVelocity(new Vect(0, -2));
					  b.setLocation(new BallPoint(point.getX() + (width-1), point.getY() - 1));
					  
					  b.setCaptured(false);
					  System.out.println("firing " + b.getIdentifier());
				capturedBalls.remove(0);
			}
		}
		active = false;
	}

	private void fillLineSegments()
	{
		/*double topLX = (point.getX() * cellWidth) - (cellWidth / 2);
		double topLY = (point.getY() * cellHeight) - (cellHeight / 2);
		
		double topRX = (point.getX() * cellWidth) + (rowWidth * cellWidth)- (cellWidth / 2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (point.getY() * cellHeight) + (columnHeight * cellHeight) - (cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		*/
		
		double topLX = point.getX() * cellWidth;
		double topLY = point.getY() * cellHeight;
		
		double topRX = (point.getX() * cellWidth) + (rowWidth * cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (point.getY() * cellHeight) + (columnHeight * cellHeight);
		
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

	public void removeStoredBall(String ballName) {
		for(iBall balls : capturedBalls){
			if(balls.getIdentifier().equals(ballName)){
				capturedBalls.remove(balls);
				break;
			}
		}
		
		
	}

	@Override
	public String toString() {
		return ("Absorber " + identifier + " " + point.getX() + " " + point.getY() + " " + (int)(rowWidth + point.getX()) + " " + (int)(columnHeight + point.getY()));
	}

}
