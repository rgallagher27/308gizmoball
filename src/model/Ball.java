package model;

import java.awt.Point;
import java.util.List;

public class Ball implements iGizmo {
	
	private String _id;
	private Point location;
	private Point bounds;
	private Double velocityX;
	private Double velocityY;
	private int width;
	private int height;
	

	public Ball() {
		// TODO Auto-generated constructor stub
	}

	public Ball(String gizmoName, float x, float y, float vx, float vy) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTrigger(iGizmo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTrigger(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTrigger(iGizmo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTrigger(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<iGizmo> getTriggers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelocity(Double vx, Double vy) {
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
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLocation(float x, float y) {
		// TODO Auto-generated method stub
		
	}



}
