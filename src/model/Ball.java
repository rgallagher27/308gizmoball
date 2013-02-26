package model;

import java.util.List;

public class Ball implements iBall {
	
	private String id;
	private boolean released;
	private BallPoint location;
	private double velocityX;
	private double velocityY;
	private double radius;
	private int immunity;
	
	public Ball(String gizmoName, float x, float y, double vx, double vy, boolean released) {
		radius = 0.25;
		location = new BallPoint(x, y);
		velocityX = vx;
		velocityY = vy;
		id = gizmoName;
		this.released = released;
	}

	public double getRadius(){
		return radius;
	}
	
	public void setImmunity(int b){
		immunity = 5;
	}
	
	public int getImmunity(){
		return immunity;
	}
	
	public String getName(){
		return id;
	}
	public void setReleased(boolean rel){
		released = rel;
	}
	public boolean getReleased(){
		return released;
	}
	@Override
	public void setVelocity(double vx, double vy) {
		velocityX = vx;
		velocityY = vy;
	}

	@Override
	public double getVelocityX() {
		return velocityX;
	}

	@Override
	public double getVelocityY() {
		return velocityY;
	}

	@Override
	public void setLocation(float x, float y) {
		location.setX(x);
		location.setY(y);
		
	}

	@Override
	public BallPoint getLocation() {
		return location;
	}



}
