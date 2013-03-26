package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import model.Absorber;
import model.iBall;
import model.iGizmo;
import model.iOverlord;

public class PhysicsController implements IController {
	
	private final int FPS 			= 60;
	private final double DELTA_T 	= ((double)1) / FPS;
	
	private iOverlord overlord;
	private Timer gameLoop;

	public PhysicsController(iOverlord ov) 
	{
		super();
		
		this.overlord 	= ov;
		this.gameLoop 	= new Timer(1000/FPS, this);
	}

	public void start(){
		gameLoop.start();
	}
	
	public void stop(){
		gameLoop.stop();
		overlord.resetGame();
	}
	
	
	public List<String> getGizmos(){
		LinkedList<String> list = new LinkedList<String>();
		for(iGizmo giz : overlord.getGizmos()){
			list.add(giz.getIdentifier());
		}
		return list;
	}
	
	public List<String> getBalls(){
		LinkedList<String> list = new LinkedList<String>();
		for(iBall ball : overlord.getBalls()){
			list.add(ball.getIdentifier());
		}
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * 
	 * Loop through all the gizmos stored and toggle it on depending on the 
	 * key event.
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent event) 
	{	
		
		int keyPressed = event.getKeyCode();
		
		for(iGizmo giz : overlord.getGizmoDownKeytriggers(keyPressed)){
			giz.performAction(true);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 * 
	 * Loop through all the gizmos stored and toggle it off depending on the 
	 * key event.
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent event) 
	{
		
		int keyPressed = event.getKeyCode();
		
		for(iGizmo giz : overlord.getGizmoUpKeytriggers(keyPressed)){
			giz.performAction(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * Call each iGizmo objects move() method every X number of ms defined when Timer object
	 * was initialised.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		/*
		 * Move all the static Gizmos
		 */
		overlord.moveAllGizmos(DELTA_T);
		
		double Current_Delta_T = DELTA_T;
		/*
		 * Move all the Balls while checking for possible
		 * collisions with iGizmo  and iBall objects
		 */
		for(int i = 0; i < 150; i++){
		for(iBall b : overlord.getBalls()){
			if(b.isCaptured())continue;
				Current_Delta_T = collideBalls(b, Current_Delta_T);
				Current_Delta_T = collideGizmos(b, Current_Delta_T);
				b.move(Current_Delta_T);
			}
		}
	}

	
	private double collideGizmos(iBall b, double Current_Delta_T) {
		double lowestTime = Double.POSITIVE_INFINITY;
		iGizmo closestGizmo = null;
		
		for(iGizmo g : overlord.getGizmos()){
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
				closestGizmo.collide(b);
			}
			b.move(Current_Delta_T);
			Current_Delta_T -= lowestTime;
			return Current_Delta_T;
		}else{
			return Current_Delta_T;
		}
	}

	private double collideBalls(iBall b, double Current_Delta_T) {
		double lowestTime = Double.POSITIVE_INFINITY;
		iBall closestBall = null;
		
		for(iBall b2 : overlord.getBalls()){
			if(b2.isCaptured())continue;
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
			b.move(Current_Delta_T);
			closestBall.collide(b);
			Current_Delta_T -= lowestTime;
			return Current_Delta_T;
		}else{
			return Current_Delta_T;
		}
	}

	@Override
	public void pause() {
		if(gameLoop.isRunning()){
			gameLoop.stop();
		}else{
			gameLoop.start();
		}
	}

}
