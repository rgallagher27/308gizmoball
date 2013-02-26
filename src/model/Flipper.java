package model;

import java.awt.Point;
import java.util.Observable;

public class Flipper extends Observable implements iGizmo {
	
	protected Point point;
	protected double row, column, cellWidth, cellHeight, rotation, maxRotation, minRotation;
	protected double rotationIncrement;
	protected boolean active;
	protected String identifier;

	public Flipper(String identifier, Point p, double row, double column, double width, double height) {
		this.point 				= p;
		this.column 			= column;
		this.cellWidth			= width;
		this.cellHeight			= height;
		this.row 				= row;
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
	public double getRowWidth() 
	{
		return this.row;
	}

	@Override
	public void setRowWidth(double w) 
	{
		this.row = w;
	}

	@Override
	public double getColumnHeight() 
	{
		return this.column;
	}

	@Override
	public void setColumnHeight(double h) 
	{
		this.column = h;
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
	public double timeUntilCollision(iBall ball) {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public void collide(iBall ball) {
		// TODO Auto-generated method stub
		
	}
}
