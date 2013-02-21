package model;

import java.awt.Point;
import java.util.List;

public class Ball implements iBall {
	
	private String _id;
	private Point location;
	private Point bounds;
	private double velocityX;
	private double velocityY;
	private int width;
	private int height;
	

	public Ball() {
		// TODO Auto-generated constructor stub
	}

	public Ball(String gizmoName, float x, float y, double vx, double vy) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setVelocity(double vx, double vy) {
		velocityX = vx;
		velocityY = vy;
		
	}

	@Override
	public Double getVelocityX() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getVelocityY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(float x, float y) {
		// TODO Auto-generated method stub
		
	}



}
