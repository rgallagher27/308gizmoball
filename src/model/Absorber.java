package model;

<<<<<<< HEAD
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import model.physics.Circle;
import model.physics.LineSegment;
import model.physics.Vect;

public class Absorber extends Gizmo implements iGizmo {

	protected List<iBall> capturedBalls;
	protected boolean active;
	
	public Absorber( String identifier, GizPoint p, double row, double column, double width, double height) {
		point 		= p;
		rowWidth 		= row;
		columnHeight	= column;
		this.identifier 	= identifier;
		cellWidth		= width;
		cellHeight	= height;
		active 		= false;
		
		lineSegments 	= new ArrayList<LineSegment>();
		circles		= new ArrayList<Circle>();
		capturedBalls 	= new ArrayList<iBall>();
		this.width = (int) width;
		this.height = (int) height;
		fillLineSegments();
=======
import java.util.ArrayList;
import java.util.List;

import exception.CannotRotateException;

import physics.LineSegment;

public class Absorber implements iGizmo {
	
	private List<iBall> storedBalls = null;
	private List<iGizmo> triggers = null;
	private String id;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int width;
	private int height;
	private List<LineSegment> boundSegments;


	public Absorber(String gizmoName, int x1, int y1, int x2, int y2) {
		id = gizmoName;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		height = Math.abs(y1 - y2);
		width = Math.abs(x1 - x2);
		calcBoundSegments();
	}

	private void calcBoundSegments(){
		boundSegments = new ArrayList<LineSegment>();
		boundSegments.add(new LineSegment(x1,y1,x2, y1));
		boundSegments.add(new LineSegment(x1, y1, x1, y2));
		boundSegments.add(new LineSegment(x2, y1, x2, y2));
		boundSegments.add(new LineSegment(x1, y2, x2, y2));
	}
	
	
	@Override
	public void doAction() {
		if(storedBalls.size() > 0){
		storedBalls.remove(0).setReleased(true);
		}
	}

	@Override
	public void setLocation(GizPoint p) {
		x1 = p.getX();
		y1 = p.getY();
>>>>>>> e94ed9e46ae68874a7f839588322fd27d7bc2e88
	}
	
	@Override
<<<<<<< HEAD
	public void performAction(boolean a)
	{
		active = a;
	}

	@Override
	public void move() {
		if(active){
			if((!capturedBalls.isEmpty())){
				iBall b = capturedBalls.get(0);
					  b.setVelocity(new Vect(0, -2));
					  b.setLocation(new BallPoint(19, 18));
					  b.setCaptured(false);
				capturedBalls.remove(0);
			}
		}
		active = false;
	}

	private void fillLineSegments()
	{
		/*double topLX = (point.getX() * cellWidth) - (cellWidth / 2);
		double topLY = (point.getY() * cellHeight) - (cellHeight / 2);
		
		double topRX = (point.getX() * cellWidth) + (rowWidth * cellWidth)- (cellWidth / 2);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (point.getY() * cellHeight) + (columnHeight * cellHeight) - (cellHeight / 2);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		*/
		
		double topLX = point.getX() * cellWidth;
		double topLY = point.getY() * cellHeight;
		
		double topRX = (point.getX() * cellWidth) + (rowWidth * cellWidth);
		double topRY = topLY;
		
		double bottomLX = topLX;
		double bottomLY = (point.getY() * cellHeight) + (columnHeight * cellHeight);
		
		double bottomRX = topRX;
		double bottomRY = bottomLY;
		
		lineSegments.add(new LineSegment(topLX, topLY, topRX, topRY));
		
		lineSegments.add(new LineSegment(bottomLX, bottomLY, bottomRX, bottomRY));
		
		lineSegments.add(new LineSegment(topLX, topLY, bottomLX, bottomLY));
		
		lineSegments.add(new LineSegment(topRX, topRY, bottomRX, bottomRY));
		
		
	}
	
	public void captureBall(iBall ball)
	{
		capturedBalls.add(ball);
	}

	public void removeStoredBall(String ballName) {
		for(iBall balls : capturedBalls){
			if(balls.getIdentifier().equals(ballName)){
				capturedBalls.remove(balls);
				break;
			}
		}
		
		
	}

=======
	public GizPoint getLocation() {
		return new GizPoint(x1, y1);
	}

	public List<LineSegment> getBoundSegments() {
		// TODO Auto-generated method stub
		return boundSegments;
	}

	@Override
	public void addTrigger(iGizmo t) {
		if(triggers == null) triggers = new ArrayList<iGizmo>();
		triggers.add(t);
		
	}

	@Override
	public void removeTrigger(iGizmo t) {
		if(triggers.size() > 0){
			triggers.remove(t);
		}
		
	}

	@Override
	public List<iGizmo> getTriggers() {
		
		return triggers;
	}

	@Override
	public void rotate() throws CannotRotateException {
		throw new CannotRotateException("Absorbers cannot be rotated");
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	public void addBall(iBall ball){
		if(storedBalls == null) storedBalls = new ArrayList<iBall>();
		storedBalls.add(ball);
	}

	public void removeStoredBall(String ballName) {
		if(storedBalls.size() > 0){
			for(iBall ball : storedBalls){
				if(ball.getName().equals(ballName)){
					storedBalls.remove(ball);
					break;
				}
			}
		}
		
	}

	@Override
	public GizPoint getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

>>>>>>> e94ed9e46ae68874a7f839588322fd27d7bc2e88
}
