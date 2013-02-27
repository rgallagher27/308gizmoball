package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.physics.Angle;
import model.physics.Geometry;
import model.physics.LineSegment;
import model.physics.Vect;

public class TriangleBumper extends Observable implements iGizmo {

	protected Point point;
	protected double row, column, cellWidth, cellHeight, rotation;
	protected List<LineSegment> lineSegments;
	protected String identifier;

	public TriangleBumper( String identifier, Point p, double row, double column, double width, double height) {
		this.point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.cellWidth		= width;
		this.cellHeight     = height;
		this.rotation		= height;
		this.identifier 	= identifier;
		this.rotation       = 0;
		
		this.fillLineSegments();
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
		return this.row;
	}

	@Override
	public void setRowWidth(double w) {
		this.row = w;
	}

	@Override
	public double getColumnHeight() {
		return this.column;
	}

	@Override
	public void setColumnHeight(double h) {
		this.column = h;
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(double r) {
		this.rotation = r;
		this.fillLineSegments();
	}

	@Override
	public void move() {
		//unneeded
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
		this.lineSegments   = null;
		this.lineSegments	= new ArrayList<LineSegment>();
		
		double topLX = (this.point.x * this.cellWidth) - (this.cellWidth / 2);
		double topLY = (this.point.y * this.cellHeight) - (this.cellHeight / 2);
		
		double topRX = (this.point.x * this.cellWidth) + (this.row * this.cellWidth) - (this.cellWidth / 2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (this.point.y * this.cellHeight) + (this.column * this.cellHeight) - (this.cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		double centerX = (this.point.x * this.cellWidth)  + (this.cellWidth  / 2);
		double centerY = (this.point.y * this.cellHeight) + (this.cellHeight / 2);
		
		switch ((int)this.rotation) {
		case 0:
			lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
			lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
			lineSegments.add(new LineSegment(bottomLX, bottomLY, topRX, topRY));
			break;
		case 90:
			lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
			lineSegments.add(new LineSegment(topRX, topRY,bottomRX, bottomRY));
			lineSegments.add(new LineSegment(topLX, topLY, bottomRX, bottomRY));

		default:
			break;
		}
		
	}

	@Override
	public double timeUntilCollision(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		
		for(LineSegment l : this.lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		return min;
	}

	@Override
	public void collide(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		LineSegment closestLine = null;
		
		for(LineSegment l : this.lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				System.out.println(min);
				min = newMin;
				closestLine = l;
			}
		}
		
		if(closestLine != null)ball.setVelocity(Geometry.reflectWall(closestLine, ball.getVelocity()));
	}

}
