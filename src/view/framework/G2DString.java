package view.framework;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

/*
 * A point object implementing G2DObject
 * This isn't much use for drawing on screen but is used extensively to define other objects
 */
public class G2DString implements G2DObject {

	private Matrix ptMatrix;
	private Color color = Color.BLACK;
	private String text;
	private Font font;
	
	// -- Constructors
	
	public G2DString(String text, double x, double y){
		ptMatrix = new Matrix(3,1);//create homogeneous 2D coordinate vector - a 3row 1col matrix
		this.setX(x);
		this.setY(y);
		this.text = text;
		ptMatrix.set(2, 0, 1);
	}
	
	public G2DString(String text, double x, double y, Color color){
		this(text, x, y);
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
		
		font = new Font("Monotype Corsiva", Font.PLAIN, (int) (30 * absCanvas.getScaleX()));

		AttributedString attributedString = new AttributedString(text);
	    attributedString.addAttribute(TextAttribute.FONT, font);
		
		absCanvas.getPhysicalGraphics().drawString(attributedString.getIterator(), px, py);
	}

	@Override
	public G2DPoint deepClone() {
		return new G2DPoint(getX(), getY(), new Color(color.getRGB()));
	}

	double getX() {
		return ptMatrix.get(0, 0);
	}

	void setX(double x) {
		ptMatrix.set(0, 0, x);
	}

	double getY() {
		return ptMatrix.get(1, 0);
	}

	void setY(double y) {
		ptMatrix.set(1, 0, y);
	}

	@Override
	public void transform(Matrix transformationMatrix) {
		ptMatrix = transformationMatrix.multiply(ptMatrix);
	}


}
