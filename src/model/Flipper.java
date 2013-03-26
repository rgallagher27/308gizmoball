package model;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.physics.Circle;
import model.physics.Geometry;
import model.physics.LineSegment;
import model.physics.Vect;

public class Flipper extends Gizmo implements iGizmo {
	
	public static final String _TYPE = "F";
	
	protected double maxRotation, minRotation;
	protected boolean active;
	protected Vect rotationCenter;
	protected double flipperVelocity, angularVel;
	protected Circle nonRotationalCircle;
	protected final double DELTA_T = ((double)1) /30;

	public Flipper(String identifier, GizPoint p, double row, double column, double width, double height) {
		point 					= p;
		columnHeight			= column;
		cellWidth				= width;
		cellHeight				= height;
		rowWidth				= row;
		rotation 				= 0;
		rotationVelocity 		= 1000;
		lineSegments 			= new ArrayList<LineSegment>();
		circles					= new ArrayList<Circle>();
		active 					= false;
		
		this.identifier 		= identifier;
		this.width 				= 2;
		this.height 			= 2;
		
		url = new File("bloop_x.wav");
		try {
			audio = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getGizType() 
	{
		return Flipper._TYPE;
	}

	@Override
	public void performAction(boolean a) {
		active = a;
		super.setColour();
	}
	
	public void rotate(){
		
	}
	
	
	
	
	@Override
	public double timeUntilCollision(iBall ball) {
		double min = Double.POSITIVE_INFINITY;
		double newMin;
		
		for(LineSegment l : lineSegments){
			newMin = Geometry.timeUntilRotatingWallCollision(l, rotationCenter, Math.toRadians(angularVel),ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		/* stationary top circle */
		newMin = Geometry.timeUntilCircleCollision(nonRotationalCircle, ball.returnBounds(), ball.getVelocity());
		if(newMin < min)min = newMin;
		
		
		for(Circle c : circles){
			newMin = Geometry.timeUntilRotatingCircleCollision(c, rotationCenter, Math.toRadians(angularVel), ball.returnBounds(), ball.getVelocity());
			if(newMin < min)min = newMin;
		}
		
		return min;
	}

	@Override
	public void collide(iBall ball) {
		if(ball.equals(null))return;
		double min 					= Double.POSITIVE_INFINITY;
		LineSegment closestLine 	= null;
		Circle closestCircle 		= null;
		boolean stationaryCircle 	= false;
		
		double newMin;
		
		if(rotation == minRotation || rotation == maxRotation){
			angularVel = 0;
		}else{
			angularVel = (flipperVelocity / 100);
		}
		
		for(LineSegment l : lineSegments){
			newMin = Geometry.timeUntilRotatingWallCollision(l, rotationCenter, Math.toRadians(angularVel),ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				min = newMin;
				closestLine = l;
			}
		}
		
		/* non rotating top circle */
		newMin = Geometry.timeUntilCircleCollision(nonRotationalCircle, ball.returnBounds(), ball.getVelocity());
		if(newMin < min){
			min = newMin;
			closestLine = null;
			closestCircle = nonRotationalCircle;
			stationaryCircle = true;
		}
		
		
		for(Circle c : circles){
			newMin = Geometry.timeUntilRotatingCircleCollision(c, rotationCenter, Math.toRadians(angularVel), ball.returnBounds(), ball.getVelocity());
			if(newMin < min){
				min = newMin;
				closestLine = null;
				closestCircle = c;
			}
		}
		 
		 
		//0.95 reflection coeff
		if(closestLine != null){
			ball.setVelocity(Geometry.reflectRotatingWall(closestLine, rotationCenter,Math.toRadians(angularVel), ball.returnBounds(), ball.getVelocity()));
		}
		if(closestCircle != null && stationaryCircle)ball.setVelocity(
					Geometry.reflectCircle(closestCircle.getCenter(), ball.returnBounds().getCenter(), ball.getVelocity()));
		if(closestCircle != null && !stationaryCircle){
			ball.setVelocity(
					Geometry.reflectRotatingCircle(closestCircle, rotationCenter, Math.toRadians(angularVel), ball.returnBounds(), ball.getVelocity())
				);
		}
		
		//trigger.
		for(iGizmo giz: triggers){
			giz.performAction(true);
			giz.move(min);
		}
		
		if(closestLine != null) System.out.println("Line Hit");
		if(closestCircle != null && stationaryCircle) System.out.println("Stat Circle Hit");
		if(closestCircle != null && !stationaryCircle) System.out.println("Moving Circle Hit");
	}
}
