package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import exception.CannotRotateException;

public class Wall implements iGizmo {

	private int boardX;
	private int boardY;
	public Wall() {
		// TODO Auto-generated constructor stub
	}

	public Wall(int boardx, int boardy) {
		boardX = boardx;
		boardY = boardy;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Wall";
	}

	@Override
	public void setLocation(GizPoint p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GizPoint getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GizPoint getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTrigger(iGizmo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTrigger(iGizmo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<iGizmo> getTriggers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rotate() throws CannotRotateException {
		// TODO Auto-generated method stub
		
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
	public List<LineSegment> getBoundSegments() {
		ArrayList<LineSegment> boundSegments = new ArrayList<LineSegment>();
		boundSegments.add(new LineSegment(0,0, boardX, 0));
		boundSegments.add(new LineSegment(0, 0, 0, boardY));
		boundSegments.add(new LineSegment(boardX, 0, boardX, boardY));
		boundSegments.add(new LineSegment(0, boardY, boardX, boardY));
		return boundSegments;
	}
	
	public double timeTillCol(iBall ball) {
		Circle circle = new Circle(ball.getLocation().getX(), ball.getLocation().getY(), ball.getRadius());
		Vect vel = new Vect(ball.getVelocityX(), ball.getVelocityY());
		
		double min = Geometry.timeUntilWallCollision(getBoundSegments().get(0), circle, vel);
		
		for(LineSegment ls : getBoundSegments()){
			if(Geometry.timeUntilWallCollision(ls, circle, vel) < min){
				min = Geometry.timeUntilWallCollision(ls, circle, vel);
			}
		}
		return min;
	}

	@Override
	public iBall collide(iBall ball) {
		iBall newBall = ball;
		System.out.println("COLLIDE WITH A WALL!");
		Circle circle = new Circle(ball.getLocation().getX(), ball.getLocation().getY(), ball.getRadius());
		Vect vel = new Vect(ball.getVelocityX(), ball.getVelocityY());
		
		LineSegment minLs = getBoundSegments().get(0);
		
		double min = Geometry.timeUntilWallCollision(getBoundSegments().get(0), circle, vel);
		
		for(LineSegment ls : getBoundSegments()){
			if(Geometry.timeUntilWallCollision(ls, circle, vel) < min){
				min = Geometry.timeUntilWallCollision(ls, circle, vel);
				minLs = ls;
			}
		}
		
		Vect afterRef = Geometry.reflectWall(minLs, vel, 1.0);
		newBall.setVelocity(afterRef.x(), afterRef.y());
		
		return newBall;
	}



}
