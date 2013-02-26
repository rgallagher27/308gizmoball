package model;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.physics.Geometry;
import model.physics.LineSegment;

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
		this.rotation		= height;
		this.identifier 	= identifier;
		this.rotation       = 0;
		this.lineSegments	= new ArrayList<LineSegment>();
		
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
		double topLX = this.point.x * this.cellWidth;
		double topLY = this.point.y * this.cellHeight;
		
		double topRX = (this.point.x + this.row) * this.cellWidth;
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (this.point.y + this.column) * this.cellHeight;
		
		double centerX = this.point.x * (this.cellWidth / 2);
		double centerY = this.point.y * (this.cellHeight / 2);
		
		this.rotatePoint(topLX, topLY, centerX, centerY);
		this.rotatePoint(topRX, topRY, centerX, centerY);
		this.rotatePoint(bottomLX, bottomLY, centerX, centerY);
		
		lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		lineSegments.add(new LineSegment(bottomLX, bottomLY, topRX, topRY));
		
	}
	
	private Point2D.Double rotatePoint(double x, double y, double cX, double cY)
	{
		double[] pt = {x, y};
		AffineTransform.getRotateInstance(Math.toRadians(this.rotation), cX, cY).transform(pt, 0, pt, 0, 1);
		
		return new Point2D.Double(pt[0], pt[1]);
		
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
