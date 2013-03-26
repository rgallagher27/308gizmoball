package model;

import java.awt.Point;

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
	public void move()
	{
		if(active){
			if(rotation > maxRotation){
				rotation -= rotationIncrement;
				if(rotation > maxRotation){
					rotation = maxRotation;
				}
				angularVel = -angularVel;  // these may be wrong
				fillLineSegments();
			}
		}else{
			if(rotation < minRotation){
				rotation += rotationIncrement;
				if(rotation < minRotation){
					rotation = minRotation;
				}
				angularVel = +angularVel; // these may be wrong + / - dependant on direction.
				fillLineSegments();
			}
		}
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
		
		/*double topLX = (point.getX() * cellWidth) - (cellWidth / 2);
		double topLY = (point.getY() * cellHeight) - (cellHeight / 2);
		
		double topRX = (point.getX() * cellWidth) + (rowWidth * cellWidth) - (cellWidth );
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (point.getY() * cellHeight) + (columnHeight * cellHeight) - (cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		double centerX = topLX - topRX;
		double centerY = topLY - bottomLY;
		*/
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
		
		
	//	LineSegment line1 = new LineSegment(topLX, topLY, topRX, topRY);
	//	LineSegment line2 = new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY);
		LineSegment line3 = new LineSegment(topLX, topLY, bottomLX, bottomLY);
		LineSegment line4 = new LineSegment(topRX, topRY, bottomRX, bottomRY);
		
		/*Geometry.rotateAround(line1, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(rotation))
			 );

		Geometry.rotateAround(line2, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(rotation))
			);
		
		Geometry.rotateAround(line3, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(rotation))
			);
		
		Geometry.rotateAround(line4, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(rotation))
			);
		*/
		Angle rotationA = new Angle(Math.toRadians(rotation));
		line3 = Geometry.rotateAround(line3, centerTop, rotationA);
		line4 = Geometry.rotateAround(line4, centerTop, rotationA);
		bot = Geometry.rotateAround(bot, centerTop, rotationA);
		
		//lineSegments.add(line1);  -- remove top line
		//lineSegments.add(line2);  -- remove bot line, inside circle.
		lineSegments.add(line3);
		lineSegments.add(line4);
		
		//circles.add(top);
		circles.add(bot);
		
		/*this.lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		this.lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		this.lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		this.lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		this.circles.add(new Circle(topLX, topLY, 0));
		
		this.circles.add(new Circle(bottomLX, bottomLY, 0));
		
		this.circles.add(new Circle(topRX, topRY, 0));
		
		this.circles.add(new Circle(bottomRX, bottomRY, 0));*/
	}

	@Override
	public String toString() {
		return ("LeftFlipper " + identifier + " " + point.getX() + " " + point.getY() + " false");
	}
}
