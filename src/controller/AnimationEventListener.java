package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.Flipper;
import model.LeftFlipper;
import model.RightFlipper;
import model.iGizmo;

public class AnimationEventListener implements KeyListener, ActionListener {
	
	private List<iGizmo> gizmos;

	public AnimationEventListener(List<iGizmo> gizmos) {
		super();
		this.gizmos = gizmos;
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
	public void keyPressed(KeyEvent arg0) {
		if(KeyEvent.VK_ESCAPE == arg0.getKeyCode()) System.exit(0);
		for(iGizmo g : gizmos){
			switch (arg0.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(g instanceof LeftFlipper)
						((LeftFlipper) g).toggleFlipper(true);
					break;
				case KeyEvent.VK_RIGHT:
					if(g instanceof RightFlipper)
						((RightFlipper) g).toggleFlipper(true);
					break;
				case KeyEvent.VK_SPACE:
					if(g instanceof Flipper)
						((Flipper) g).toggleFlipper(true);
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
	public void keyReleased(KeyEvent arg0) {
		for(iGizmo g : gizmos){
			switch (arg0.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(g instanceof LeftFlipper)
						((LeftFlipper) g).toggleFlipper(false);
					break;
				case KeyEvent.VK_RIGHT:
					if(g instanceof RightFlipper)
						((RightFlipper) g).toggleFlipper(false);
					break;
				case KeyEvent.VK_SPACE:
					if(g instanceof Flipper)
						((Flipper) g).toggleFlipper(false);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * Call each iGizmo objects move() method every X number of ms defined when Timer object
	 * was initalised.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for(iGizmo g : this.gizmos)
			g.move();
	}
}
