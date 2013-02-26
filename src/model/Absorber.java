package model;

import java.util.ArrayList;
import java.util.List;

import exception.CannotRotateException;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Absorber implements iGizmo {
	
	private List<iBall> storedBalls = null;
	private List<iGizmo> triggers = null;
	private String id;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int width;
	private int height;
	private List<LineSegment> boundSegments;


	public Absorber(String gizmoName, int x1, int y1, int x2, int y2) {
		id = gizmoName;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		height = Math.abs(y1 - y2);
		width = Math.abs(x1 - x2);
		calcBoundSegments();
	}

	public String getName(){
		return id;
	}
	private void calcBoundSegments(){
		boundSegments = new ArrayList<LineSegment>();
		//x1=0 y1 = 19 x2 = 20 y2 = 20
		//boundSegments.add(new LineSegment(x1,y1, x2, y1)); // -1 gap for ball
		//boundSegments.add(new LineSegment(x1, y1, x1, y2));
		//boundSegments.add(new LineSegment(x2, y1, x2, y2));
		//boundSegments.add(new LineSegment(x1, y2, x2, y2));
	}
	
	
	@Override
	public void doAction() {
		iBall ball;
		if(storedBalls.size() > 0){
			ball = storedBalls.remove(0);
			ball.setLocation(ball.getLocation().getX(), ball.getLocation().getY());
			ball.setVelocity(-10, -10);
			ball.setReleased(true);
		}
	}

	@Override
	public void setLocation(GizPoint p) {
		x1 = p.getX();
		y1 = p.getY();
	}

	@Override
	public GizPoint getLocation() {
		return new GizPoint(x1, y1);
	}

	public List<LineSegment> getBoundSegments() {
		// TODO Auto-generated method stub
		return boundSegments;
	}

	@Override
	public void addTrigger(iGizmo t) {
		if(triggers == null) triggers = new ArrayList<iGizmo>();
		triggers.add(t);
		
	}

	@Override
	public void removeTrigger(iGizmo t) {
		if(triggers.size() > 0){
			triggers.remove(t);
		}
		
	}

	@Override
	public List<iGizmo> getTriggers() {
		
		return triggers;
	}

	@Override
	public void rotate() throws CannotRotateException {
		throw new CannotRotateException("Absorbers cannot be rotated");
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	public void addBall(iBall ball){
		if(storedBalls == null) storedBalls = new ArrayList<iBall>();
		storedBalls.add(ball);
		ball.setReleased(false);
		ball.setImmunity(1);
	}

	public void removeStoredBall(String ballName) {
		if(storedBalls.size() > 0){
			for(iBall ball : storedBalls){
				if(ball.getName().equals(ballName)){
					storedBalls.remove(ball);
					break;
				}
			}
		}
		
	}

	@Override
	public GizPoint getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double timeTillCol(iBall ball) {
		Circle circle = new Circle(ball.getLocation().getX(), ball.getLocation().getY(), ball.getRadius());
		Vect vel = new Vect(ball.getVelocityX(), ball.getVelocityY());
		double min;
		if(getBoundSegments().size() > 0){
		min = Geometry.timeUntilWallCollision(getBoundSegments().get(0), circle, vel);
		
		for(LineSegment ls : getBoundSegments()){
			if(Geometry.timeUntilWallCollision(ls, circle, vel) < min){
				min = Geometry.timeUntilWallCollision(ls, circle, vel);
			}
		}
		}else{
			min = Double.POSITIVE_INFINITY;
		}
		return min;
	}

	@Override
	public iBall collide(iBall ball) {
		System.out.println("COLLIDE WITH ABSORBER!");
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
		
		System.out.println(minLs.p1().x() + ":" + minLs.p1().y() + "- " + minLs.p2().x() + ":" + minLs.p2().y());
		//Vect afterRef = reflectWall(minLs, vel, 0);
		// TODO Auto-generated method stub
		ball.setLocation(((float)(getLocation().getX() + getWidth()) - 0.25F), ((float)(getLocation().getY() + getHeight()) - 0.25F));
		ball.setReleased(false);
		ball.setVelocity(0, 0);
		ball.setImmunity(1);
		addBall(ball);
		return ball;
	}

}
