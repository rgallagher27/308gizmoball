package model;

import java.awt.Point;
import java.util.List;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.LineSegment;

public class Gizmo implements iGizmo {
	
	protected GizPoint point;
	protected double rowWidth, columnHeight, cellWidth, cellHeight;
	protected double rotation, rotationIncrement;
	protected List<LineSegment> lineSegments;
	protected List<Circle> circles;
	protected String identifier;

	public Gizmo() {
		rotation = 0;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public GizPoint getLocation() {
		return point;
	}

	@Override
	public void setLocation(GizPoint p) {
		point = p;
	}

	@Override
	public double getRowWidth() {
		return rowWidth;
	}

	@Override
	public void setRowWidth(double w) {
		rowWidth = w;
	}

	@Override
	public double getColumnHeight() {
		return columnHeight;
	}

	@Override
	public void setColumnHeight(double h) {
		columnHeight = h;
	}

	@Override
	public double getCellWidth() {
		return cellWidth;
	}

	@Override
	public void setCellWidth(double w) {
		cellWidth = w;
	}

	@Override
	public double getCellHeight() {
		return cellHeight;
	}

	@Override
	public void setCellHeight(double h) {
		cellHeight = h;
	}

	@Override
	public double getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(double r) {
		rotation = r;
	}

	@Override
	public double timeUntilCollision(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		
		for(LineSegment l : lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		for(Circle c : circles){
			newMin = Geometry.timeUntilCircleCollision(c, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		return min;
	}

	@Override
	public void collide(iBall ball) {
		if(ball.equals(null))return;
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		LineSegment closestLine = null;
		Circle closestCircle = null;
		
		for(LineSegment l : lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				min = newMin;
				closestLine = l;
			}
		}
		for(Circle c : circles){
			newMin = Geometry.timeUntilCircleCollision(c, ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
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
		
	}
}
