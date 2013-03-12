package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Adapted from MySecondCanvas used in G2D framework in CS309.
 * @author James Byrne
 *
 */

public class PlayCanvas extends JPanel implements Observer, ICanvas {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<Polygon> polygons;
	private G2DAbstractCanvas absCanvas;
	private Image bufferImage;
	protected String buildAction;
	
	/**
	 * Constructs a new PlayCanvas with the given shapes.
	 * @param shapes
	 */
	public PlayCanvas(Collection<Polygon> shapes){
		super();
		this.polygons = shapes;
		this.absCanvas = new G2DAbstractCanvas(200, 200);
		this.bufferImage = null;
	}
	
	/**
	 * Constructs a new, empty PlayCanvas.
	 */
	public PlayCanvas(){
		this(new ArrayList<Polygon>());
	}
	
	/**
	 * Adds the given polygon to the polygons collection.
	 * @param shape
	 */
	public void addShape(Polygon shape){
		this.polygons.add(shape);
	}

	@Override
	public void paint(Graphics g){
		if(this.isDisplayable()){
			//Get the buffer
			bufferImage = createImage(getWidth(), getHeight() );
			Graphics2D buffer = (Graphics2D) bufferImage.getGraphics();
			//Initialise absCanvas
			absCanvas.setPhysicalDisplay(getWidth(), getHeight(), buffer);
			//Get changed rectangle
			Rectangle changed;
			if((changed = g.getClipBounds()) == null){
				changed = new Rectangle(0, 0, this.getWidth(), this.getHeight());
			}
			//Clear the changed section
			buffer.clearRect(changed.x, changed.y, changed.width, changed.height);
			//Turn antialiasing on
			buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			//Only redraw the changed area.
			for(Polygon poly : polygons){
				if(poly.intersects(changed)){
					switch(poly.npoints){
						case 3: buffer.setColor(Color.RED); break;
						case 4: buffer.setColor(Color.GREEN); break;
						case 5: buffer.setColor(Color.BLUE); break;
						default: buffer.setColor(Color.WHITE);
					}	
					int[] xs = new int[poly.npoints];
					int[] ys = new int[poly.npoints];
					for(int i = 0; i < poly.npoints; i++){
						xs[i] = absCanvas.physicalX(poly.xpoints[i]);
						ys[i] = absCanvas.physicalY(poly.ypoints[i]);
					}
					Polygon newPoly = new Polygon();
					newPoly.xpoints = xs;
					newPoly.ypoints = ys;
					newPoly.npoints = poly.npoints;
					buffer.fill(newPoly);
				}
			}
			g.drawImage(bufferImage, 0, 0, null); 
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		repaint((Rectangle) arg1);
	}

	@Override
	public void replaceShapes(Collection<Polygon> polygons) {
		this.polygons = polygons;
	}

	@Override
	/***
	 * Not used here.
	 */
	public void addShape(int x, int y) {
	}

}