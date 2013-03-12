package view;

import java.awt.Polygon;
import java.util.Collection;

public interface ICanvas {
	
	/**
	 * Replaces the current collection of shapes to be drawn
	 * each time the paint method is called.  Calls the repaint
	 * method when this is done.
	 * @param shapes
	 */
	void replaceShapes(Collection<Polygon> polygons);
	
	/**
	 * Used to add a new gizmo with the top-left corner
	 * at the given x and y coordinates.
	 * @param x
	 * @param y
	 */
	void addShape(int x, int y);
	
	/**
	 * The repaint method from java.awt.Canvas. 
	 */
	void repaint();

}
