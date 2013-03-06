package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Timer;

import model.Absorber;
import model.iBall;
import model.iGizmo;
import model.iOverlord;

public class AnimationEventListener implements IController {
	
	private final int FPS = 30;
	private final double DELTA_T = ((double)1) / FPS;

	private iOverlord overlord;
	
	private Timer gameLoop;
			

	public AnimationEventListener(iOverlord ov) 
	{
		super();
		this.overlord 	= ov;
		
		this.gameLoop 	= new Timer(1000/FPS, this);
		
		this.gameLoop.start();
	}

	public int getGizX(String name){
		return overlord.getGizmo(name).getLocation().getX();
	}

	public int getGizY(String name){
		return overlord.getGizmo(name).getLocation().getY();
	}
	
	public int getGizWidth(String name){
		return overlord.getGizmo(name).getWidth();
	}
	
	public int getGizHeight(String name){
		return overlord.getGizmo(name).getHeight();
	}
	
	public float getBallX(String name){
		return overlord.getBall(name).getLocation().getX();
	}

	public float getBallY(String name){
		return overlord.getBall(name).getLocation().getY();
	}
	
	public double getBallRadius(String name){
		return overlord.getBall(name).getRadius();
	}
	
	public List<String> getGizmos(){
		LinkedList<String> list = new LinkedList<String>();
		for(iGizmo giz : overlord.getGizmos()){
			list.add(giz.getIdentifier());
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
		if(KeyEvent.VK_ENTER == event.getKeyCode()) System.exit(0);
		Iterator<Entry<Integer, ArrayList<iGizmo>>> it = overlord.getGizmoDownKeytriggers().entrySet().iterator();
		
	    while (it.hasNext()) {
	        Map.Entry pairs = it.next();
	        
	        for(iGizmo gizmo : (ArrayList<iGizmo>)pairs.getValue()){
	        	if((int)pairs.getKey() == event.getKeyCode()){
		        	gizmo.performAction(true);
		        }
	        }
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
		Iterator it = overlord.getGizmoDownKeytriggers().entrySet().iterator();
		
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        
	        for(iGizmo gizmo : (ArrayList<iGizmo>)pairs.getValue()){
	        	if((int)pairs.getKey() == event.getKeyCode()){
		        	gizmo.performAction(false);
		        }
	        }
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
		new Runnable() {
			
			@Override
			public void run() {
				double Current_Delta_T = DELTA_T;
				/*
				 * Move all the Balls while checking for possible
				 * collisions with iGizmo  and iBall objects
				 */
				for(iBall b : overlord.getBalls()){
					if(b.isCaptured())continue;
					for(int i = 0; i < 50; i++){
						Current_Delta_T = collideBalls(b, Current_Delta_T);
						Current_Delta_T = collideGizmos(b, Current_Delta_T);
						b.move((float)Current_Delta_T);
					}
				}
				/*
				 * Move all the static Gizmos
				 */
				overlord.moveAllGizmos();
			}
		}.run();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
			}
			b.move((float)Current_Delta_T);
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
			b.move((float)Current_Delta_T);
			closestBall.collide(b);
			Current_Delta_T -= lowestTime;
			return Current_Delta_T;
		}else{
			return Current_Delta_T;
		}
	}
}
