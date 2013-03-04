package model;

import java.awt.Point;

import model.physics.Angle;
import model.physics.Circle;
import model.physics.Geometry;
import model.physics.LineSegment;
import model.physics.Vect;

public class LeftFlipper extends Flipper {

	public LeftFlipper(String identifier, Point p, double row, double column, double width, double height) {
		super(identifier, p, row, column, width, height);
		super.minRotation = 0;
		super.maxRotation = -90;
		
		this.fillLineSegments();
	}

	@Override
	public void move()
	{
		if(super.active){
			if(super.rotation > maxRotation){
				super.rotation -= super.rotationIncrement;
				this.fillLineSegments();
			}
		}else{
			if(super.rotation < minRotation){
				super.rotation += super.rotationIncrement;
				this.fillLineSegments();
			}
		}
	}
	
	@Override
	public void setRotation(double r) {
		super.rotation    = -r;
		super.maxRotation -= r;
		super.minRotation -= r;
	}
	
	private void fillLineSegments()
	{
		this.lineSegments.clear();
		
		double topLX = (this.point.x * this.cellWidth) - (this.cellWidth / 2);
		double topLY = (this.point.y * this.cellHeight) - (this.cellHeight / 2);
		
		double topRX = (this.point.x * this.cellWidth) + (this.rowWidth * this.cellWidth) - (this.cellWidth );
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (this.point.y * this.cellHeight) + (this.columnHeight * this.cellHeight) - (this.cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		double centerX = topLX - topRX;
		double centerY = topLY - bottomLY;
		
		LineSegment line1 = new LineSegment(topLX, topLY, topRX, topRY);
		LineSegment line2 = new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY);
		LineSegment line3 = new LineSegment(topLX, topLY, bottomLX, bottomLY);
		LineSegment line4 = new LineSegment(topRX, topRY, bottomRX, bottomRY);
		
		Geometry.rotateAround(line1, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(this.rotation))
			 );

		Geometry.rotateAround(line2, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(this.rotation))
			);
		
		Geometry.rotateAround(line3, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(this.rotation))
			);
		
		Geometry.rotateAround(line4, 
				new Vect(centerX, centerY), 
				new Angle(Math.toRadians(this.rotation))
			);
		
		this.lineSegments.add(line1);
		this.lineSegments.add(line2);
		this.lineSegments.add(line3);
		this.lineSegments.add(line4);
		
		/*this.lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		this.lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		this.lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		this.lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		this.circles.add(new Circle(topLX, topLY, 0));
		
		this.circles.add(new Circle(bottomLX, bottomLY, 0));
		
		this.circles.add(new Circle(topRX, topRY, 0));
		
		this.circles.add(new Circle(bottomRX, bottomRY, 0));*/
	}
}
