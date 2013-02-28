package model;

import java.awt.Point;
import java.util.List;
import java.util.Observable;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.LineSegment;

public class Gizmo extends Observable implements iGizmo {
	
	protected Point point;
	protected double rowWidth, columnHeight, cellWidth, cellHeight;
	protected double rotation, rotationIncrement;
	protected List<LineSegment> lineSegments;
	protected List<Circle> circles;
	protected String identifier;

	public Gizmo() {
		this.rotation = 0;
	}

	@Override
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public Point getLocation() {
		return this.point;
	}

	@Override
	public void setLocation(Point p) {
		this.point = p;
	}

	@Override
	public double getRowWidth() {
		return this.rowWidth;
	}

	@Override
	public void setRowWidth(double w) {
		this.rowWidth = w;
	}

	@Override
	public double getColumnHeight() {
		return this.columnHeight;
	}

	@Override
	public void setColumnHeight(double h) {
		this.columnHeight = h;
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

	@Override
	public double getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(double r) {
		this.rotation = r;
	}

	@Override
	public double timeUntilCollision(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		
		for(LineSegment l : this.lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		for(Circle c : this.circles){
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
		for(Circle c : this.circles){
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

	@Override
	public void performAction(boolean a) {
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}
}
