package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.physics.Geometry;
import model.physics.LineSegment;

public class SquareBumper extends Observable implements iGizmo {
	
	protected Point point;
	protected double row, column, cellWidth, cellHeight;
	protected List<LineSegment> lineSegments;
	protected String identifier;

	public SquareBumper(String identifier, Point p, double row, double column, double width, double height) {
		this.point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.cellWidth		= width;
		this.cellHeight 	= height;
		this.identifier 	= identifier;
		
		this.lineSegments = new ArrayList<LineSegment>();
		
		this.fillLineSegments();
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
		return this.identifier;
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
		double topLX = this.point.x * this.cellWidth;
		double topLY = (this.point.y) * this.cellHeight;
		
		double topRX = (this.point.x + this.row) * this.cellWidth;
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (this.point.y + this.column) * this.cellHeight;
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;

		
		this.lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		LineSegment bottom = new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY);
		
		this.lineSegments.add(bottom);
		
		
	}

	@Override
	public double timeUntilCollision(iBall ball) {
		double min;
		double newMin;
		
		if(!this.lineSegments.isEmpty()){
			min = Geometry.timeUntilWallCollision(this.lineSegments.get(0), ball.returnBounds(), ball.getVelocity());
			
			for(LineSegment l : this.lineSegments){
				if(  ( newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity())) < min)
					min = newMin;
				
			}
		}else
			min = Double.POSITIVE_INFINITY;
			
		return min;
	}

	@Override
	public void collide(iBall ball) {

		LineSegment minLS = this.lineSegments.get(0);
		double min = Geometry.timeUntilWallCollision(this.lineSegments.get(0), ball.returnBounds(), ball.getVelocity());
		double newMin;
		
		for(LineSegment l : this.lineSegments){
			if(  ( newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity())) < min){
				min = newMin;
				minLS = l;
			}	
		}
		
		ball.setVelocity(Geometry.reflectWall(minLS, ball.getVelocity()));
		
	}

}
