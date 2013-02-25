package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Flipper;
import model.GameGrid;
import model.LeftFlipper;
import model.RightFlipper;
import model.iGizmo;
import view.framework.G2DAbstractCanvas;
import view.framework.G2DFlipper;
import view.framework.G2DLine;
import view.framework.G2DObject;
import view.framework.G2DPoint;
import view.framework.Matrix;
import controller.AnimationEventListener;

public class PrototypeView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private final int  timerInterval = 10; //For 24 FPS Aprox
	private final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

	private final Dimension windowSize = new Dimension(640, 420);
	private final Dimension canvasSize = new Dimension(1000, 1000);
	private final GameGrid gameGrid	   = new GameGrid(20, 20, this.canvasSize);
	
	private AnimationEventListener eventListener;
	private Timer timer;
	
	private G2DAbstractCanvas abstractCanvas;
	private List<iGizmo> prototypeFlippers;

	public PrototypeView() {
		super();
		this.setPreferredSize(this.windowSize);
		this.setCursor(this.cursor);
		
		this.abstractCanvas 	= new G2DAbstractCanvas(this.canvasSize.getWidth(), this.canvasSize.getHeight());
		this.prototypeFlippers 	= new ArrayList<iGizmo>();
		this.eventListener 		= new AnimationEventListener(this.prototypeFlippers, this.abstractCanvas, this.gameGrid);
		this.timer 				= new Timer(this.timerInterval, this.eventListener);
		
		/*
		 * Add event listener to key presses.
		 * 
		 * Start the timer for Action performed.
		 * 
		 * Request window focus.
		 */
		this.addKeyListener(this.eventListener);
		this.addMouseListener(this.eventListener);
		this.timer.start();
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
		
		this.drawGrid(this.abstractCanvas);
		
		for(iGizmo gizmo : this.prototypeFlippers)
			if(gizmo instanceof Flipper) this.drawFlipper(gizmo).draw(this.abstractCanvas);
		
		g.drawImage(bufferImage, 0, 0, null);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		this.repaint();
	}
	
	private G2DObject drawFlipper(iGizmo flipper)
	{	
		double flipperX = 0;
		double flipperY = 0;
		double cellWidth 			= this.gameGrid.getCellWidth();
		double cellheight 			= this.gameGrid.getCellHeight();
		double flipperGridX			= flipper.getLocation().x;
		double flipperGridY        	= flipper.getLocation().y;
		double flipperGridWidth 	= flipper.getWidth();
		double flipperGridHeight	= flipper.getHeight();
		double flipperWidth 		= (flipperGridWidth  * cellWidth) / 4;
		double flipperHeight 		= (flipperGridHeight * cellheight);
		
		if(flipper instanceof LeftFlipper){
			flipperX = ((flipperGridX * cellWidth)  + (cellWidth  / 2)) - flipperWidth;
			flipperY = ((flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}else if(flipper instanceof RightFlipper){
			flipperX = (((flipperGridX + (flipperGridWidth / 2)) * cellWidth) + (cellWidth)) + flipperWidth;
			flipperY = (( flipperGridY * cellheight) + (cellheight / 2)) - flipperWidth;
		}
		G2DObject flipperGroup = new G2DFlipper(flipperX, flipperY, (int)flipperWidth, (int)flipperHeight);
					
		this.rotateObjectAroundSelf(flipper.getRotation(), 
									flipperGroup, 
									flipperX, 
									flipperY);
		return flipperGroup;
	}
	
	private void drawGrid(G2DAbstractCanvas canvas)
	{
		for(int i = 0; i <= this.gameGrid.getWidth(); i++){
			int startPointX  = (int) (i * this.gameGrid.getCellWidth());
			int startPointY  = 0;
			int endPointX 	 = startPointX;
			int endPointY    = (int) (startPointY + (this.gameGrid.getHeight() * this.gameGrid.getCellHeight()));
			
			G2DLine line = new G2DLine(new G2DPoint(startPointX, startPointY), new G2DPoint(endPointX, endPointY), Color.gray);
			line.draw(canvas);
		}
		
		for(int i = 0; i <= this.gameGrid.getHeight(); i++){
			int startPointX  = 0;
			int startPointY  = (int) (i * this.gameGrid.getCellHeight());
			int endPointX 	 = (int) (startPointX + (this.gameGrid.getWidth() * this.gameGrid.getCellWidth()));
			int endPointY    = startPointY;
			
			G2DLine line = new G2DLine(new G2DPoint(startPointX, startPointY), new G2DPoint(endPointX, endPointY), Color.gray);
			line.draw(canvas);
		}
	}

	/*
	 * A private method to perform the rotation matrix calculations.
	 * 
	 * Steps needed to rotate:
	 * 		*Move the objects center to the origin (0,0).
	 * 		*Rotate the Object.
	 * 		*Move the objects center back to its original position.
	 */
	private void rotateObjectAroundSelf(double rotationDegree, G2DObject object, double X, double Y)
	{			
		double[][] movetoorigin = 	{ {1.0, 0.0, -X} , 
									  {0.0, 1.0, -Y}, 
									  {0.0, 0.0, 1.0} 
									};
		
		double[][] rotate = { {Math.cos(Math.toRadians(rotationDegree)), -Math.sin(Math.toRadians(rotationDegree)), 0.0}, 
							  {Math.sin(Math.toRadians(rotationDegree)), Math.cos(Math.toRadians(rotationDegree)), 0.0}, 
							  {0.0, 0.0, 1.0} 
							};
		
		double[][] movetostart = { {1.0, 0.0, X} , 
								   {0.0, 1.0, Y}, 
								   {0.0, 0.0, 1.0} 
								 };

		object.transform(new Matrix(movetoorigin));
		object.transform(new Matrix(rotate));
		object.transform(new Matrix(movetostart));
	}
	
	// This is just here so that we can accept the keyboard focus
	public boolean isFocusable() { return true; }
}
