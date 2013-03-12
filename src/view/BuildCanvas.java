package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class BuildCanvas extends JPanel implements Observer, ICanvas {

	private static final long serialVersionUID = 1L;
	private G2DAbstractCanvas absCanvas = new G2DAbstractCanvas(200,200);
	private Image bufferImage;
	protected String buildAction;
	private Polygon[][] polygons = new Polygon[20][20];

	@Override
	public void paint(Graphics g) {
		if (this.isDisplayable()) {
			// Get the buffer
			bufferImage = createImage(getWidth(), getHeight());
			Graphics2D buffer = (Graphics2D) bufferImage.getGraphics();
			// Initialise absCanvas
			absCanvas.setPhysicalDisplay(getWidth(), getHeight(), buffer);
			// Get changed rectangle
			Rectangle changed;
			if ((changed = g.getClipBounds()) == null) {
				changed = new Rectangle(0, 0, this.getWidth(), this.getHeight());
			}
			// Clear the changed section
			buffer.clearRect(changed.x, changed.y, changed.width, changed.height);
			// Turn antialiasing on
			buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			// draw the grid.
			buffer.setColor(Color.BLACK);
			for (int i = 0; i <= 200; i += 10) {
				buffer.drawLine(absCanvas.physicalX(i), absCanvas.physicalY(0),
						absCanvas.physicalX(i), absCanvas.physicalY(200));
				buffer.drawLine(absCanvas.physicalX(0), absCanvas.physicalY(i),
						absCanvas.physicalX(200), absCanvas.physicalY(i));
			}
			// Only redrawing the changed areas.
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					if (polygons[i][j] != null) {
						if (polygons[i][j].intersects(changed)) {
							switch (polygons[i][j].npoints) {
							case 3:
								buffer.setColor(Color.RED);
								break;
							case 4:
								buffer.setColor(Color.GREEN);
								break;
							case 5:
								buffer.setColor(Color.BLUE);
								break;
							default:
								buffer.setColor(Color.WHITE);
							}
							int[] xs = new int[polygons[i][j].npoints];
							int[] ys = new int[polygons[i][j].npoints];
							for (int k = 0; k < polygons[i][j].npoints; k++) {
								xs[k] = absCanvas.physicalX(polygons[i][j].xpoints[k]);
								ys[k] = absCanvas.physicalY(polygons[i][j].ypoints[k]);
							}
							Polygon newPoly = new Polygon();
							newPoly.xpoints = xs;
							newPoly.ypoints = ys;
							newPoly.npoints = polygons[i][j].npoints;
							buffer.fill(newPoly);
						}
					}
				}
			}
			g.drawImage(bufferImage, 0, 0, null); 
		}

	}
	
	@Override
	public void addShape(int x, int y) {
		int absX = (int) absCanvas.abstractX(x);
		int absY = (int) absCanvas.abstractY(y);
		if ((absX = absX - (absX % 10)) >= 200)
			return;
		if ((absY = absY - (absY % 10)) >= 200)
			return;
		Polygon poly = new Polygon();
		if (buildAction.equals("triangle")
				&& polygons[absX / 10][absY / 10] == null) {
			poly.addPoint(absX, absY);
			poly.addPoint(absX + 10, absY);
			poly.addPoint(absX, absY + 10);
			polygons[absX / 10][absY / 10] = poly;
		}
		if (buildAction.equals("square") && polygons[absX / 10][absY / 10] == null) {
			poly.addPoint(absX, absY);
			poly.addPoint(absX + 10, absY);
			poly.addPoint(absX + 10, absY + 10);
			poly.addPoint(absX, absY + 10);
			polygons[absX / 10][absY / 10] = poly;
		}
		if (buildAction.equals("remove")) {
			polygons[absX / 10][absY / 10] = null;
		}
		repaint();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint((Rectangle) arg1);
	}

	@Override
	/**
	 * Not used here.
	 */
	public void replaceShapes(Collection<Polygon> polygons) {
	}

}
