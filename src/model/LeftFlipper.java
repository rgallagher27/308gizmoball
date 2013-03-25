package model;

import model.physics.Angle;
import model.physics.Circle;
import model.physics.Geometry;
import model.physics.LineSegment;
import model.physics.Vect;

public class LeftFlipper extends Flipper {

	public LeftFlipper(String identifier, GizPoint p, double row, double column, double width, double height) {
		super(identifier, p, row, column, width, height);
		minRotation = 0;
		maxRotation = -90;
		fillLineSegments();
	}

	@Override
	public void move(double Delta_T)
	{
		if(rotation < maxRotation){
			rotation = maxRotation;
		}
		if(rotation > minRotation){
			rotation = minRotation;
		}
		if(active){
			if(rotation > maxRotation){
				super.flipperVelocity = (rotationVelocity * Delta_T);
				rotation = rotation - super.flipperVelocity;

				angularVel = -angularVel;  // these may be wrong
			}
		}else{
			if(rotation < minRotation){
				super.flipperVelocity = (rotationVelocity * Delta_T);
				rotation = rotation + super.flipperVelocity;

				angularVel = +angularVel; // these may be wrong + / - dependant on direction.
			}
		}
		fillLineSegments();
	}
	
	@Override
	public void setRotation(double r) {
		rotation    = -r;
		maxRotation -= r;
		minRotation -= r;
		fillLineSegments();
	}
	
	private void fillLineSegments()
	{
		lineSegments.clear();
		circles.clear();
		
		double topLX = (point.getX() * cellWidth);
		double topLY = (point.getY() * cellHeight) + (cellWidth/4);
		
		double topRX = (point.getX() * cellWidth) + (rowWidth * cellWidth/2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (point.getY() * cellHeight) + (columnHeight * cellHeight) - (cellHeight / 4);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		double centerXTop = (topLX + cellWidth/4);
		double centerYTop = topLY + 1;
		rotationCenter = new Vect(centerXTop, centerYTop);
		
		double centerXBot = (bottomLX + cellWidth/4);
		double centerYBot = bottomLY + 1;
		
		Vect centerTop = new Vect(centerXTop, centerYTop);  // center point of rotation
		
		nonRotationalCircle = new Circle(centerXTop, centerYTop, cellWidth/4);
		Circle bot = new Circle(centerXBot, centerYBot, cellWidth/4);
		
		
		LineSegment line3 = new LineSegment(topLX, topLY, bottomLX, bottomLY);
		LineSegment line4 = new LineSegment(topRX, topRY, bottomRX, bottomRY);
		
		
		Angle rotationA = new Angle(Math.toRadians(rotation));
		line3 = Geometry.rotateAround(line3, centerTop, rotationA);
		line4 = Geometry.rotateAround(line4, centerTop, rotationA);
		bot = Geometry.rotateAround(bot, centerTop, rotationA);
		
		lineSegments.add(line3);
		lineSegments.add(line4);

		circles.add(bot);
		
	}

	@Override
	public String toString() {
		return ("LeftFlipper " + identifier + " " + point.getX() + " " + point.getY() + " false");
	}
}
