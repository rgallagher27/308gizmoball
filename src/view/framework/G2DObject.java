package view.framework;
import java.awt.Color;

/*
 * An interface for a general Graphics 2D Object
 * All G2D objects should implement this so that this set
 * of operations can work on all classes.
 */
public interface G2DObject {

	/*
	 * Draw the current object onto the given abstract canvas
	 */
	public void draw(G2DAbstractCanvas absCanvas);
	
	/*
	 * Clone the current object deeply - not called clone to save hassle from Cloneable
	 */
	public G2DObject deepClone();
	
	public void setColor(Color color);
	
	public void transform(Matrix transformationMatrix);
	
}
