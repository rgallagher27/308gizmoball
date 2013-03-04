package view;

import java.awt.Shape;
import java.util.Collection;

public interface ICanvas {
	
	/**
	 * Replaces the current collection of shapes to be drawn
	 * each time the paint method is called.  Calls the repaint
	 * method when this is done.
	 * @param shapes
	 */
	void replaceShapes(Collection<Shape> shapes);
	
	/**
	 * The repaint method from java.awt.Canvas. 
	 */
	void repaint();

}
