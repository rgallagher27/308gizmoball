package model;

import java.awt.Point;
import java.util.Observable;

public class CircleBumper extends Observable implements iGizmo {

	protected Point point;
	protected double row, column, cellWidth, cellHeight;
	protected String identifier;
	
	public CircleBumper(String identifier, Point p, double row, double column, double width, double height) {
		this.point 			= p;
		this.row 			= row;
		this.column 		= column;
		this.identifier 	= identifier;
		this.cellWidth		= width;
		this.cellHeight		= height;
		
	}

	@Override
	public String getIdentifier() {
		return this.identifier;
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
		return this.row;
	}

	@Override
	public void setRowWidth(double w) {
		this.row = w;
	}

	@Override
	public double getColumnHeight() {
		return this.column;
	}

	@Override
	public void setColumnHeight(double h) {
		this.column = h;
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
	public void move() {
		//unneeded
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

}
