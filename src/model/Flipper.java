package model;


import java.util.ArrayList;

import model.physics.Circle;
import model.physics.LineSegment;

public class Flipper extends Gizmo implements iGizmo {
	
	protected double maxRotation, minRotation;
	protected boolean active;

	public Flipper(String identifier, GizPoint p, double row, double column, double width, double height) {
		point 			= p;
		columnHeight		= column;
		cellWidth			= width;
		cellHeight		= height;
		rowWidth			= row;
		rotation 			= 0;
		rotationIncrement = 10;
		this.identifier 		= identifier;
		
		lineSegments 		= new ArrayList<LineSegment>();
		circles			= new ArrayList<Circle>();
		
		active 			= false;
	}

	@Override
	public void performAction(boolean a) {
		active = a;
	}
}
