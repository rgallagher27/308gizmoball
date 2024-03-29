package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.physics.Circle;
import model.physics.LineSegment;

public class TriangleBumper extends Gizmo implements iGizmo {
	
	public static final String _TYPE = "T";

	public TriangleBumper( String identifier, GizPoint p, double row, double column, double width, double height) {
		super();
		point 		= p;
		rowWidth 		= row;
		columnHeight 	= column;
		cellWidth		= width;
		cellHeight    = height;
		this.identifier 	= identifier;
		rotation		= 0;
		this.height = 1;
		this.width = 1;
		
		url = new File("boing2.wav");
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
	public void setRotation(double r) {
		rotation = r;
		fillLineSegments();
	}
	
	@Override
	public String getGizType() 
	{
		return TriangleBumper._TYPE;  
	}
	
	public void setLocation(GizPoint p) {
		point = p;
		fillLineSegments();
	}
	
	private void fillLineSegments()
	{
		lineSegments   	= null;
		circles  	= null;
		lineSegments		= new ArrayList<LineSegment>();
		circles 	= new ArrayList<Circle>();
		
		/*double topLX = (point.getX() * cellWidth)  - ((rowWidth * cellWidth) / 2);
		double topLY = (point.getY() * cellHeight) - ((columnHeight * cellHeight) / 2);
		
		double topRX = topLX + (rowWidth * cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = topLY + (columnHeight * cellHeight);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		*/
		
		double topLX = point.getX() * cellWidth;
		double topLY = point.getY() * cellHeight;
		
		double topRX = topLX + (rowWidth * cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = topLY + (columnHeight * cellHeight);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		
	
		switch ((int)rotation) {
			case 0:
				lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
				lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
				lineSegments.add(new LineSegment(bottomLX, bottomLY, topRX, topRY));
				
				circles.add(new Circle(topLX, topLY, 0));
				circles.add(new Circle(topRX, topRY, 0));
				circles.add(new Circle(bottomLX, bottomLY, 0));
				
				break;
			case 90:
				lineSegments.add(new LineSegment(topLX, topLY, bottomRX, bottomRY));
				lineSegments.add(new LineSegment(bottomRX, bottomRY, topRX, topRY));
				lineSegments.add(new LineSegment(topRX, topRY, topLX, topLY));
				
				circles.add(new Circle(topLX, topLY, 0));
				circles.add(new Circle(bottomRX, bottomRY, 0));
				circles.add(new Circle(topRX, topRY, 0));
				break;
			case 180:
				lineSegments.add(new LineSegment(bottomLX, bottomLY, topRX, topRY));
				lineSegments.add(new LineSegment(topRX, topRY,bottomRX, bottomRY));
				lineSegments.add(new LineSegment(bottomRX, bottomRY, bottomLX, bottomLY));
				
				circles.add(new Circle(topRX, topRY, 0));
				circles.add(new Circle(bottomLX, bottomLY, 0));
				circles.add(new Circle(bottomRX, bottomRY, 0));
				break;
			case 270:
				lineSegments.add(new LineSegment(topLX, topLY, bottomRX, bottomRY));
				lineSegments.add(new LineSegment(topLX, topLY,bottomLX, bottomLY));
				lineSegments.add(new LineSegment(bottomRX, bottomRY, bottomLX, bottomLY));
				    
				circles.add(new Circle(topLX, topLY, 0));
				circles.add(new Circle(bottomLX, bottomLY, 0));
				circles.add(new Circle(bottomRX, bottomRY, 0));
				break;
			default:
				break;
		}
		
	}

	@Override
	public String toString() {
		return ("Triangle " + identifier + " " + point.getX() + " " + point.getY());
	}

}
