package model;

import java.awt.Point;
import java.util.Observable;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.Vect;

public class CircleBumper extends Observable implements iGizmo {

	protected Point point;
	protected double row, column, cellWidth, cellHeight;
	protected String identifier;
	protected Circle physicsCircle;
	
	public CircleBumper(String identifier, Point p, double row, double column, double width, double height) {
		this.point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.identifier 	= identifier;
		this.cellWidth		= width;
		this.cellHeight		= height;
		
		this.physicsCircle = new Circle(new Vect(this.point.x * this.cellWidth, this.point.y * this.cellHeight), this.cellWidth / 2);
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
		//unneeded
		return 0;
	}

	@Override
	public void setRotation(double r) {
		//unneeded
	}

	@Override
	public void move() {
		//unneeded
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

	@Override
	public double timeUntilCollision(iBall ball) {
		//return 1000;
		return Geometry.timeUntilCircleCollision(this.physicsCircle, ball.returnBounds(), ball.getVelocity());
	}

	@Override
	public void collide(iBall ball) {
		ball.setVelocity(
					Geometry.reflectCircle(this.physicsCircle.getCenter(), ball.returnBounds().getCenter(), ball.getVelocity())
				);
	}

}
