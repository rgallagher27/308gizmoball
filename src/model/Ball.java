package model;

import java.awt.geom.Point2D;
import java.util.Observable;

public class Ball extends Observable implements iBall {

	protected Point2D.Double point;
	protected double row, column, cellWidth, cellHeight;
	protected double velocityX, velocityY;
	protected String identifier;

	public Ball(String identifier, Point2D.Double p, double row, double column, double width, double height) {
		this.point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.identifier 	= identifier;
		this.cellWidth		= width;
		this.cellHeight		= height;
		
		this.velocityY = -50;
	}

	@Override
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public Point2D.Double getLocation() {
		return this.point;
	}

	@Override
	public void setLocation(Point2D.Double p) {
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
		//unneeded
		return 0;
	}

	@Override
	public void setRotation(double r) {
		//unneeded
	}

	@Override
	public void move() {
		double t = (double) 1 / (double) 24; 
		
		System.out.println(t);
		
		double yVel = this.velocityY;
		double xVel = this.velocityX;
		yVel = yVel + 25;
		double ytemp = yVel * (1 - (0.025 * t) - (0.025 * Math.abs(yVel)));
		double xtemp = xVel * (1 - ((0.025 / 24 ) * t) - ((0.025 * this.cellWidth / 2) * Math.abs(xVel)));
		//ball.setVelocity(xtemp, ytemp);
		this.velocityX = xtemp;
		this.velocityY = ytemp;
		
		this.point.setLocation(this.point.x + this.velocityX, this.point.y + 0.6601950130931442);
		
		//System.out.println(xtemp + " : " + ytemp);
		
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public double getCellWidth() {
		// TODO Auto-generated method stub
		return this.cellWidth;
	}

	@Override
	public void setCellWidth(double w) {
		this.cellWidth = w;
	}

	@Override
	public double getCellHeight() {
		// TODO Auto-generated method stub
		return this.cellHeight;
	}

	@Override
	public void setCellHeight(double h) {
		this.cellHeight = h;
	}

}
