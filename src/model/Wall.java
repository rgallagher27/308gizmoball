package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.LineSegment;

public class Wall extends Observable implements iGizmo {
	
	protected double cellWidth, cellHeight;
	protected GameGrid gmGrid;
	protected List<LineSegment> lineSegments;
	protected List<Circle> circleCorners;

	public Wall(double width, double height, GameGrid gmGrid) {
		this.cellWidth		= width;
		this.cellHeight 	= height;
		this.gmGrid			= gmGrid;
		
		this.lineSegments = new ArrayList<LineSegment>();
		this.circleCorners = new ArrayList<Circle>();
		
		this.fillLineSegments();
		
	}

	@Override
	public Point getLocation() {
		return null;
	}

	@Override
	public void setLocation(Point p) {
		//nope
	}

	@Override
	public double getRowWidth() {
		return 0;
	}

	@Override
	public void setRowWidth(double w) {
		//nope
	}

	@Override
	public double getColumnHeight() {
		return 0;
	}

	@Override
	public void setColumnHeight(double h) {
		//nope
	}

	@Override
	public double getRotation() {
		return 0;
	}

	@Override
	public void setRotation(double r) {
		//Unneeded 
	}

	@Override
	public void move() {
		//unneeded
	}

	@Override
	public String getIdentifier() {
		return "Wall";
	}

	@Override
	public double getCellWidth() {
		return this.cellWidth;
	}

	@Override
	public void setCellWidth(double w) {
		this.cellWidth = w;
	}

	@Override
	public double getCellHeight() {
		return this.cellHeight;
	}

	@Override
	public void setCellHeight(double h) {
		this.cellHeight = h;
	}
	
	
	private void fillLineSegments()
	{
		double topLX = 0 - (this.cellWidth / 2);
		double topLY = 0 - (this.cellHeight / 2);
		
		double topRX = (this.gmGrid.rows * this.cellWidth) - (this.cellWidth / 2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (this.gmGrid.columns * this.cellHeight) - (this.cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		this.lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		this.lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		this.lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		this.lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		this.circleCorners.add(new Circle(topLX, topLY, 0));
		
		this.circleCorners.add(new Circle(bottomLX, bottomLY, 0));
		
		this.circleCorners.add(new Circle(topRX, topRY, 0));
		
		this.circleCorners.add(new Circle(bottomRX, bottomRY, 0));
		
		
	}

	@Override
	public double timeUntilCollision(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		
		for(LineSegment l : this.lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		for(Circle c : this.circleCorners){
			newMin = Geometry.timeUntilCircleCollision(c, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		return min;
	}

	@Override
	public void collide(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		LineSegment closestLine = null;
		Circle closestCircle = null;
		
		for(LineSegment l : this.lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				min = newMin;
				closestLine = l;
			}
		}
		for(Circle c : this.circleCorners){
			newMin = Geometry.timeUntilCircleCollision(c, ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				System.out.println(min);
				min = newMin;
				closestLine = null;
				closestCircle = c;
			}
		}
		
		if(closestLine != null)ball.setVelocity(Geometry.reflectWall(closestLine, ball.getVelocity()));
		if(closestCircle != null)ball.setVelocity(
					Geometry.reflectCircle(closestCircle.getCenter(), ball.returnBounds().getCenter(), ball.getVelocity())
				);
	}

}
