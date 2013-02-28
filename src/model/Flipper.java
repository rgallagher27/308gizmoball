package model;

import java.awt.Point;
import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;

public class Flipper extends Gizmo implements iGizmo {
	
	protected double maxRotation, minRotation;
	protected boolean active;

	public Flipper(String identifier, Point p, double row, double column, double width, double height) {
		super.point 			= p;
		super.columnHeight		= column;
		super.cellWidth			= width;
		super.cellHeight		= height;
		super.rowWidth			= row;
		super.rotation 			= 0;
		super.rotationIncrement = 10;
		super.identifier 		= identifier;
		
		super.lineSegments 		= new ArrayList<LineSegment>();
		super.circles			= new ArrayList<Circle>();
		
		this.active 			= false;
	}

	@Override
	public void performAction(boolean a) {
		this.active = a;
	}
}
