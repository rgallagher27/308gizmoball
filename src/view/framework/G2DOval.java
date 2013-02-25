package view.framework;
import java.awt.Color;

/*
 * A point object implementing G2DObject
 * This isn't much use for drawing on screen but is used extensively to define other objects
 */
public class G2DOval implements G2DObject {

	private Matrix ptMatrix;
	private Color color = Color.BLACK;
	private int width;
	private int height;
	
	// -- Constructors
	
	public G2DOval(double x, double y, int width, int height){
		ptMatrix = new Matrix(3,1);//create homogeneous 2D coordinate vector - a 3row 1col matrix
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
		ptMatrix.set(2, 0, 1);
	}
	
	public G2DOval(double x, double y, int width, int height, Color color){
		this(x, y, width, height);
		this.setColor(color);
	}
	
	// -- other methods
	
	public void setColor(Color color){
		this.color = color;
	}
	
	@Override
	public void draw(G2DAbstractCanvas absCanvas) {
		absCanvas.getPhysicalGraphics().setColor(color);
		int px = absCanvas.physicalX(getX());
		int py = absCanvas.physicalY(getY());
		absCanvas.getPhysicalGraphics().fillOval(px, py, (int) (width * absCanvas.getScaleX()), (int) (height * absCanvas.getScaleY()));
	}

	@Override
	public G2DPoint deepClone() {
		return new G2DPoint(getX(), getY(), new Color(color.getRGB()));
	}

	public int getX() {
		return (int)ptMatrix.get(0, 0);
	}

	void setX(double x) {
		ptMatrix.set(0, 0, x);
	}

	public int getY() {
		return (int)ptMatrix.get(1, 0);
	}

	void setY(double y) {
		ptMatrix.set(1, 0, y);
	}

	@Override
	public void transform(Matrix transformationMatrix) {
		ptMatrix = transformationMatrix.multiply(ptMatrix);
	}


}
