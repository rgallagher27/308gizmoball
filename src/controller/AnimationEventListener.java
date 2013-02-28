package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Timer;

import model.Absorber;
import model.iBall;
import model.iGizmo;
import model.iOverlord;
import model.physics.Vect;

public class AnimationEventListener implements KeyListener, ActionListener, MouseListener {
	
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
		 Iterator it = this.overlord.getGizmoDownKeytriggers().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        
		        if((int)pairs.getKey() == event.getKeyCode()){
		        	((iGizmo)pairs.getValue()).performAction(true);
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
		Iterator it = this.overlord.getGizmoUpKeytriggers().entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        
	        if((int)pairs.getKey() == event.getKeyCode()){
	        	((iGizmo)pairs.getValue()).performAction(false);
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
		double lowestTime = Double.POSITIVE_INFINITY;
		iGizmo closestGizmo = null;
		
		/*
		 * Move all the Balls while checking for possible
		 * collisions with iGizmo objects
		 */
		for(iBall b : this.overlord.getAllballs()){
			
			for(iGizmo g : this.overlord.getAllGizmos()){
				double time = g.timeUntilCollision(b);
				/*
				 * Only deal with the closest object
				 */
				if(time < lowestTime){
					lowestTime = time;
					closestGizmo = g;
				}
			}
			
			if(lowestTime < DELTA_T  && closestGizmo != null && !b.isCaptured()){
				Point2D.Double tmpBall = b.getLocation();
				
				Vect tmpVect = b.getVelocity();
				
				tmpBall.x = tmpBall.x + (lowestTime * tmpVect.x());
				tmpBall.y = tmpBall.y + (lowestTime * tmpVect.y());
				
				b.setLocation(tmpBall);
				b.move(lowestTime);
				
				if(!(closestGizmo instanceof Absorber))closestGizmo.collide(b);
				else{
					((Absorber)closestGizmo).captureBall(b);
					b.setCaptured(true);
				}
				
			}else{
				b.move(DELTA_T);
			}
		}
		
		/*
		 * Move all the static Gizmos
		 */
		for(iGizmo g : this.overlord.getAllGizmos())
			g.move();
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
}
