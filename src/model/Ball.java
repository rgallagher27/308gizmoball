package model;

import java.awt.geom.Point2D;
import java.util.Observable;

import model.physics.Circle;
import model.physics.Vect;

public class Ball extends Observable implements iBall {

	protected Point2D.Double point;
	protected double row, column, cellWidth, cellHeight;
	protected Vect gravity;
	protected Vect velocity;
	protected Circle physicsCircle;
	protected String identifier;
	protected boolean isCaptured;
	protected double i = 0;

	public Ball(String identifier, Point2D.Double p, double row, double column, double width, double height) {
		this.point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.identifier 	= identifier;
		this.cellWidth		= width;
		this.cellHeight		= height;
		this.physicsCircle	= new Circle(this.point, this.cellWidth/4);
		this.gravity 		= new Vect(0, 25);
		this.velocity 		= new Vect(0, 0);
		this.isCaptured		= false;
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
	public void move(double deltaT) {
		if(!this.isCaptured){	
			deltaT /= 2;
			double mu  = 0.025;
			double mu2 = 0.025;
			
			double friction = 1 - mu * deltaT - mu2 * Math.abs(velocity.length()) * deltaT;
			
			Vect newVelocity = new Vect(this.velocity.x() * friction, this.velocity.y() * friction + (gravity.y() * deltaT));
			this.velocity = newVelocity;
			
			this.point.x  	= this.point.x + (deltaT * this.velocity.x());
			this.point.y	= this.point.y + (deltaT * this.velocity.y());
			
			Point2D.Double newCirclePoint = new Point2D.Double(this.point.x * this.cellWidth, this.point.y * cellHeight);
			
			this.physicsCircle = new Circle(newCirclePoint, this.getCellWidth() / 4);
		}
			this.setChanged();
			this.notifyObservers();
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
	public Circle returnBounds() {
		return this.physicsCircle;
	}

	@Override
	public Vect getVelocity() {
		return this.velocity;
	}

	@Override
	public void setVelocity(Vect v) {
		this.velocity = v;
	}

	@Override
	public boolean isCaptured() {
		return this.isCaptured;
	}

	@Override
	public void setCaptured(boolean update) {
		if(update){
			double newX = 19;
			double newY = 19;
			
			this.point = new Point2D.Double(newX, newY);

			this.isCaptured = update;
		}else{
			this.velocity = new Vect(0, -44);
			
			this.isCaptured = update;
		}
	}

}
