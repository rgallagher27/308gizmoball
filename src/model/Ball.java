package model;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.Geometry.VectPair;
import model.physics.Vect;

public class Ball implements iBall {

	protected BallPoint point;
	protected BallPoint startPoint;
	protected double row, column, cellWidth, cellHeight;
	protected double gravity;
	protected Vect velocity;
	protected Circle physicsCircle;
	protected String identifier;
	protected boolean isCaptured;
	protected double radius;
	protected Vect velocityOrig;
	protected boolean origCapture;

	public Ball(String identifier, BallPoint p, double row, double column, double width, double height, double velocityX, double velocityY, double gravity, boolean isCaptured) {
		point 			= p;
		startPoint		= new BallPoint(p.getX(), p.getY());
		cellWidth		= width;
		cellHeight		= height;
		velocity 		= new Vect(velocityX, velocityY);
		velocityOrig    = new Vect(velocityX, velocityY);
		origCapture 	= isCaptured;
		radius 			= 0.25F;

		this.row 		= row;
		this.column 	= column;
		this.identifier = identifier;
		this.isCaptured	= isCaptured;
		this.gravity	= gravity;
		
		physicsCircle 	= new Circle((point.getX() * cellWidth) + (cellWidth/2), (point.getY() * cellHeight) + (cellWidth/2), cellWidth/4);
	}

	public double getRadius(){
		return radius;
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
		point = new BallPoint(p.getX(), p.getY());
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
	public void move(double deltaT) {
		if(!isCaptured){	
			deltaT /= 2;
			double mu  = 0.015F;
			double mu2 = 0.015F;
			
			double friction = (1 - mu * deltaT - mu2 * Math.abs(velocity.length()) * deltaT);
			
			Vect newVelocity = new Vect(velocity.x() * friction, velocity.y() * friction + gravity * deltaT);
			velocity = newVelocity;
			
			point.setX((float)(point.getX() + (deltaT * velocity.x())));
			point.setY((float)(point.getY() + (deltaT * velocity.y())));
			
			physicsCircle = new Circle((point.getX() * cellWidth) + (cellWidth/2), (point.getY() * cellHeight) + (cellWidth/2), cellWidth/4);	
		}
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

	public String toString() {
		return ("Ball " + identifier + " " + startPoint.getX() + " " + startPoint.getY() + " " + (int)(velocityOrig.x()*25) + " " + (int)(velocityOrig.y()*25) );
	}

	@Override
	public BallPoint getOrigLocation() {

		return startPoint;
	}
	
	public Vect getOrigVelocity(){
		return velocityOrig;
	}
	
	public boolean getOrigCapture(){
		return origCapture;
	}

	@Override
	public void setgravity(double g) {
		this.gravity = g;
		
		System.err.println("Gravity set to: " + g);
	}
	
}
