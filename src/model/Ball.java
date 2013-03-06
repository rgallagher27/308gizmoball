package model;

import java.awt.geom.Point2D;
import java.util.Observable;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.Geometry.VectPair;
import model.physics.Vect;

public class Ball extends Observable implements iBall {

	protected BallPoint point;
	protected double row, column, cellWidth, cellHeight;
	protected float gravity;
	protected Vect velocity;
	protected Circle physicsCircle;
	protected String identifier;
	protected boolean isCaptured;
	protected double i = 0;

	public Ball(String identifier, BallPoint p, double row, double column, double width, double height) {
		point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.identifier 	= identifier;
		cellWidth		= width;
		cellHeight		= height;
		physicsCircle	= new Circle(point.getX(), point.getY(), cellWidth/4);
		gravity 		= (float)1/25;
		velocity 		= new Vect(0, 0);
		isCaptured		= false;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public BallPoint getLocation() {
		return point;
	}

	@Override
	public void setLocation(BallPoint p) {
		point = p;
	}

	@Override
	public double getRowWidth() {
		return row;
	}

	@Override
	public void setRowWidth(double w) {
		row = w;
	}

	@Override
	public double getColumnHeight() {
		return column;
	}

	@Override
	public void setColumnHeight(double h) {
		column = h;
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
	public void move(float deltaT) {
		if(!isCaptured){	
			deltaT /= 2;
			float mu  = 0.015F;
			float mu2 = 0.015F;
			
			float friction = (float) (1 - mu * deltaT - mu2 * Math.abs(velocity.length()) * deltaT);
			
			Vect newVelocity = new Vect(velocity.x() * friction, velocity.y() * friction + gravity * deltaT);
			velocity = newVelocity;
			
			point.setX(point.getX() + (deltaT * (float)velocity.x()));
			point.setY(point.getY() + (deltaT * (float)velocity.y()));
			
			BallPoint newCirclePoint = new BallPoint(point.getX() * (float)cellWidth, point.getY() * (float)cellHeight);
			
			physicsCircle = new Circle(newCirclePoint.getX(),newCirclePoint.getY(), getCellWidth() / 4);
		}
			setChanged();
			notifyObservers();
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
	public Circle returnBounds() {
		return physicsCircle;
	}

	@Override
	public Vect getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(Vect v) {
		velocity = v;
	}

	@Override
	public boolean isCaptured() {
		return isCaptured;
	}

	@Override
	public void setCaptured(boolean update) {
		if(update){
			float newX = 19;
			float newY = 19;
			
			point = new BallPoint(newX, newY);

			isCaptured = update;
		}else{
			isCaptured = update;
		}
	}

	@Override
	public double timeUntilCollision(iBall ball)
	{
		return Geometry.timeUntilBallBallCollision(physicsCircle, velocity, ball.returnBounds(), ball.getVelocity());
	}

	@Override
	public void collide(iBall ball)
	{
		if(ball.equals(null))return;
		VectPair velPer = Geometry.reflectBalls(physicsCircle.getCenter(), 1, getVelocity(), ball.returnBounds().getCenter(), 1, ball.getVelocity());
		setVelocity(velPer.v1);
		ball.setVelocity(velPer.v2);
	}

}
