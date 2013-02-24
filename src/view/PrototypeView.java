package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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

import view.framework.G2DCircle;
import view.framework.G2DRectangle;
import view.framework.G2DTriangle;

import model.FileParser;

public class PrototypeView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private final int  timerInterval = 10; //For 24 FPS Aprox

	private final Dimension windowSize = new Dimension(1200, 800);
	private final Dimension canvasSize = new Dimension(1000, 1000);
	private final GameGrid gameGrid	   = new GameGrid(20, 20, this.canvasSize);
	
	private AnimationEventListener eventListener;
	private Timer timer;
	
	private G2DAbstractCanvas abstractCanvas;
	private List<iGizmo> prototypeFlippers;
    
    private List<G2DObject> objects;
    private List<String> names;
    private List<String> fNames;

	public PrototypeView() {
		super();
		this.setPreferredSize(this.windowSize);
		
		this.abstractCanvas 	= new G2DAbstractCanvas(canvasSize.getWidth(), canvasSize.getHeight());
		this.prototypeFlippers 	= new ArrayList<iGizmo>();
		this.eventListener 		= new AnimationEventListener(prototypeFlippers);
		this.timer 				= new Timer(this.timerInterval, this.eventListener);
        
        this.objects = new ArrayList<G2DObject>();
        this.names = new ArrayList<String>();
        this.fNames = new ArrayList<String>();
		
		/*
		 * Add prototype Left and Right flippers to test against.
		 * 
		 */
        
        FileParser fp = new FileParser(this);
        fp.loadFile("Input");
        
		/*
		 * Add 'this' as an Observer to each test flipper.
		 */
		for(iGizmo g : prototypeFlippers){
			((Observable) g).addObserver(this);
			/*
			 * Set game grid location here just to be complete, would normally 
			 * be set when attempting to add new gizmo through UI
			 * once sanity checks have been applied.
			 */
			this.gameGrid.setGridPoint(g.getLocation(), true);
		}
		
		/*
		 * Add event listener to key presses.
		 * 
		 * Start the timer for Action performed.
		 * 
		 * Request window focus.
		 */
		this.addKeyListener(eventListener);
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
		
        for(G2DObject o : this.objects)
            o.draw(this.abstractCanvas);
            
		g.drawImage(bufferImage, 0, 0, null);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		this.repaint();
	}
	
	private G2DObject drawFlipper(iGizmo flipper)
	{	
		
		double cellWidth 		= this.gameGrid.getCellWidth();
		double cellheight 		= this.gameGrid.getCellHeight();
		double flipperWidth 	= (flipper.getWidth()      * cellWidth) / 4;
		double flipperHeight 	= (flipper.getHeight()     * cellheight);
		
		double flipperX = 0;
		double flipperY = 0;
		if(flipper instanceof LeftFlipper){
			flipperX 		= ((flipper.getLocation().x * cellWidth)  + (cellWidth  / 2)) - flipperWidth;
			flipperY 		= ((flipper.getLocation().y * cellheight) + (cellheight / 2)) - flipperWidth;
		}else if(flipper instanceof RightFlipper){
			flipperX 		= ((flipper.getLocation().x * cellWidth)  + (cellWidth  / 2)) + flipperWidth;
			flipperY 		= ((flipper.getLocation().y * cellheight) + (cellheight / 2)) - flipperWidth;
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
		
		for(int i = 0; i <= this.gameGrid.getCellHeight(); i++){
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
    
    public void addCircle(String name, int x, int y) {
        double cellWidth 		= this.gameGrid.getCellWidth();
		double cellheight 		= this.gameGrid.getCellHeight();
        this.names.add(name);
        this.objects.add(new G2DCircle(new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), cellWidth/2, Color.green));
    }
    
    public void addSquare(String name, int x, int y) {
        double cellWidth 		= this.gameGrid.getCellWidth();
		double cellheight 		= this.gameGrid.getCellHeight();
        this.names.add(name);
        this.objects.add(new G2DRectangle(x*cellWidth,
                                          y*cellheight,
                                          (x*cellWidth)+(cellWidth),
                                          (y*cellheight)+(cellheight),
                                          Color.green));
    }
    
    public void addFlipper(String name, int x, int y, boolean lr) {
        this.fNames.add(name);
        if(lr) {
            this.prototypeFlippers.add(new RightFlipper ( new Point(x ,y), 1, 2));
        } else {
            this.prototypeFlippers.add(new LeftFlipper ( new Point(x ,y), 1, 2));
        }

    }
    
    public void addTriangle(String name, int x, int y) {
        double cellWidth 		= this.gameGrid.getCellWidth();
		double cellheight 		= this.gameGrid.getCellHeight();
        this.names.add(name);
        this.objects.add(new G2DTriangle((int)(x*cellWidth),
                                         (int)(y*cellheight),
                                         (int)cellWidth,
                                         (int)cellheight,
                                         Color.green));
    }
    
    public void rotate(String name) {
        int i = 0;
        int index = 0;
        boolean found = false;
        for(String n : names) {
            if(n.equals(name)) {
                found = true;
                index = i;
            }
            i++;
        }
        if(found) {
            rotateObjectAroundSelf((double)90, objects.get(index), (double)objects.get(index).getX(), (double)objects.get(index).getY());
        } else {
            //No name
        }
    }
}
