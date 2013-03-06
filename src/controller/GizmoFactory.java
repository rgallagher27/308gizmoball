package controller;

import java.awt.Color;

import model.LeftFlipper;
import model.RightFlipper;
import model.iBall;
import model.iGizmo;
import view.framework.G2DAbstractCanvas;
import view.framework.G2DCircle;
import view.framework.G2DFlipper;
import view.framework.G2DLine;
import view.framework.G2DObject;
import view.framework.G2DPoint;
import view.framework.G2DRectangle;
import view.framework.G2DTriangle;
import view.framework.Matrix;

public class GizmoFactory {

	public GizmoFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public G2DObject drawBall(iBall ball)
    {
        double cellWidth 		= ball.getCellWidth();
		double cellheight 		= ball.getCellHeight();

		double x 				= ball.getLocation().getX();
		double y 				= ball.getLocation().getY();
		
    	return new G2DCircle(new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), cellWidth/4, Color.yellow);
    }
	
	public G2DObject drawFlipper(String flipper)
	{	
		
		double flipperX = 0;
		double flipperY = 0;
		double cellWidth 			= flipper.getCellWidth();
		double cellheight 			= flipper.getCellHeight();
		double flipperGridX			= flipper.getLocation().getX();
		double flipperGridY        	= flipper.getLocation().getY();
		double flipperGridWidth 	= flipper.getRowWidth();
		double flipperGridHeight	= flipper.getColumnHeight();
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

    public G2DObject drawAbsorber(String absorber)
    {
        double cellWidth 		= absorber.getCellWidth();
		double cellheight 		= absorber.getCellHeight();
		
		double x 				= absorber.getLocation().getX() * cellWidth;
		double y 				= absorber.getLocation().getY() * cellheight;
    	
    	return new G2DRectangle(new G2DPoint( x , y ), 
    			                new G2DPoint( x + (absorber.getRowWidth() * cellWidth), y + (absorber.getColumnHeight() * cellheight)), 
    			                Color.red);
    }
	
	public G2DObject drawSquareBumper(String bumper)
    {
        double cellWidth 		= bumper.getCellWidth();
		double cellheight 		= bumper.getCellHeight();
		
		double x = bumper.getLocation().getX();
		double y = bumper.getLocation().getY();
    	
    	return new G2DRectangle(x*cellWidth,
				                y*cellheight,
				                (x*cellWidth)+(cellWidth),
				                (y*cellheight)+(cellheight),
				                Color.red);
    }
    
    public G2DObject drawTriangleBumper(String triangle)
    {
        double cellWidth 		= triangle.getCellWidth();
		double cellheight 		= triangle.getCellHeight();
		
		double x 				= triangle.getLocation().getX();
		double y 				= triangle.getLocation().getY();
		
		G2DObject newTriangle = new G2DTriangle((int)(x*cellWidth),
					                (int)(y*cellheight),
					                (int)cellWidth,
					                (int)cellheight,
					                Color.blue);
		
		this.rotateObjectAroundSelf( triangle.getRotation(), newTriangle, 
									 (x * cellWidth) + (cellWidth / 2), 
									 (y * cellheight) + (cellheight / 2)
									);
    	
    	return newTriangle;
    }
	
	public G2DObject drawCircleBumper(String circle)
    {
        double cellWidth 		= circle.getCellWidth();
		double cellheight 		= circle.getCellHeight();
		
		double x 				= circle.getLocation().getX();
		double y 				= circle.getLocation().getY();
		
		return new G2DCircle( new G2DPoint((int)(x*cellWidth)+(cellWidth/2), (int)(y*cellheight)+(cellheight/2)), 
							circle.getRowWidth() * (cellWidth / 2),
							Color.green);
    }
	
	public void drawGrid(G2DAbstractCanvas canvas, int rows, int columns, double rowWidth, double columnHeight)
	{
		for(int i = 0; i <= columns; i++){
			int startPointX  = (int) (i * rowWidth);
			int startPointY  = 0;
			int endPointX 	 = startPointX;
			int endPointY    = (int) (startPointY + (columns * columnHeight));
			
			G2DLine line = new G2DLine(new G2DPoint(startPointX, startPointY), new G2DPoint(endPointX, endPointY), Color.gray);
			line.draw(canvas);
		}
		
		for(int i = 0; i <= rows; i++){
			int startPointX  = 0;
			int startPointY  = (int) (i * columnHeight);
			int endPointX 	 = (int) (startPointX + (rows * rowWidth));
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

}
