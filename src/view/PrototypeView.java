package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Absorber;
import model.CircleBumper;
import model.Flipper;
import model.Overlord;
import model.SquareBumper;
import model.TriangleBumper;
import model.iBall;
import model.iGizmo;
import model.iOverlord;
import view.framework.G2DAbstractCanvas;
import controller.AnimationEventListener;

public class PrototypeView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private final Dimension windowSize 		= new Dimension(1000, 800);
	private final Dimension canvasSize 		= new Dimension(1000, 1000);
	private final Dimension gridSize   		= new Dimension(20, 20);
	private final GizmoFactory gizmoFactory = new GizmoFactory();
	
	private AnimationEventListener eventListener;
	
	private G2DAbstractCanvas abstractCanvas;
	
	private iOverlord overlord;

	public PrototypeView() {
		super();
		this.setPreferredSize(this.windowSize);
		
		this.overlord			= new Overlord(this.gridSize, this.canvasSize);
		this.abstractCanvas 	= new G2DAbstractCanvas(canvasSize.getWidth(), canvasSize.getHeight());
		this.eventListener 		= new AnimationEventListener(this.overlord);
		
		((Observable)this.overlord).addObserver(this);
		
		/*
		 * Add event listener to key presses.
		 * 
		 * Start the timer for Action performed.
		 * 
		 * Request window focus.
		 */
		this.addKeyListener(eventListener);
		this.addMouseListener(eventListener);
		this.requestFocus();
	}
	
	private Image bufferImage;
	
	@Override 
	public void paint(Graphics g)
	{
		super.paint(g);
		
		this.bufferImage = createImage(getWidth(), getHeight());
		
		Graphics buffer = bufferImage.getGraphics();
		this.abstractCanvas.setPhysicalDisplay(getWidth(), getHeight(), buffer);
		
		buffer.clearRect(0, 0, getWidth(), getHeight());
		
		this.gizmoFactory.drawGrid(this.abstractCanvas, 20, 20, 50, 50);
		
		for(iGizmo gizmo : this.overlord.getAllGizmos())
			if(gizmo instanceof Flipper) this.gizmoFactory.drawFlipper(gizmo).draw(this.abstractCanvas);
			else if(gizmo instanceof SquareBumper)this.gizmoFactory.drawSquareBumper(gizmo).draw(abstractCanvas);
			else if(gizmo instanceof CircleBumper)this.gizmoFactory.drawCircleBumper(gizmo).draw(abstractCanvas);
			else if(gizmo instanceof Absorber)this.gizmoFactory.drawAbsorber(gizmo).draw(abstractCanvas);
			else if(gizmo instanceof TriangleBumper)this.gizmoFactory.drawTriangleBumper(gizmo).draw(abstractCanvas);
		
		for(iBall ball : this.overlord.getAllballs())
			this.gizmoFactory.drawBall(ball).draw(abstractCanvas);
            
		g.drawImage(bufferImage, 0, 0, null);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		this.repaint();
	}
    
    
	
	// This is just here so that we can accept the keyboard focus
	public boolean isFocusable() { return true; }
}
