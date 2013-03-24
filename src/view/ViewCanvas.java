package view;

import java.awt.Color;
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
import view.framework.*;
import controller.GraphicsController;
import controller.PhysicsController;
import controller.IController;

public class ViewCanvas extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private final Dimension windowSize 		= new Dimension(1000, 800);
	private final Dimension canvasSize 		= new Dimension(1000, 1000);
	private final Dimension gridSize   		= new Dimension(20, 20);
	
	private IController eventListener;
	private GraphicsController graphics;
	
	private G2DAbstractCanvas abstractCanvas;

	public ViewCanvas() {
		super();
		setPreferredSize(this.windowSize);
		abstractCanvas 	= new G2DAbstractCanvas(canvasSize.getWidth(), canvasSize.getHeight());
		/*
		 * Add event listener to key presses.
		 * 
		 * Start the timer for Action performed.
		 * 
		 * Request window focus.
		 */
		requestFocus();
		setVisible(true);
	}
	
	public void addController(IController ic, GraphicsController gc){
		eventListener = ic;
		graphics = gc;
		addKeyListener(eventListener);
		addMouseListener(eventListener);
		
	}
	
	public Dimension getGridSize(){
		return gridSize;
	}
	
	public Dimension getCanvasSize(){
		return canvasSize;
	}
	
	private Image bufferImage;

	private Object G2DObject;
	
	@Override 
	public void paint(Graphics g)
	{
		super.paint(g);
		
		bufferImage = createImage(getWidth(), getHeight());
		
		Graphics buffer = bufferImage.getGraphics();
		abstractCanvas.setPhysicalDisplay(getWidth(), getHeight(), buffer);
		
		buffer.clearRect(0, 0, getWidth(), getHeight());
		buffer.setColor(Color.BLACK);
		buffer.fillRect(0, 0, getWidth(), getHeight());
		
		graphics.factoryDraw(abstractCanvas, 20, 20, (double)50, (double)50);
		
		for(String gizmo : eventListener.getGizmos()){
			if(graphics.getGraphicsGizmo(gizmo) != null){
				graphics.getGraphicsGizmo(gizmo).draw(abstractCanvas);
			}
		}
		for(String ball : eventListener.getBalls()){
			graphics.getGraphicsBall(ball).draw(abstractCanvas);
		
		}
		
            
		g.drawImage(bufferImage, 0, 0, null);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		repaint();
	}
    
    
	
	// This is just here so that we can accept the keyboard focus
	public boolean isFocusable() { return true; }
}
