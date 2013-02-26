package model;

import java.awt.Point;
import java.util.Observable;

public class SquareBumper extends Observable implements iGizmo {
	
	protected Point point;
	protected double width, height;
	protected String identifier;

	public SquareBumper(String identifier, Point p, double width, double height) {
		this.point 			= p;
		this.width 			= width;
		this.height 		= height;
		this.identifier 	= identifier;
	}

	@Override
	public Point getLocation() {
		return this.point;
	}

	@Override
	public void setLocation(Point p) {
		this.point = p;
	}

	@Override
	public double getRowWidth() {
		return this.width;
	}

	@Override
	public void setRowWidth(double w) {
		this.width = w;
	}

	@Override
	public double getColumnHeight() {
		return this.height;
	}

	@Override
	public void setColumnHeight(double h) {
		this.height = h;
	}

	@Override
	public double getRotation() {
		return 0;
	}

	@Override
	public void setRotation(double r) {
		//Unneeded 
	}

	@Override
	public void move() {
		//unneeded
	}

	@Override
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public double getCellWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCellWidth(double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getCellHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCellHeight(double h) {
		// TODO Auto-generated method stub
		
	}

}
