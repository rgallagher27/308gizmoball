package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.physics.Vect;

public class Overlord extends Observable implements iOverlord {
	
	private Dimension gridDimentions, canvasDimentions;
	
	private List<iGizmo> gizmoObjects;
	private List<iBall> ballObjects;
	private Map<Integer, ArrayList<iGizmo>> gizmoDownTriggers;
	private Map<Integer, ArrayList<iGizmo>> gizmoUpTriggers;
	private FileParser fileParser;
	
	private double cellWidth, cellHeight;

	public Overlord(Dimension gridDimentions, Dimension canvasDimentions) {
		
		this.gridDimentions	 	= gridDimentions;
		this.canvasDimentions	= canvasDimentions;
		this.cellWidth			= this.canvasDimentions.getWidth()  / this.gridDimentions.getWidth();
		this.cellHeight			= this.canvasDimentions.getHeight() / this.gridDimentions.getHeight();
		
		this.gizmoObjects 		= new ArrayList<iGizmo>();
		this.ballObjects		= new ArrayList<iBall>();
		this.gizmoDownTriggers	= new HashMap<Integer, ArrayList<iGizmo>>();
		this.gizmoUpTriggers	= new HashMap<Integer, ArrayList<iGizmo>>();
		this.fileParser			= new FileParser(this);
		
		this.gizmoObjects.add(new Wall(cellWidth, cellHeight));
		
		this.fileParser.loadFile("Input");

	}

	@Override
	public List<iGizmo> getAllGizmos() {
		return this.gizmoObjects;
	}

	@Override
	public List<iBall> getAllballs() {
		return this.ballObjects;
	}

	@Override
	public iGizmo getGizmo(String identifier) {
		for(iGizmo g : this.gizmoObjects)
			if(g.getIdentifier().equals(identifier))return g;
		
		return null;
	}

	@Override
	public iBall getBall(String identifier) {
		for(iBall b : this.ballObjects)
			if(b.getIdentifier().equals(identifier))return b;
		
		return null;
	}

	@Override
	public void addGizmoObserver(Observer o) {
		for(iGizmo g : this.gizmoObjects) ((Observable)g).addObserver(o);
	}

	@Override
	public void addBallObserver(Observer o) {
		for(iBall b : this.ballObjects) ((Observable)b).addObserver(o);
	}

	public Map<Integer, ArrayList<iGizmo>> getGizmoDownKeytriggers()
	{
		return this.gizmoDownTriggers;
	}

	@Override
	public Map<Integer, ArrayList<iGizmo>> getGizmoUpKeytriggers() {
		return this.gizmoUpTriggers;
	}
	
	@Override
	public void addAbsorber(String id, int x, int y, int width, int height) {
		this.gizmoObjects.add(new Absorber(id, new Point(x, y), width, height, this.cellWidth, this.cellHeight));
	}

	@Override
	public void addBall(String id, double x, double y, double xV, double yV) {
		iBall newBall = new Ball(id, new Point2D.Double( x, y ), 1, 1, this.cellWidth, this.cellHeight);
		newBall.setVelocity(new Vect(xV, yV));
		this.ballObjects.add(newBall);
	}

	@Override
	public void addCircle(String id, int x, int y) {
		this.gizmoObjects.add(new CircleBumper(id, new Point(x, y), 1, 1, this.cellWidth, this.cellHeight));
	}
	
	@Override
	public void addFlipper(String id, String type, int x, int y)
    {
    	if(type.equals("L")){
    		this.gizmoObjects.add(
        			new LeftFlipper(id, new Point(x, y),1, 2, this.cellWidth, this.cellHeight));
    	}else if(type.equals("R")){
    		this.gizmoObjects.add(
        			new RightFlipper(id, new Point(x, y),1, 2, this.cellWidth, this.cellHeight));
    	}
    }
    
	@Override
    public void addSquare(String id, int x, int y)
    {
    	this.gizmoObjects.add( new SquareBumper(id, new Point( x, y ), 1, 1, this.cellWidth, this.cellHeight));
    }
    
	@Override
    public void addTriangel(String id, int x, int y)
    {
    	this.gizmoObjects.add( new TriangleBumper(id, new Point( x, y ), 1, 1, this.cellWidth, this.cellHeight));
    }
	
	@Override
	public void delete(String name) 
	{
    	for(iGizmo g : gizmoObjects){
    		if( g.getIdentifier().equals(name) ) this.gizmoObjects.remove(g);
    	}
    }
    
	@Override
	public void move(String name, double x, double y) 
	{
    	for(iGizmo g : gizmoObjects){
    		if(g.getIdentifier().equals(name))g.setLocation( new Point((int)x, (int)y) );
    	}
    }
    
	@Override
	public void rotate(String name) 
	{
    	for(iGizmo g : gizmoObjects){
    		if(g.getIdentifier().equals(name))g.setRotation(90);
    	}
    }
    
    public void keyConnect(int keyCode, String type, String consumer )
    {
    	switch (type) {
		case "down":
			if(this.gizmoDownTriggers.containsKey(keyCode)){
				this.gizmoDownTriggers.get(keyCode).add(this.getGizmo(consumer));
			}else {
				ArrayList<iGizmo> newList  = new ArrayList<iGizmo>();
								  newList.add(this.getGizmo(consumer));
				this.gizmoDownTriggers.put(keyCode, newList);
			}
			break;

		case "up":
			if(this.gizmoUpTriggers.containsKey(keyCode)){
				this.gizmoUpTriggers.get(keyCode).add(this.getGizmo(consumer));
			}else {
				ArrayList<iGizmo> newList  = new ArrayList<iGizmo>();
								  newList.add(this.getGizmo(consumer));
				this.gizmoUpTriggers.put(keyCode, newList);
			}
			break;
		default:
			break;
		}
    }

	@Override
	public double collideGizmos(iBall b, double Current_Delta_T) {
		double lowestTime = Double.POSITIVE_INFINITY;
		iGizmo closestGizmo = null;
		
		for(iGizmo g : this.gizmoObjects){
			double time = g.timeUntilCollision(b);
			/*
			 * Only deal with the closest object
			 */
			if(time < lowestTime){
				lowestTime = time;
				closestGizmo = g;
			}
		}
		
		
		if(lowestTime < Current_Delta_T  && closestGizmo != null && !b.isCaptured()){
			
			if(!(closestGizmo instanceof Absorber))closestGizmo.collide(b);
			else{
				((Absorber)closestGizmo).captureBall(b);
				b.setCaptured(true);
			}
			Current_Delta_T -= lowestTime;
			return Current_Delta_T;
			
		}else{
			return Current_Delta_T;
		}
	}

	@Override
	public double collideBalls(iBall b, double Current_Delta_T) {
		double lowestTime = Double.POSITIVE_INFINITY;
		iBall closestBall = null;
		
		for(iBall b2 : this.ballObjects){
			double time = b2.timeUntilCollision(b);
			/*
			 * Only deal with the closest object
			 */
			if(time < lowestTime){
				lowestTime = time;
				closestBall = b2;
			}
		}
		
		if(lowestTime < Current_Delta_T  && closestBall != null && !b.isCaptured()){
			closestBall.collide(b);
			Current_Delta_T -= lowestTime;
			return Current_Delta_T;
		}else{
			return Current_Delta_T;
		}
	}
}
