package model;

import java.awt.Point;
import java.util.Observable;

public class TriangleBumper extends Observable implements iGizmo {

	protected Point point;
	protected double width, height, rotation;
	protected String identifier;

	public TriangleBumper( String identifier, Point p, double width, double height) {
		this.point 			= p;
		this.width 			= width;
		this.height 		= height;
		this.rotation		= 0;
		this.identifier 	= identifier;
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
	public double getWidth() {
		return this.width;
	}

	@Override
	public void setWidth(double w) {
		this.width = w;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	public void setHeight(double h) {
		this.height = h;
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(double r) {
		this.rotation = r;
	}

	@Override
	public void move() {
		//unneeded
	}

}
