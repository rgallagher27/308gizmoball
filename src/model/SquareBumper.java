package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.physics.Circle;
import model.physics.LineSegment;

public class SquareBumper extends Gizmo implements iGizmo {
	
	public static final String _TYPE = "S";

	public SquareBumper(String identifier, GizPoint p, double rowWidth, double columnHeight, double cellWidth, double cellHeight) {
		super();
		point 				= p;
		this.rowWidth 		= rowWidth;
		this.columnHeight   = columnHeight;
		this.cellWidth		= cellWidth;
		this.cellHeight 	= cellHeight;
		this.identifier 	= identifier;
		height 				= 1;
		width 				= 1;
		
		lineSegments 		= new ArrayList<LineSegment>();
		circles 			= new ArrayList<Circle>();
		
		url = new File("boing_x.wav");
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
		
		fillLineSegments();
		
	}
	
	@Override
	public String getGizType() 
	{
		return SquareBumper._TYPE;
	}

	private void fillLineSegments()
	{
		double topLX = point.getX() * cellWidth;
		double topLY = point.getY() * cellHeight;
		
		double topRX = topLX + (rowWidth * cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = topLY + (columnHeight * cellHeight);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		circles.add(new Circle(topLX, topLY, 0));
		
		circles.add(new Circle(bottomLX, bottomLY, 0));
		
		circles.add(new Circle(topRX, topRY, 0));
		
		circles.add(new Circle(bottomRX, bottomRY, 0));
		
		
	}
	
	public void setLocation(GizPoint p) {
		point = p;
		fillLineSegments();
	}

	@Override
	public String toString() {
		return ("Square " + identifier + " " + point.getX() + " " + point.getY());
	}

}
