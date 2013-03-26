package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.LineSegment;

public class Gizmo implements iGizmo {
	
	protected GizPoint point;
	protected double rowWidth, columnHeight, cellWidth, cellHeight;
	protected double rotation, rotationVelocity;
	protected List<LineSegment> lineSegments;
	protected List<Circle> circles;
	protected String identifier;
	protected List<iGizmo> triggers;
	protected int width, height;
	protected Color colour;
	
	public Gizmo() {
		rotation = 0;
		triggers = new LinkedList<iGizmo>();
		width    = 0;
		height   = 0;
		colour   = this.setColour();
	}
	
	

	public int getTriggerCount(){
		return triggers.size();
	}
	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public GizPoint getLocation() {
		return point;
	}

	@Override
	public void setLocation(GizPoint p) {
		point = p;
	}

	@Override
	public double getRowWidth() {
		return rowWidth;
	}

	@Override
	public void setRowWidth(double w) {
		rowWidth = w;
	}

	@Override
	public double getColumnHeight() {
		return columnHeight;
	}

	@Override
	public void setColumnHeight(double h) {
		columnHeight = h;
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
	public double getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(double r) {
		rotation = r;
	}

	@Override
	public double timeUntilCollision(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		
		for(LineSegment l : lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		for(Circle c : circles){
			newMin = Geometry.timeUntilCircleCollision(c, ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		return min;
	}

	@Override
	public void collide(iBall ball) {
		if(ball.equals(null))return;
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		LineSegment closestLine = null;
		Circle closestCircle = null;
		
		for(LineSegment l : lineSegments){
			newMin = Geometry.timeUntilWallCollision(l, ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				min = newMin;
				closestCircle = null;
				closestLine = l;
			}
		}
		for(Circle c : circles){
			newMin = Geometry.timeUntilCircleCollision(c, ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				min = newMin;
				closestLine = null;
				closestCircle = c;
			}
		}
		
		if(closestLine != null)ball.setVelocity(Geometry.reflectWall(closestLine, ball.getVelocity()));
		if(closestCircle != null)ball.setVelocity(
					Geometry.reflectCircle(closestCircle.getCenter(), ball.returnBounds().getCenter(), ball.getVelocity())
				);
		//trigger.
		this.performAction(false);
		for(iGizmo giz: triggers){
			giz.performAction(true);
			giz.move(min);
		}
	}

	@Override
	public void performAction(boolean a) {
		this.setColour();
	}

	@Override
	public void move(double Delta_T) {
		
	}

	@Override
	public void addTrigger(iGizmo giz) {
		triggers.add(giz);
		
	}
	
	public ArrayList<iGizmo> getTriggers() {
		return new ArrayList<iGizmo>(triggers);
	}

	@Override
	public void removeTrigger(iGizmo giz) {
		triggers.remove(giz);
		
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	public void rotate(){
		rotation += 90;
		if(rotation > 360){
			rotation = 0;
		}
		setRotation(rotation);
	}

	@Override
	public List<LineSegment> getSegments() {
		return lineSegments;
	}

	@Override
	public List<Circle> getCircles() {
		return circles;
	}



	@Override
	public Color getColour() {
		return this.colour;
	}
	
	protected Color setColour()
	{
		Random rand = new Random();
		return this.colour = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}
}
