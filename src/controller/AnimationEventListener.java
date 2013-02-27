package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Absorber;
import model.GameGrid;
import model.LeftFlipper;
import model.RightFlipper;
import model.iBall;
import model.iGizmo;
import model.physics.Vect;
import view.framework.G2DAbstractCanvas;

public class AnimationEventListener implements KeyListener, ActionListener, MouseListener {
	
	private double DELTA_T = ((double)1) / 40;
	
	private List<iGizmo> gizmos;
	private List<iBall> balls;
	private G2DAbstractCanvas absCanvas;
	private GameGrid gmGrid;
	
	private String flipperType = "L";
			

	public AnimationEventListener(List<iGizmo> gizmos, List<iBall> balls, G2DAbstractCanvas absCanvas, GameGrid gmGrid) 
	{
		super();
		this.gizmos = gizmos;
		this.balls = balls;
		this.absCanvas = absCanvas;
		this.gmGrid = gmGrid;
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
		for(iGizmo g : gizmos){
			switch (event.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(g instanceof LeftFlipper)
						((LeftFlipper) g).toggleFlipper(true);
					break;
				case KeyEvent.VK_RIGHT:
					if(g instanceof RightFlipper)
						((RightFlipper) g).toggleFlipper(true);
					break;
				case KeyEvent.VK_SPACE:
					if(g instanceof Absorber){
						System.out.println("Releasing ball");
						((Absorber)g).releaseBall();
					}
				default:
					break;
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
		switch (event.getKeyCode()) {
			case KeyEvent.VK_L:
				this.flipperType = "L";
				break;
			case KeyEvent.VK_R :
				this.flipperType = "R";
				break;
			case KeyEvent.VK_P:
				this.gmGrid.printGrid();
				break;
			default:
				for(iGizmo g : gizmos){
					switch (event.getKeyCode()) {
						case KeyEvent.VK_LEFT:
							if(g instanceof LeftFlipper)
								((LeftFlipper) g).toggleFlipper(false);
							break;
						case KeyEvent.VK_RIGHT:
							if(g instanceof RightFlipper)
								((RightFlipper) g).toggleFlipper(false);
							break;
						default:
							break;
					}
				}
				break;
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
		 * Move all the static Gizmos
		 */
		for(iGizmo g : this.gizmos)
			g.move();
		
		/*
		 * Move all the Balls while checking for possible
		 * collisions with iGizmo objects
		 */
		for(iBall b : this.balls){
			
			for(iGizmo g : this.gizmos){
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
				
				System.out.println(lowestTime);
				
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
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		JPanel source = (JPanel)event.getSource();
		
		this.absCanvas.setPhysicalDisplay(source.getWidth(), source.getHeight(), null);
		
		int mouseX = (int)(this.absCanvas.abstractX(event.getX()) / this.gmGrid.getCellWidth());
		
		int mouseY = (int)(this.absCanvas.abstractY(event.getY()) / this.gmGrid.getCellHeight());
		
		if(this.gmGrid.setGridPoint(new Point(mouseX, mouseY), 2, 2, true)){
			
			iGizmo newFlipper = null;
			
			if(this.flipperType.equals("L")){
				newFlipper = new LeftFlipper("LFnew", new Point(mouseX, mouseY), 1, 2, gmGrid.getCellWidth(), gmGrid.getCellHeight());
				
			}else if(this.flipperType.equals("R")){
				newFlipper = new RightFlipper("RFnew", new Point(mouseX, mouseY), 1, 2, gmGrid.getCellWidth(), gmGrid.getCellHeight());
			}
			
			((Observable)newFlipper).addObserver((Observer) event.getSource());
			this.gizmos.add(newFlipper);
		}else{
			System.out.println("Cannot place Gizmo at position " + mouseX + " : " + mouseY);
		}
		
		
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
