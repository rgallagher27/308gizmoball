package model;

import java.awt.Point;
import java.util.Observable;

public class Flipper extends Observable implements iGizmo {
	
	protected Point point;
	protected double width, height, rotation, maxRotation, minRotation;
	protected double rotationIncrement;
	protected boolean active;
	protected String identifier;

	public Flipper(String identifier, Point p, double width, double height) {
		this.point 				= p;
		this.height 			= height;
		this.width 				= width;
		this.rotation 			= 0;
		this.rotationIncrement 	= 10;
		this.active 			= false;
		this.identifier 		= identifier;
	}
	
	public void toggleFlipper(boolean b)
	{
		this.active = b;
	}

	@Override
	public Point getLocation() 
	{
		return this.point;
	}

	@Override
	public void setLocation(Point p) 
	{	
		this.point = p;
	}

	@Override
	public double getWidth() 
	{
		return this.width;
	}

	@Override
	public void setWidth(double w) 
	{
		this.width = w;
	}

	@Override
	public double getHeight() 
	{
		return this.height;
	}

	@Override
	public void setHeight(double h) 
	{
		this.height = h;
	}

	@Override
	public void move() {
		/*
		 * Implemented in extended classes
		 */
	}
	
	public double getRotation()
	{
		return this.rotation;
	}

	@Override
	public void setRotation(double r) {
		/*
		 * Implemented in extended classes
		 */
	}

	@Override
	public String getIdentifier() {
		return this.identifier;
	}
}
