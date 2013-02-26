package model;

import java.awt.geom.Point2D;
import java.util.Observable;

import model.physics.Circle;
import model.physics.Vect;

public class Ball extends Observable implements iBall {

	protected Point2D.Double point;
	protected double row, column, cellWidth, cellHeight;
	protected Vect gravity;
	protected Vect friction;
	protected Vect velocity;
	protected Circle physicsCircle;
	protected String identifier;
	protected double i = 0;

	public Ball(String identifier, Point2D.Double p, double row, double column, double width, double height) {
		this.point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.identifier 	= identifier;
		this.cellWidth		= width;
		this.cellHeight		= height;
		this.physicsCircle	= new Circle(this.point, this.cellWidth/4);
		this.gravity 		= new Vect(0, ((double)25/24));
		this.velocity 		= new Vect(0, -1.4);
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
		
		double tick = ((double) 1) / 24;
		
		double ytemp = this.velocity.y() * (1 - (0.025 * tick) - (0.025 * Math.abs(this.velocity.y())));
		double xtemp = this.velocity.x() * (1 - (0.025 * tick) - (0.025 * Math.abs(this.velocity.x())));
		
		System.out.println(xtemp +  " : " + ytemp);
		
		this.friction = new Vect(xtemp, ytemp);
		
	    //this.velocity = this.velocity.plus(this.gravity);
	    
		//this.velocity = this.velocity.minus(this.friction);
	    
		Vect currentPos = new Vect(point);
			 currentPos = currentPos.plus(this.velocity);
			 currentPos = currentPos.plus(this.gravity);
		
		this.point.x  	= currentPos.x();
		this.point.y	= currentPos.y();
		
		//System.out.println(this.velocity.toString());
		
		Point2D.Double newCirclePoint = new Point2D.Double(this.point.x * this.cellWidth, this.point.y * cellHeight);
		
		this.physicsCircle = new Circle(newCirclePoint, this.getCellWidth() / 4);
		
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

	@Override
	public Circle returnBounds() {
		// TODO Auto-generated method stub
		return this.physicsCircle;
	}

	@Override
	public Vect getVelocity() {
		// TODO Auto-generated method stub
		return this.velocity;
	}

	@Override
	public void setVelocity(Vect v) {
		this.velocity = v;
	}

}
