package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;

import model.Flipper;
import model.GameGrid;
import model.LeftFlipper;
import model.RightFlipper;
import model.iGizmo;
import view.framework.G2DAbstractCanvas;

public class AnimationEventListener implements KeyListener, ActionListener, MouseListener {
	
	private List<iGizmo> gizmos;
	private G2DAbstractCanvas absCanvas;
	private GameGrid gmGrid;
	
	private boolean _Space;
	private boolean _Left;
	private boolean _Right;

	public AnimationEventListener(List<iGizmo> gizmos, G2DAbstractCanvas absCanvas, GameGrid gmGrid) 
	{
		super();
		this.gizmos = gizmos;
		this.absCanvas = absCanvas;
		this.gmGrid = gmGrid;
		this._Space = this._Left = this._Right = false;
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
	public void keyPressed(KeyEvent arg0) 
	{	
		if(KeyEvent.VK_ESCAPE == arg0.getKeyCode()) System.exit(0);
		for(iGizmo g : gizmos){
			switch (arg0.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(g instanceof LeftFlipper)
						((LeftFlipper) g).toggleFlipper(true);
					this._Left = true;
					break;
				case KeyEvent.VK_RIGHT:
					if(g instanceof RightFlipper)
						((RightFlipper) g).toggleFlipper(true);
					this._Right = true;
					break;
				case KeyEvent.VK_SPACE:
					if(g instanceof Flipper)
						((Flipper) g).toggleFlipper(true);
					this._Space = true;
					break;
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
	public void keyReleased(KeyEvent arg0) 
	{
		for(iGizmo g : gizmos){
			switch (arg0.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(g instanceof LeftFlipper)
						if(!this._Space)((LeftFlipper) g).toggleFlipper(false);
					this._Left = false;
					break;
				case KeyEvent.VK_RIGHT:
					if(g instanceof RightFlipper)
						if(!this._Space)((RightFlipper) g).toggleFlipper(false);
					this._Right = false;
					break;
				case KeyEvent.VK_SPACE:
					if(g instanceof Flipper)
						if(!this._Left || !this._Right)((Flipper) g).toggleFlipper(false);
					this._Space = false;
					break;
				default:
					break;
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
		for(iGizmo g : this.gizmos)
			g.move();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		JPanel source = (JPanel)event.getSource();
		
		this.absCanvas.setPhysicalDisplay(source.getWidth(), source.getHeight(), null);
		
		System.out.println( this.absCanvas.abstractX( event.getX() ) );
		
		System.out.println(this.absCanvas.getScaleX());
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
